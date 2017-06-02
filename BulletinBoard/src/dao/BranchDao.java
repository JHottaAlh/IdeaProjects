package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exception.SQLRuntimeException;
import model.Branch;

public class BranchDao {
	
	//支店情報を取り出すメソッド
	public List<Branch> getBranch(Connection connection){
		PreparedStatement ps = null;
		try{
			String sql = "SELECT * FROM branches";
			ps = connection.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			List<Branch> branchList = toBranchList(rs);
			if (branchList.isEmpty()){
				return null;
			}else{
				return branchList;
			}
		}catch(SQLException e){
			throw new SQLRuntimeException(e);
		}finally{
			close(ps);
		}
	}
	
	//特定の支店を取り出すメソッド
	public Branch getBranchID(Connection connection, int id){
		PreparedStatement ps = null;
		try{
			String sql = "SELECT * FROM branches WHERE id = ?";
			ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			List<Branch> branchList = toBranchList(rs);
			if (branchList.isEmpty()){
				return null;
			}else{
				return branchList.get(0);
			}
		}catch(SQLException e){
			throw new SQLRuntimeException(e);
		}finally{
			close(ps);
		}
	}
	
	private List<Branch> toBranchList(ResultSet rs) 
			throws SQLException{
		
		List<Branch> ret = new ArrayList<Branch>();
		try{
			while (rs.next()){
				int id = rs.getInt("id");
				String name = rs.getString("name");
				
				Branch branch = new Branch();
				branch.setId(id);
				branch.setName(name);
				
				ret.add(branch);
			}
			return ret;
		}finally{
			close(rs);
		}
	}

}
