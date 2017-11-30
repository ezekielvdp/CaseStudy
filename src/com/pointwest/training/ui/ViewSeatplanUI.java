package com.pointwest.training.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

import com.pointwest.training.beans.EmployeeBean;
import com.pointwest.training.constants.Constants;
import com.pointwest.training.exception.DaoException;
import com.pointwest.training.service.SearchService;
import com.pointwest.training.service.ViewSeatPlanService;

public class ViewSeatplanUI extends ParentUI{
	
	public static final List<String> validInputs = new ArrayList<>(Arrays.asList("1", "2"));
	
	public String viewHandler() throws DaoException {
		boolean isValidChoice = false;
		boolean isValidSearchTxt = false;
		boolean isHome = false;
		boolean isAgain = false;
		String choice = "";
		
		// Gateway between home and view UI
		do {
			HashMap<Integer, EmployeeBean> employeesById = new HashMap<Integer, EmployeeBean>();
			
			// DISPLAY SEARCH MENU UI
			displayViewSeatPlanMenu();
			choice = this.getChoice();
			
			// CHECK if valid input
			isValidChoice = SearchUI.validInputs.contains(choice);
			
			if(isValidChoice) {
				// Search Employee UI Level
				do {
					
					String location = "";
					String flrLevel = "";
					String quadrant = "";
					
					ViewSeatPlanService seatplanService = new ViewSeatPlanService();
					
				switch(choice) {
					case "1": // View By Location
						System.out.println(Constants.ENTERLOCATION);
						location = scan.nextLine();
						System.out.println(Constants.ENTERFLOORLVL);
						flrLevel = scan.nextLine();
						
						employeesById = seatplanService.viewSeatplanByLocation(location, flrLevel);
						break;
					case "2": // View By Quadrant
						System.out.println(Constants.ENTERLOCATION);
						location = scan.nextLine();
						System.out.println(Constants.ENTERFLOORLVL);
						flrLevel = scan.nextLine();
						System.out.println(Constants.ENTERQUADRANT);
						quadrant = scan.nextLine();
						
						employeesById = seatplanService.viewSeatplanByQuadrant(location, flrLevel, quadrant);
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
	
	private void displayViewSeatPlanMenu() {
		System.out.println(Constants.DOUBLESHARP + " " + Constants.VIEWSEATPLAN + " " + Constants.DOUBLESHARP);
		System.out.println(Constants.HEADER_MENU);
		System.out.println(Constants.OPT_1 + Constants.BY + Constants.LOCATION);
		System.out.println(Constants.OPT_2 + Constants.BY + Constants.QUADRANT);
	}
	
	private void viewSeatPlanUI(String choice) {
		
		System.out.println(Constants.DOUBLESHARP + " " + Constants.VIEWSEATPLAN + " " 
						  + Constants.BY + " " + Constants.LOCATION + Constants.DOUBLESHARP);
		
		switch(choice) {
		case "1":
			
			break;
		case "2":

		}
		
	}
}
