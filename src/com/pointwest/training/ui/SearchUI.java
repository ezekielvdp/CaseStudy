package com.pointwest.training.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.pointwest.training.beans.EmployeeBean;
import com.pointwest.training.beans.SeatBean;
import com.pointwest.training.constants.Constants;
import com.pointwest.training.exception.DaoException;
import com.pointwest.training.service.SearchService;

public class SearchUI extends ParentUI{
	
	public static final List<String> validInputs = new ArrayList<>(Arrays.asList("1", "2", "3"));
	
	public String searchUIHandler() throws DaoException {
		
		boolean isValidInput = false;
		boolean isHome = false;
		String choice = "";
		SearchService searchService = new SearchService();
		
		HashMap<Integer, EmployeeBean> employeesById = new HashMap<Integer, EmployeeBean>();
		
		do {
			displaySearchMenu();
			choice = this.getChoice();
			isValidInput = SearchUI.validInputs.contains(choice);
			
			String searchTxt = "";
			
			switch(choice) {
			case "1": // Search By EmployeeID
				searchTxt = searchByEmployeeIdUI();
				employeesById = searchService.searchEmployeeById(searchTxt);
				displayResult(employeesById);
				// TODO: make this a function
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
				
				isHome = "HOME".equalsIgnoreCase(choice);
				break;
			case "2": // Search By Name
				// TODO: finish this function
				searchTxt = searchByNameUI();
				
				break;
			case "3": // Search By Project
				// TODO: finish this function
				break;
			default: // Error handling
				System.out.println("Invalid input. Try Again!"); 
				break;
			}
			
		} while("AGAIN".equalsIgnoreCase(choice) || !isValidInput || !isHome);
		
		return choice;
	}
	
	// DISPLAY SEARCH MENU
	public void displaySearchMenu() {
		System.out.println(Constants.DOUBLESHARP + " " + Constants.HEADER_SEARCH + " " + Constants.DOUBLESHARP);
		System.out.println(Constants.HEADER_MENU);
		System.out.println(Constants.OPT_1 + Constants.BY + Constants.EMPLOYEEID);
		System.out.println(Constants.OPT_2 + Constants.BY + Constants.NAME);
		System.out.println(Constants.OPT_3 + Constants.BY + Constants.PROJECT);
	}
	
	// Search By Employee ID
	public String searchByEmployeeIdUI() {
		
		System.out.println(Constants.DOUBLESHARP + " " 
						 + Constants.HEADER_SEARCH + Constants.DASH + Constants.BY + Constants.EMPLOYEEID
						 + " " + Constants.DOUBLESHARP);
		
		System.out.println("Enter " + Constants.EMPLOYEEID + ": ");

		String employeeNum = scan.next();
		try {
		    Integer.parseInt(employeeNum);
		} catch (NumberFormatException e) {
		    System.out.println("Input is not a valid integer");
		}
		return employeeNum;
	}
	
	public String searchByNameUI() {
		
		System.out.println(Constants.DOUBLESHARP + " " 
				 + Constants.HEADER_SEARCH + Constants.DASH + Constants.BY + Constants.NAME
				 + " " + Constants.DOUBLESHARP);

		System.out.println("Enter " + Constants.NAME + ": ");
		
		String employeeName = getChoice();
				
		return employeeName;
	}
	
	// DISPLAY RESULT LIST
	public void displayResult(HashMap<Integer, EmployeeBean> searchResult) {

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
