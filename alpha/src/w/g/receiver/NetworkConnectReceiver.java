package w.g.receiver;

import w.g.activity.BrowserCMCCActivity;
import w.g.util.ToastLogUtil;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.wifi.WifiManager;

/**
 * 网络状态广播
 * 
 * @author Ws
 * 
 */
public class NetworkConnectReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// WiFi网络开关改变
		if (WifiManager.WIFI_STATE_CHANGED_ACTION.equals(intent.getAction())) {
			ToastLogUtil.log("NetworkConnectReceiver", "WiFi开关状态改变", "d");
			showWiFiStatus(intent, context);
		}
		// WiFi网络连接状态改变
		if (WifiManager.NETWORK_STATE_CHANGED_ACTION.equals(intent.getAction())) {
			ToastLogUtil.log("NetworkConnectReceiver", "WiFi网络状态改变", "i");
			showNetworkInfo(intent, context);
		}
		// TODO 未注册
		// WiFi和移动数据的打开和关闭: 速度慢
		if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
			ToastLogUtil.log("NetworkConnectReceiver", "WiFi和移动数据状态改变", "e");
			showNetworkInfo(intent, context);
		}
	}

	/**
	 * 显示WiFi开关状态
	 * 
	 * @param intent
	 * @param context
	 */
	private void showWiFiStatus(Intent intent, Context context) {
		int WiFiState = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, 0);
		ToastLogUtil.log("NetworkConnectReceiver", "WiFi状态: " + WiFiState, "d");
		switch (WiFiState) {
		case WifiManager.WIFI_STATE_DISABLING:
			ToastLogUtil.log("NetworkConnectReceiver", "WiFi状态: 正在关闭...", "d");
			break;
		case WifiManager.WIFI_STATE_DISABLED:
			ToastLogUtil.toastAndlog(context, "NetworkConnectReceiver",
					"WiFi: 已关闭", "d");
			break;
		case WifiManager.WIFI_STATE_ENABLING:
			ToastLogUtil.log("NetworkConnectReceiver", "WiFi状态: 正在打开...", "d");
			break;
		case WifiManager.WIFI_STATE_ENABLED:
			ToastLogUtil.toastAndlog(context, "NetworkConnectReceiver",
					"WiFi: 已打开", "d");
			break;
		}
	}

	/**
	 * 显示网络连接状况
	 * 
	 * @param intent
	 * @param context
	 */
	private void showNetworkInfo(Intent intent, Context context) {
		ConnectivityManager manager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo gprs = manager
				.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		NetworkInfo WiFi = manager
				.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		ToastLogUtil.log("NetworkConnectReceiver",
				"WiFi连接: " + WiFi.isConnected(), "d");
		ToastLogUtil.log("NetworkConnectReceiver",
				"移动网络连接: " + gprs.isConnected(), "d");
		NetworkInfo info = intent
				.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
		if (info != null) {
			if (info.getType() == 1) {
				// WiFi
				showTypeInfo(info);
				if (State.CONNECTED == info.getState()) {
					String name = info.getExtraInfo();
					ToastLogUtil.toast(context, "已连接到: " + name);
					if (name.contains("CMCC")) {
						// 打开WebView
						intent.setClass(context, BrowserCMCCActivity.class);
						intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//						ToastLogUtil.toastAndlog(context,
//								"NetworkConnectReceiver", "打开登陆界面", "d");
//						context.startActivity(intent);
					}
				}
			} else {
				// 其他 TODO
				showTypeInfo(info);
			}
		}
	}

	/**
	 * 显示网络类型
	 * 
	 * @param info
	 */
	private void showTypeInfo(NetworkInfo info) {
		ToastLogUtil.log("getState", "网络: " + info.getState(), "e");
		ToastLogUtil.log("getType", "类型: " + info.getType(), "i");
		ToastLogUtil.log("getTypeName", "网络类型: " + info.getTypeName(), "i");
		ToastLogUtil.log("getSubtypeName", "连接类型: " + info.getSubtypeName(),
				"i");
		ToastLogUtil.log("getState", "连接状态: " + info.getState(), "i");
		ToastLogUtil.log("getDetailedState", "网络详细信息: " + info.getExtraInfo(),
				"i");
	}

}
