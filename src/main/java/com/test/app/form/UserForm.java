package com.test.app.form;

public class UserForm {

	private Integer id;

	private String userName;

	private String email;

	// アクセサ
	public Integer getId() {return id;}
	public String getUserName() {return userName;}
	public String getEmail() {return email;}

	public void setId(int id) {this.id = id;}
	public void setUserName(String userName) {this.userName = userName;}
	public void setEmail(String email) {this.email = email;}
}
