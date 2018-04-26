package com.w.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.w.util.ToastLogUtil;

public class BootBroadcastReceiver extends BroadcastReceiver {

	// 系统启动完成
	static final String ACTION = "android.intent.action.BOOT_COMPLETED";

	@Override
	public void onReceive(Context context, Intent intent) {
		// 当收听到的事件是“BOOT_COMPLETED”时，就创建并启动相应的Activity和Service
		if (intent.getAction().equals(ACTION)) {
			Log.i("BootBroadcastReceiver", "系统启动完毕...");
			/*
			 * Intent serviceIntent = new Intent(context, RedBagService.class);
			 * context.startService(serviceIntent);
			 */
			ToastLogUtil.toast(context, "Hi,^_^");
		}
	}
}
