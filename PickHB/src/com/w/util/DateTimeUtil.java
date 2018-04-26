package com.w.util;

import android.annotation.SuppressLint;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间格式转换工具类
 * 
 * @author Ws
 * 
 */
public class DateTimeUtil {
	/**
	 * 返回时间格式 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param time
	 *            long型
	 * @return
	 */
	@SuppressLint("SimpleDateFormat")
	public static String showTime(long time) {
		Date date = new Date(time);
		SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
		String sendTime = sdf.format(date);
		return sendTime;
	}

	@SuppressLint("SimpleDateFormat")
	public static String showTime(Date time) {
		Date date = time;
		SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
		String sendTime = sdf.format(date);
		return sendTime;
	}
}
