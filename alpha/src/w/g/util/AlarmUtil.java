package w.g.util;

import java.util.Calendar;

import w.g.receiver.PowerOffReceiver;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;

public class AlarmUtil {
	private static AlarmManager am;
	private static Intent intent;
	private static PendingIntent pendingIntent;

	/**
	 * 设置关机时间
	 * 
	 * @param context
	 * @param hour
	 *            几点
	 * @param minutes
	 *            几分
	 */
	public static void alarmInfo(Context context, Integer hour, Integer minutes) {
		Calendar myCal = Calendar.getInstance();
		long shutDownTime;
		if (hour != null & minutes != null) {
			// 设置小时
			myCal.set(Calendar.HOUR_OF_DAY, hour);
			// 设置分钟
			myCal.set(Calendar.MINUTE, minutes);
			myCal.set(Calendar.SECOND, 0);
			shutDownTime = myCal.getTimeInMillis();
			Log.d("关机时间", DateTimeUtil.showTime(shutDownTime));
			Toast.makeText(context,
					"关机时间:" + DateTimeUtil.showTime(shutDownTime),
					Toast.LENGTH_LONG).show();
		} else {
			// 十秒后关机
			myCal.set(Calendar.SECOND, Calendar.SECOND + 10);
			shutDownTime = myCal.getTimeInMillis();
			Log.d("10秒后关机:", DateTimeUtil.showTime(shutDownTime));
			Toast.makeText(context,
					"10秒后关机:" + DateTimeUtil.showTime(shutDownTime),
					Toast.LENGTH_LONG).show();
		}

		am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

		intent = new Intent("com.android.settings.action.REQUEST_POWER_OFF");// 也可直接指定Receiver.class(不用配置该字符串了)
		pendingIntent = PendingIntent.getBroadcast(context, 0, intent,
				PendingIntent.FLAG_CANCEL_CURRENT);

		am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		// 设定时间发送
		am.set(AlarmManager.RTC_WAKEUP, shutDownTime, pendingIntent);

	}

	/**
	 * 设置倒计时
	 * 
	 * @param context
	 */
	public static void shutDown(Context context) {
		am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		intent = new Intent(context, PowerOffReceiver.class);
		pendingIntent = PendingIntent.getBroadcast(context, 0, intent,
				PendingIntent.FLAG_CANCEL_CURRENT);
		// 倒计时发送
		int triggerAtTime = (int) SystemClock.elapsedRealtime() + 10 * 1000;
		am.set(AlarmManager.ELAPSED_REALTIME, triggerAtTime, pendingIntent);
	}

	/**
	 * 执行重复动作
	 * 
	 * @param context
	 */
	public static void repeatAlarm(Context context) {
		// TODO
		am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		intent = new Intent(context, PowerOffReceiver.class);
		pendingIntent = PendingIntent.getBroadcast(context, 0, intent,
				PendingIntent.FLAG_CANCEL_CURRENT);
		// 5秒后发送广播，然后每个3秒重复发广播
		int triggerAtTime = (int) (SystemClock.elapsedRealtime() + 5 * 1000);
		// 倒计时发送
		int interval = (int) SystemClock.elapsedRealtime() + 3 * 1000;
		am.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime,
				interval, pendingIntent);
	}

	/**
	 * 取消
	 * 
	 * @param context
	 */
	public static void cancleAlarm(Context context) {
		// TODO
		am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		intent = new Intent(context, PowerOffReceiver.class);
		pendingIntent = PendingIntent.getBroadcast(context, 0, intent,
				PendingIntent.FLAG_CANCEL_CURRENT);
		// // 与上面的intent匹配（filterEquals(intent)）的闹钟会被取消
		am.cancel(pendingIntent);
	}

	// 调用
	{
		// Calendar myCal = Calendar.getInstance();
		// AlarmUtil.alarmInfo(this, myCal.get(Calendar.HOUR_OF_DAY),
		// myCal.get(Calendar.MINUTE)+1);
		// AlarmUtil.shutDown(this);
	}

}
