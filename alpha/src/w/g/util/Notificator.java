package w.g.util;

import w.g.R;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

/**
 * 通知工具类
 * 
 * @author Ws
 * 
 */
public class Notificator {
	// TODO
	private Context context;

	public Notificator(Context context) {
		this.context = context;
	}

	/**
	 * 在状态栏显示通知
	 * 
	 * @param <T>
	 * 
	 * @param info
	 *            提示
	 * @param content
	 *            内容
	 * @param cls
	 *            要跳转的activity
	 */
	@SuppressWarnings("deprecation")
	public <T> void showNotification(String info, String content, Class<T> cls,
			int position) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			ToastLogUtil.log("Notificator", "延时失败", "e");
		}
		// 创建引用
		NotificationManager notificationManager = (NotificationManager) context
				.getSystemService(android.content.Context.NOTIFICATION_SERVICE);
		// 定义属性
		Notification notification = new Notification(R.drawable.spyonyou, info,
				System.currentTimeMillis());
		// notification.flags |= Notification.FLAG_ONGOING_EVENT; // 通知放置在正在运行
		// notification.flags |= Notification.FLAG_NO_CLEAR;
		// //表明在点击了通知栏中的"清除通知"后，此通知不清除，经常与FLAG_ONGOING_EVENT一起使用
		notification.flags |= Notification.FLAG_AUTO_CANCEL;// 该通知能被状态栏的清除按钮给清除掉

		// notification.flags |=
		// Notification.FLAG_INSISTENT;//一直进行，比如音乐一直播放，直到用户响应
		// DEFAULT_ALL 使用所有默认值，比如声音，震动，闪屏等等
		// DEFAULT_SOUNDS 使用默认提示声音
		// DEFAULT_VIBRATE 使用默认手机震动，需加上权限
		notification.defaults |= Notification.DEFAULT_VIBRATE;
		long[] vibrate = { 0, 100, 200, 300 };
		notification.vibrate = vibrate;
		// notification.defaults = Notification.DEFAULT_LIGHTS;
		notification.defaults |= Notification.DEFAULT_SOUND;
		notification.ledARGB = Color.BLUE;
		notification.ledOnMS = 5000; // 闪光时间，毫秒
		notification.ledOffMS = 1000;
		notification.flags |= Notification.FLAG_SHOW_LIGHTS;// 使用闪光提示
		// notification.number = 1; // number字段表示此通知代表的当前事件数量，它将覆盖在状态栏图标的顶部
		// 通知栏标题
		CharSequence contentTitle = info;
		// 通知栏内容
		CharSequence contentText = content;
		if (cls != null) {
			// 点击该通知后要跳转的Activity
			Intent intent = new Intent(context, cls);
			// 测试跳转到短信
			/*intent.setClassName("com.android.mms",
					"com.android.mms.ui.ConversationList");*/

			// 跳转对象
			PendingIntent contentItent = PendingIntent.getActivity(context, 0,
					intent, 0);
			notification.setLatestEventInfo(context, contentTitle, contentText,
					contentItent);
		} else {
			notification.setLatestEventInfo(context, contentTitle, contentText,
					null);
		}
		// 把Notification传递给NotificationManager
		notificationManager.notify(position, notification);
	}

	// 删除通知
	public void clearNotification() {
		// 启动后删除之前我们定义的通知
		NotificationManager notificationManager = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);
		notificationManager.cancel(0);// 移除标记为0的通知
	}
}
