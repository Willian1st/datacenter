package w.g.receiver;

import w.g.util.ToastLogUtil;

import w.g.service.RedBagService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BootBroadcastReceiver extends BroadcastReceiver {

	// 系统启动完成
	static final String ACTION = "android.intent.action.BOOT_COMPLETED";

	@Override
	public void onReceive(Context context, Intent intent) {
		// 当收听到的事件是“BOOT_COMPLETED”时，就创建并启动相应的Activity和Service
		if (intent.getAction().equals(ACTION)) {
			Log.i("BootBroadcastReceiver", "系统启动完毕...");
			// 开机启动的Activity
			// Intent activityIntent = new Intent(context, MainActivity.class);
			// activityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			// context.startActivity(activityIntent);

			// 开机启动的Service
			Intent serviceIntent = new Intent(context, RedBagService.class);
			context.startService(serviceIntent);
			ToastLogUtil.toast(context, "Hi");
		}
	}
}
