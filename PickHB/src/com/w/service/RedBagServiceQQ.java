package com.w.service;

import java.util.ArrayList;
import java.util.List;

import com.w.util.CommonUtil;
import com.w.util.ToastLogUtil;

import android.accessibilityservice.AccessibilityService;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

/**
 * 抢红包服务
 */
public class RedBagServiceQQ extends AccessibilityService {
	private static final String QQHONGBAO = "[QQ红包]";
	private static final String TAG = "RedBagServiceQQ";
	private static final String WECHAT_OPEN_EN = "Open";
	private static final String WECHAT_OPENED_EN = "You've opened";
	private final static String QQ_CLICK_OPEN = "点击拆开";
	private final static String QQ_PASSWORD = "口令红包";
	private final static String QQ_CLICK_TO_INPUT = "点击输入口令";
	private final static String QQ_CLASS_NAME = "com.tencent.mobileqq.activity.SplashActivity";
	private final static String SEND_HB_ACTIVITY = "com.tencent.mobileqq.activity.qwallet.SendHbActivity";
	private final static String HB_DETAIL = "cooperation.qwallet.plugin.QWalletPluginProxyActivity";
	private boolean g_flag = false;// 口令红包打开标识

	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	@Override
	public void onAccessibilityEvent(AccessibilityEvent event) {
		String className = (String) event.getClassName();
		if (HB_DETAIL.equals(className)) {
			Log.i(TAG, "红包详情界面");
			return;
		}
		int eventType = event.getEventType();
		if (eventType == AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED) {
			// 通知栏事件：过滤红包信息
			List<CharSequence> texts = event.getText();
			if (!texts.isEmpty()) {
				for (CharSequence t : texts) {
					String text = String.valueOf(t);
					if (text.contains(QQHONGBAO)) {
						CommonUtil.lightScreen(getApplicationContext(), TAG);
						CommonUtil.openNotify(event, getApplicationContext(), TAG);
						break;
					}
				}
			}
		} else if (eventType == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
			// 窗口事件：过滤聊天界面红包信息
			AccessibilityNodeInfo rootNodeInfo = event.getSource();
			if (rootNodeInfo == null) {
				return;
			}
			if (SEND_HB_ACTIVITY.equals(event.getClassName())) {
				// 只在非红包发送界面过滤红包
				return;
			}
			List<AccessibilityNodeInfo> hblist = this.findAccessibilityNodeInfosByTexts(rootNodeInfo, new String[] {
					QQ_CLICK_OPEN, QQ_PASSWORD, QQ_CLICK_TO_INPUT });
			// 有红包并且还没有打开
			if (hblist != null && hblist.size() > 0) {
				int size = hblist.size();
				if (size > 0) {
					AccessibilityNodeInfo cellNode = hblist.get(size - 1);
					String cellNodeText = cellNode.getText().toString();
					boolean flag = false;
					g_flag = false;
					if (QQ_CLICK_OPEN.equals(cellNodeText)) {
						flag = cellNode.getParent().performAction(AccessibilityNodeInfo.ACTION_CLICK);
						Log.i(TAG, cellNodeText + "点击结果：" + flag);
					} else if (QQ_PASSWORD.equals(cellNodeText)) {
						flag = cellNode.getParent().performAction(AccessibilityNodeInfo.ACTION_CLICK);
						Log.i(TAG, cellNodeText + " 点击结果：" + flag);
						AccessibilityNodeInfo rowNode = getRootInActiveWindow();
						if (rowNode == null) {
							Log.e(TAG, QQ_PASSWORD + "所在窗口为空");
							return;
						} else {
							Log.i(TAG, QQ_PASSWORD + "开始领取");
							recycle(rowNode);
							flag = g_flag;
						}
					} else {
						ToastLogUtil.toastAndlog(getApplicationContext(), TAG, "☀☁", "d");
					}
					if (flag) {
						Log.i(TAG, "回到主页");
						CommonUtil.backToHome(2000, this, getApplicationContext());
					}
				}
			} else {
				if (QQ_CLASS_NAME.equals(className)) {
					ToastLogUtil.toastAndlog(getApplicationContext(), TAG, "☀☁", "d");
				}
			}
		} else {
			ToastLogUtil.toastAndlog(getApplicationContext(), TAG, "其他事件", "i");
		}
	}

	/**
	 * 打开口令红包
	 * 
	 * @param info
	 */
	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	public void recycle(AccessibilityNodeInfo info) {
		if (info.getChildCount() == 0) {
			/* 这个if代码的作用是：匹配“点击输入口令的节点，并点击这个节点” */
			if (info.getText() != null && info.getText().toString().equals(QQ_CLICK_TO_INPUT)) {
				g_flag = info.getParent().performAction(AccessibilityNodeInfo.ACTION_CLICK);
				Log.i(TAG, QQ_PASSWORD + "点击输入口令：" + g_flag);
			}
			/* 这个if代码的作用是：匹配文本编辑框后面的发送按钮，并点击发送口令 */
			if (g_flag && info.getClassName().toString().equals("android.widget.Button")
					&& info.getText().toString().equals("发送")) {
				g_flag = info.performAction(AccessibilityNodeInfo.ACTION_CLICK);
				Log.i(TAG, QQ_PASSWORD + "发送按钮：" + g_flag);
			}
		} else {
			for (int i = 0; i < info.getChildCount(); i++) {
				if (info.getChild(i) != null) {
					recycle(info.getChild(i));
				}
			}
		}
	}

	private List<AccessibilityNodeInfo> findAccessibilityNodeInfosByTexts(AccessibilityNodeInfo nodeInfo, String[] texts) {
		for (String text : texts) {
			if (text == null)
				continue;
			List<AccessibilityNodeInfo> nodes = nodeInfo.findAccessibilityNodeInfosByText(text);
			if (!nodes.isEmpty()) {
				if (text.equals(WECHAT_OPEN_EN) && !nodeInfo.findAccessibilityNodeInfosByText(WECHAT_OPENED_EN).isEmpty()) {
					continue;
				}
				return nodes;
			}
		}
		return new ArrayList<AccessibilityNodeInfo>();
	}

	@Override
	public void onInterrupt() {
		ToastLogUtil.toastAndlog(getApplicationContext(), TAG, "☸☁", "w");
	}

	@Override
	public void onDestroy() {
		ToastLogUtil.toastAndlog(getApplicationContext(), TAG, "☾☁", "w");
		super.onDestroy();
		Intent localIntent = new Intent();
		localIntent.setClass(this, RedBagServiceQQ.class); // 销毁时重新启动Service
		this.startService(localIntent);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		flags = START_STICKY;
		return super.onStartCommand(intent, flags, startId);
	}
}
