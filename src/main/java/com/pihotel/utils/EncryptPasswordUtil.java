package com.pihotel.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncryptPasswordUtil {

	public static String passwordEncoder(String password) {
		return new BCryptPasswordEncoder().encode(password);
	}
	
	public static void main(String[] args) {
		System.out.println("password: " + passwordEncoder("123456"));
	}
	
}
