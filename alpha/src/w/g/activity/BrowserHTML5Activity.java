package w.g.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.Window;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import w.g.R;

public class BrowserHTML5Activity extends Activity {

	@SuppressWarnings("unused")
	private static final String LOG_TAG = "BrowserHTML5Activity";
	private WebView browser;
	Handler handler = new Handler();

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && browser.canGoBack()) {
			browser.goBack();
			Toast.makeText(getApplicationContext(), "后退", Toast.LENGTH_SHORT)
					.show();
			// 调用js
			browser.loadUrl("javascript:hideLoading()");
			return true;
		} else {
			return super.onKeyDown(keyCode, event);
		}
	}

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏
		setContentView(R.layout.activity_browserhtml5);
		browser = (WebView) findViewById(R.id.browser);
		WebSettings webSettings = browser.getSettings();
		webSettings.setSaveFormData(true);
		webSettings.setJavaScriptEnabled(true);
		webSettings.setJavaScriptCanOpenWindowsAutomatically(true);// 允许js弹出窗口
		webSettings.setDomStorageEnabled(true);// 打开本地存储
		webSettings.setSupportZoom(true);
		// webSettings.setBuiltInZoomControls(true);
		browser.setWebViewClient(new WebViewClient());
		browser.setWebChromeClient(new WebChromeClient() {
			@Override
			public boolean onJsAlert(WebView view, String url, String message,
					final JsResult result) {
				AlertDialog.Builder builder = new AlertDialog.Builder(
						BrowserHTML5Activity.this)
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
		// 调用js
		// browser.loadUrl("javascript:hideLoading()");
		// 调用java
		// browser.addJavascriptInterface(new Object() {
		// @JavascriptInterface
		// public void clickOnAndroid() {
		// handler.post(new Runnable() {
		// public void run() {
		// //
		// }
		// });
		// }
		// }, "demo");

		browser.loadUrl("file:///android_asset/www/travel/travel.html");
	}

}
