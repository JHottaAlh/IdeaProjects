/*package service;

import java.sql.Connection;

import model.User;

public class LoginService {
	public User login(String id, String password){
		Connection connection = null;
		try{
			connection = getConnection();
			
			UserDao userDao = new UserDao();
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

}*/
