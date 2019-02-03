package com.w.util;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import com.w.service.RedBagService;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class CommonUtil {
	/**
	 * 管理地址
	 */
	public static final String SERVER_URL = "http://datacenter.imwork.net/";
	public static final long RETURN_TIME = 0 * 1000;

	public static String simpleMapToJsonStr(Map<String, String> map) {
		if (map == null || map.isEmpty()) {
			return "null";
		}
		String jsonStr = "{";
		Set<?> keySet = map.keySet();
		for (Object key : keySet) {
			jsonStr += "\"" + key + "\":\"" + map.get(key) + "\",";
		}
		jsonStr = jsonStr.substring(0, jsonStr.length() - 2);
		jsonStr += "}";
		return jsonStr;
	}

	public static void setSharedValue(String flag, String value, Context context) {
		try {
			if (!value.equals(getSharedValue(flag, context))) {
				SharedPreferences sp = context.getSharedPreferences("RedBag", Context.MODE_PRIVATE);
				Editor editor = sp.edit();
				editor.putString(flag, value);
				editor.commit();
				if (value.equals(getSharedValue(flag, context))) {
					Log.d(RedBagService.TAG, flag + "已更新为:" + value);
				}
			} else {
				Log.d(RedBagService.TAG, flag + "未更新为:" + value);
			}
		} catch (Exception e) {
			Log.e(RedBagService.TAG, flag + "修改失败:" + value);
		}
	}

	public static String getSharedValue(String flag, Context context) {
		String result = "";
		try {
			SharedPreferences sp = context.getSharedPreferences("RedBag", Context.MODE_PRIVATE);
			result = sp.getString(flag, "");
		} catch (Exception e) {
			Log.e(RedBagService.TAG, e.getMessage());
		}
		return result;
	}


	/**
	 * 返回主界面
	 * 
	 * @param redBagService
	 * @param context
	 */
	public static void backToHome(long time, Object redBagService, Context context) {
		if (time > 0) {
			try {
				Thread.sleep(time);
			} catch (InterruptedException e) {
				Log.i(RedBagService.TAG, "设置等待时间失败");
			}
		}
		// 返回主页
		Intent mHomeIntent = new Intent(Intent.ACTION_MAIN);
		mHomeIntent.addCategory(Intent.CATEGORY_HOME);
		mHomeIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
		((ContextWrapper) redBagService).startActivity(mHomeIntent);
		// closeScreen(context.getApplicationContext());
	}

	/**
	 * 点亮屏幕
	 * 
	 * @param context
	 */
	@SuppressWarnings("deprecation")
	public static void lightScreen(Context context, String TAG) {
		PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
		WakeLock wakeObj = (WakeLock) pm.newWakeLock(PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP
				| PowerManager.ON_AFTER_RELEASE, TAG);
		if (wakeObj != null) {
			wakeObj.acquire();
			wakeObj.release();
			Log.w(TAG, "屏幕点亮");
		} else {
			Log.w(TAG, "屏幕未点亮");
		}
	}

	/**
	 * 打开通知栏消息
	 * 
	 * @param context
	 * @param tag
	 */
	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	public static void openNotify(AccessibilityEvent event, Context context, String TAG) {
		if (event.getParcelableData() == null || !(event.getParcelableData() instanceof Notification)) {
			return;
		}
		// 将通知栏消息打开
		Notification notification = (Notification) event.getParcelableData();
		PendingIntent pendingIntent = notification.contentIntent;
		try {
			pendingIntent.send();
			ToastLogUtil.log(TAG, "打开通知栏消息", "i");
			setSharedValue("pick_flag", "0", context);// 初始化领取标识
		} catch (PendingIntent.CanceledException e) {
			ToastLogUtil.toastAndlog(context, TAG, "通知栏消息打开异常", "e");
		}
	}

	/**
	 * 返回
	 * 
	 * @param nodeInfo
	 * @param TAG
	 */
	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	public static void goBack(long timeWait, AccessibilityNodeInfo nodeInfo, String TAG) {
		long time = RETURN_TIME;
		if (timeWait > 0) {
			time = timeWait;
		}
		if (nodeInfo != null) {
			List<AccessibilityNodeInfo> nodes = nodeInfo.findAccessibilityNodeInfosByText("返回");
			// android.widget.ImageButton
			if (nodes.isEmpty()) {
				Log.i(TAG, "无返回按钮");
			} else {
				for (AccessibilityNodeInfo n : nodes) {
					try {
						Thread.sleep(time);
					} catch (InterruptedException e) {
						Log.i(TAG, "设置等待时间失败");
					}
					boolean flag = false;
					flag = n.performAction(AccessibilityNodeInfo.ACTION_CLICK);
					AccessibilityNodeInfo returnButton = n.getParent();
					if (returnButton != null && !flag) {
						flag = returnButton.performAction(AccessibilityNodeInfo.ACTION_CLICK);
					}
					Log.i(TAG, "触发返回:" + flag);
					break;
				}
			}
		} else {
			Log.w(TAG, "窗口为空");
		}
	}

}