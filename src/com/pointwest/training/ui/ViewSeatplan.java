package com.pointwest.training.ui;

import java.util.HashMap;
import java.util.regex.Pattern;

import com.pointwest.training.beans.EmployeeBean;
import com.pointwest.training.constants.Constants;
import com.pointwest.training.service.SearchService;

public class ViewSeatplan extends ParentUI{
	public String viewHandler() {
		boolean isValidChoice = false;
		boolean isValidSearchTxt = false;
		boolean isHome = false;
		boolean isAgain = false;
		String choice = "";
		SearchService searchService = new SearchService();
		
		// Gateway between home and search UI
		do {
			HashMap<Integer, EmployeeBean> seatByQuadrant = new HashMap<Integer, EmployeeBean>();

			String location = "";
			String floor = "";
			
				
			// DISPLAY SEARCH MENU UI
			displayViewSeatPlanMenu();
			choice = this.getChoice();
			
			// CHECK if valid input
			isValidChoice = SearchUI.validInputs.contains(choice);
			
			if(isValidChoice) {
				// Search Employee UI Level
				do {
				switch(choice) {
					case "1": // View By Location
						
						break;
					case "2": // View By Quadrant
						
						break;
					default: // Error handling
						System.out.println("Invalid input. Try Again!"); 
						break;
					}
				} while(!isValidSearchTxt);
			}
			
			
			isHome = "HOME".equalsIgnoreCase(choice);
			isAgain = "AGAIN".equalsIgnoreCase(choice);
			
		} while(isAgain || !isHome && !isValidChoice);
		
		return choice;
	}
	
	public void displayViewSeatPlanMenu() {
		System.out.println(Constants.DOUBLESHARP + " " + Constants.VIEWSEATPLAN + " " + Constants.DOUBLESHARP);
		System.out.println(Constants.HEADER_MENU);
		System.out.println(Constants.OPT_1 + Constants.BY + Constants.LOCATION);
		System.out.println(Constants.OPT_2 + Constants.BY + Constants.QUADRANT);
	}
}
