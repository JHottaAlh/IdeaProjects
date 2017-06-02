package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exception.SQLRuntimeException;
import model.Department;

public class DepartmentDao {
	//部署・役職情報を取り出すメソッド
		public List<Department> getDepartment(Connection connection){
			PreparedStatement ps = null;
			try{
				String sql = "SELECT * FROM departments";
				ps = connection.prepareStatement(sql);
				
				ResultSet rs = ps.executeQuery();
				List<Department> departmentList = toDepartmentList(rs);
				if (departmentList.isEmpty()){
					return null;
				}else{
					return departmentList;
				}
			}catch(SQLException e){
				throw new SQLRuntimeException(e);
			}finally{
				close(ps);
			}
		}
		
		//特定の部署・役職情報を取り出すメソッド
		public Department getDepartmentID(Connection connection, int id){
			PreparedStatement ps = null;
			try{
				String sql = "SELECT * FROM departments WHERE id = ?";
				ps = connection.prepareStatement(sql);
				ps.setInt(1, id);
				
				ResultSet rs = ps.executeQuery();
				List<Department> departmentList = toDepartmentList(rs);
				if (departmentList.isEmpty()){
					return null;
				}else{
					return departmentList.get(0);
				}
			}catch(SQLException e){
				throw new SQLRuntimeException(e);
			}finally{
				close(ps);
			}
		}
		
		private List<Department> toDepartmentList(ResultSet rs) 
				throws SQLException{
			
			List<Department> ret = new ArrayList<Department>();
			try{
				while (rs.next()){
					int id = rs.getInt("id");
					String name = rs.getString("name");
					
					Department department = new Department();
					department.setId(id);
					department.setName(name);
					
					ret.add(department);
				}
				return ret;
			}finally{
				close(rs);
			}
		}

}
