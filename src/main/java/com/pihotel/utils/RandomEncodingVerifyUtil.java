package com.pihotel.utils;

public class RandomEncodingVerifyUtil {

	public static String getCoding(int numChar) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < numChar; i++) {
			builder.append(random(0, 10));
		}
		return builder.toString();
	}
	
	public static int random(int min, int max) {
		return (int) (Math.random() * (max - min) + min);
	}
	
	
}
