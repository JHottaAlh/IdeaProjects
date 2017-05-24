package model;

import java.io.Serializable;
import java.util.Date;

public class UserMessage implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int user_id;
	private String title;
	private int category;
	private String text;
	private Date timed_at;
	private Date updated_at;
	private String name;
	
	
	public int getUser_id() {return user_id;}
	public void setUser_id(int user_id) {this.user_id = user_id;}
	
	public String getTitle() {return title;}
	public void setTitle(String title) {this.title = title;
	}
	public int getCategory() {return category;}
	public void setCategory(int category) {this.category = category;}
	
	public String getText() {return text;}
	public void setText(String text) {this.text = text;}
	
	public Date getTimed_at() {return timed_at;}
	public void setTimed_at(Date timed_at) {this.timed_at = timed_at;}
	
	public Date getUpdated_at() {return updated_at;}
	public void setUpdated_at(Date updated_at) {this.updated_at = updated_at;}
	
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}

}
