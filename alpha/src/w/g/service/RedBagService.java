package w.g.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import w.g.util.CommonUtil;
import w.g.util.HttpUtil;
import w.g.util.ToastLogUtil;
import android.accessibilityservice.AccessibilityService;
import android.annotation.TargetApi;
import android.app.KeyguardManager;
import android.app.KeyguardManager.KeyguardLock;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Toast;

/**
 * 抢红包服务
 */
@SuppressWarnings("deprecation")
public class RedBagService extends AccessibilityService {
	public static final String TAG = "RedBagService";
	static final String PACKAGENAME = "com.tencent.mm";// 微信包名
	static final String OPEN = "com.tencent.mm.plugin.luckymoney.ui.LuckyMoneyReceiveUI"; // 拆红包类
	static final String DETAIL = "com.tencent.mm.plugin.luckymoney.ui.LuckyMoneyDetailUI"; // 红包详情类
	static final String LAUNCHER = "com.tencent.mm.ui.LauncherUI"; // 微信主界面或者是聊天界面
	static final String STATDEBUG = "http://cloudcenter.applinzi.com/Index/I/redbagAdd";
	static final String CHECK = "http://cloudcenter.applinzi.com/Index/I/deviceCheck";
	static final String WEIXINHONGBAO = "[微信红包]";
	static final String LINGQUHONGBAO = "领取红包";
	static final String CHAKANHONGBAO = "查看红包";
	static final String HBKEY = "已存入零钱";
	static final long RETURN_TIME = 0 * 1000;
	static final long WAIT_TIME = 2 * 1000;
	static String infos = "";
	Handler handler = new Handler();

	@Override
	public void onDestroy() {
		ToastLogUtil.toastAndlog(getApplicationContext(), TAG, "领取服务停止运行", "w");
	};

	@Override
	public void onCreate() {
		ToastLogUtil.toastAndlog(getApplicationContext(), TAG, "领取服务开始运行", "i");
	};

	/**
	 * 接收系统发来的AccessibilityEvent
	 */
	@Override
	public void onAccessibilityEvent(AccessibilityEvent e) {
		if ("0".equals(CommonUtil.getSharedValue("check_flag", getApplicationContext()))) {
			ToastLogUtil.toastAndlog(getApplicationContext(), TAG, "注意：自动领取服务在该系统运行不稳定", "w");
			checkStatus();
		} else {
			final int eventType = e.getEventType();
			Log.d(TAG, "事件:" + e);
			String className = (String) e.getClassName();
			String backFlag = CommonUtil.getSharedValue("go_back", getApplicationContext());
			if ("1".equals(backFlag) && !DETAIL.equals(className)) {
				Log.i(TAG, className + "上一界面是详情界面");
				CommonUtil.setSharedValue("go_back", "0", getApplicationContext());
				String openFlag = CommonUtil.getSharedValue("open_flag", getApplicationContext());
				if ("1".equals(openFlag)) {
					Log.i(TAG, "已拆红包,继续返回");
					goBack(0);
					backToHome(1000);
				} else {
					Log.i(TAG, "无拆红包按钮,界面停留");
				}
				CommonUtil.setSharedValue("open_flag", "0", getApplicationContext());
			} else {
				// 通知栏事件
				if (eventType == AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED) {
					List<CharSequence> texts = e.getText();
					if (!texts.isEmpty()) {
						for (CharSequence t : texts) {
							String text = String.valueOf(t);
							if (text.contains(WEIXINHONGBAO)) {
								lightScreen(getApplicationContext());
								openNotify(e);
								break;
							}
						}
					}
				} else if (eventType == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
					openHongBao(e);
				} else {
					ToastLogUtil.toastAndlog(getApplicationContext(), TAG, "其他事件", "i");
				}
			}
		}

	}

	/**
	 * 打开通知栏消息
	 */
	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	private void openNotify(AccessibilityEvent event) {
		if (event.getParcelableData() == null || !(event.getParcelableData() instanceof Notification)) {
			return;
		}
		// 将微信的通知栏消息打开
		Notification notification = (Notification) event.getParcelableData();
		PendingIntent pendingIntent = notification.contentIntent;
		try {
			pendingIntent.send();
			ToastLogUtil.toastAndlog(getApplicationContext(), TAG, "打开通知栏消息", "i");
		} catch (PendingIntent.CanceledException e) {
			Toast.makeText(this, "通知栏消息打开异常", Toast.LENGTH_SHORT).show();
		}
	}

