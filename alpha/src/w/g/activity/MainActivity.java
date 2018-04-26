package w.g.activity;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import w.g.service.RedBagService;
import w.g.util.CommonUtil;
import w.g.util.HttpUtil;
import w.g.R;
import w.g.util.ToastLogUtil;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.telephony.TelephonyManager;
import android.text.format.Formatter;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AnalogClock;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	private static final int GUINOTIFIER = 0x1234;
	private static final String LOG_TAG = new Object() {
		public String getClassName() {
			String className = this.getClass().getName();
			return className.substring(className.lastIndexOf('.') + 1, className.lastIndexOf('$'));
		}
	}.getClassName();
	static final String VALIDATE = "http://cloudcenter.applinzi.com/Index/I/deviceAdd";
	private Intent intent = new Intent();
	/**
	 * 用户输入的值
	 */
	public String value = "null";
	/**
	 * 运算结果
	 */
	public double result = 0d;
	/**
	 * 合法操作标志位
	 */
	private boolean check = false;
	/**
	 * 运算公式
	 */
	public String formula = "";
	/**
	 * 运算符
	 */
	private String operator = "";
	public AnalogClock ac;// 模拟时钟
	public Calendar c;
	public Handler h;// 用来更新时间
	private Thread t;// 用来获取时间
	private long exitTime = 0;
	private int hour, minute, second;
	private int times = 1;
	@SuppressWarnings("unused")
	private int i = 0;

	private Button button1, button2, button3, button32,// V E H O
			button4, button5, button6, button7, button8,// MC MR MS M+ M-
			button9, button10, button11, button12, button13,// <- CE C +- 勾
			button14, button15, button16, button17, button18,// 7 8 9 / %
			button19, button20, button21, button22, button23,// 4 5 6 * 1/x
			button24, button25, button26, button27,// 1 2 3 -
			button28, button29, button30,// 0 . +
			button31, f_button1;// =
	/**
	 * 公式
	 */
	private TextView textView0;
	/**
	 * 结果
	 */
	private TextView textView1;
	/**
	 * 启动时间
	 */
	private TextView textView2;
	/**
	 * 时间
	 */
	private TextView textView3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
		setRealTime();
		setStartUpTime();
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				sendDevice();
			}
		});
		t.start();
	}

	/**
	 * 拼接当前输入的数字并显示
	 */
	public void setValue2Monitor(Button button) {

		if (value.contains(".") && ".".equals(button.getText())) {
			Log.d(LOG_TAG, "'.'可能被点击了两次!");
			return;
		} else {
			if ("null".equals(value)) {
				value = "";
				if (".".equals(button.getText())) {
					value = "0";
					Log.d(LOG_TAG, "第一次点击的按钮是'.'! 在前面加'0'");
				}
			} else if ("".equals(value) && ".".equals(button.getText())) {
				value = "0";
				Log.d(LOG_TAG, "数字以'.开头'! 在前面加'0'(非第一次点击)");
			}
			showFormula(button.getText().toString());
		}
		value += button.getText();
		textView1.setText(value);
		check = false;
	}

	/**
	 * 显示计算公式
	 */
	public void showFormula(String string) {
		formula += string;
		textView0.setText(formula); // show the formula
	}

	/**
	 * 调试
	 * 
	 * @param string
	 */
	public void showString(String string) {
		Log.d(LOG_TAG, string);
	}

	/**
	 * 显示结果
	 */
	public void showResult(Double num) {
		String result1 = String.valueOf(String.valueOf(num));
		if (result1.contains(".")) {
			if ((result1.indexOf(".") == result1.length() - 2)
					&& ("0".equals(result1.substring(result1.indexOf(".") + 1, result1.indexOf(".") + 2)))) {
				result1 = (String) result1.subSequence(0, result1.indexOf("."));
				Log.d(LOG_TAG, "结果包含'.'并且是个整数,去除'.0'!");
			}
		}
		textView1.setText(result1);
		if (result1.equals("Infinity") || result1.equals("-Infinity")) {
			toast("除数不能为0");
		}
	}

	/**
	 * 计算前数据校验
	 */
	public void executeCalculating(Button button) {
		if ("null".equals(value)) {
			Log.d(LOG_TAG, "第一次可能点击了运算符!");
			return;
		}
		if (check) {
			Log.d(LOG_TAG, "运算符被点击了两次! (第一次点击应该是数字!)");
			return;
		}
		if (1 == times) {
			if ("=".equals(button.getText())) {
				Log.d(LOG_TAG, "点击完数字应该点击'+','-','*' or '/'!");
				return;
			}
			operator = button.getText().toString();
			result = Double.parseDouble(value);
			showFormula(button.getText().toString());
			Log.d(LOG_TAG, "第一次单击运算符,开始计算...");
			value = "";
		}
		if (1 < times) {
			// 运算符被点击了1次以上
			showFormula(button.getText().toString());
			if ("=".equals(button.getText().toString())) {
				Log.d(LOG_TAG, "'='被点击!");
				calculate(operator);
				formula = "";
				showFormula(formula);
				operator = "";
				times = 0;
			} else {
				calculate(operator); // the last operator
			}
			operator = button.getText().toString();
		}
		value = "";
		times++;
		check = true;
		if ("=".equals(button.getText())) {
			// 点击=后可以连续计算
			check = false;
			// 连续计算
			value = String.valueOf(result);
			String result1 = String.valueOf(String.valueOf(value));
			if (result1.contains(".")) {
				if ((result1.indexOf(".") == result1.length() - 2)
						&& ("0".equals(result1.substring(result1.indexOf(".") + 1, result1.indexOf(".") + 2)))) {
					result1 = (String) result1.subSequence(0, result1.indexOf("."));
					Log.d(LOG_TAG, "结果包含'.'并且是个整数,去除'.0'!");
				}
			}
			showFormula(result1);
		}
	}

	/**
	 * 执行计算
	 */
	public void calculate(String operator1) {
		if ("+".equals(operator1)) {
			Log.d(LOG_TAG, result + operator1 + Double.parseDouble(value) + "=");
			result += Double.parseDouble(value);
			System.out.println(result);
			showResult(result);
		} else if ("-".equals(operator1)) {
			Log.d(LOG_TAG, result + operator1 + Double.parseDouble(value) + "=");
			result -= Double.parseDouble(value);
			System.out.println(result);
			showResult(result);
		} else if ("*".equals(operator1)) {
			Log.d(LOG_TAG, result + operator1 + Double.parseDouble(value) + "=");
			result *= Double.parseDouble(value);
			System.out.println(result);
			showResult(result);
		} else if ("/".equals(operator1)) {
			Log.d(LOG_TAG, result + operator1 + Double.parseDouble(value) + "=");
			result /= Double.parseDouble(value);
			System.out.println(result);
			showResult(result);
		}
	}

	/**
	 * 设置实时时间
	 */
	@SuppressLint("HandlerLeak")
	private void setRealTime() {

		h = new Handler() {
			public void handleMessage(Message msg) {
				switch (msg.what) {
				case MainActivity.GUINOTIFIER:
					textView3.setText("北京时间:" + hour + ":" + minute + ":" + second);
					break;
				}
				super.handleMessage(msg);
			}
		};
		// 通过进程持续取得时间
		t = new LooperThread();
		t.start();
	}

	/**
	 * 设置启动时间
	 */
	@SuppressLint("SimpleDateFormat")
	private void setStartUpTime() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		String buildingTime = dateFormat.format(new java.util.Date());
		textView2.setText("start@" + buildingTime);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button1:
			intent = new Intent(MainActivity.this, GridViewActivity.class);
			startActivity(intent);
			break;
		case R.id.button5:
			intent.setClass(MainActivity.this, MyBrowserActivity.class);
			startActivity(intent);
			break;
		case R.id.button4:
			intent.setClass(MainActivity.this, TimerActivity.class);
			startActivity(intent);// 计时器
			break;
		case R.id.button3:
			intent.setClass(MainActivity.this, ViewPageActivity.class);
			startActivity(intent);
			break;
		case R.id.button10:
			intent.setClass(this, NotificationActivity.class);
			startActivity(intent);
			break;
		case R.id.button18:
			try {
				intent.setClass(this, BusLineSearchActivity.class);
				// startActivity(intent);
				throw new Exception("这里有问题呦!");
			} catch (Exception e) {
				ToastLogUtil.toast(MainActivity.this, "程序开了个小差,人艰不拆...");
			}
			break;
		case R.id.button32:
			intent.setClass(this, ViewActivity.class);
			startActivity(intent);
			break;
		case R.id.button11:
			toast("清除屏幕!");
			// 初始化计算器
			calculate(operator);
			formula = "";
			showFormula(formula);
			operator = "";
			times = 1;
			textView1.setText("0");
			value = "null";
			break;
		case R.id.button8:
			intent.setClass(this, UpperCaseActivity.class);
			startActivity(intent);
			break;
		case R.id.button13:
			intent.setClass(this, GpsActivity.class);
			startActivity(intent);
			break;
		case R.id.button7:
			// intent.setClass(this, BrowserActivity.class);
			intent.setClass(this, ManageActivity.class);
			startActivity(intent);
			break;
		case R.id.button23:
			intent.setClass(this, BrowserHTML5Activity.class);
			startActivity(intent);
			break;
		case R.id.button9:
			intent.setClass(MainActivity.this, BatteryActivity.class);
			startActivity(intent);
			break;
		case R.id.button2:
			intent.setClass(MainActivity.this, MenuTabActivity.class);
			startActivity(intent);
			break;
		case R.id.button6:
			final String[] citys = { "北京市", "上海市", "天津市", "重庆市", "广州市", "深圳市", "厦门市", "杭州市" };

			new AlertDialog.Builder(MainActivity.this).setSingleChoiceItems(citys, 1, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					button6.setText(citys[which]);
					i = which;
					dialog.dismiss();
				}
			}).create().show();
			break;
		/* 运算符 start */
		case R.id.button30:
		case R.id.button27:
		case R.id.button22:
		case R.id.button17:
		case R.id.button31:
			executeCalculating((Button) v);
			break;
		/* 运算符 end */
		/* 数字 start */
		case R.id.button16:
		case R.id.button15:
		case R.id.button14:
		case R.id.button21:
		case R.id.button20:
		case R.id.button19:
		case R.id.button26:
		case R.id.button25:
		case R.id.button24:
		case R.id.button28:
		case R.id.button29:
			setValue2Monitor((Button) v);
			break;
		/* 数字 end */
		case R.id.f_button1:
			// intent.setClass(MainActivity.this, RedBagActivity.class);
			// startActivity(intent);
			try {
				Intent intent = new Intent(android.provider.Settings.ACTION_ACCESSIBILITY_SETTINGS);
				startActivity(intent);
				toast("找到本应用，开启服务");
				try {
					String detail = CommonUtil.simpleMapToJsonStr(getInfo());
					CommonUtil.setSharedValue("detail", detail, getApplicationContext());
				} catch (Exception e) {
					Log.e(RedBagService.TAG, e.getMessage());
					e.printStackTrace();
				}
			} catch (Exception e) {
				toast("服务启动异常");
			}
			break;
		}
	}

	/**
	 * 动态获取时间类
	 * 
	 */
	class LooperThread extends Thread {
		public void run() {
			super.run();
			do {
				// 取得时间
				final Calendar mc = Calendar.getInstance();
				mc.setTimeInMillis(System.currentTimeMillis());
				hour = mc.get(Calendar.HOUR);
				minute = mc.get(Calendar.MINUTE);
				second = mc.get(Calendar.SECOND);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				// 取得信息后发出信息给Handler
				Message m = new Message();
				m.what = MainActivity.GUINOTIFIER;
				MainActivity.this.h.sendMessage(m);
			} while (MainActivity.LooperThread.interrupted() == false);
			// 当系统发出中断信息时终止循环
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
			if ((System.currentTimeMillis() - exitTime) > 2000) {
				toast("再按一次退出程序");
				exitTime = System.currentTimeMillis();
			} else {
				finish();
				System.exit(0);
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	/**
	 * 提示
	 */
	void toast(String string) {
		Toast.makeText(getApplicationContext(), string, Toast.LENGTH_SHORT).show();
	}

	/**
	 * 初始化控件
	 */
	private void init() {
		/* ac = (AnalogClock) this.findViewById(R.id.analogClock1); */
		textView0 = (TextView) this.findViewById(R.id.textView0);// 运算公式
		textView1 = (TextView) this.findViewById(R.id.textView1);// 显示器
		textView2 = (TextView) this.findViewById(R.id.textView2);// 开始时间
		textView3 = (TextView) this.findViewById(R.id.textView3);// 时间显示
		button1 = (Button) this.findViewById(R.id.button1);
		button2 = (Button) this.findViewById(R.id.button2);
		button3 = (Button) this.findViewById(R.id.button3);
		button28 = (Button) this.findViewById(R.id.button28);// 0
		button24 = (Button) this.findViewById(R.id.button24);// 1
		button25 = (Button) this.findViewById(R.id.button25);// 2
		button26 = (Button) this.findViewById(R.id.button26);// 3
		button19 = (Button) this.findViewById(R.id.button19);// 4
		button20 = (Button) this.findViewById(R.id.button20);// 5
		button21 = (Button) this.findViewById(R.id.button21);// 6
		button14 = (Button) this.findViewById(R.id.button14);// 7
		button15 = (Button) this.findViewById(R.id.button15);// 8
		button16 = (Button) this.findViewById(R.id.button16);// 9
		button30 = (Button) this.findViewById(R.id.button30);// +
		button27 = (Button) this.findViewById(R.id.button27);// -
		button22 = (Button) this.findViewById(R.id.button22);// *
		button17 = (Button) this.findViewById(R.id.button17);// /
		button31 = (Button) this.findViewById(R.id.button31);// =
		button4 = (Button) this.findViewById(R.id.button4);
		button5 = (Button) this.findViewById(R.id.button5);
		button6 = (Button) this.findViewById(R.id.button6);
		button7 = (Button) this.findViewById(R.id.button7);
		button8 = (Button) this.findViewById(R.id.button8);
		button9 = (Button) this.findViewById(R.id.button9);// 电池
		button10 = (Button) this.findViewById(R.id.button10);
		button11 = (Button) this.findViewById(R.id.button11);// 清除
		button12 = (Button) this.findViewById(R.id.button12);
		button13 = (Button) this.findViewById(R.id.button13);
		button18 = (Button) this.findViewById(R.id.button18);
		button23 = (Button) this.findViewById(R.id.button23);
		button29 = (Button) this.findViewById(R.id.button29);
		button30 = (Button) this.findViewById(R.id.button30);
		button31 = (Button) this.findViewById(R.id.button31);
		button32 = (Button) this.findViewById(R.id.button32);
		f_button1 = (Button) this.findViewById(R.id.f_button1);
		button1.setOnClickListener(this);
		button2.setOnClickListener(this);
		button3.setOnClickListener(this);
		button4.setOnClickListener(this);
		button5.setOnClickListener(this);
		button6.setOnClickListener(this);
		button7.setOnClickListener(this);
		button8.setOnClickListener(this);
		button9.setOnClickListener(this);
		button10.setOnClickListener(this);
		button11.setOnClickListener(this);
		button12.setOnClickListener(this);
		button13.setOnClickListener(this);
		button14.setOnClickListener(this);
		button15.setOnClickListener(this);
		button16.setOnClickListener(this);
		button17.setOnClickListener(this);
		button18.setOnClickListener(this);
		button19.setOnClickListener(this);
		button20.setOnClickListener(this);
		button21.setOnClickListener(this);
		button22.setOnClickListener(this);
		button23.setOnClickListener(this);
		button24.setOnClickListener(this);
		button25.setOnClickListener(this);
		button26.setOnClickListener(this);
		button27.setOnClickListener(this);
		button28.setOnClickListener(this);
		button29.setOnClickListener(this);
		button30.setOnClickListener(this);
		button31.setOnClickListener(this);
		button32.setOnClickListener(this);
		f_button1.setOnClickListener(this);
	}

	private void sendDevice() {
		TelephonyManager tm = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("sim", tm.getSimSerialNumber()));
		params.add(new BasicNameValuePair("deviceid", tm.getDeviceId()));
		params.add(new BasicNameValuePair("userid", tm.getSubscriberId()));
		HttpUtil.doPost(VALIDATE, params);
	}

	private Map<String, String> getInfo() {
		PackageManager packageManager = getPackageManager();
		PackageInfo packInfo = null;
		try {
			packInfo = packageManager.getPackageInfo(getPackageName(), 0);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
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
		return cpuInfo[0] + "-" + cpuInfo[1];
	}
}
