package service;

import static utils.CloseableUtil.*;
import static utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import dao.BranchDao;
import model.Branch;

public class BranchService {
	
	public List<Branch> getBranch(){
		Connection connection = null;
		try{
			connection = getConnection();

			BranchDao branchDao = new BranchDao();
			List<Branch> branchList = branchDao.getBranch(connection);


			commit(connection);
			return branchList;

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
