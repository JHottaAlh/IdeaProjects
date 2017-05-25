//userControlに必要な情報を取り出すDao
//Daoはあくまでメソッドを定義するだけだから呼び出されて初めて処理を実行できる
package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exception.SQLRuntimeException;
import model.UserControl;

public class UserControlDao {
	
	public List<UserControl> getUserControl(Connection connection){
		PreparedStatement ps = null;
		try{
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM user_control ");
			//DESCはデータのソート機能
			sql.append("ORDER BY id ASC");
			
			//psには上述したSQL文が一行で記述されている
			ps = connection.prepareStatement(sql.toString());
			
			//ps(SQL文)を実行、Queryによりデータを取り出しそれをrsに格納している
			//その後rs
			ResultSet rs = ps.executeQuery();
			List<UserControl> ret = toUserControlList(rs);
			//メソッドの呼び出し元に取り出したデータを格納したretリストを戻り値として送る
			return ret;
		}catch(SQLException e){
			throw new SQLRuntimeException(e);
		}finally{
			close(ps);
		}
	}
	
	
	//user_controlビューから取り出したデータを格納するメソッド
	private List<UserControl> toUserControlList(ResultSet rs) 
			throws SQLException{
		List<UserControl> ret = new ArrayList<UserControl>();
		try{
			//rs(一行ずつrsの結果を取り出す（無くなるまで続く）)
			while (rs.next()){
				//各要素を同名のローカル変数に格納
				int id = rs.getInt("id");
				String login_id = rs.getString("login_id");
				String name = rs.getString("name");
				int is_stopped = rs.getInt("is_stopped");
				
				//ローカル変数をuserData(Beans)にセット
				UserControl userData = new UserControl();
				userData.setId(id);
				userData.setLogin_id(login_id);
				userData.setName(name);
				userData.setIs_stopped(is_stopped);
				
				//retにBeansの情報を格納して戻り値として戻す
				ret.add(userData);
			}
			return ret;
		}finally{
			close(rs);
		}
	}

}
