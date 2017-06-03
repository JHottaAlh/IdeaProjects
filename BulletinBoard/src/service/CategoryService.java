package service;

import static utils.CloseableUtil.*;
import static utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import dao.CategoryDao;
import model.Category;

public class CategoryService {
	public List<Category> getCategory(){
		Connection connection = null;
		try{
			connection = getConnection();

			CategoryDao categoryDao = new CategoryDao();
			List<Category> categoryList = categoryDao.getCategory(connection);


			commit(connection);
			return categoryList;

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
