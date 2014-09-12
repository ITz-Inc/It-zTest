package dev.test.com.testclient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;
import android.webkit.JavascriptInterface;

public class WebInterface implements UserInformationWrapper {

	// サーバのIPアドレスは環境によって違うので注意
	static final String SERVER_URL = "http://192.168.0.102:8080/TestServer";
	// エミュレータの場合は↓
	//static final String SERVER_URL = "http://10.0.2.2:8080/TestServer";
	
	Context mContext;
	ScreenTransitionEventListner mScreenTransitionEventListner;
	UserInformationWrapper mUserInformationWrapper;
	
	public WebInterface(Context c) {
		mContext = c;
	}
	
	public void setScreenTransitionEventListener(ScreenTransitionEventListner listner) {
		mScreenTransitionEventListner = listner;
	}
	
	public void setUserInformationWrapper(UserInformationWrapper wrapper) {
		mUserInformationWrapper = wrapper;
	}
	
	@JavascriptInterface
	public void sendLoginInformation(String id, String password) {
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(SERVER_URL + "/SPMainServlet");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("user_id", id));
		params.add(new BasicNameValuePair("user_pass", password));

		HttpResponse httpResponse = null;
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			httpResponse = httpClient.execute(httpPost);
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (httpResponse != null) {
			int statusCode = httpResponse.getStatusLine().getStatusCode();
			if (statusCode == HttpStatus.SC_OK) {
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		        try {
					httpResponse.getEntity().writeTo(outputStream);
			        String data = outputStream.toString(); // JSONデータ
					Log.d("ITz_TEST", "GET DATA : " + data);
					JSONObject rootObject = new JSONObject(data);
					String name = rootObject.getString("name");
					Log.d("ITz_TEST", "GET NAME : " + name);
					
					mUserInformationWrapper.setId(id);
					mUserInformationWrapper.setName(name);
					mUserInformationWrapper.setPassword(password);
					mScreenTransitionEventListner.transitionTo("file:///android_asset/home.html");
		        } catch (IOException e) {
					// TODO Auto-generated catch block
		        	Log.d("ITz_TEST", " error1 ");
					e.printStackTrace();
				}  catch (JSONException e) {
					// TODO Auto-generated catch block
					Log.d("ITz_TEST", " error2 ");
					e.printStackTrace();
				}
			} else {
				//error code
				Log.d("", "response status error");
				mScreenTransitionEventListner.transitionTo("file:///android_asset/login.html");
			}
		} else {
			//connection
			Log.d("", "cannot connect");
			mScreenTransitionEventListner.transitionTo("file:///android_asset/login.html");
		}
	}

	@Override
	@JavascriptInterface
	public String getId() {
		return mUserInformationWrapper.getId();
	}

	@Override
	@JavascriptInterface
	public void setId(String id) {
		mUserInformationWrapper.setId(id);
	}

	@Override
	@JavascriptInterface
	public String getName() {
		return mUserInformationWrapper.getName();
	}

	@Override
	@JavascriptInterface
	public void setName(String name) {
		mUserInformationWrapper.setName(name);
	}

	@Override
	@JavascriptInterface
	public String getPassword() {
		return mUserInformationWrapper.getPassword();
	}

	@Override
	@JavascriptInterface
	public void setPassword(String password) {
		mUserInformationWrapper.setPassword(password);
	}
}
