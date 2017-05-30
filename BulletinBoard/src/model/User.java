//ユーザー登録の情報を格納するBeans(セッションスコープ)
//仕様書に応じて格納する要素を変える
package model;

import java.io.Serializable;

import com.sun.jmx.snmp.Timestamp;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String login_id;
	private String password;
	private String name;
	private int branch_id;
	private int department_id;
	private Timestamp timed_at;
	private Timestamp updated_at;
	private int is_stopped;

	public int getId(){ return id; }
	public void setId(int id){ this.id = id; }
	
	public String getLogin_id(){ return login_id; }
	public void setLogin_id(String login_id){ this.login_id = login_id; }

	public String getPassword(){ return password; }
	public void setPassword(String password){ this.password = password; }

	public String getName(){ return name; }
	public void setName(String name){ this.name = name; }

	public int getBranch_id(){ return branch_id; }
	public void setBranch_id(int branch_id){ this.branch_id = branch_id; }

	public int getDepartment_id(){ return department_id; }
	public void setDepartment_id(int department_id){ this.department_id = department_id; }
	
	public Timestamp getTimed_at() {return timed_at;}
	public void setTimed_at(Timestamp timed_at) {this.timed_at = timed_at;}
	
	public Timestamp getUpdated_at() {return updated_at;}
	public void setUpdated_at(Timestamp updated_at) {this.updated_at = updated_at;}
	
	public int getIs_stopped() {return is_stopped;}
	public void setIs_stopped(int is_stopped) {this.is_stopped = is_stopped;}

}
