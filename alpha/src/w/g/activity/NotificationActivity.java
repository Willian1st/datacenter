package w.g.activity;

import w.g.R;
import w.g.util.Notificator;
import android.app.Activity;
import android.os.Bundle;

public class NotificationActivity extends Activity {
	private Notificator notificator;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notification);
		notificator = new Notificator(this);
		notificator.clearNotification();
	}

	@Override
	protected void onStop() {
		notificator.showNotification("提示", "内容", NotificationActivity.class, 0);
		super.onStop();
	}

	@Override
	protected void onStart() {
		notificator.clearNotification();
		super.onStart();
	}

}
