package w.g.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class PowerOffReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.d("PowerOffReceiver", "收到关机广播");
		if ("android.intent.action.REQUEST_POWER_OFF".equals(intent.getAction())) {
			Log.d("PowerOffReceiver", "设置关机时间");
			Toast.makeText(context, "设置关机时间", Toast.LENGTH_SHORT).show();
		} else {
			Log.d("PowerOffReceiver", "立即关机");
			Toast.makeText(context, "立即关机", Toast.LENGTH_SHORT).show();
		}
	}
}
