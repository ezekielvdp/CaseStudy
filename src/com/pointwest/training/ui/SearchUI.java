package com.pointwest.training.ui;

import com.pointwest.training.constants.Constants;

public class SearchUI {
	public void displaySearchMenu() {
		System.out.println(Constants.DOUBLESHARP + " " + Constants.HEADER_SEARCH + " " + Constants.DOUBLESHARP);
		System.out.println(Constants.HEADER_MENU);
		System.out.println(Constants.OPT_1 + Constants.BY + Constants.EMPLOYEEID);
		System.out.println(Constants.OPT_2 + Constants.BY + Constants.NAME);
		System.out.println(Constants.OPT_3 + Constants.BY + Constants.PROJECT);
	}
}
