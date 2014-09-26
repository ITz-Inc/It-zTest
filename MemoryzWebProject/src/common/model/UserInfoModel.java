/**クラス名： UserInfoModel.java
 *説明： ユーザ情報格納用モデルです。Android側から送信されたrequestデータをセットしてください
 *作成者:蝦名弘紀
 *作成日：2014/09/23
 *最終更新日:2014/09/23
 *備考:Memoryz Ph1.0新規開発
 */

package common.model;

public class UserInfoModel {

private String userId="";
private String passWord="";
private String userName="";


	public UserInfoModel(){
	}

	public void setUserId(String userId){
			this.userId = userId;
	}

	public String getUserId(){
			return this.userId;
	}

	public void setPassWord(String passWord){
		this.passWord = passWord;
	}

	public String getPassWord(){
		return this.passWord;
	}

	public void setUserName(String userName){
		this.userName = userName;
	}

	public String getUserName(){
		return this.userName;
	}

}
