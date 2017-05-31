//update保留案件有(更新時間での編集重複防止機能)

package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exception.NoRowsUpdatedRuntimeException;
import exception.SQLRuntimeException;
import model.User;

public class UserDao {
	
	//戻り値User(Beans)型のgetUserメソッド
	//usersテーブルからログインIDとパスワードを参照し、レコード（該当するユーザー情報）が存在するかチェック
	public User getUser(Connection connection, String login_id, String password){
		PreparedStatement ps = null;
		try{
			//データベースからデータを取得するSQL文
			String sql = "SELECT * FROM users WHERE login_id = ? AND password = ?";
			ps = connection.prepareStatement(sql);
			ps.setString(1, login_id);
			ps.setString(2, password);
			
			//psでデータベースから取り出した値をrsに代入
			ResultSet rs = ps.executeQuery();
			List<User> userList = toUserList(rs);
			if (userList.isEmpty()){
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
	
	private List<User> toUserList(ResultSet rs) 
			throws SQLException{
		
		List<User> ret = new ArrayList<User>();
		try{
			while (rs.next()){
				int id = rs.getInt("id");
				String login_id = rs.getString("login_id");
				String password = rs.getString("password");
				String name = rs.getString("name");
				int branch_id = rs.getInt("branch_id");
				int department_id = rs.getInt("department_id");
				int is_stopped = rs.getInt("is_stopped");
				
				User user = new User();
				user.setId(id);
				user.setLogin_id(login_id);
				user.setPassword(password);
				user.setName(name);
				user.setBranch_id(branch_id);
				user.setDepartment_id(department_id);
				user.setIs_stopped(is_stopped);
				
				ret.add(user);
			}
			return ret;
		}finally{
			close(rs);
		}
	}
	
	//ユーザー情報のログインIDとプライマリキーを取り出す
	public int getID(Connection connection, String login_id){
		PreparedStatement ps = null;
		try{
			//データベースからデータを取得するSQL文
			String sql = "SELECT id, login_id FROM users WHERE login_id = ? ";
			ps = connection.prepareStatement(sql);
			ps.setString(1, login_id);
			
			//psでデータベースから取り出した値をrsに代入
			ResultSet rs = ps.executeQuery();
			
			User user = toGetID(rs);
			int userKey = user.getId();
			//ログインIDが重複していなかったら
			if(user.getLogin_id() == null){
				return 0;
			}else{
				//重複していたら重複していたユーザーのプライマリキーを送る
				return userKey;
			}
		}catch(SQLException e){
			throw new SQLRuntimeException(e);
		}finally{
			close(ps);
		}
	}
	private User toGetID(ResultSet rs) 
			throws SQLException{
		
		User userKey = new User();
		try{
			while (rs.next()){
				int id = rs.getInt("id");
				String login_id = rs.getString("login_id");

				userKey.setId(id);
				userKey.setLogin_id(login_id);
				return userKey;	
			}
			return userKey;
		}finally{
			close(rs);
		}
	}
	
	//ユーザー登録のためのSQL文作成
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
	
	//ユーザー情報を変更するためのDao
	public void update(Connection connection, User user){
		
		PreparedStatement ps = null;
		try{
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE users SET");
			sql.append(" login_id = ?");	//1.login_id
			sql.append(", password = ?");	//2.password
			sql.append(", name = ?");		//3.name
			sql.append(", branch_id = ?");		//4.branch_id
			sql.append(", department_id = ?");	//5.department_id
			sql.append(", updated_at = CURRENT_TIMESTAMP");
			sql.append(" WHERE");
			sql.append(" id = ?");			//6.id
			//sql.append(" AND");
			//sql.append(" updated_at = ?");	//7.updated_at
			
			ps = connection.prepareStatement(sql.toString());
			
			ps.setString(1, user.getLogin_id());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getName());
			ps.setInt(4, user.getBranch_id());
			ps.setInt(5, user.getDepartment_id());
			ps.setInt(6, user.getId());
			//ps.setTimestamp(7, new Timestamp(user.getUpdated_at().getTime));
			
			int count = ps.executeUpdate();
			if(count == 0){
				throw new NoRowsUpdatedRuntimeException();
			}
		}catch(SQLException e){
			throw new SQLRuntimeException(e);
		}finally{
			close(ps);
		}
	}
	
	//is_stoppedを切り替えるためのDao
	public void isStopped(Connection connection, int id, int is_stopped){
		
		PreparedStatement ps = null;
		try{
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE users SET");
			sql.append(" is_stopped = ?");	//1.is_stopped
			sql.append(" ,updated_at = CURRENT_TIMESTAMP");
			sql.append(" WHERE");
			sql.append(" id = ?");			//2.id
			
			ps = connection.prepareStatement(sql.toString());
			
			//is_stoppedが0なら1に、1なら0にアップデートする
			if(is_stopped == 0){
				ps.setInt(1, 1);
			}
			if(is_stopped == 1){
				ps.setInt(1, 0);
			}
			ps.setInt(2, id);
			
			int count = ps.executeUpdate();
			if(count == 0){
				throw new NoRowsUpdatedRuntimeException();
			}
			
		}catch(SQLException e){
			throw new SQLRuntimeException(e);
		}finally{
			close(ps);
		}
	}
	
	//ユーザー削除メソッド
	public void userDelete(Connection connection, int id){
		
		PreparedStatement ps = null;
		try{
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM users WHERE id = ?");
				
			ps = connection.prepareStatement(sql.toString());
				
			ps.setInt(1, id);
			
			ps.executeUpdate();
		}catch(SQLException e){
			throw new SQLRuntimeException(e);
		}finally{
			close(ps);
		}
	}
}
