package com.pihotel.controller.modelview;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;

import com.pihotel.constant.SystemConstant;
import com.pihotel.entity.AccountEntity;
import com.pihotel.service.IAccountServ;

@Controller
public class AuthController {

	@Autowired
	private IAccountServ accountServ;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(HttpServletRequest request,
//			@RequestParam("error") String err, 
			Model model) {
//		model.addAttribute("message_err", "");
		String referrer = request.getHeader("Referrer");
		if (referrer != null) {
			request.getSession().setAttribute("redirectUrl", referrer);
		}
//		if (err != null) {
//			model.addAttribute("message_err", "Tên đăng nhập hoặc mật khẩu không đúng!");
//		} else {
//			model.addAttribute("message_err", "");
//		}
		return "auth/bodys/login";
	}
	
	@RequestMapping("/register")
	public String register() {
		return "auth/bodys/register";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String doRegister(@RequestPart("account") AccountEntity account,
			HttpServletRequest request) throws UnsupportedEncodingException, MessagingException {
		String siteURL = request.getRequestURL().toString();
		siteURL = siteURL.replace(request.getServletPath(), "");
		SystemConstant.MAIL_VERIFY_CODE = account.getName();
		accountServ.register(account, siteURL);
		return "redirect:/home";
	}
	
	@RequestMapping("/register/success")
	public String registerSuccess(Model model) {
		model.addAttribute("MAIL_VERIFY_NAME", SystemConstant.MAIL_VERIFY_CODE);
		return "auth/bodys/register_verify";
	}
	
	@RequestMapping(value = "/register/verify", method = RequestMethod.GET)
	public String verifyEmail(@Param("code") String code) throws MessagingException {
		if (accountServ.verify(code)) {
			SystemConstant.MAIL_VERIFY_CODE = null;
			return "auth/bodys/verify_success";
		}
		SystemConstant.MAIL_VERIFY_CODE = null;
		return "auth/bodys/verify_fail";
	}
	
}
