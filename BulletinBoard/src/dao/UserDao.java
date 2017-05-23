package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import exception.SQLRuntimeException;
import model.User;

public class UserDao {
	
	public void insert(Connection connection, User user){
		PreparedStatement ps = null;
		//新規登録フォームで記載された情報をデータベースに格納
		try{
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO user (");
			sql.append(", login_id");
			sql.append(", password");
			sql.append(", name");
			sql.append(", branch_id");
			sql.append(", department_id");
			sql.append(", is_stopped");
			sql.append(", timed_at");
			sql.append(", updated_at");
			sql.append(")VALUES(");
			sql.append(", ?");//login_id
			sql.append(", ?");//password
			sql.append(", ?");//name
			sql.append(", ?");//branch_id
			sql.append(", ?");//department_id
			sql.append(", ?");//is_stopped
			sql.append(",CURRENT_TIMESTAMP");//timed_at
			sql.append(",CURRENT_TIMESTAMP");//updated_at
			sql.append(")");
			
			ps = connection.prepareStatement(sql.toString());
			
			ps.setString(1, user.getLogin_id());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getName());
			ps.setInt(4, user.getBranch_id());
			ps.setInt(5, user.getDepartment_id());
			ps.setInt(6, 0);
			
			ps.executeUpdate();
			
		}catch(SQLException e){
			throw new SQLRuntimeException(e);
		}finally{
			close(ps);
		}
	}

}
