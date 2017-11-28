package com.pointwest.training.ui;

import java.util.Scanner;

import com.pointwest.training.beans.UserBean;
import com.pointwest.training.constants.Constants;
import com.pointwest.training.exception.DaoException;
import com.pointwest.training.service.LogInService;

public class LogInUI {
	
	private Scanner scan = new Scanner(System.in);
	
	public UserBean logIn() throws DaoException {
		LogInService login = new LogInService();
		
		// LOG-IN UI
		System.out.println(Constants.DIV_HORIZONTAL);
		System.out.println(Constants.HEADER_LOGIN);
		System.out.println(Constants.DIV_HORIZONTAL);
		String inUserName = inputUserName();
		String inUserPass = inputUserPassword();
		
		UserBean user = login.retrieveUser(inUserName, inUserPass);
		
		return user;
	}
	
	public void displayInvalidLogIn() {
		System.out.println("The username/password you've inputted is invalid. Please Try Again.");
	}
	
	public String inputUserName() {
		System.out.println(Constants.UNAME);
		return scan.next();
	}
	
	public String inputUserPassword() {
		System.out.println(Constants.UPASS);
		return scan.next();
	}
}
