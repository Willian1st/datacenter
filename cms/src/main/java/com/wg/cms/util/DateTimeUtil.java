package com.wg.cms.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUtil {
	/**
	 * 获取当前时间
	 * 
	 * @param format
	 * @return
	 */

	public static String getCurrentTime(String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String currentTime = sdf.format(new Date());
		return currentTime;
	}

	/**
	 * 获取当前时间
	 * 
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String getCurrentTime() {
		String defaultFormat = "yyyy-MM-dd HH:mm:ss";
		return getCurrentTime(defaultFormat);
	}

	/**
	 * 获取当前时间 转换为数字
	 * 
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String getCurrentTimeByMath() {
		String defaultFormat = "yyyy-MM-dd HH:mm:ss";
		// 剔除-
		String math = defaultFormat.replaceAll("-", "");
		// 剔除空格
		String math2 = math.replaceAll(" ", "");
		// 剔除：
		String math3 = math2.replaceAll(":", "");
		return getCurrentTime(math3);
	}

	/**
	 * 毫秒转换为日期
	 * 
	 * @param time
	 * @return
	 */
	public static String long2Date(long time) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(time);
		String stringTime = formatter.format(calendar.getTime());
		return stringTime;
	}

	/**
	 * 日期转换为毫秒
	 * 
	 * @param time
	 * @return
	 */
	public static Long Date2long(String time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		long longTime = 0;
		try {
			longTime = sdf.parse(time).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return longTime;
	}

	/**
	 * 获取当年开始时间
	 * 
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String getStartTimeOfYear() {
		String defaultFormat = "yyyy-MM-dd HH:mm:ss";
		String time = getCurrentTime(defaultFormat);
		return StringUtil.subString(time, 0, 4) + "-01-01 00:00:00";
	}

	/**
	 * 获取当年结束时间
	 * 
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String getEndTimeOfYear() {
		String defaultFormat = "yyyy-MM-dd HH:mm:ss";
		String time = getCurrentTime(defaultFormat);
		return StringUtil.subString(time, 0, 4) + "-12-31 23:59:59";
	}

	/**
	 * 获取当天开始时间
	 * 
	 * @return
	 */
	public static String getStartTimeOfToday() {
		String defaultFormat = "yyyy-MM-dd HH:mm:ss";
		String time = getCurrentTime(defaultFormat);
		return StringUtil.subString(time, 0, 10) + " 00:00:00";
	}

	/**
	 * 获取当天结束时间
	 * 
	 * @return
	 */
	public static String getEndTimeOfToday() {
		String defaultFormat = "yyyy-MM-dd HH:mm:ss";
		String time = getCurrentTime(defaultFormat);
		return StringUtil.subString(time, 0, 10) + " 23:59:59";
	}

	/**
	 * 获取下个月的当天结束时间
	 * 
	 * @return
	 */
	public static String getTimeOfNextMonth() {
		Date date = new Date();// 当前日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 格式化对象
		Calendar calendar = Calendar.getInstance();// 日历对象
		calendar.setTime(date);// 设置当前日期
		calendar.add(Calendar.MONTH, 1);// 月份减一
		return StringUtil.subString(sdf.format(calendar.getTime()), 0, 10) + " 23:59:59";
	}

	/**
	 * 根据月数获取当天结束时间
	 * 
	 * @return
	 */
	public static String getTimeByMonth(int monthNum) {
		Date date = new Date();// 当前日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 格式化对象
		Calendar calendar = Calendar.getInstance();// 日历对象
		calendar.setTime(date);// 设置当前日期
		calendar.add(Calendar.MONTH, monthNum);
		return StringUtil.subString(sdf.format(calendar.getTime()), 0, 10) + " 23:59:59";
	}

	/**
	 * 根据月数获取当天开始或结束时间
	 * 
	 * @param monthNum
	 * @param flag
	 *            true 为开始 false为结束
	 *
	 * @return String 返回类型
	 */
	public static String getTimeByMonth(int monthNum, boolean flag) {
		Date date = new Date();// 当前日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 格式化对象
		Calendar calendar = Calendar.getInstance();// 日历对象
		calendar.setTime(date);// 设置当前日期
		calendar.add(Calendar.MONTH, monthNum);
		if (flag) {
			return StringUtil.subString(sdf.format(calendar.getTime()), 0, 10) + " 00:00:00";
		} else {
			return StringUtil.subString(sdf.format(calendar.getTime()), 0, 10) + " 23:59:59";
		}
	}
}
