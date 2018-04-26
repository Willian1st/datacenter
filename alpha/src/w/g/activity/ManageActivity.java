package w.g.activity;

import java.lang.reflect.Field;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import w.g.R;

public class ManageActivity extends Activity {

	@SuppressWarnings("unused")
	private static final String LOG_TAG = "ManageActivity";
	private WebView browser;
	Handler handler = new Handler();

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && browser.canGoBack()) {
			browser.goBack();
			Toast.makeText(getApplicationContext(), "后退", Toast.LENGTH_SHORT).show();
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
		setContentView(R.layout.activity_browsermanage);
		browser = (WebView) findViewById(R.id.browser);
		WebSettings webSettings = browser.getSettings();
		webSettings.setSaveFormData(true);
		webSettings.setJavaScriptEnabled(true);
		webSettings.setJavaScriptCanOpenWindowsAutomatically(true);// 允许js弹出窗口
		webSettings.setDomStorageEnabled(true);// 打开本地存储
		webSettings.setSupportZoom(false);
		webSettings.setBuiltInZoomControls(false);
		browser.setWebViewClient(new WebViewClient());
		browser.setWebChromeClient(new WebChromeClient() {
			@Override
			public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
				AlertDialog.Builder builder = new AlertDialog.Builder(ManageActivity.this).setTitle("提示").setMessage(message)
						.setPositiveButton(android.R.string.ok, new AlertDialog.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								result.confirm();
							}
						});

				builder.setCancelable(false);
				builder.create();
				builder.show();
				return true;

			}
		});
		browser.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus) {
					try {
						Field defaultScale = WebView.class.getDeclaredField("mDefaultScale");
						defaultScale.setAccessible(true);
						defaultScale.setFloat(browser, 0.5f);
					} catch (SecurityException e) {
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (NoSuchFieldException e) {
						e.printStackTrace();
					}
				}
			}
		});
		browser.loadUrl("file:///android_asset/www/manage_all.html");
	}

}
