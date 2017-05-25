//user_controlビューを参照するためのBeans
package model;

import java.io.Serializable;


public class UserControl implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String login_id;
	private String name;
	private int is_stopped;
	
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	
	public String getLogin_id() {return login_id;}
	public void setLogin_id(String login_id) {this.login_id = login_id;}
	
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	
	public int getIs_stopped() {return is_stopped;}
	public void setIs_stopped(int is_stopped) {this.is_stopped = is_stopped;}
	
}
