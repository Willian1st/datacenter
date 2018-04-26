package com.w.service;

import java.util.List;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.view.accessibility.AccessibilityEvent;

import com.w.util.ToastLogUtil;

/**
 * 抢红包服务
 */
public class DebugService extends AccessibilityService {
	private static final String TAG = "DebugService";
	String[] PACKAGES = { "com.android.settings" };

	@Override
	protected void onServiceConnected() {
		AccessibilityServiceInfo accessibilityServiceInfo = new AccessibilityServiceInfo();
		// accessibilityServiceInfo.packageNames = PACKAGES;
		// accessibilityServiceInfo.packageNames = new String[] {
		// "com.tencent.mm", "com.tencent.mqq", "com.tencent.mobileqq" };
		accessibilityServiceInfo.eventTypes = AccessibilityEvent.TYPES_ALL_MASK;
		accessibilityServiceInfo.feedbackType = AccessibilityServiceInfo.FEEDBACK_SPOKEN;
		accessibilityServiceInfo.notificationTimeout = 100;
		setServiceInfo(accessibilityServiceInfo);
	}

	@Override
	public void onAccessibilityEvent(AccessibilityEvent event) {
		int eventType = event.getEventType();
		String eventText = "";
		String className = (String) event.getClassName();
		if ("android.widget.Toast".equals(className) || "android.widget.Toast$TN".equals(className)) {
			return;
		}
		switch (eventType) {
		case AccessibilityEvent.TYPE_VIEW_CLICKED:
			eventText = "TYPE_VIEW_CLICKED";
			break;
		case AccessibilityEvent.TYPE_VIEW_FOCUSED:
			eventText = "TYPE_VIEW_FOCUSED";
			break;
		case AccessibilityEvent.TYPE_VIEW_LONG_CLICKED:
			eventText = "TYPE_VIEW_LONG_CLICKED";
			break;
		case AccessibilityEvent.TYPE_VIEW_SELECTED:
			eventText = "TYPE_VIEW_SELECTED";
			break;
		case AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED:
			eventText = "TYPE_VIEW_TEXT_CHANGED";
			break;
		case AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED:
			eventText = "TYPE_WINDOW_STATE_CHANGED";
			break;
		case AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED:
			eventText = "TYPE_NOTIFICATION_STATE_CHANGED";
			break;
		case AccessibilityEvent.TYPE_TOUCH_EXPLORATION_GESTURE_END:
			eventText = "TYPE_TOUCH_EXPLORATION_GESTURE_END";
			break;
		case AccessibilityEvent.TYPE_ANNOUNCEMENT:
			eventText = "TYPE_ANNOUNCEMENT";
			break;
		case AccessibilityEvent.TYPE_TOUCH_EXPLORATION_GESTURE_START:
			eventText = "TYPE_TOUCH_EXPLORATION_GESTURE_START";
			break;
		case AccessibilityEvent.TYPE_VIEW_HOVER_ENTER:
			eventText = "TYPE_VIEW_HOVER_ENTER";
			break;
		case AccessibilityEvent.TYPE_VIEW_HOVER_EXIT:
			eventText = "TYPE_VIEW_HOVER_EXIT";
			break;
		case AccessibilityEvent.TYPE_VIEW_SCROLLED:
			eventText = "TYPE_VIEW_SCROLLED";
			return;
		case AccessibilityEvent.TYPE_VIEW_TEXT_SELECTION_CHANGED:
			eventText = "TYPE_VIEW_TEXT_SELECTION_CHANGED";
			break;
		case AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED:
			eventText = "TYPE_WINDOW_CONTENT_CHANGED";
			break;
		}
		List<CharSequence> texts = event.getText();
		if (!texts.isEmpty()) {
			for (CharSequence t : texts) {
				eventText = eventText + ":" + event.getClassName();
				ToastLogUtil.log(TAG, eventText, "i");
				String text = String.valueOf(t);
				ToastLogUtil.toastAndlog(getApplicationContext(), TAG, "内容:" + text, "i");
			}
		}
	}

	@Override
	public void onInterrupt() {
		// TODO Auto-generated method stub
		
	}

}
