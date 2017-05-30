//投稿に関するBeans
package model;

import java.io.Serializable;

public class Post implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String title;
	private String category;
	private String text;
	private int user_id;
	
	public int getId(){ return id; }
	public void setId(int id){ this.id = id; }
	
	public String getTitle(){ return title; }
	public void setTitle(String title){ this.title = title; }
	
	public String getCategory(){ return category; }
	public void setCategory(String category){ this.category = category; }
	
	public String getText(){ return text; }
	public void setText(String text){ this.text = text; }
	
	public int getUser_id(){ return user_id; }
	public void setUser_id(int user_id){ this.user_id = user_id; }

}
