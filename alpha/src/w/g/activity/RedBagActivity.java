package w.g.activity;

import w.g.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

/**
 * 
 * 红包主界面
 */
public class RedBagActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_redbag);

		findViewById(R.id.start_button).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						open();
					}
				});
	}

	private void open() {
		try {
			Intent intent = new Intent(
					android.provider.Settings.ACTION_ACCESSIBILITY_SETTINGS);
			startActivity(intent);
			Toast.makeText(this, "找到本应用，开启服务", Toast.LENGTH_LONG).show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
