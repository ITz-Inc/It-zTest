/**クラス名： RegistUserDataController.java
 *説明： ユーザ登録用コントローラ
 *作成者:蝦名弘紀
 *作成日：2014/09/23
 *最終更新日:2014/09/23
 *備考:Memoryz Ph1.0新規開発
 */


package business.service;

import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.http.HttpServletResponse;

import business.dao.RegistUserDataDAO;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import common.model.UserInfoModel;
import common.util.DBConnection;

public class RegistUserDataController implements IBaseController{

	String userName = "";
	String userId = "";
	String passWord = "";



	@Override
	public void excute(String json, HttpServletResponse response) throws Exception {

		JsonFactory factory = new JsonFactory();
		JsonParser parser = factory.createParser(json);

		//JSONのパース処理
		while (parser.nextToken() != JsonToken.END_OBJECT) {
		    String name = parser.getCurrentName();
		    if(name != null) {
		        parser.nextToken();
		        if(name.equals("name")) {
		            //ユーザ名
		            this.setUserName(parser.getText());
		        }else if(name.equals("id")) {
		            //ユーザID
		            this.setUserId(parser.getText());
		        }else if(name.equals("password")) {
		            //パスワード
		            this.setPassWord(parser.getText());
		        }else{
		            //想定外のものは無視して次へ
		            parser.skipChildren();
		        }
		    }
		}

		//Fail First Policy by ebina
		if(userId ==null || passWord==null){
			return;
		}

		try{
			UserInfoModel userInfoModel = new UserInfoModel();
			userInfoModel.setUserName(userName);
			userInfoModel.setUserId(userId);
			userInfoModel.setPassWord(passWord);

			System.out.println(userInfoModel.getUserId());
			System.out.println(userInfoModel.getUserName());
			System.out.println(userInfoModel.getPassWord());

			//DBコネクション確立
			DBConnection dbConnection = new DBConnection();
			Connection conn = dbConnection.connectDB();

			if(conn !=null){
				RegistUserDataDAO registUserDataDAO = new RegistUserDataDAO();

//				//検索処理
//				registUserDataDAO.SearchUserData(dbConnection, userInfoModel);

				//登録処理
				registUserDataDAO.RegistUserData(dbConnection,userInfoModel);
			}
			//TODO 仮
			 String responseJsonString = "";
			 int responseStatus = HttpServletResponse.SC_NO_CONTENT;

			responseJsonString = "{\"name\":\"たまちゃん\"}";
    		responseStatus = HttpServletResponse.SC_OK;

			response.setStatus(responseStatus);
			PrintWriter out = response.getWriter();
			out.print(responseJsonString);
		}catch(Exception e){
			e.printStackTrace();
		}


	}

	public void setUserName(String name) {
		userName = name;
	}

	public void setUserId(String id) {
		userId = id;
	}

	public void setPassWord(String password) {
		passWord = password;
	}
}
