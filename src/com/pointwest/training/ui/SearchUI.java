package com.pointwest.training.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import com.pointwest.training.beans.EmployeeBean;
import com.pointwest.training.beans.SeatBean;
import com.pointwest.training.constants.Constants;
import com.pointwest.training.exception.DaoException;
import com.pointwest.training.service.SearchService;

public class SearchUI extends ParentUI{
	
	Scanner scan = new Scanner(System.in);
	public static final List<String> validInputs = new ArrayList<>(Arrays.asList("1", "2", "3"));
	
	public void searchUIHandler() throws DaoException {
		displaySearchMenu();
		boolean isValidInput = false;
		
		SearchService searchService = new SearchService();
		
		HashMap<Integer, EmployeeBean> searchResultByEmployeeId = new HashMap<Integer, EmployeeBean>();
		
		do {
			String choice = this.getChoice();
			isValidInput = SearchUI.validInputs.contains(choice);
			
			switch(choice) {
			case "1": // Search By EmployeeID
				String employeeId = searchByEmployeeIdUI();
				searchResultByEmployeeId = searchService.searchEmployeeById(employeeId);
				if(!searchResultByEmployeeId.isEmpty()) {
					displayResult(searchResultByEmployeeId);
				}
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
		    Integer.parseInt(employeeNum);
		} catch (NumberFormatException e) {
		    System.out.println("Input is not a valid integer");
		}
		return employeeNum;
	}
	
	public void displayResult(HashMap<Integer, EmployeeBean> searchResult) {
		
		int i = 1; // counter
		
		
		String result = "";
		
		for(EmployeeBean employee : searchResult.values()) {
			
			for(SeatBean seat : employee.getListOfSeats()) {
				
				result += "[" + i + "] " + 
						  employee.getEmployeeId() + Constants.DIV_VERTICAL + // 10231|
						  employee.getEmployeeFirstName() + Constants.DIV_VERTICAL + // Juan|
						  employee.getEmployeeLastName() + Constants.DIV_VERTICAL + // Santos|
						  seat.getSeatBldgId() + seat.getSeatFlrNum() + seat.getSeatQuadrant() +  // PIC4A
						  seat.getSeatColumnNum() + Constants.DASH + seat.getSeatRowNum() + Constants.DIV_VERTICAL;
						  // 1-2
				if(seat.getLocalNumber() == 0) {
					result += "NONE|";
				} else {
					result += seat.getLocalNumber() + "|";
				}
				
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

		System.out.println(Constants.DOUBLESHARP + " " + 
				   Constants.SEARCH + " RESULT" + Constants.DASH + " "  + --i + " " + 
				   Constants.DOUBLESHARP);
		
		System.out.println(result);
	}
}
