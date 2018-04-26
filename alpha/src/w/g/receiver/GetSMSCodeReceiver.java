package w.g.receiver;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import w.g.util.DateTimeUtil;
import w.g.util.Notificator;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;
//import w.g.activity.BrowserCMCCActivity;
//import w.g.util.ToastLogUtil;

public class GetSMSCodeReceiver extends BroadcastReceiver {

	private String TAG = "GetSMSCodeReceiver";
	// 广播消息类型
	public static final String SMS_RECEIVED_ACTION = "android.provider.Telephony.SMS_RECEIVED";
	// 模式字符串
	private Pattern pattern = Pattern.compile("[a-zA-Z0-9]{3,8}");
	private final String IDENTIFYCODE[] = { "验证码", "短信密码", "校验码" };
	private final String PASSWORD = "密码";
	// 通知工具类
	private Notificator notificator;
	private static int num = 0;

	@SuppressLint("SimpleDateFormat")
	@SuppressWarnings("deprecation")
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	public void onReceive(Context context, Intent intent) {
		Log.i(TAG, "收到新短信");
		notificator = new Notificator(context);
		if (SMS_RECEIVED_ACTION.equals(intent.getAction())) {
			Bundle bundle = intent.getExtras();
			// 判断bundle内容
			if (bundle != null) {
				// 取pdus内容,转换为Object[]
				Object[] pdus = (Object[]) bundle.get("pdus");
				// 解析短信
				SmsMessage[] messages = new SmsMessage[pdus.length];
				for (int i = 0; i < messages.length; i++) {
					byte[] pdu = (byte[]) pdus[i];
					messages[i] = SmsMessage.createFromPdu(pdu);
				}
				// 循环短信
				for (SmsMessage msg : messages) {
					// 内容
					String content = msg.getMessageBody();
					// 发送者
					String sender = msg.getOriginatingAddress();
					// 发送时间
					Date date = new Date(msg.getTimestampMillis());
					Log.i(TAG, "接收时间" + DateTimeUtil.showTime(date));
					List<String> infoList = new ArrayList<String>();
					if (ifExists(content)) {
						showCode(infoList, context, content, sender, IDENTIFYCODE[0], num++);
					}
					if (content.contains(PASSWORD)) {
						@SuppressWarnings("unused")
						String code = showCode(infoList, context, content, sender, PASSWORD, num++);
						/*
						 * if (BrowserCMCCActivity.browser == null) {
						 * ToastLogUtil.toast(context, "登录界面未打开或已关闭! "); } else
						 * { if (content.contains("4G")) {
						 * BrowserCMCCActivity.browser
						 * .loadUrl("javascript:setInfo(" + code + ")"); }
						 * 
						 * }
						 */
					}
					// 在状态栏执行系统通知
					if (infoList.size() >= 1) {
						for (int i = 0; i < infoList.size(); i++) {
							notificator.showNotification(sender, infoList.get(i), null, num++);
						}
					}
					// 只有一条时,放入剪贴板
					if (infoList.size() == 1) {
						String theCode = infoList.get(0).substring(infoList.get(0).indexOf(":") + 2, infoList.get(0).length());
						if (android.os.Build.VERSION.SDK_INT > 11) {
							android.content.ClipboardManager c = (android.content.ClipboardManager) context
									.getSystemService(Context.CLIPBOARD_SERVICE);
							c.setPrimaryClip(ClipData.newPlainText(null, theCode));
						} else {
							android.text.ClipboardManager c = (android.text.ClipboardManager) context
									.getSystemService(Context.CLIPBOARD_SERVICE);
							c.setText(theCode);
						}
						Toast.makeText(context, "可直接粘贴...", Toast.LENGTH_LONG).show();
					}
					/*
					 * { // 对于其他短信,取消广播 abortBroadcast(); }
					 */
				}
			}
		}
	}

	/**
	 * 提示*码
	 * 
	 * @param infoList
	 *            通知
	 * 
	 * @param context
	 *            上下文
	 * @param content
	 *            内容
	 * @param sender
	 *            发送者
	 * @param info
	 *            *码
	 * @param position
	 */

	private String showCode(List<String> infoList, Context context, String content, String sender, String info, int position) {
		String original = content;
		// 获取"*码"字符后面的所有字符
		// content = content.substring(content.indexOf(info), content.length());
		content = content.substring(0, content.length());
		Matcher matcher = pattern.matcher(content);
		String code = "";
		if (matcher.find()) {
			int codeLength = matcher.group().toCharArray().length;
			// 对3,4,6位的验证码启用通知
			if (codeLength == 6 || codeLength == 4 || codeLength == 3) {
				code = matcher.group();
				String notification = "收到" + info + ": " + code;
				Toast.makeText(context, notification, Toast.LENGTH_SHORT).show();
				// 加到通知列表
				infoList.add(notification);
			} else {
				showMessage(context, sender, original, position);
			}
		} else {
			showMessage(context, sender, original, position);
		}
		return code;

	}

	/**
	 * 无法提取*码时显示完整信息
	 * 
	 * @param context
	 *            上下文
	 * @param sender
	 *            发送者
	 * @param content
	 *            内容
	 * @param position
	 *            通知栏显示位置
	 */
	private void showMessage(Context context, String sender, String content, int position) {
		Toast.makeText(context, sender + " 发来信息:\n: " + content, Toast.LENGTH_LONG).show();
		notificator.showNotification(sender, content, null, position);
	}

	private boolean ifExists(String content) {
		for (String s : IDENTIFYCODE) {
			if (content.contains(s)) {
				return true;
			}
		}
		return false;

	}
}
