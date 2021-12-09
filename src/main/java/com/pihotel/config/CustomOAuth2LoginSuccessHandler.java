package com.pihotel.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.pihotel.entity.AccountEntity;
import com.pihotel.entity.enums.EAuthenticationProvider;
import com.pihotel.oauth2.CustomOAuth2User;
import com.pihotel.service.IAccountServ;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CustomOAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler{

	@Autowired
	private IAccountServ accountServ;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// TODO Auto-generated method stub
		CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();
		
		String id = oAuth2User.getId();
		String name = oAuth2User.getName();
		String avatar = oAuth2User.getAvatar();
		String email = oAuth2User.getEmail();
		Boolean gender = oAuth2User.getGender();
		String phone = oAuth2User.getPhone();
		EAuthenticationProvider provider = oAuth2User.getAuthenticationProvider();
		
		log.info("id: {}, name: {}, avatar: {}, email: {}, provider: {}, Phone: {}, gender: {}", id, name, avatar, email, provider, phone, gender);
		
		AccountEntity account = accountServ.findOneById(id);
		
		if (account == null) {
			accountServ.saveOneNewAccountByOAuth2(id, name, email, avatar, provider);
		} else {
			accountServ.updateOneAccountByOAuth2(account, name, email, avatar, provider);
		}
		
		request.getSession().setAttribute("account", accountServ.findOneById(id));
		super.onAuthenticationSuccess(request, response, authentication);
	}
	
}
