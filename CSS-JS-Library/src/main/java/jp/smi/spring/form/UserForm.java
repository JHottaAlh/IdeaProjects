package jp.smi.spring.form;

import javax.validation.constraints.Max;

import org.hibernate.validator.constraints.NotEmpty;


public class UserForm {
	@NotEmpty
	private String name;
	@NotEmpty
	private String login_id;
	@NotEmpty
	@Max(20)
	private String password;
	private String mailaddress;
	
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	
	public String getLogin_id() {return login_id;}
	public void setLogin_id(String login_id) {this.login_id = login_id;}
	
	public String getPassword() {return password;}
	public void setPassword(String password) {this.password = password;}
	
	public String getMailaddress() {return mailaddress;}
	public void setMailaddress(String mailaddress) {this.mailaddress = mailaddress;}
}
