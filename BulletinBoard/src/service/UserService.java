package service;

import static utils.CloseableUtil.*;
import static utils.DBUtil.*;

import java.sql.Connection;

import dao.UserDao;
import model.User;
import utils.CipherUtil;

public class UserService {
	public void register(User user){
		Connection connection = null;
		try{
			connection = getConnection();
			
			//userBeansに格納されたPasswordを暗号化して再度userBeansのPasswordに格納
			String encPassword = CipherUtil.encrypt(user.getPassword());
			user.setPassword(encPassword);
			
			
			UserDao userDao = new UserDao();
			userDao.insert(connection, user);
			
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
