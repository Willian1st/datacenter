package w.g.activity;

import w.g.R;
import w.g.util.ToastLogUtil;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class BrowserCMCCActivity extends Activity {
	public static WebView browser;

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏
		setContentView(R.layout.activity_browser);
		browser = (WebView) findViewById(R.id.browser);
		WebSettings webSettings = browser.getSettings();
		webSettings.setJavaScriptCanOpenWindowsAutomatically(true);// 允许js弹出窗口
		webSettings.setSaveFormData(true);
		webSettings.setJavaScriptEnabled(true);
		webSettings.setBuiltInZoomControls(true);
		browser.setWebChromeClient(new WebChromeClient() {
			@Override
			public boolean onJsAlert(WebView view, String url, String message,
					final JsResult result) {
				AlertDialog.Builder builder = new AlertDialog.Builder(
						BrowserCMCCActivity.this)
						.setTitle("提示")
						.setMessage(message)
						.setPositiveButton(android.R.string.ok,
								new AlertDialog.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										result.confirm();
									}
								});

				builder.setCancelable(false);
				builder.create();
				builder.show();
				return true;

			}
		});
		browser.loadUrl("file:///android_asset/www/CMCC.html");

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		this.finish();
		return super.onTouchEvent(event);
	}

	@Override
	protected void onDestroy() {
		ToastLogUtil.toastAndlog(this, "BrowserCMCCActivity", "关闭登陆界面", "d");
		super.onDestroy();
	}
}
