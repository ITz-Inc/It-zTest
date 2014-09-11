package dev.test.com.testclient;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class MainActivity extends Activity implements ScreenTransitionEventListner {

	private WebView mWebView;
	private Handler mHandler;
	
	@SuppressLint({ "SetJavaScriptEnabled", "HandlerLeak" })
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mWebView = (WebView)findViewById(R.id.webview);
        mWebView.loadUrl("file:///android_asset/login.html");
        mWebView.addJavascriptInterface(new WebInterface(this, this), "webInterface");
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        //画面遷移時に次のページをデフォルトのブラウザで新しく開かないように、このwebViewにloadするようにする
//        webView.setWebViewClient(new WebViewClient(){
//        	@Override
//        	public boolean shouldOverrideUrlLoading(WebView view, String url) {
//        		return false;
//        	}
//        });
        mHandler = new Handler() {
        	@Override
        	public void handleMessage(Message message) {
        		mWebView.loadUrl((String)message.obj);
        	}
        };
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void transitionTo(String url) {
		Log.d("ScreenTransition", "test1 transition success! : " + url);
		Message message = Message.obtain();
		message.obj = url;
		mHandler.sendMessage(message);
	}
}
