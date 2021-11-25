package com.pihotel.controller.modelview;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pihotel.service.IAccountServ;

@Controller
public class AuthController {

	@Autowired
	private IAccountServ accountServ;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(HttpServletRequest request, Model model) {
		String referrer = request.getHeader("Referrer");
		if (referrer != null) {
			request.getSession().setAttribute("redirectUrl", referrer);
		}
		return "/auth/layouts/index";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request) {
		request.getSession().removeAttribute("account");
		return "redirect:/";
	}
	
}
