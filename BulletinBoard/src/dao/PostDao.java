//postsテーブルに新規投稿画面で入力された内容を投稿するためのSQL文の作成
package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
//import java.sql.ResultSet;
import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;

import exception.SQLRuntimeException;
import model.Post;

public class PostDao {
	
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
			ps.setInt(3, post.getCategory());
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

}
