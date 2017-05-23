//データベースコネクション関連のユーティリティ
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import exception.SQLRuntimeException;

/*
 * DBのユーティリティ（コネクション関係）
 */

public class DBUtil {
	//接続したいデータベースの指定
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost/bulletinboard";
	private static final String USER = "root";
	private static final String PASSWORD = "alhincPPA7Xn24";

	static{
		try{
			Class.forName(DRIVER);
		}catch(ClassNotFoundException e){
			throw new RuntimeException(e);
		}
	}
	/*
	 * コネクション取得
	 * Connection型のconnection(データベースへのコネクション)が戻り値
	 * getConnectionを宣言すると（戻り値として）コネクションを取得できるメソッド
	 */

	public static Connection getConnection(){
		try{
			Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
			//自動でコミットしないように設定
			connection.setAutoCommit(false);

			return connection;

		//エラーがあればエラーを返す
		}catch(SQLException e){
			throw new SQLRuntimeException(e);
		}
	}
	/*
	 * トランザクション（全処理が成功した時はコミット、一つでもエラーがあればロールバック（中止）する）
	 * コミットメソッド
	 */

	public static void commit(Connection connection){
		try{
			connection.commit();
		}catch(SQLException e){
			throw new SQLRuntimeException(e);
		}
	}
	/*
	 * ロールバックメソッド
	 */

	public static void rollback(Connection connection){
		try{
			connection.rollback();
		}catch(SQLException e){
			throw new SQLRuntimeException(e);
		}
	}

}
