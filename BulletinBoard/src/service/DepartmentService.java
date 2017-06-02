package service;

import static utils.CloseableUtil.*;
import static utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import dao.DepartmentDao;
import model.Department;

public class DepartmentService {
	
	public List<Department> getDepartment(){
		Connection connection = null;
		try{
			connection = getConnection();

			DepartmentDao departmentDao = new DepartmentDao();
			List<Department> departmentList = departmentDao.getDepartment(connection);


			commit(connection);
			return departmentList;

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