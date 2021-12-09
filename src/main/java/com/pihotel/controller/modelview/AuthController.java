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
import org.springframework.web.bind.annotation.RequestPart;

import com.pihotel.constant.SystemConstant;
import com.pihotel.entity.AccountEntity;
import com.pihotel.service.IAccountServ;

import lombok.extern.slf4j.Slf4j;


@Controller
@Slf4j
public class AuthController {

	@Autowired
	private IAccountServ accountServ;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(HttpServletRequest request,
			Model model) {
		log.info("error: {}", request.getParameter("error"));
		log.info("otp: {}", request.getParameter("otp"));
		String error = request.getParameter("error");
		String otp = request.getParameter("otp");
		if (error != null) {
			request.getSession().setAttribute(SystemConstant.LOG_AUTHENTICATION, "Tên đăng nhập hoặc mật khẩu không đúng!");
		} else if (otp != null) {
			request.getSession().setAttribute(SystemConstant.LOG_AUTHENTICATION, "Tài khoản của bạn chưa xác minh email!");
		}
		
		String referrer = request.getHeader("Referrer");
		if (referrer != null) {
			request.getSession().setAttribute("redirectUrl", referrer);
		}
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
		
		log.info("siteURL: {}", siteURL);
		
		SystemConstant.MAIL_VERIFY_NAME = account.getName();
		
		accountServ.register(account, siteURL);
		return "redirect:/register/success";
	}
	
	@RequestMapping("/register/success")
	public String registerSuccess(Model model) throws InterruptedException {
		model.addAttribute("MAIL_VERIFY_NAME", SystemConstant.MAIL_VERIFY_NAME);
		return "auth/bodys/register_verify";
	}
	
	@RequestMapping(value = "/register/verify", method = RequestMethod.GET)
	public String verifyEmail(@Param("code") String code) throws MessagingException {
		if (accountServ.verify(code)) {
			SystemConstant.MAIL_VERIFY_NAME = null;
			return "auth/bodys/verify_success";
		}
		SystemConstant.MAIL_VERIFY_NAME = null;
		return "auth/bodys/verify_fail";
	}
	
}
