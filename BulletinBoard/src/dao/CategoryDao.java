package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exception.SQLRuntimeException;
import model.Category;

public class CategoryDao {
	//カテゴリ一覧を取り出すメソッド
	public List<Category> getCategory(Connection connection){
		PreparedStatement ps = null;
		try{
			String sql = "SELECT * FROM categories";
			ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			List<Category> categoryList = toCategoryList(rs);
			if (categoryList.isEmpty()){
				return null;
			}else{
				return categoryList;
			}
		}catch(SQLException e){
			throw new SQLRuntimeException(e);
		}finally{
			close(ps);
		}
	}

	private List<Category> toCategoryList(ResultSet rs)
			throws SQLException{

		List<Category> ret = new ArrayList<Category>();
		try{
			while (rs.next()){
				int id = rs.getInt("id");
				String category = rs.getString("category");

				Category categories = new Category();
				categories.setId(id);
				categories.setCategory(category);

				ret.add(categories);
			}
			return ret;
		}finally{
			close(rs);
		}
	}

}
