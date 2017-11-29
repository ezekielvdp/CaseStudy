package com.pointwest.training.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

import com.pointwest.training.beans.EmployeeBean;
import com.pointwest.training.beans.SeatBean;
import com.pointwest.training.constants.Constants;
import com.pointwest.training.exception.DaoException;
import com.pointwest.training.service.SearchService;

public class SearchUI extends ParentUI{
	
	public static final List<String> validInputs = new ArrayList<>(Arrays.asList("1", "2", "3"));
	
	protected String searchUIHandler() throws DaoException {
		
		boolean isValidChoice = false;
		boolean isValidSearchTxt = false;
		boolean isHome = false;
		boolean isAgain = false;
		String choice = "";
		SearchService searchService = new SearchService();
		
		// Gateway between home and search UI
		do {
			HashMap<Integer, EmployeeBean> employeesById = new HashMap<Integer, EmployeeBean>();
			
			String searchTxt = "";
			
			// DISPLAY SEARCH MENU UI
			displaySearchMenu();
			choice = this.getChoice();
			
			// CHECK if valid input
			isValidChoice = SearchUI.validInputs.contains(choice);
			
			if(isValidChoice) {
				// Search Employee UI Level
				do {
				searchTxt = searchEmployeeUI(choice);

				switch(choice) {
					case "1": // Search By EmployeeID
						// Does not accept non-integer but accepts blank as search all
						isValidSearchTxt = Pattern.compile("\\d+").matcher(searchTxt).matches() || searchTxt.isEmpty();
						if(isValidSearchTxt) {
							employeesById = searchService.searchEmployeeById(searchTxt);
						} else {
							System.out.println("Invalid input! Please try inputting numeric values or empty to search all employees.");
						}
						break;
					case "2": // Search By Name
						isValidSearchTxt = Pattern.compile("\\D+").matcher(searchTxt.trim()).matches() || searchTxt.isEmpty();
						if(isValidSearchTxt) {
							employeesById = searchService.searchEmployeeByName(searchTxt);
						} else {
							System.out.println("Invalid input! Please try inputting text or empty to search all employees. [Note: Space is invalid input too]");
						}
						break;
					case "3": // Search By Project
						isValidSearchTxt = true;
						employeesById = searchService.searchEmployeeByProject(searchTxt);
						break;
					default: // Error handling
						System.out.println("Invalid input. Try Again!"); 
						break;
					}
				} while(!isValidSearchTxt);
			}
			
			if(!employeesById.isEmpty() || isValidSearchTxt) {
				displayResult(employeesById);
				choice = againMenu();
			}
			
			isHome = "HOME".equalsIgnoreCase(choice);
			isAgain = "AGAIN".equalsIgnoreCase(choice);
			
		} while(isAgain || !isHome && !isValidChoice);
		
		return choice;
	}
	
	
	// DISPLAY SEARCH MENU
	protected void displaySearchMenu() {
		System.out.println(Constants.DOUBLESHARP + " " + Constants.HEADER_SEARCH + " " + Constants.DOUBLESHARP);
		System.out.println(Constants.HEADER_MENU);
		System.out.println(Constants.OPT_1 + Constants.BY + Constants.EMPLOYEEID);
		System.out.println(Constants.OPT_2 + Constants.BY + Constants.NAME);
		System.out.println(Constants.OPT_3 + Constants.BY + Constants.PROJECT);
	}
	
	// Search By Employee ID
	protected String searchEmployeeUI(String choice) {
		String searchTxt = "";
				
		switch(choice) {
		case "1":
			System.out.println(Constants.DOUBLESHARP + " " 
					 + Constants.HEADER_SEARCH + Constants.DASH + Constants.BY + Constants.EMPLOYEEID
					 + " " + Constants.DOUBLESHARP);
	
			System.out.println("Enter " + Constants.EMPLOYEEID + ": ");			
			break;
		case "2":
			System.out.println(Constants.DOUBLESHARP + " " 
					 + Constants.HEADER_SEARCH + Constants.DASH + Constants.BY + Constants.NAME
					 + " " + Constants.DOUBLESHARP);
			System.out.println("Enter " + Constants.NAME + ": ");	
			break;
		case "3":
			System.out.println(Constants.DOUBLESHARP + " " 
					 + Constants.HEADER_SEARCH + Constants.DASH + Constants.BY + Constants.PROJECT
					 + " " + Constants.DOUBLESHARP);
			System.out.println("Enter " + Constants.PROJECT + ": ");	
			break;
		}
		searchTxt = scan.nextLine();
		return searchTxt;
	}
	
	protected String againMenu() {
		String choice = "";
		do {
			System.out.println(Constants.OPT_1 + "Search Again" + " " + Constants.OPT_2 + "Home");
			choice = getChoice();
			switch(choice) {
			case "1":
				choice = "AGAIN";
				break;
			case "2":
				choice = "HOME";
				break;
			default:
				System.out.println("Invalid input try again.");
			}
		} while("1".equals(choice) || "2".equals(choice));
		
		return choice;
	}
	
	// DISPLAY RESULT LIST
	protected void displayResult(HashMap<Integer, EmployeeBean> searchResult) {
		int i = 1; // counter
		
		String result = "";

		for(EmployeeBean employee : searchResult.values()) {
			for(SeatBean seat : employee.getListOfSeats()) {
				result += "[" + i + "] " + 
						  employee.getEmployeeId() + Constants.DIV_VERTICAL + 
						  employee.getEmployeeFirstName() + Constants.DIV_VERTICAL + 
						  employee.getEmployeeLastName() + Constants.DIV_VERTICAL + 
						  seat.getSeatBldgId() + seat.getSeatFlrNum() + seat.getSeatQuadrant() + 
						  seat.getSeatColumnNum() + Constants.DASH + seat.getSeatRowNum() + Constants.DIV_VERTICAL;
				
				// set none to no local number if 0 else display local number
				result += seat.getLocalNumber() == 0 ? "NONE|" : seat.getLocalNumber() + "|";
				
				int iter = 1;
				for(String project : employee.getEmployeeProjects()) {
					if(iter == employee.getEmployeeProjects().size()) {
						result += project;
					} else {
						iter++;
						result += project + ", ";
					}
				}
				result+="\n";
				i++;
			}
		}
		
		result = "".equals(result) ? "NO DATA FOUND" : result;
		System.out.println(Constants.DOUBLESHARP + " " + Constants.SEARCH + " RESULT" + Constants.DASH + --i + " " + Constants.DOUBLESHARP);
		System.out.println(Constants.DIV_HORIZONTAL);
		System.out.println("EMPLOYEE ID|FIRSTNAME|LASTNAME|SEAT|LOCAL|SHIFT|PROJECT(S)");
		System.out.println(Constants.DIV_HORIZONTAL);
		System.out.println(result);
		System.out.println(Constants.DIV_HORIZONTAL2 + "end of result" + Constants.DIV_HORIZONTAL2);
	}
}
