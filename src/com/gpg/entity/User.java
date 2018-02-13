package com.gpg.entity;

public class User {
	private int id;
	private String userName;
	private String userPwd;

	public User() {
		super();
	}

	public User(int id, String userName, String userPwd) {
		super();
		this.id = id;
		this.userName = userName;
		this.userPwd = userPwd;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", userPwd=" + userPwd + "]";
	}

}
