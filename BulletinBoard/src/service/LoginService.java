package service;

import static utils.CloseableUtil.*;
import static utils.DBUtil.*;

import java.sql.Connection;

import dao.UserDao;
import model.User;
import utils.CipherUtil;

public class LoginService {
	//戻り値User型のloginメソッド(引数はString型のid、password)
	public User login(String id, String password){
		Connection connection = null;
		try{
			//データベースのコネクション（パスのようなもの）を取得
			connection = getConnection();
			
			UserDao userDao = new UserDao();
			//受け取ったパスワードを暗号化
			String encPassword = CipherUtil.encrypt(password);
			User user = userDao.getUser(connection, id, encPassword);
			
			commit(connection);
			return user;
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