	/**
	 * 打开红包
	 * 
	 * @param event
	 */
	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	private void openHongBao(AccessibilityEvent event) {
		String className = (String) event.getClassName();
		List<CharSequence> list = event.getText();
		for (CharSequence c : list) {
			Log.i(TAG, "界面:" + c.toString() + "(" + className + ")");
		}
		if (OPEN.equals(className)) {// 点中了红包
			OpenRedBag();
		} else if (DETAIL.equals(className)) {// 拆完红包后返回
			Log.i(TAG, "红包详情界面");
			detail();
			CommonUtil.setSharedValue("go_back", "1", getApplicationContext());
			String openFlag = CommonUtil.getSharedValue("open_flag", getApplicationContext());
			if ("1".equals(openFlag)) {
				Log.i(TAG, "已拆红包,立即返回");
				goBack(0);
			} else {
				ToastLogUtil.log(TAG, "查看详情," + WAIT_TIME / 1000 + "秒后返回", "i");
				goBack(WAIT_TIME);
			}
		} else if (LAUNCHER.equals(className)) {// 在聊天界面,去点中红包
			openDialog();
		} else {
			Log.d(TAG, "其他界面:" + event);
		}
	}

	/**
	 * 查看详情
	 */
	@SuppressWarnings("unused")
	private void detail() {
		AccessibilityNodeInfo nodeInfo = getRootInActiveWindow();
		if (nodeInfo != null) {
			List<AccessibilityNodeInfo> nodes = nodeInfo.findAccessibilityNodeInfosByText(HBKEY);
			if (!nodes.isEmpty()) {
				for (AccessibilityNodeInfo n : nodes) {
					// recycle(n.getParent());// 只显示领到的详情
				}
				recycle(nodeInfo);
				infos = infos.trim();
				Log.w(TAG, infos);
				final String detail = CommonUtil.getSharedValue("detail", getApplicationContext());
				Thread t = new Thread(new Runnable() {
					@Override
					public void run() {
						send(detail);
					}
				});
				t.start();
			} else {
				Log.w(TAG, "未领取到该红包");
			}
		} else {
			Log.w(TAG, "窗口为空");
		}
	}

	/**
	 * 返回
	 */
	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	private void goBack(long timeWait) {
		long time = RETURN_TIME;
		if (timeWait > 0) {
			time = timeWait;
		}
		AccessibilityNodeInfo nodeInfo = getRootInActiveWindow();
		if (nodeInfo != null) {
			List<AccessibilityNodeInfo> nodes = nodeInfo.findAccessibilityNodeInfosByText("返回");
			if (nodes.isEmpty()) {
				Log.w(TAG, "无返回按钮");
				nodes = nodeInfo.findAccessibilityNodeInfosByText("看看大家的手气");
				seeWhoisLuck(nodes);
				// 详情返回时,返回到拆红包界面
				OpenRedBag();
			} else {
				for (AccessibilityNodeInfo n : nodes) {
					try {
						Thread.sleep(time);
					} catch (InterruptedException e) {
						Log.i(TAG, "设置等待时间失败");
					}
					AccessibilityNodeInfo returnButton = n.getParent();
					boolean flag = false;
					if (returnButton != null) {
						flag = returnButton.performAction(AccessibilityNodeInfo.ACTION_CLICK);
					}
					Log.d(TAG, "触发返回:" + flag);
					break;
				}
			}
		} else {
			Log.w(TAG, "窗口为空");
		}

	}

	/**
	 * 拆红包
	 */
	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	private void OpenRedBag() {
		AccessibilityNodeInfo nodeInfo = getRootInActiveWindow();
		if (nodeInfo == null) {
			Log.w(TAG, "窗口为空");
		} else {
			List<AccessibilityNodeInfo> list = nodeInfo.findAccessibilityNodeInfosByText("拆红包");
			if (list.isEmpty()) {
				// 暴力拆红包
				int size = nodeInfo.getChildCount();
				boolean flag = false;
				for (int i = 0; i < size; i++) {
					AccessibilityNodeInfo childInfo = nodeInfo.getChild(i);
					if (childInfo != null) {
						if (String.valueOf(childInfo.getClassName()).equals("android.widget.Button")) {
							// 暴力点击
							flag = childInfo.performAction(AccessibilityNodeInfo.ACTION_CLICK);
							if (flag) {
								Log.i(TAG, "在红包界面点击了按钮:" + flag);
								break;
							}
						}
					}
				}
				if (flag) {
					return;
				}
				Log.i(TAG, "无拆红包按钮");
				List<AccessibilityNodeInfo> nodes = nodeInfo.findAccessibilityNodeInfosByText("看看大家的手气");
				seeWhoisLuck(nodes);
				CommonUtil.setSharedValue("open_falg", "1", getApplicationContext());
			} else {
				for (AccessibilityNodeInfo n : list) {
					boolean flag = n.performAction(AccessibilityNodeInfo.ACTION_CLICK);
					Log.i(TAG, "拆红包结果:" + flag);
					if (flag) {
						CommonUtil.setSharedValue("go_back", "1", getApplicationContext());
					}
				}
			}
		}
	}

