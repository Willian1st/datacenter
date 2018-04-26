package com.w.util;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Toast和Log工具类
 * 
 * @author Ws
 * 
 */
public class ToastLogUtil {

	/**
	 * Toast并Log 屏幕提示并打印日志
	 * 
	 * @param context
	 *            上下文
	 * @param TAG
	 *            TAG
	 * @param info
	 *            内容
	 * @param type
	 *            Log级别
	 */
	public static void toastAndlog(Context context, String TAG, String info, String type) {
		logCommon(TAG, info, type);
		toastCommon(context, info);

	}

	/**
	 * Toast:屏幕提示
	 * 
	 * @param context
	 */
	public static void toast(Context context, String info) {
		toastCommon(context, info);
	}

	/**
	 * Log:打印日志
	 * 
	 * @param TAG
	 * @param info
	 * @param type
	 */
	public static void log(String TAG, String info, String type) {
		logCommon(TAG, info, type);
	}

	private static void toastCommon(Context context, String info) {
		Toast.makeText(context, info, Toast.LENGTH_SHORT).show();
	}

	private static void logCommon(String TAG, String info, String type) {
		if ("e".equals(type)) {
			Log.e(TAG, info);
		} else if ("d".equals(type)) {
			Log.d(TAG, info);
		} else if ("i".equals(type)) {
			Log.i(TAG, info);
		} else if ("w".equals(type)) {
			Log.w(TAG, info);
		} else {
			Log.e("ToastLogUtil.logCommon()", "Type参数错误!");
		}
		// l.print(null, info);
	}
}
