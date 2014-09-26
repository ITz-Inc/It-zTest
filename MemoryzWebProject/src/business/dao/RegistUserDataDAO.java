/**クラス名： RegistUserDataDAO.java
 *説明： ユーザ登録用DAO
 *作成者:蝦名弘紀
 *作成日：2014/09/23
 *最終更新日:2014/09/23
 *備考:Memoryz Ph1.0新規開発
 */

package business.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import common.model.UserInfoModel;
import common.util.DBConnection;

public class RegistUserDataDAO {

	public RegistUserDataDAO(){

	}

/*	public boolean RegistUserData(DBConnection connection,UserInfoModel userInfoModel){

		Statement statement = connection.getStatement();

		String sql = "INSERT INTO `memoryztest`.`user_data` (`userName`, `password`, `userId`) VALUES ('" + userInfoModel.getUserName() + "', '" + userInfoModel.getPassWord() + "' , '" + userInfoModel.getUserId() + "')";

		boolean registFlag = false;
		try {
			statement.execute(sql);
			registFlag = true;
		} catch (SQLException e) {
			e.printStackTrace();
			registFlag = false;
		} finally {
			connection.close();
		}
		return registFlag;

	}
*/

/*	public boolean SearchUserData(DBConnection connection,UserInfoModel userInfoModel) {

		Statement statement = connection.getStatement();

		String sql = "SELECT userId FROM user_data";

		boolean searchFlag = false;
		try {
			ResultSet rs = statement.executeQuery(sql);

			while(rs.next()){
				String ID = rs.getString("userId");
				String name = rs.getString("userName");
				System.out.println("登録済みID: " + ID);
				System.out.println("新規登録ID: " + userInfoModel.getUserId());

				if (ID == userInfoModel.getUserId()) {
					if (name != userInfoModel.getUserName()) {
						System.out.println("既にIDが使われています．");
					} else if(name == userInfoModel.getUserName()) {
						System.out.println("パスワードが間違っています．");
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			searchFlag = false;
		} finally {
			connection.close();
		}
		return searchFlag;
	}*/

	public boolean RegistUserData(DBConnection connection,UserInfoModel userInfoModel) {

		Statement statement = connection.getStatement();

		String sql = "SELECT userId, userName, password FROM user_data";

		boolean searchFlag = false;
		boolean registFlag = false;
		try {
			ResultSet rs = statement.executeQuery(sql);

			while(rs.next()){
				String ID = rs.getString("userId");
				String name = rs.getString("userName");
				String password = rs.getString("password");
				System.out.println("登録済みID: " + ID);
				System.out.println("登録済みName: " + name);
				System.out.println("登録済みPASS: " + password);

				System.out.println("新規登録ID: " + userInfoModel.getUserId());
				System.out.println("新規登録Name: " + userInfoModel.getUserName());
				System.out.println("新規登録PASS: " + userInfoModel.getPassWord());

				if (ID.equals(userInfoModel.getUserId())) {
					if (!name.equals(userInfoModel.getUserName())) {
						System.out.println("既にIDが使われています．");
						connection.close();
						return searchFlag = true;
					} else if(name.equals(userInfoModel.getUserName()) && !password.equals(userInfoModel.getPassWord())) {
						System.out.println("パスワードが間違っています．");
						connection.close();
						return searchFlag = true;
					} else if(name.equals(userInfoModel.getUserName()) && password.equals(userInfoModel.getPassWord())) {
						System.out.println("ログインする．");
						connection.close();
						return searchFlag = true;
					}
				}
			}
			System.out.println("ユーザ登録を行う．");
			sql = "INSERT INTO `memoryztest`.`user_data` (`userName`, `password`, `userId`) VALUES ('" + userInfoModel.getUserName() + "', '" + userInfoModel.getPassWord() + "' , '" + userInfoModel.getUserId() + "')";
			statement.execute(sql);
			registFlag = true;


		} catch (SQLException e) {
			e.printStackTrace();
			registFlag = false;
		} finally {
			connection.close();
		}
		return registFlag;
	}
}
