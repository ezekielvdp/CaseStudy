package com.pointwest.training.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.pointwest.training.beans.UserBean;
import com.pointwest.training.constants.Constants;

public class HomePageUI extends MenuUI {
	
	Scanner scan = new Scanner(System.in);
	
	public static final List<String> validInputs = new ArrayList<>(Arrays.asList("1", "2", "3"));
	
	public void displayHomePage(UserBean user) {
		
		String firstName = user.getUserFirstName();
		String lastName = user.getUserLastName();
		String role = user.getUserRole();
		
		// Header & Welcome Message
		System.out.println(Constants.DOUBLESHARP + " " + Constants.HEADER_HOME + " " + Constants.DOUBLESHARP);
		System.out.println(Constants.DIV_HORIZONTAL);
		System.out.println(Constants.WELCOME + " " + firstName + " " + lastName + "[" + role + "]!");
		System.out.println(Constants.DIV_HORIZONTAL);
		System.out.println(Constants.HEADER_MENU);
		
		// Options
		System.out.println(Constants.OPT_1 + Constants.SEARCH);
		System.out.println(Constants.OPT_2 + Constants.VIEWSEATPLAN);
		System.out.println(Constants.OPT_3 + Constants.LOGOUT);
	}
	
}
