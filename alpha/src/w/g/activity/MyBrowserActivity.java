package w.g.activity;

import w.g.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Window;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MyBrowserActivity extends Activity {
	private static final String LOG_TAG = "MyBrowserActivity";
	private WebView browser;

	private Handler mHandler = new Handler();

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏
		setContentView(R.layout.activity_browser);
		browser = (WebView) findViewById(R.id.browser);
		WebSettings webSettings = browser.getSettings();
		// webSettings.setSavePassword(false);
		// webSettings.setSaveFormData(false);
		webSettings.setJavaScriptEnabled(true);
		// webSettings.setSupportZoom(false);
		webSettings.setBuiltInZoomControls(true);
		// browser.setWebChromeClient(new MyWebChromeClient());
		// browser.addJavascriptInterface(new DemoJavaScriptInterface(),
		// "demo");
		browser.setWebViewClient(new Callback());
		browser.loadUrl("http://m.baidu.com/");
		
	}

	private class Callback extends WebViewClient {
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			return (false);
		}
	}

	final class DemoJavaScriptInterface {

		DemoJavaScriptInterface() {
		}

		/**
		 * This is not called on the UI thread. Post a runnable to invoke
		 * loadUrl on the UI thread.
		 */
		public void clickOnAndroid() {
			mHandler.post(new Runnable() {
				public void run() {
					browser.loadUrl("javascript:wave()");
				}
			});

		}
	}

	/**
	 * Provides a hook for calling "alert" from javascript. Useful for debugging
	 * your javascript.
	 */
	final class MyWebChromeClient extends WebChromeClient {
		@Override
		public boolean onJsAlert(WebView view, String url, String message,
				JsResult result) {
			Log.d(LOG_TAG, message);
			result.confirm();
			return true;
		}
	}
}
