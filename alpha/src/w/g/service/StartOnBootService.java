package w.g.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class StartOnBootService extends Service {
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onStart(Intent intent, int startId) {
		// Service被启动时，将会有弹出消息提示
		Log.i("BootBroadcastReceiver", "检测服务启动...");
	}

}
