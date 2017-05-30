package service;

import static utils.CloseableUtil.*;
import static utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import dao.CommentDao;
import model.Comment;

public class CommentService {
	
	//コメント新規投稿メソッド
	public void register(Comment comment){
		Connection connection = null;
		try{
			
			connection = getConnection();
			
			CommentDao commentDao = new CommentDao();
			commentDao.insert(connection, comment);
			
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
	
	//コメント取得メソッド
	public List<Comment> getComment(){
		 
		Connection connection = null;
		try{
			connection = getConnection();
			 
			CommentDao commentDao = new CommentDao();
			List<Comment> ret = commentDao.getComment(connection);
			 
			commit(connection);
			 
			return ret;
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
	
	//投稿削除用のメソッド
	public void delete(int id, int user_id){
			 
		Connection connection = null;
		try{
			connection = getConnection();
				 
			CommentDao commentDao = new CommentDao();
			commentDao.delete(connection, id, user_id);
				 
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
