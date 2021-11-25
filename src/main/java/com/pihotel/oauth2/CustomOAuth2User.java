package com.pihotel.oauth2;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.pihotel.entity.enums.EAuthenticationProvider;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomOAuth2User implements OAuth2User{

	private OAuth2User oAuth2User;
	private String clientName;
	
	public CustomOAuth2User(OAuth2User oAuth2User, String clientName) {
		this.oAuth2User = oAuth2User;
		this.clientName = clientName;
		log.info("clientName: {}", clientName);
	}

	@Override
	public Map<String, Object> getAttributes() {
		// TODO Auto-generated method stub
		return oAuth2User.getAttributes();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		Collection<? extends GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_MEMBER");
		return authorities;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return oAuth2User.getAttribute("name");
	}
	
	public String getEmail() {
		return oAuth2User.getAttribute("email");
	}
	
	public String getClientName() {
		return this.clientName;
	}
	
	public String getAvatar() {
		if (clientName == "Google") {
			return oAuth2User.getAttribute("picture");
		}
		return null;
	}
	
	public EAuthenticationProvider getAuthenticationProvider() {
		if (clientName == "Google") {
			return EAuthenticationProvider.GOOGLE;
		}
		return null;
	}

	public String getId() {
		if (clientName == "Google") {
			return oAuth2User.getAttribute("sub").toString();
		}
		return ""; 
	}
}
