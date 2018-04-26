package w.g.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import w.g.R;

public class BatteryActivity extends Activity {
	// 取得当前类名
	private static final String LOG_TAG = new Object() {
		public String getClassName() {
			String className = this.getClass().getName();
			// w.g.activity.BatteryActivity$1
			return className.substring(className.lastIndexOf('.') + 1,
					className.lastIndexOf('$'));
		}
	}.getClassName();
	// 目前电量
	private int BatteryN;
	// 电池电压
	private int BatteryV;
	// 电池温度
	private double BatteryT;
	// 电池状态
	private String BatteryStatus;
	// 电池使用情况
	private String BatteryTemp;

	private TextView textView1;
	private TextView textView2;
	private TextView textView3;
	private TextView textView4;
	private TextView textView5;
	private TextView textView6;
	private TextView textView7;
	private TextView textView8;
	private TextView textView9;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_battery);
		textView1 = (TextView) findViewById(R.id.textView1);
		textView2 = (TextView) findViewById(R.id.textView2);
		textView3 = (TextView) findViewById(R.id.textView3);
		textView4 = (TextView) findViewById(R.id.textView4);
		textView5 = (TextView) findViewById(R.id.textView5);
		textView6 = (TextView) findViewById(R.id.textView6);
		textView7 = (TextView) findViewById(R.id.textView7);
		textView8 = (TextView) findViewById(R.id.textView8);
		textView9 = (TextView) findViewById(R.id.textView9);
		showString("接收电池信息...");
		// 注册广播
		registerReceiver(mBatInfoReceiver, new IntentFilter(
				Intent.ACTION_BATTERY_CHANGED));
	}

	/**
	 * 提示信息
	 * 
	 * @param string
	 */

	private void toast(String string) {
		Toast.makeText(getApplicationContext(), string, Toast.LENGTH_SHORT)
				.show();
	}

	/*
	 * 控制台提示信息
	 * 
	 * @param string
	 */
	private void showString(String string) {
		Log.d(LOG_TAG, string);
		toast(string);
	}

	/*
	 * 创建广播接收器
	 */
	private BroadcastReceiver mBatInfoReceiver = new BroadcastReceiver() {
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			/*
			 * 如果捕捉到的action是ACTION_BATTERY_CHANGED， 就运行onBatteryInfoReceiver()
			 */
			if (Intent.ACTION_BATTERY_CHANGED.equals(action)) {
				BatteryN = intent.getIntExtra("level", 0); // 目前电量
				BatteryV = intent.getIntExtra("voltage", 0); // 电池电压
				BatteryT = intent.getIntExtra("temperature", 0); // 电池温度
				switch (intent.getIntExtra("status",
						BatteryManager.BATTERY_STATUS_UNKNOWN)) {
				case BatteryManager.BATTERY_STATUS_CHARGING:
					BatteryStatus = "充电状态";
					break;
				case BatteryManager.BATTERY_STATUS_DISCHARGING:
					BatteryStatus = "放电状态";
					break;
				case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
					BatteryStatus = "未充电";
					break;
				case BatteryManager.BATTERY_STATUS_FULL:
					BatteryStatus = "充满电";
					break;
				case BatteryManager.BATTERY_STATUS_UNKNOWN:
					BatteryStatus = "未知状态";
					break;
				}
				switch (intent.getIntExtra("health",
						BatteryManager.BATTERY_HEALTH_UNKNOWN)) {
				case BatteryManager.BATTERY_HEALTH_UNKNOWN:
					BatteryTemp = "未知错误";
					break;
				case BatteryManager.BATTERY_HEALTH_GOOD:
					BatteryTemp = "状态良好";
					break;
				case BatteryManager.BATTERY_HEALTH_DEAD:
					BatteryTemp = "电池没有电";
					break;
				case BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE:
					BatteryTemp = "电池电压过高";
					break;
				case BatteryManager.BATTERY_HEALTH_OVERHEAT:
					BatteryTemp = "电池过热";
					break;
				}
				textView1.setText("目前电量: ");
				textView2.setText(String.valueOf(BatteryN) + "%");
				textView3.setText(String.valueOf(BatteryStatus));
				textView4.setText("电压: ");
				textView5.setText(String.valueOf(BatteryV) + "mV");
				textView6.setText(String.valueOf(BatteryTemp));
				textView7.setText("温度: ");
				textView8.setText(String.valueOf(BatteryT * 0.1).substring(0,
						String.valueOf(BatteryT * 0.1).indexOf(".") + 2)
						+ "℃");
				textView9.setText("手机现在情绪稳定");
			}
		}
	};

	@Override
	protected void onStop() {
		super.onStop();
		Log.d(LOG_TAG, "onStop()");
		// 注销广播
		unregisterReceiver(mBatInfoReceiver);
	}

}
