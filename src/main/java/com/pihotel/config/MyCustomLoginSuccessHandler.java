package com.pihotel.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.pihotel.constant.SystemConstant;
import com.pihotel.repository.IAccountRepo;

public class MyCustomLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	@Autowired
	private IAccountRepo accountRepo;
	
	public MyCustomLoginSuccessHandler(String defaultTargetUrl) {
		// TODO Auto-generated constructor stub
		setDefaultTargetUrl(defaultTargetUrl);
	}
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		
//		all handle in here
		request.getSession().setAttribute("account", accountRepo.findByUsername(authentication.getName()));
		SystemConstant.EMAIL_AUDITING = accountRepo.findByUsername(authentication.getName()).getEmail();
		if (session != null) {
			String redirectUrl = (String) session.getAttribute("redirectUrl");
			if (redirectUrl != null) {
				session.removeAttribute("redirectUrl");
				getRedirectStrategy().sendRedirect(request, response, "redirectUrl");
			} else {
				super.onAuthenticationSuccess(request, response, authentication);
			}
		}
		else super.onAuthenticationSuccess(request, response, authentication);
	}
	
}
