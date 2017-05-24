package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exception.SQLRuntimeException;
import model.User;

public class UserDao {
	
	//戻り値User(Beans)型のgetUserメソッド
	//
	public User getUser(Connection connection, String id, String password){
		PreparedStatement ps = null;
		try{
			//データベースからデータを取得するSQL文
			String sql = "SELECT * FROM users WHERE login_id = ? AND password = ?";
			ps = connection.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, password);
			
			//psでデータベースから取り出した値をrsに代入
			ResultSet rs = ps.executeQuery();
			List<User> userList = toUserList(rs);
			if (userList.isEmpty() == true){
				return null;
			}else if(2 <= userList.size()){
				throw new IllegalStateException("2 <= userList.size()");
			}else{
				return userList.get(0);
			}
		}catch(SQLException e){
			throw new SQLRuntimeException(e);
		}finally{
			close(ps);
		}
	}
	
	private List<User> toUserList(ResultSet rs) throws SQLException{
		
		List<User> ret = new ArrayList<User>();
		try{
			while (rs.next()){
				int id = rs.getInt("id");
				String login_id = rs.getString("login_id");
				String password = rs.getString("password");
				String name = rs.getString("name");
				int branch_id = rs.getInt("branch_id");
				int department_id = rs.getInt("department_id");
				
				User user = new User();
				user.setId(id);
				user.setLogin_id(login_id);
				user.setPassword(password);
				user.setName(name);
				user.setBranch_id(branch_id);
				user.setDepartment_id(department_id);
				
				ret.add(user);
			}
			return ret;
		}finally{
			close(rs);
		}
	}
	

	public void insert(Connection connection, User user){
		//プリコンパイルされたSQL文を生成
		PreparedStatement ps = null;
		//新規登録フォームで記載された情報をデータベースに格納
		try{
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO users (");
			sql.append("login_id");
			sql.append(", password");
			sql.append(", name");
			sql.append(", branch_id");
			sql.append(", department_id");
			sql.append(", is_stopped");
			sql.append(", timed_at");
			sql.append(", updated_at");
			sql.append(")VALUES(");
			sql.append("?");//login_id
			sql.append(", ?");//password
			sql.append(", ?");//name
			sql.append(", ?");//branch_id
			sql.append(", ?");//department_id
			sql.append(", ?");//is_stopped
			sql.append(",CURRENT_TIMESTAMP");//timed_at		
			sql.append(",CURRENT_TIMESTAMP");//updated_at
			sql.append(");");

			//コネクションに使うプリコンパイルされたSQL文をPreparedStatement型のpsインスタンスにString型で格納
			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, user.getLogin_id());			//ここの番号は昇順で？に割り振られる
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getName());
			ps.setInt(4, user.getBranch_id());
			ps.setInt(5, user.getDepartment_id());
			//is_stoppedはデフォルトで0(アカウント利用可能)を設定する
			ps.setInt(6, 0);
			
			
			//SQL文を実行
			ps.executeUpdate();
			//逆にデータを取得する場合はexecuteQueryを使う

		}catch(SQLException e){
			throw new SQLRuntimeException(e);
		}finally{
			close(ps);
		}
	}

}
