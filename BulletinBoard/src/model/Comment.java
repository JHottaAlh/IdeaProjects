//コメントに関するBeans
package model;

import java.io.Serializable;
import java.util.Date;

public class Comment implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String text;
	private Date timed_at;
	private Date updated_at;
	private int post_id;
	private int user_id;
	private String name;
	private int branch_id;
	private int department_id;
	
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	
	public String getText() {return text;}
	public void setText(String text) {this.text = text;}
	
	public Date getTimed_at() {return timed_at;}
	public void setTimed_at(Date timed_at) {this.timed_at = timed_at;}
	
	public Date getUpdated_at() {return updated_at;}
	public void setUpdated_at(Date updated_at) {this.updated_at = updated_at;}
	
	public int getPost_id() {return post_id;}
	public void setPost_id(int post_id) {this.post_id = post_id;}
	
	public int getUser_id() {return user_id;}
	public void setUser_id(int user_id) {this.user_id = user_id;}
	
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	
	public int getBranch_id(){ return branch_id; }
	public void setBranch_id(int branch_id){ this.branch_id = branch_id; }

	public int getDepartment_id(){ return department_id; }
	public void setDepartment_id(int department_id){ this.department_id = department_id; }

}