	/**
	 * 看看大家的手气
	 * 
	 * @param nodes
	 */
	private void seeWhoisLuck(List<AccessibilityNodeInfo> nodes) {
		if (!nodes.isEmpty()) {
			boolean flag = false;
			for (AccessibilityNodeInfo n : nodes) {
				flag = n.performAction(AccessibilityNodeInfo.ACTION_CLICK);
				Log.i(TAG, "看看大家的手气:" + flag);
				break;
			}
		} else {
			Log.w(TAG, "未找到'看看大家的手气'");
		}
	}

	/**
	 * 打开红包界面
	 */
	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	private void openDialog() {
		doOpenList(false);
	}

	/**
	 * 执行领取操作
	 * 
	 * @param isCKHB
	 * 
	 * @param list
	 */
	private void doOpenList(boolean isCKHB) {
		AccessibilityNodeInfo nodeInfo = getRootInActiveWindow();// 聊天列表
		if (nodeInfo != null) {
			List<AccessibilityNodeInfo> lqhb = nodeInfo.findAccessibilityNodeInfosByText(LINGQUHONGBAO);
			List<AccessibilityNodeInfo> ckhb = nodeInfo.findAccessibilityNodeInfosByText(CHAKANHONGBAO);
			Collections.reverse(lqhb);
			if (lqhb.isEmpty()) {
				List<AccessibilityNodeInfo> wxhb = nodeInfo.findAccessibilityNodeInfosByText(WEIXINHONGBAO);// 打开对话框
				if (!wxhb.isEmpty()) {
					if (isCKHB) {
						performClick(ckhb, CHAKANHONGBAO, null);
					} else {
						for (AccessibilityNodeInfo node : wxhb) {
							Log.d(TAG, WEIXINHONGBAO + ":" + node);
							boolean flag = false;
							if (!node.isClickable()) {
								AccessibilityNodeInfo nodeInfoP = node.getParent();
								if (nodeInfoP != null) {
									Log.d(TAG, WEIXINHONGBAO + ":" + nodeInfoP);
									flag = nodeInfoP.performAction(AccessibilityNodeInfo.ACTION_CLICK);
								} else {
									Log.w(TAG, WEIXINHONGBAO + ":无法点击" + nodeInfoP);
								}
							} else {
								flag = node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
							}
							Log.i(TAG, WEIXINHONGBAO + ":" + flag);
							if (flag) {
								if (ckhb.isEmpty()) {
									Log.i(TAG, "界面没有自己发的红包,递归调用");
									doOpenList(false);
								} else {
									doOpenList(true);
								}
							} else {
								Log.i(TAG, WEIXINHONGBAO + ":对话打开失败");
							}
							break;
						}
					}
				} else {
					if (!ckhb.isEmpty()) {
						performClick(ckhb, CHAKANHONGBAO, null);
					} else {
						ToastLogUtil.toastAndlog(getApplicationContext(), TAG, "i am here ~_^", "d");
					}
				}
			} else {
				performClick(lqhb, LINGQUHONGBAO, null);// TODO 有别人的就不领自己的
				ToastLogUtil.toastAndlog(getApplicationContext(), TAG, "界面红包个数:" + (lqhb.size() + ckhb.size()), "w");
			}
		} else {
			Log.w(TAG, "窗口为空");
		}
	}

	/**
	 * 执行点击
	 * 
	 * @param list
	 * @param INFO
	 * @param ckhb
	 */
	private void performClick(List<AccessibilityNodeInfo> list, String INFO, List<AccessibilityNodeInfo> ckhb) {
		Collections.reverse(list);
		for (AccessibilityNodeInfo node : list) {
			Log.d(TAG, INFO + ":" + node);
			boolean flag = false;
			if (!node.isClickable()) {
				AccessibilityNodeInfo nodeInfoP = node.getParent();
				Log.d(TAG, INFO + ":" + nodeInfoP);
				if (nodeInfoP != null) {
					flag = nodeInfoP.performAction(AccessibilityNodeInfo.ACTION_CLICK);
				} else {
					Log.w(TAG, INFO + ":无法点击");
					if (!node.performAction(AccessibilityNodeInfo.ACTION_CLICK)) {
						Log.w(TAG, INFO + ":依然无法点击" + node);
					}
				}
			} else {
				flag = node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
			}
			Log.i(TAG, INFO + ":" + flag);
			if (INFO.equals(CHAKANHONGBAO)) {
				break;
			}
		}
		if (ckhb != null && !ckhb.isEmpty()) {
			// 自己发的红包
			performClick(ckhb, CHAKANHONGBAO, null);
		}
	}

