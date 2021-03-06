//ユーザー管理に関するservice
package service;

import static utils.CloseableUtil.*;
import static utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import dao.UserControlDao;
import model.UserControl;

public class UserControlService {
	
	//戻り値がUserControl型のリストであるgetControlメソッド
	//機能としてはUserControl型(Beans)のretリストにuser_controlテーブルから取り出したデータを格納
	public List<UserControl> getControl(){
		
		 Connection connection = null;
		 try{
			 connection = getConnection();
			 
			 //UserControlDaoクラスのインスタンスを生成
			 UserControlDao controlDao = new UserControlDao();
			 List<UserControl> ret = controlDao.getUserControl(connection);
			 
			 //データを取り出すだけの場合でも確定という意味合いでコミットが必要！
			 commit(connection);
			 
			 return ret;
		 }catch(RuntimeException e){
			 rollback(connection);
			 throw e;
		 }catch(Error e){
			 rollback(connection);
			 throw e;
		 }finally{
			 close(connection);
		 }
	 }
	
	//特定のユーザー情報を取得するためのコード
	public List<UserControl> personalData(int primaryID){
				 
		Connection connection = null;
		try{
			connection = getConnection();
					 
			UserControlDao controlDao = new UserControlDao();
			//Daoの戻り値であるretをこちらで同型同名で宣言し、そこに格納
			List<UserControl> ret = controlDao.personalDataDao(connection, primaryID);	
					 
			commit(connection);
			
			//同じようにサーブレットにUserControl型のretリストをリターン
			return ret;
		}catch(RuntimeException e){
			rollback(connection);
			throw e;
		}catch(Error e){
			rollback(connection);
			throw e;
		}finally{
			close(connection);
		}
	}
	

}
