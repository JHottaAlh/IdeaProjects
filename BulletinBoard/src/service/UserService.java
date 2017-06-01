package service;

import static utils.CloseableUtil.*;
import static utils.DBUtil.*;

import java.sql.Connection;

import dao.UserDao;
import model.User;
//パスワードを暗号化するユーティリティ
import utils.CipherUtil;

public class UserService {
	//registerメソッドの生成
	public void register(User user){
		Connection connection = null;
		try{
			//connectionにDBUtilのgetConnectionメソッドの戻り値(コネクション)を代入
			connection = getConnection();

			//userBeansに格納されたPasswordを暗号化して再度userBeansのPasswordに格納
			String encPassword = CipherUtil.encrypt(user.getPassword());
			user.setPassword(encPassword);

			//UserDaoクラスのインスタンスを生成
			UserDao userDao = new UserDao();
			//userDaoインスタンスにコネクションとuser(Beans)を挿入
			userDao.insert(connection, user);

			//上の一連の処理にエラーがひとつも無ければコミット
			//DBUtilクラスのcommitメソッド
			commit(connection);

		//ひとつでもエラーがあればロールバック
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
	
	//ユーザー情報のアップデート処理をするメソッド
	public void update(User user){
		Connection connection = null;
		try{
			connection = getConnection();
			
			//元のパスワードの場合ここで暗号化はNG
			
			UserDao userDao = new UserDao();
			userDao.update(connection, user);
			
			commit(connection);
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
	
	//ログインIDの重複チェック用メソッド
	public boolean idCheck(String login_id, int id){
		Connection connection = null;
		try{
			connection = getConnection();
			
			UserDao userDao = new UserDao();
			int user = userDao.getID(connection, login_id);
			
			commit(connection);
			
			//未登録のユーザーIDであれば通す
			if(user == 0){
				return true;
			//ユーザーのプライマリキーと変更したいユーザーのプライマリキーが一致していれば通す
			}else if(user == id){
				return true;
			//その他、重複しているならfalse
			}else{
				return false;
			}
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
	
	//is_stopped切り替えメソッド
	public void isStopped(int id, int is_stopped){
		Connection connection = null;
		try{
			connection = getConnection();
			
			UserDao userDao = new UserDao();
			userDao.isStopped(connection, id, is_stopped);
			
			commit(connection);
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
	
	//ユーザー削除メソッド
	public void userDelete(int id){
		Connection connection = null;
		try{
			connection = getConnection();
			
			UserDao userDao = new UserDao();
			userDao.userDelete(connection, id);
			
			commit(connection);
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
