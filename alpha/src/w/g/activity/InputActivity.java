package w.g.activity;

import w.g.R;

import android.app.Activity;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class InputActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_input);
		final EditText e = (EditText) findViewById(R.id.editText);
		final TextView textView0 = (TextView) findViewById(R.id.textView0);
		Button button1 = (Button) findViewById(R.id.button1);
		
		button1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				textView0.setText(e.getText());
				// 判断类型
				Linkify.addLinks(textView0, Linkify.WEB_URLS
						| Linkify.EMAIL_ADDRESSES | Linkify.PHONE_NUMBERS
						| Linkify.MAP_ADDRESSES | Linkify.ALL);
			}
		});

	}

}
