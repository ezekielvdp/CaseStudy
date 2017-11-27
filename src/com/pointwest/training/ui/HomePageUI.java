package com.pointwest.training.ui;

import com.pointwest.training.beans.UserBean;
import com.pointwest.training.constants.Constants;

public class HomePageUI {
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