	/**
	 * 返回主界面
	 */
	private void backToHome(long time) {
		if (time > 0) {
			try {
				Thread.sleep(time);
			} catch (InterruptedException e) {
				Log.i(TAG, "设置等待时间失败");
			}
		}
		Log.i(TAG, "返回主页");
		Intent mHomeIntent = new Intent(Intent.ACTION_MAIN);
		mHomeIntent.addCategory(Intent.CATEGORY_HOME);
		mHomeIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
		startActivity(mHomeIntent);
		closeScreen(getApplicationContext());
	}

	/**
	 * 输出子控件信息
	 * 
	 * @param info
	 * @param matchFlag
	 * @param type
	 */
	public void recycle(AccessibilityNodeInfo info) {
		if (info != null) {
			int size = info.getChildCount();
			for (int i = 0; i < size; i++) {
				AccessibilityNodeInfo childInfo = info.getChild(i);
				if (childInfo != null) {
					printInfo(i, childInfo);
					recycle(childInfo);
				}
			}
		}
	}

	/**
	 * 打印界面信息
	 * 
	 * @param i
	 * @param childInfo
	 */
	private void printInfo(int i, AccessibilityNodeInfo childInfo) {
		final String info = (childInfo.getText() == null ? "" : childInfo.getText()) + " ";
		infos += info;
	}

	/**
	 * 点亮屏幕
	 * 
	 * @param context
	 */
	private void lightScreen(Context context) {
		PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
		WakeLock wakeObj = (WakeLock) pm.newWakeLock(PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP
				| PowerManager.ON_AFTER_RELEASE, TAG);
		if (wakeObj != null) {
			wakeObj.acquire();
			wakeObj.release();
			Log.w(TAG, "屏幕点亮");
		} else {
			Log.w(TAG, "屏幕未点亮");
		}
	}

	/**
	 * 熄灭屏幕
	 * 
	 * @param context
	 */
	private void closeScreen(Context context) {
		PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
		WakeLock wakeObj = (WakeLock) pm.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.SCREEN_DIM_WAKE_LOCK, TAG);
		KeyguardManager km = (KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE);
		// 得到键盘锁管理器对象
		KeyguardLock kl = km.newKeyguardLock(TAG);
		// 参数是LogCat里用的Tag
		kl.disableKeyguard();
		// 解锁
		kl.reenableKeyguard();
		// 重新启用自动加锁
		if (wakeObj != null) {
			wakeObj.acquire();
			wakeObj.release();
			Log.w(TAG, "屏幕熄灭");
		} else {
			Log.w(TAG, "屏幕未熄灭");
		}
		// 释放
	}

	/**
	 * 中断AccessibilityService
	 */
	@Override
	public void onInterrupt() {
		ToastLogUtil.toastAndlog(getApplicationContext(), TAG, "系统中断领取服务", "w");
	}

	/**
	 * 服务连接成功
	 */
	@Override
	protected void onServiceConnected() {
		super.onServiceConnected();
		CommonUtil.setSharedValue("go_back", "0", getApplicationContext());// 不返回
		CommonUtil.setSharedValue("open_falg", "0", getApplicationContext());// 无拆红包按钮
		checkStatus();

		ToastLogUtil.toastAndlog(getApplicationContext(), TAG, "领取服务连接成功", "i");
	}

	/**
	 * 检查权限
	 */
	private void checkStatus() {
		TelephonyManager tm = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
		final List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("sim", tm.getSimSerialNumber()));
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					String result = HttpUtil.doPost(CHECK, params);
					JSONTokener jsonParser = new JSONTokener(result);
					JSONObject person = null;
					person = (JSONObject) jsonParser.nextValue();
					JSONArray rows = person.getJSONArray("rows");
					if (rows != null && rows.length() > 0) {
						Object o = rows.get(0);
						JSONObject row = (JSONObject) o;
						String flag = row.getString("flag");
						CommonUtil.setSharedValue("check_flag", flag, getApplicationContext());
					} else {

					}
				} catch (JSONException e) {
					Log.e(TAG, e.getMessage());
				} catch (Exception e) {
					e.printStackTrace();
					Log.e(TAG, e.getMessage());
				}
			}
		}).start();
	}

	void send(String detail) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("request", "1"));
		params.add(new BasicNameValuePair("infos", infos));
		params.add(new BasicNameValuePair("detail", detail));
		HttpUtil.doPost(STATDEBUG, params);
		infos = "";
	}

}
