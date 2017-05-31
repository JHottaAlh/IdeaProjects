package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import exception.SQLRuntimeException;
import model.UserMessage;

public class UserMessageDao {
	
	public List<UserMessage> getUserMessages(Connection connection, int num){
		PreparedStatement ps = null;
		try{
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM users_posts ");
			//DESCはデータのソート機能
			sql.append("ORDER BY timed_at DESC limit " + num);
			
			ps = connection.prepareStatement(sql.toString());
			
			//データの取り出しを実行
			ResultSet rs = ps.executeQuery();
			List<UserMessage> ret = toUserMessageList(rs);
			return ret;
		}catch(SQLException e){
			throw new SQLRuntimeException(e);
		}finally{
			close(ps);
		}
	}
	
	private List<UserMessage> toUserMessageList(ResultSet rs) 
			throws SQLException{
		List<UserMessage> ret = new ArrayList<UserMessage>();
		try{
			while (rs.next()){
				int id = rs.getInt("id");
				int user_id = rs.getInt("user_id");
				String title = rs.getString("title");
				String category = rs.getString("category");
				String text = rs.getString("text");
				Timestamp timed_at = rs.getTimestamp("timed_at");
				Timestamp updated_at = rs.getTimestamp("updated_at");
				String name = rs.getString("name");
				int branch_id = rs.getInt("branch_id");
				int department_id = rs.getInt("department_id");
				
				UserMessage message = new UserMessage();
				message.setId(id);
				message.setUser_id(user_id);
				message.setTitle(title);
				message.setCategory(category);
				message.setText(text);
				message.setTimed_at(timed_at);
				message.setUpdated_at(updated_at);
				message.setName(name);
				message.setBranch_id(branch_id);
				message.setDepartment_id(department_id);
				
				ret.add(message);
			}
			return ret;
		}finally{
			close(rs);
		}
	}

}
