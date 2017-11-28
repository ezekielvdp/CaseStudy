package com.pointwest.training.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.pointwest.training.constants.Constants;
import com.pointwest.training.service.SearchService;

public class SearchUI extends ParentUI{
	
	Scanner scan = new Scanner(System.in);
	public static final List<String> validInputs = new ArrayList<>(Arrays.asList("1", "2", "3"));
	
	public void searchUIHandler() {
		displaySearchMenu();
		boolean isValidInput = false;
		
		SearchService searchService = new SearchService();
		
		do {
			String choice = this.getChoice();
			isValidInput = this.validInputs.contains(choice);
			
			switch(choice) {
			case "1": // Search By EmployeeID
				String employeeId = searchByEmployeeIdUI();
				searchService.searchEmployeeById(employeeId);
				break;
			case "2": // Search By Name
				break;
			case "3": // Search By Project
				break;
			default: // Error handling
				System.out.println("Invalid input. Try Again!"); 
				break;
			}
			
		} while(!isValidInput);
	}
	
	public void displaySearchMenu() {
		System.out.println(Constants.DOUBLESHARP + " " + Constants.HEADER_SEARCH + " " + Constants.DOUBLESHARP);
		System.out.println(Constants.HEADER_MENU);
		System.out.println(Constants.OPT_1 + Constants.BY + Constants.EMPLOYEEID);
		System.out.println(Constants.OPT_2 + Constants.BY + Constants.NAME);
		System.out.println(Constants.OPT_3 + Constants.BY + Constants.PROJECT);
	}
	
	public String searchByEmployeeIdUI() {
		
		System.out.println(Constants.DOUBLESHARP + " " 
						 + Constants.HEADER_SEARCH + Constants.DASH + Constants.BY + Constants.EMPLOYEEID
						 + Constants.DOUBLESHARP);
		
		System.out.println("Enter " + Constants.EMPLOYEEID + ": ");

		String employeeNum = scan.next();
		try {
		    int intValue = Integer.parseInt(employeeNum);
		} catch (NumberFormatException e) {
		    System.out.println("Input is not a valid integer");
		}
		return employeeNum;
	}
}
