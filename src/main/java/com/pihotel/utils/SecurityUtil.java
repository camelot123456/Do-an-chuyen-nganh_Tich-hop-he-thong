package com.pihotel.utils;

import org.springframework.security.core.context.SecurityContextHolder;

import com.pihotel.entity.custom.MyUserCustom;

public class SecurityUtil {

	public static MyUserCustom getPrincipal() {
		MyUserCustom myUser =  (MyUserCustom) (SecurityContextHolder.getContext()).getAuthentication().getPrincipal();
		return myUser;
	}
	
	public static int isAuthentication() {
		try {
			if (SecurityUtil.getPrincipal().getAccount() != null) {
				return 1;
			}
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
		return 0;
	}
	
	public static int getTest() {
		return 1403;
	}
	
}
