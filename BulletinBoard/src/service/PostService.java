package service;

import static utils.CloseableUtil.*;
import static utils.DBUtil.*;

import java.sql.Connection;

import dao.PostDao;
import model.Post;

public class PostService {
	//registerメソッドの生成
	public void register(Post post){
		Connection connection = null;
		try{
			//connectionにDBUtilのgetConnectionメソッドの戻り値(コネクション)を代入
			connection = getConnection();
			//postDaoクラスのインスタンスを生成
			PostDao postDao = new PostDao();
			//postDaoインスタンスにコネクションとuser(Beans)を挿入
			postDao.insert(connection, post);
			//上の一連の処理にエラーがひとつも無ければコミット
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

}
