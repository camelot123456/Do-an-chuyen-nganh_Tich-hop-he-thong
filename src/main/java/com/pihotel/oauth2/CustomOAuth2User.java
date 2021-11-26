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
	
	@SuppressWarnings("unchecked")
	public String getAvatar() {
		if (clientName == "Google") {
			return oAuth2User.getAttribute("picture");
		} else if (clientName == "Facebook") {
			if(oAuth2User.getAttributes().containsKey("picture")) {
	            Map<String, Object> pictureObj =  (Map<String, Object>) oAuth2User.getAttributes().get("picture");
	            if(pictureObj.containsKey("data")) {
	                Map<String, Object>  dataObj = (Map<String, Object>) pictureObj.get("data");
	                if(dataObj.containsKey("url")) {
	                    return (String) dataObj.get("url");
	                }
	            }
	        }
		}
		return null;
	}
	
	public EAuthenticationProvider getAuthenticationProvider() {
		if (clientName == "Google") {
			return EAuthenticationProvider.GOOGLE;
		} else if(clientName == "Facebook") {
			return EAuthenticationProvider.FACEBOOK;
		}
		return null;
	}

	public String getId() {
		if (clientName == "Google") {
			return oAuth2User.getAttribute("sub").toString();
		} else if (clientName == "Facebook") {
			return oAuth2User.getAttribute("id");
		}
		return ""; 
	}
}
