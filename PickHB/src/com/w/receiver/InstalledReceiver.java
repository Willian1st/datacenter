package com.w.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.w.service.RedBagService;

public class InstalledReceiver extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		if (intent.getAction().equals("android.intent.action.PACKAGE_ADDED")) { // install
			String packageName = intent.getDataString();
			Log.i(RedBagService.TAG, "安装了 :" + packageName);
		}
		if (intent.getAction().equals("android.intent.action.PACKAGE_REMOVED")) { // uninstall
			String packageName = intent.getDataString();
			Log.i(RedBagService.TAG, "卸载了 :" + packageName);
		}
	}
}
