package com.pihotel.utils;

import static org.junit.Assert.*;

import org.assertj.core.internal.bytebuddy.utility.RandomString;
import org.junit.Test;

public class RandomEncodingVerifyUtilTest {

	@Test
	public void testGetCoding() {
		System.out.println("test random coding verify:"+RandomString.make(64));
	}

	@Test
	public void testRandom() {
		for (int i = 0; i < 100; i++) {
			System.out.println("random: "+ RandomEncodingVerifyUtil.random(0, 10));
		}
	}

}
