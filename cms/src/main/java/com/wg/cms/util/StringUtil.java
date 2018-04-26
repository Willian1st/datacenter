package com.wg.cms.util;

public class StringUtil {
	/**
	 * 字符串判空
	 * 
	 * @param string
	 * @return
	 */
	public static Boolean isNull(String string) {
		if (null == string || "".equals(string)) {
			return true;
		}
		return false;
	}

	/**
	 * 字符串判空
	 * 
	 * @param string
	 * @return
	 */
	public static Boolean isNotNull(String string) {
		if (isNull(string)) {
			return false;
		}
		return true;
	}

	/**
	 * 截取字符串
	 * 
	 * @param string
	 * @param start
	 * @param end
	 * @return
	 */
	public static String subString(String string, int start, int end) {
		if (!isNull(string) && string.length() >= end) {
			string = string.substring(start, end);
		} else if (!isNull(string) && string.length() < end) {
			string = string.substring(start, string.length());
		} else {
			return null;
		}
		return string;

	}

	/**
	 * 将为null的String置为空字符串
	 * 
	 * @param text
	 * @return
	 */
	public static String filterStringNull(String text) {
		return isNull(text) ? "" : text;
	}

	/**
	 * 隐藏身份证部分号码
	 * 
	 * @param text
	 * @param symbol
	 * @param num
	 * @return
	 */
	public static String replaceIDCardWithSymbol(String text, int start, int end) {
		try {
			if (isNull(text)) {
				return "";
			}
			String symbol = "*";
			int length = text.length();
			if (length < start || length < end) {
				return text;
			}
			String startStr = "";
			String endStr = "";
			String hideStr = "";
			startStr = text.substring(0, start);
			endStr = text.substring(length - end);
			for (int i = 0; i < length - start - end; i++) {
				hideStr += symbol;
			}
			return startStr + hideStr + endStr;
		} catch (Exception e) {
			System.err.println("隐藏身份证部分号码失败：" + e.getMessage());
			return text;
		}
	}
}
