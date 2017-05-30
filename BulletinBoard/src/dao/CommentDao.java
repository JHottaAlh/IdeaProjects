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
import model.Comment;

public class CommentDao {
	
	//コメント新規投稿メソッド
	public void insert(Connection connection, Comment comment){
			
		PreparedStatement ps = null;
		try{
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO comments (");
			sql.append("text");
			sql.append(", timed_at");
			sql.append(", updated_at");
			sql.append(", post_id");
			sql.append(", user_id");
			sql.append(")VALUES(");
			sql.append("?");					//1.text
			sql.append(",CURRENT_TIMESTAMP");	//timed_at
			sql.append(",CURRENT_TIMESTAMP");	//updated_at
			sql.append(", ?");					//2.post_id		
			sql.append(", ?");					//3.user_id
			sql.append(");");

			//コネクションに使うプリコンパイルされたSQL文をPreparedStatement型のpsインスタンスにString型で格納
			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, comment.getText());			//1.text
			ps.setInt(2, comment.getPost_id());			//2.post_id
			ps.setInt(3, comment.getUser_id());			//3.user_id
				
				
			//SQL文を実行
			ps.executeUpdate();
			//逆にデータを取得する場合はexecuteQueryを使う

		}catch(SQLException e){
			throw new SQLRuntimeException(e);
		}finally{
			close(ps);
		}
	}
	
	//コメント取得メソッド
	public List<Comment> getComment(Connection connection){
		PreparedStatement ps = null;
		try{
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM comment_list ");
			sql.append("ORDER BY timed_at DESC");
			
			ps = connection.prepareStatement(sql.toString());
			
			ResultSet rs = ps.executeQuery();
			List<Comment> ret = toCommentList(rs);
			return ret;
		}catch(SQLException e){
			throw new SQLRuntimeException(e);
		}finally{
			close(ps);
		}
	}
	
	private List<Comment> toCommentList(ResultSet rs) 
			throws SQLException{
		List<Comment> ret = new ArrayList<Comment>();
		try{
			while (rs.next()){
				int id = rs.getInt("id");
				String text = rs.getString("text");
				Timestamp timed_at = rs.getTimestamp("timed_at");
				Timestamp updated_at = rs.getTimestamp("updated_at");
				int post_id = rs.getInt("post_id");
				int user_id = rs.getInt("user_id");
				String name = rs.getString("name");
				
				
				Comment comment = new Comment();
				comment.setId(id);
				comment.setText(text);
				comment.setTimed_at(timed_at);
				comment.setUpdated_at(updated_at);
				comment.setPost_id(post_id);
				comment.setUser_id(user_id);
				comment.setName(name);
				
				ret.add(comment);
			}
			return ret;
		}finally{
			close(rs);
		}
	}
	
	//投稿削除メソッド
	public void delete(Connection connection, int id, int user_id){
		PreparedStatement ps = null;
		try{
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM comments WHERE id = ? AND user_id = ?");
				
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
}
