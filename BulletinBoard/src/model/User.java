//ユーザー登録の情報を格納するBeans(セッションスコープ)
//仕様書に応じて格納する要素を変える
package model;

import java.io.Serializable;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	private String login_id;
	private String password;
	private String name;
	private int branch_id;
	private int department_id;


	public String getLogin_id(){ return login_id; }
	public void setLogin_id(String login_id){ this.password = login_id; }

	public String getPassword(){ return password; }
	public void setPassword(String password){ this.password = password; }

	public String getName(){ return name; }
	public void setName(String name){ this.name = name; }

	public int getBranch_id(){ return branch_id; }
	public void setBranch_id(int branch_id){ this.branch_id = branch_id; }

	public int getDepartment_id(){ return department_id; }
	public void setDepartment_id(int department_id){ this.department_id = department_id; }

}
