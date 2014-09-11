package dev.test.com.testclient;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Context;
import android.util.Log;
import android.webkit.JavascriptInterface;

public class WebInterface {

	// サーバのIPアドレスは環境によって違うので注意
	static final String SERVER_URL = "http://192.168.0.102:8080/TestServer";
	// エミュレータの場合は↓
	//static final String SERVER_URL = "http://10.0.2.2:8080/TestServer";
	
	Context mContext;
	ScreenTransitionEventListner mScreenTransitionEventListner;
	UserInformation mUserInformation;
	
	public WebInterface(Context c, ScreenTransitionEventListner listener) {
		mContext = c;
		mScreenTransitionEventListner = listener;
		mUserInformation = new UserInformation();
	}
	
	@JavascriptInterface
	public void sendJson(String msg) {
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(SERVER_URL + "/SPMainServlet");
		HttpResponse httpResponse = null;
		try {
			httpResponse = httpClient.execute(httpGet);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		if (httpResponse != null) {
			int statusCode = httpResponse.getStatusLine().getStatusCode();
			if (statusCode == HttpStatus.SC_OK) {
				mScreenTransitionEventListner.transitionTo("file:///android_asset/login.html");
			} else {
				//error code
				Log.d("", "response status error");
			}
		} else {
			//connection
			Log.d("", "cannot connect");
		}

		
	}
}
