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

public class MainActivity extends Activity implements ScreenTransitionEventListner, UserInformationWrapper {

	private WebView mWebView;
	private Handler mHandler;
	private UserInformation mUserInformation;
	
	@SuppressLint({ "SetJavaScriptEnabled", "HandlerLeak" })
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		WebInterface webInterface = new WebInterface(this);
		webInterface.setScreenTransitionEventListener(this);
		webInterface.setUserInformationWrapper(this);
		
		mWebView = (WebView)findViewById(R.id.webview);
        mWebView.loadUrl("file:///android_asset/login.html");
        mWebView.addJavascriptInterface(webInterface, "webInterface");
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
				Log.d("ITz_TEST", "URL : " + (String)message.obj);
        		mWebView.loadUrl((String)message.obj);
        	}
        };
        
        mUserInformation = new UserInformation();
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
	public void onDestroy() {
		super.onDestroy();
		mWebView.destroy();
	}
	
	@Override
	public void transitionTo(String url) {
		Log.d("ITz_TEST", "screen transition to : " + url);
		Message message = Message.obtain();
		message.obj = url;
		mHandler.sendMessage(message);
	}

	@Override
	public String getId() {
		return mUserInformation.getId();
	}

	@Override
	public void setId(String id) {
		mUserInformation.setId(id);
	}
	
	@Override
	public String getName() {
		return mUserInformation.getName();
	}

	@Override
	public void setName(String name) {
		mUserInformation.setName(name);
	}

	@Override
	public String getPassword() {
		return mUserInformation.getPassword();
	}

	@Override
	public void setPassword(String password) {
		mUserInformation.setPassword(password);
	}
}
