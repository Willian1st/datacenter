package w.g.activity;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import w.g.R;
import w.g.adapter.Adapter;
import w.g.bean.Bean;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.format.DateUtils;
import android.text.format.Formatter;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.ListView;

public class ViewActivity extends Activity {
	private static final String LOG_TAG = new Object() {
		public String getClassName() {
			String className = this.getClass().getName();
			return className.substring(className.lastIndexOf('.') + 1, className.lastIndexOf('$'));
		}
	}.getClassName();

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_view);
		ListView lv = (ListView) findViewById(R.id.listView1);
		List<Bean> l = new ArrayList<Bean>();
		Bean d = new Bean();
		Bean d1 = new Bean();
		Bean d2 = new Bean();
		Bean d3 = new Bean();
		Bean d4 = new Bean();
		Bean d5 = new Bean();
		Bean d6 = new Bean();
		Bean d7 = new Bean();
		Bean d8 = new Bean();
		Bean d9 = new Bean();
		Bean d10 = new Bean();
		Bean d11 = new Bean();
		Bean d12 = new Bean();
		Bean d13 = new Bean();
		Bean d14 = new Bean();
		Bean d15 = new Bean();
		Bean d16 = new Bean();
		Bean d17 = new Bean();
		Bean d18 = new Bean();
		Bean d19 = new Bean();
		Bean d20 = new Bean();
		Bean d21 = new Bean();
		Bean d22 = new Bean();
		Bean d23 = new Bean();
		Bean d24 = new Bean();
		Bean d25 = new Bean();
		Bean d26 = new Bean();
		Bean d27 = new Bean();
		Bean d28 = new Bean();
		Bean d29 = new Bean();
		Bean d30 = new Bean();
		Bean d31 = new Bean();
		Bean d32 = new Bean();
		PackageManager packageManager = getPackageManager();
		PackageInfo packInfo = null;
		try {
			packInfo = packageManager.getPackageInfo(getPackageName(), 0);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		d.setInfo("软件版本");
		d.setInfoStr(packInfo.versionName);
		l.add(d);
		d1.setInfo("手机型号");
		d1.setInfoStr(android.os.Build.MODEL);
		l.add(d1);
		d2.setInfo("品牌");
		d2.setInfoStr(android.os.Build.BOARD);
		l.add(d2);
		d3.setInfo("Firmware/OS版本号");
		d3.setInfoStr(android.os.Build.VERSION.RELEASE);
		l.add(d3);
		d4.setInfo("源码控制版本号");
		d4.setInfoStr(android.os.Build.VERSION.INCREMENTAL);
		l.add(d4);
		d5.setInfo("Build.TIME");
		DateUtils.formatDateTime(null, android.os.Build.TIME, 0);
		// d5.setInfoStr(String.valueOf(android.os.Build.TIME));
		d5.setInfoStr(String.valueOf(DateUtils.formatDateTime(getApplicationContext(), android.os.Build.TIME, 0)));
		l.add(d5);
		TelephonyManager tm = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
		d29.setInfo("Mac地址");
		d29.setInfoStr(String.valueOf(getLocalMacAddress()));
		l.add(d29);
		d30.setInfo("SDK版本");
		d30.setInfoStr(String.valueOf(android.os.Build.VERSION.SDK));
		l.add(d30);
		/*
		 * 电话状态： 1.tm.CALL_STATE_IDLE=0 无活动 2.tm.CALL_STATE_RINGING=1 响铃
		 * 3.tm.CALL_STATE_OFFHOOK=2 摘机
		 */
		d9.setInfo("电话状态");
		d9.setInfoStr(String.valueOf(tm.getCallState()));
		l.add(d9);
		/*
		 * 电话方位：
		 */
		// tm.getCellLocation();// CellLocation
		d22.setInfo("电话方位");
		d22.setInfoStr(String.valueOf(tm.getCellLocation()));
		l.add(d22);
		/*
		 * 唯一的设备ID： GSM手机的 IMEI 和 CDMA手机的 MEID. Return null if device ID is not
		 * available.
		 */
		// str=tm.getDeviceId();// String
		d23.setInfo("唯一的设备ID");
		d23.setInfoStr((tm.getDeviceId()));
		l.add(d23);
		/*
		 * 设备的软件版本号： 例如：the IMEI/SV(software version) for GSM phones. Return
		 * null if the software version is not available.
		 */
		d24.setInfo("设备的软件版本号");
		d24.setInfoStr((tm.getDeviceSoftwareVersion()));
		l.add(d24);
		/*
		 * 手机号： GSM手机的 MSISDN. Return null if it is unavailable.
		 */
		d25.setInfo("CPU信息");
		d25.setInfoStr((getCpuInfo()));
		l.add(d25);
		/*
		 * 附近的电话的信息: 类型：List
		 * 需要权限：android.Manifest.permission#ACCESS_COARSE_UPDATES
		 */
		// tm.getNeighboringCellInfo();// List

		/*
		 * 获取ISO标准的国家码，即国际长途区号。 注意：仅当用户已在网络注册后有效。 在CDMA网络中结果也许不可靠。
		 */
		d26.setInfo("国际长途区号");
		d26.setInfoStr((tm.getNetworkCountryIso()));
		l.add(d26);
		/*
		 * MCC+MNC(mobile country code + mobile network code) 注意：仅当用户已在网络注册时有效。
		 * 在CDMA网络中结果也许不可靠。
		 */
		d27.setInfo("MCC+MNC");
		d27.setInfoStr((tm.getNetworkOperator()));
		l.add(d27);
		/*
		 * 按照字母次序的current registered operator(当前已注册的用户)的名字 注意：仅当用户已在网络注册时有效。
		 * 在CDMA网络中结果也许不可靠。
		 */
		d6.setInfo("当前已注册的网络");
		d6.setInfoStr(tm.getNetworkOperatorName());
		l.add(d6);
		/*
		 * 当前使用的网络类型： 例如： NETWORK_TYPE_UNKNOWN 网络类型未知 0 NETWORK_TYPE_GPRS GPRS网络
		 * 1 NETWORK_TYPE_EDGE EDGE网络 2 NETWORK_TYPE_UMTS UMTS网络 3
		 * NETWORK_TYPE_HSDPA HSDPA网络 8 NETWORK_TYPE_HSUPA HSUPA网络 9
		 * NETWORK_TYPE_HSPA HSPA网络 10 NETWORK_TYPE_CDMA CDMA网络,IS95A 或 IS95B. 4
		 * NETWORK_TYPE_EVDO_0 EVDO网络, revision 0. 5 NETWORK_TYPE_EVDO_A EVDO网络,
		 * revision A. 6 NETWORK_TYPE_1xRTT 1xRTT网络 7
		 */
		d8.setInfo("当前使用的网络类型");
		d8.setInfoStr(String.valueOf(tm.getNetworkType()));
		l.add(d8);
		/*
		 * 手机类型： 例如： PHONE_TYPE_NONE 无信号 PHONE_TYPE_GSM GSM信号 PHONE_TYPE_CDMA
		 * CDMA信号
		 */
		d7.setInfo("手机类型");
		d7.setInfoStr(String.valueOf(tm.getPhoneType()));
		l.add(d7);
		/*
		 * Returns the ISO country code equivalent for the SIM provider's
		 * country code. 获取ISO国家码，相当于提供SIM卡的国家码。
		 */
		d10.setInfo("获取ISO国家码");
		d10.setInfoStr(tm.getSimCountryIso());
		l.add(d10);
		/*
		 * Returns the MCC+MNC (mobile country code + mobile network code) of
		 * the provider of the SIM. 5 or 6 decimal digits.
		 * 获取SIM卡提供的移动国家码和移动网络码.5或6位的十进制数字. SIM卡的状态必须是
		 * SIM_STATE_READY(使用getSimState()判断).
		 */
		d11.setInfo("MCC+MNC");
		d11.setInfoStr(tm.getSimOperator());
		l.add(d11);
		/*
		 * 服务商名称： 例如：中国移动、联通 SIM卡的状态必须是 SIM_STATE_READY(使用getSimState()判断).
		 */
		d15.setInfo("总内存和可用内存");
		d15.setInfoStr(getTotalMemory());
		l.add(d15);
		/*
		 * SIM卡的序列号： 需要权限：READ_PHONE_STATE
		 */
		d16.setInfo("SIM卡的序列号");
		d16.setInfoStr(tm.getSimSerialNumber());
		l.add(d16);
		/*
		 * SIM的状态信息： SIM_STATE_UNKNOWN 未知状态 0 SIM_STATE_ABSENT 没插卡 1
		 * SIM_STATE_PIN_REQUIRED 锁定状态，需要用户的PIN码解锁 2 SIM_STATE_PUK_REQUIRED
		 * 锁定状态，需要用户的PUK码解锁 3 SIM_STATE_NETWORK_LOCKED 锁定状态，需要网络的PIN码解锁 4
		 * SIM_STATE_READY 就绪状态 5
		 */
		d17.setInfo("SIM的状态信息");
		d17.setInfoStr(String.valueOf(tm.getSimState()));
		l.add(d17);
		/*
		 * 唯一的用户ID： 例如：IMSI(国际移动用户识别码) for a GSM phone. 需要权限：READ_PHONE_STATE
		 */
		d18.setInfo("唯一的用户ID");
		d18.setInfoStr(tm.getSubscriberId());
		l.add(d18);
		/*
		 * 取得和语音邮件相关的标签，即为识别符 需要权限：READ_PHONE_STATE
		 */
		d19.setInfo("语音邮件的标签");
		d19.setInfoStr(tm.getVoiceMailAlphaTag());
		l.add(d19);
		/*
		 * 获取语音邮件号码： 需要权限：READ_PHONE_STATE
		 */
		d14.setInfo("语音邮件号码");
		d14.setInfoStr(tm.getVoiceMailNumber());
		l.add(d14);
		/*
		 * ICC卡是否存在
		 */
		d21.setInfo("ICC卡是否存在");
		d21.setInfoStr(String.valueOf(tm.hasIccCard()));
		l.add(d21);
		/*
		 * 是否漫游: (在GSM用途下)
		 */
		d28.setInfo("是否漫游");
		d28.setInfoStr(String.valueOf(tm.isNetworkRoaming()));
		l.add(d28);
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		d12.setInfo("屏幕高度");
		d12.setInfoStr(String.valueOf(dm.heightPixels));
		l.add(d12);
		d13.setInfo("屏幕宽度");
		d13.setInfoStr(String.valueOf(dm.widthPixels));
		l.add(d13);
		d20.setInfo("时间戳");
		d20.setInfoStr(String.valueOf(System.currentTimeMillis()));
		l.add(d20);
		d31.setInfo("IP地址");
		d31.setInfoStr(String.valueOf(getLocalIpAddress()));
		l.add(d31);
		d32.setInfo("已安软件");
		d32.setInfoStr(getAllApp());
		l.add(d32);
		lv.setAdapter(new Adapter(l, this));

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
		} catch (SocketException ex) {
			ex.toString();
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
			e.printStackTrace();
		}
		result[0] = Formatter.formatFileSize(this, mTotalMem);
		result[1] = Formatter.formatFileSize(this, mAvailMem);
		Log.i(LOG_TAG, result[0] + "-" + result[1]);
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
		}
		Log.i(LOG_TAG, cpuInfo[0] + "-" + cpuInfo[1]);
		return cpuInfo[0] + "-" + cpuInfo[1];
	}
}
