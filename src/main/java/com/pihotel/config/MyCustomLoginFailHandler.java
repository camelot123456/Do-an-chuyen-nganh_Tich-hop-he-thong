package com.pihotel.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.pihotel.entity.AccountEntity;
import com.pihotel.service.IAccountServ;

@Component
public class MyCustomLoginFailHandler extends SimpleUrlAuthenticationFailureHandler{

	@Autowired
	private IAccountServ accountServ;
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		
		String redirectURL = "/login?error&username=" + username;
		
		if (exception.getMessage().contains("OTP")) {
			redirectURL = "/login?otp=true&username=" + username;
		} else {
			AccountEntity account = accountServ.findOneByUsername(username);
			if (account == null) {
				redirectURL = "/login?error=true&username=" + username;
			} else if (!account.getEnabled()) {
				redirectURL = "/login?otp=true&username=" + username;
			}
		}
		super.setDefaultFailureUrl(redirectURL);
        
        super.onAuthenticationFailure(request, response, exception);
	}
	
}
