//user_controlビューを参照するためのBeans
package model;

import java.io.Serializable;


public class UserControl implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String login_id;
	private String name;
	private int is_stopped;
	private String password;
	private int branch_id;
	private int department_id;
	private String branch_name;
	private String department_name;
	
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	
	public String getLogin_id() {return login_id;}
	public void setLogin_id(String login_id) {this.login_id = login_id;}
	
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	
	public int getIs_stopped() {return is_stopped;}
	public void setIs_stopped(int is_stopped) {this.is_stopped = is_stopped;}
	
	public String getPassword() {return password;}
	public void setPassword(String password) {this.password = password;}
	
	public int getBranch_id() {return branch_id;}
	public void setBranch_id(int branch_id) {this.branch_id = branch_id;}
	
	public int getDepartment_id() {return department_id;}
	public void setDepartment_id(int department_id) {this.department_id = department_id;}
	
	public String getBranch_name() {return branch_name;}
	public void setBranch_name(String branch_name) {this.branch_name = branch_name;}
	
	public String getDepartment_name() {return department_name;}
	public void setDepartment_name(String department_name) {this.department_name = department_name;}
	
}
