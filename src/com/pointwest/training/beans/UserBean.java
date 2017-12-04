package com.pointwest.training.beans;

public class UserBean extends EmployeeBean{

	private String userName;
	private String userRole;
	

	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
