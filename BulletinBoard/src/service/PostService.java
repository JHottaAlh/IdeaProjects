package service;

import static utils.CloseableUtil.*;
import static utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import dao.PostDao;
import dao.UserMessageDao;
import model.Post;
import model.UserMessage;

public class PostService {
	//新規投稿のコミットをするためのメソッド
	public void register(Post post){
		Connection connection = null;
		try{
			//connectionにDBUtilのgetConnectionメソッドの戻り値(コネクション)を代入
			//DBUtilのメソッドはstaticなメソッドなのでメソッドを宣言するだけで利用出来る
			connection = getConnection();
			//postDaoクラスのインスタンスを生成
			PostDao postDao = new PostDao();
			//postDaoインスタンスにコネクションとpost(Beans)を挿入
			postDao.insert(connection, post);
			//上の一連の処理にエラー(入力不備)がひとつも無ければコミット
			//DBUtilクラスのcommitメソッド
			commit(connection);

			//ひとつでもエラーがあればロールバック
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
	
	//メッセージ一覧を取得するためのコード
	private static final int LIMIT_NUM = 1000;
	 public List<UserMessage> getMessage(){
		 
		 Connection connection = null;
		 try{
			 connection = getConnection();
			 
			 UserMessageDao messageDao = new UserMessageDao();
			 List<UserMessage> ret = messageDao.getUserMessages(connection, LIMIT_NUM);
			 
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

}
