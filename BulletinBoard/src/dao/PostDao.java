//postsテーブルに新規投稿画面で入力された内容を投稿するためのSQL文の作成
package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.ResultSet;
import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import exception.SQLRuntimeException;
import model.Post;
import model.UserMessage;

public class PostDao {
	
	//新規投稿メソッド
	public void insert(Connection connection, Post post){
		//プリコンパイルされたSQL文を生成
		PreparedStatement ps = null;
		//新規登録フォームで記載された情報をデータベースに格納
		try{
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO posts (");
			sql.append("title");
			sql.append(", text");
			sql.append(", category");
			sql.append(", timed_at");
			sql.append(", updated_at");
			sql.append(", user_id");
			sql.append(")VALUES(");
			sql.append("?");//title
			sql.append(", ?");//text
			sql.append(", ?");//category
			sql.append(",CURRENT_TIMESTAMP");//timed_at		
			sql.append(",CURRENT_TIMESTAMP");//updated_at
			sql.append(", ?");//user_id
			sql.append(");");

			//コネクションに使うプリコンパイルされたSQL文をPreparedStatement型のpsインスタンスにString型で格納
			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, post.getTitle());			//ここの番号は昇順で？に割り振られる
			ps.setString(2, post.getText());
			ps.setString(3, post.getCategory());
			ps.setInt(4, post.getUser_id());
			
			
			//SQL文を実行
			ps.executeUpdate();
			//逆にデータを取得する場合はexecuteQueryを使う

		}catch(SQLException e){
			throw new SQLRuntimeException(e);
		}finally{
			close(ps);
		}
	}
	
	//投稿削除メソッド
	public void delete(Connection connection, int id, int user_id){
		PreparedStatement ps = null;
		try{
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM posts WHERE id = ? AND user_id = ?");
			
			ps = connection.prepareStatement(sql.toString());
			
			ps.setInt(1, id);
			ps.setInt(2, user_id);
			
			ps.executeUpdate();
		}catch(SQLException e){
			throw new SQLRuntimeException(e);
		}finally{
			close(ps);
		}
	}
	
	//投稿に対するコメント削除メソッド
	public void commentDelete(Connection connection, int id){
		PreparedStatement ps = null;
		try{
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM comments WHERE post_id = ?");
				
			ps = connection.prepareStatement(sql.toString());
				
			ps.setInt(1, id);
				
			ps.executeUpdate();
		}catch(SQLException e){
			throw new SQLRuntimeException(e);
		}finally{
			close(ps);
		}
	}
	
	//記事をソートするためのメソッド(日付のみ)
	public List<UserMessage> postSortDate(Connection connection, String oldDate, String latestDate){
		PreparedStatement ps = null;
		try{
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM users_posts WHERE timed_at <= ? AND ? <= timed_at ");
			//DESCはデータのソート機能
			sql.append("ORDER BY timed_at DESC");
				
			ps = connection.prepareStatement(sql.toString());
				
			ps.setString(1, latestDate);
			ps.setString(2, oldDate);
				
			//データの取り出しを実行
			ResultSet rs = ps.executeQuery();
			List<UserMessage> ret = toPostSort(rs);
			return ret;
		}catch(SQLException e){
			throw new SQLRuntimeException(e);
		}finally{
			close(ps);
		}
	}
	//記事をソートするためのメソッド
	public List<UserMessage> postSort(Connection connection,String category, String oldDate, String latestDate){
		PreparedStatement ps = null;
		try{
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM users_posts WHERE (timed_at <= ? AND ? <= timed_at) AND category = ? ");
			//DESCはデータのソート機能
			sql.append("ORDER BY timed_at DESC");
					
			ps = connection.prepareStatement(sql.toString());
					
			ps.setString(1, latestDate);
			ps.setString(2, oldDate);
			ps.setString(3, category);
					
			//データの取り出しを実行
			ResultSet rs = ps.executeQuery();
			List<UserMessage> ret = toPostSort(rs);
			return ret;
		}catch(SQLException e){
			throw new SQLRuntimeException(e);
		}finally{
			close(ps);
		}
	}
	
	private List<UserMessage> toPostSort(ResultSet rs) 
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
	
	//一番古い記事の投稿日時を取得
	public UserMessage oldDate(Connection connection){
		PreparedStatement ps = null;
		try{
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT timed_at FROM users_posts ");
			//DESCはデータのソート機能
			sql.append("ORDER BY timed_at ASC LIMIT 1");
				
			ps = connection.prepareStatement(sql.toString());
				
			//データの取り出しを実行
			ResultSet rs = ps.executeQuery();
			List<UserMessage> oldDateList = toOldDate(rs);
			//空のとき(ひとつも投稿がない)時
			if (oldDateList.isEmpty()){
				return null;
			}else{
				return oldDateList.get(0);
			}
		}catch(SQLException e){
			throw new SQLRuntimeException(e);
		}finally{
			close(ps);
		}
	}
	private List<UserMessage> toOldDate(ResultSet rs) 
			throws SQLException{
		List<UserMessage> ret = new ArrayList<UserMessage>();
		try{
			while (rs.next()){
				Timestamp timed_at = rs.getTimestamp("timed_at");
				UserMessage message = new UserMessage();
				message.setTimed_at(timed_at);
				
				ret.add(message);
			}
			return ret;
		}finally{
			close(rs);
		}
	}
}
