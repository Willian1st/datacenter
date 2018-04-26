package w.g.activity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import w.g.R;

public class GpsActivity extends Activity {
	private TextView speed_value;// 速度
	private TextView max_v_value;// 最大速度
	private TextView lat_value;
	private TextView lon_value;
	private TextView alt_value;
	private TextView bearing_value;
	private TextView acc_value;
	private TextView gpstime_value;
	private TextView provider_value;
	private LocationManager lm;
	private Criteria criteria;
	private Location location;
	private final static int MENU_ABOUT = Menu.FIRST;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gps);
		speed_value = (TextView) findViewById(R.id.speed_value);
		max_v_value = (TextView) findViewById(R.id.max_v_value);
		lat_value = (TextView) findViewById(R.id.lat_value);
		lon_value = (TextView) findViewById(R.id.lon_value);
		alt_value = (TextView) findViewById(R.id.alt_value);
		bearing_value = (TextView) findViewById(R.id.bearing_value);
		acc_value = (TextView) findViewById(R.id.acc_value);
		gpstime_value = (TextView) findViewById(R.id.gpstime_value);
		provider_value = (TextView) findViewById(R.id.provider_value);
		lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		if (!lm.isProviderEnabled(android.location.LocationManager.GPS_PROVIDER)) {
			Toast.makeText(this, "GPS已关闭,请手动开启GPS以获得最佳的位置信息！",
					Toast.LENGTH_SHORT).show();
		} else {
			Toast.makeText(this, "定位中...", Toast.LENGTH_SHORT).show();
		}
		criteria = new Criteria();
		criteria.setAccuracy(Criteria.ACCURACY_FINE); // 设置精确度,高精度
		criteria.setAltitudeRequired(true); // 设置请求海拔
		criteria.setBearingRequired(true); // 设置请求方位
		criteria.setCostAllowed(true); // 设置允许运营商收费
		// criteria.setSpeedRequired(true);// 手机位置移动
		// criteria.setPowerRequirement(Criteria.POWER_LOW); // 低功耗
		criteria.setPowerRequirement(Criteria.POWER_LOW); // 消耗大的话，获取的频率高
		String provider = lm.getBestProvider(criteria, true);// 获取最佳provider:
																// 手机或者模拟器上均为gps
		provider_value.setText("当前的位置提供者:" + provider.toUpperCase()
				+ "\n(NETWORK和GPS都可用时则显示最佳提供者)");

		AlertDialog.Builder bd = new Builder(this);
		bd.setMessage("当前位置提供者:" + provider.toUpperCase() + "\n使用此功能时请注意交通安全!");
		bd.setTitle("提示");
		bd.setPositiveButton("确认", new OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				arg0.dismiss();
			}
		});
		bd.setNegativeButton("取消", new OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				arg0.dismiss();
				GpsActivity.this.finish();
			}
		});
		bd.create().show();

		location = lm.getLastKnownLocation(provider);// 通过GPS获取位置
		newLocalGPS(location);
		// 监听1秒一次 忽略位置变化
		lm.requestLocationUpdates(provider, 1000, 0f, new locationListener());
		// 设置监听器，自动更新的最小时间为间隔1秒或最小位移变化超过0米
	}

	class locationListener implements LocationListener {

		@Override
		public void onLocationChanged(Location location) {
			newLocalGPS(location);
		}

		@Override
		public void onProviderDisabled(String provider) {
			newLocalGPS(null);
		}

		@Override
		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub

		}

	}

	String datestring = "";
	float v1 = 0;
	float v2 = 0;
	float maxV1 = 0;
	float maxV2 = 0;
	float accuracy = 0;
	double latitude = 0;
	double longitude = 0;
	float speed = 0;
	double altitude = 0;
	float bearing = 0;
	long time = 0;
	Calendar calendar = Calendar.getInstance();
	SimpleDateFormat df = null;

	@SuppressLint("SimpleDateFormat")
	private void newLocalGPS(Location location) {
		if (location != null) {
			accuracy = location.getAccuracy();// 精确度
			latitude = location.getLatitude(); // 经度
			longitude = location.getLongitude(); // 纬度
			speed = location.getSpeed(); // 速度
			altitude = location.getAltitude(); // 海拔
			bearing = location.getBearing(); // 方位
			time = location.getTime();// 时间

			calendar.setTimeInMillis(time);
			df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			datestring = df.format(calendar.getTime());
			v1 = ((float) Math.round(speed * 3.6 * 100)) / 100;
			v2 = ((float) Math.round(speed * 100)) / 100;
			if (v1 > maxV1) {
				maxV1 = v1;
				maxV2 = v2;
			}
			max_v_value.setText(maxV1 + " km/h(" + maxV2 + " m/s)");
			speed_value.setText(v1 + "  km/h(" + v2 + " m/s)");
			lat_value.setText(((double) Math.round(latitude * 10000)) / 10000
					+ "°");
			lon_value.setText(((double) Math.round(longitude * 10000)) / 10000
					+ "°");
			alt_value
					.setText(((double) Math.round(altitude * 100)) / 100 + "米");
			bearing_value.setText(String.valueOf(((float) Math
					.round(bearing * 100) / 100) / 10));
			acc_value.setText(String.valueOf(((float) Math
					.round(accuracy * 100)) / 100) + "米");
			gpstime_value.setText(datestring);

		} else {
			// 未获取地理信息位置
			Toast.makeText(this, "位置未知或正在获取位置...", Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, MENU_ABOUT, 1, "关于");
		// menu.add(0, MENU_EXIT, 2, "退出");
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case MENU_ABOUT:
			AlertDialog.Builder bd = new Builder(this);
			bd.setMessage("该功能用于获取GPS信息\n" + "原始版本时间:" + 20140207 + "\n"
					+ "作者:Willie");
			bd.setTitle("关于");
			bd.setPositiveButton("确认", new OnClickListener() {
				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					arg0.dismiss();
				}
			});
			bd.create().show();
			break;
		// case MENU_EXIT:
		// exit();
		// break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0)
		// {
		// // exit();
		// return true;
		// }
		return super.onKeyDown(keyCode, event);
	}

	// private void exit() {
	// AlertDialog.Builder builder = new Builder(this);
	// builder.setMessage("确认退出吗？");
	// builder.setTitle("提示");
	// builder.setPositiveButton("确认", new OnClickListener() {
	// @SuppressWarnings("deprecation")
	// @Override
	// public void onClick(DialogInterface arg0, int arg1) {
	// // TODO Auto-generated method stub
	// arg0.dismiss();
	// ActivityManager actMgr = (ActivityManager)
	// getSystemService(ACTIVITY_SERVICE);
	// actMgr.restartPackage(getPackageName());
	// }
	// });
	// builder.setNegativeButton("取消", new OnClickListener() {
	//
	// @Override
	// public void onClick(DialogInterface dialog, int which) {
	// dialog.dismiss();
	// }
	// });
	// builder.create().show();
	//
	// }
}
