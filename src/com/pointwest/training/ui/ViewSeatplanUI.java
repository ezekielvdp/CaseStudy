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
import com.pointwest.training.service.ViewSeatPlanService;

public class ViewSeatplanUI extends ParentUI{
	
	public static final List<String> validInputs = new ArrayList<>(Arrays.asList("1", "2"));
	
	public static final List<String> validLocation = new ArrayList<>(Arrays.asList("PTC", "PIC", "PLC"));
	
	public String viewHandler() throws DaoException {
		boolean isValidChoice = false;
		boolean isValidInputs = false;
		boolean isHome = false;
		boolean isAgain = false;
		String choice = "";
		
		// Gateway between home and view UI
		do {
			HashMap<String, List<EmployeeBean>> listOfSeatsByQuadrants = new HashMap<String, List<EmployeeBean>>();
			
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
						
						if(!location.isEmpty() && validLocation.contains(location)) {
							if(!flrLevel.isEmpty() && Pattern.compile("\\d+").matcher(flrLevel).matches()) {
								isValidInputs = true;
								listOfSeatsByQuadrants = seatplanService.viewSeatplanByLocation(location, flrLevel);
							}
						}
						
						if(!isValidInputs) {
							System.out.println("Invalid inputs. Try again!@@");
						} else {
							displaySeatPlan(listOfSeatsByQuadrants);
						}
						
						break;
					case "2": // View By Quadrant
						System.out.println(Constants.ENTERLOCATION);
						location = scan.nextLine();
						System.out.println(Constants.ENTERFLOORLVL);
						flrLevel = scan.nextLine();
						System.out.println(Constants.ENTERQUADRANT);
						quadrant = scan.nextLine();
						
						listOfSeatsByQuadrants = seatplanService.viewSeatplanByQuadrant(location, flrLevel, quadrant);
						break;
					default: // Error handling
						System.out.println("Invalid input. Try Again!"); 
						break;
					}
				} while(!isValidInputs);
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

	private void displaySeatPlan(HashMap<String, List<EmployeeBean>> listOfSeatsByQuadrants) {
		
		
		int ctr = 0;
		
		HashMap<Integer, List<String>> resultMap = new HashMap<Integer, List<String>>();
		
		for(List<EmployeeBean> listOfEmployeeSeats : listOfSeatsByQuadrants.values()) {
			ctr++;
			List<String> resultList = new ArrayList<String>();
			
			for(int i = 0; i < listOfEmployeeSeats.size(); i++) {
				
				if(i+5 % 5 == 0) {
					System.out.println();
				}
			}
			
			for(EmployeeBean employee : listOfEmployeeSeats) {
				
				// Get Employee Info if available
				String employeeNameHolder = "";
				if(employee.getEmployeeFirstName() != null && !employee.getEmployeeFirstName().isEmpty()) {
					employeeNameHolder += employee.getEmployeeFirstName();
				}
				
				if(employee.getEmployeeLastName() != null && !employee.getEmployeeLastName().isEmpty()) {
					employeeNameHolder += ", " + employee.getEmployeeLastName();
				}
				
				// Iterate per seats in employee even if employee is null.
				for(SeatBean seat : employee.getListOfSeats()) {
					String result = "";
					String seatLocation = seat.getSeatBldgId() + seat.getSeatFlrNum() + seat.getSeatQuadrant() + 
										  seat.getSeatColumnNum() + Constants.DASH + seat.getSeatRowNum();
					result += seatLocation;
					if(!employeeNameHolder.isEmpty()) {
						result+= "\n" + employeeNameHolder;
					}
					result += seat.getLocalNumber() == 0 ? "" : "\nloc." +  seat.getLocalNumber();
					resultList.add(result);
				}
				
			}
			resultMap.put(ctr, resultList);
		}
	}
}
