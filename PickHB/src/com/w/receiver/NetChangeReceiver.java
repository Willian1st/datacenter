package com.w.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class NetChangeReceiver extends BroadcastReceiver {

	static final String ACTION = "android.net.conn.CONNECTIVITY_CHANGE";

	@Override
	public void onReceive(Context context, Intent intent) {
		if (intent.getAction().equals(ACTION)) {
			Log.i("NetChangeReceiver", "网络变化...");
		}
	}
}
