package com.ws.browser;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.webkit.GeolocationPermissions;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    /**
     * 标题栏
     */
    private TextView titleBar;

    /**
     * 地址栏
     */
    private EditText content;
    /**
     * j界面
     */
    private WebView display;

    ProgressBar bar;

    static final String HOME = "file:///android_asset/home.html";

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    titleBar.setText(R.string.title_home);
                    display.loadUrl(HOME);
                    return true;
                case R.id.navigation_dashboard:
                    return true;
                case R.id.navigation_notifications:
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐藏标题
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        //隐藏状态栏
        View decorView = getWindow().getDecorView();
        int option = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(option);

        setContentView(R.layout.activity_main);

        // 隐藏输入法
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        content = (EditText) findViewById(R.id.content);
        display = (WebView) findViewById(R.id.display);
        bar = (ProgressBar) findViewById(R.id.status);
        titleBar = (TextView) findViewById(R.id.title);
        display.getSettings().setJavaScriptEnabled(true);
        display.getSettings().setDomStorageEnabled(true);
        display.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        display.getSettings().setSupportMultipleWindows(true);
        display.getSettings().setBuiltInZoomControls(true);
        display.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        display.getSettings().setBlockNetworkImage(true);
        display.getSettings().setGeolocationEnabled(true);
        display.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return false;
            }
        });
        display.loadUrl(HOME);

        display.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    bar.setVisibility(View.INVISIBLE);
                    content.setVisibility(View.GONE);
                    titleBar.setVisibility(View.VISIBLE);
                } else {
                    if (View.INVISIBLE == bar.getVisibility()) {
                        bar.setVisibility(View.VISIBLE);
                    }
                    bar.setProgress(newProgress);
                }
                super.onProgressChanged(view, newProgress);
            }

            @Override
            public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
                callback.invoke(origin, true, true);
                super.onGeolocationPermissionsShowPrompt(origin, callback);
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                if (title != null) {
                    titleBar.setText(title);
                }
            }


        });
        display.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                Log.i("onPageStarted", url);
                String title = view.getTitle();
                titleBar.setText(title);
                if (!HOME.equals(url)) {
                    content.setText(url);
                } else {
                    content.setText("");
                }
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Log.i("onPageFinished", url);
                display.getSettings().setBlockNetworkImage(false);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                Log.e("onReceivedSslError", "PrimaryError:" + error.getPrimaryError());
                if (error.getPrimaryError() == SslError.SSL_DATE_INVALID
                        || error.getPrimaryError() == SslError.SSL_EXPIRED
                        || error.getPrimaryError() == SslError.SSL_INVALID
                        || error.getPrimaryError() == SslError.SSL_UNTRUSTED) {

                    handler.proceed();

                } else {
                    handler.cancel();
                }
            }
        });
        content.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView text, int actionId, KeyEvent event) {
                String url = content.getText().toString();
                switch (actionId) {
                    case EditorInfo.IME_ACTION_SEARCH:
                        return true;
                    case EditorInfo.IME_ACTION_GO:
                        display.loadUrl(url);
                        return true;
                    case EditorInfo.IME_ACTION_SEND:
                        return true;
                    case EditorInfo.IME_ACTION_DONE:
                        return true;
                    default:
                        display.loadUrl(url);
                        content.setVisibility(View.GONE);
                        titleBar.setVisibility(View.VISIBLE);
                        return false;
                }
            }
        });
        titleBar.setOnTouchListener(
                new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        content.setVisibility(View.VISIBLE);
                        titleBar.setVisibility(View.GONE);
                        return false;
                    }
                }
        );
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.i("onKeyDown", "keyCode:" + keyCode);
        if (keyCode == KeyEvent.KEYCODE_SEARCH || keyCode == KeyEvent.KEYCODE_ENTER) {
            String url = content.getText().toString();
            display.loadUrl(url);
        }
        if ((keyCode == KeyEvent.KEYCODE_BACK) && display.canGoBack()) {
            display.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.exit(0);
    }
}
