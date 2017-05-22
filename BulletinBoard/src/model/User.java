//ユーザー登録の情報を格納するBeans(セッションスコープ)
//仕様書に応じて格納する要素を変える
package model;

import java.io.Serializable;

public class User implements Serializable {
	private String id;
	private String name;
	private String password;

	public User(){}
	public User(String id, String name, String password){
		this.id = id;
		this.name = name;
		this.password = password;
	}

	public String getId(){ return id; }
	public String getName(){ return name; }
	public String getPassword(){ return password; }
}
