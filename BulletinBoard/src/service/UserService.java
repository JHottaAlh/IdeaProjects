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

}
