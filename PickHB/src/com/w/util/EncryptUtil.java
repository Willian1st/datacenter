package com.w.util;

import android.util.Base64;

public class EncryptUtil {
	private static int falgs = Base64.DEFAULT;

	/**
	 * 解密
	 * 
	 * @param strBase64
	 */
	public static String decode(String strBase64) {
		String result = new String(Base64.decode(strBase64.getBytes(), falgs));
		return new String(Base64.decode(result.getBytes(), falgs));
	}

	/**
	 * 加密
	 * 
	 * @param str
	 * @return
	 */
	public static String encode2Str(String str) {
		String enToStr = Base64.encodeToString(str.getBytes(), falgs);
		return enToStr;
	}

	/**
	 * 加密
	 * 
	 * @param str
	 * @return
	 */
	public static byte[] encode(String str) {
		return Base64.encode(str.getBytes(), falgs);
	}

}
