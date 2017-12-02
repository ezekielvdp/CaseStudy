package com.pointwest.training.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

import com.pointwest.training.beans.EmployeeBean;
import com.pointwest.training.constants.Constants;
import com.pointwest.training.exception.DaoException;
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
						location = scan.nextLine().toUpperCase();
						System.out.println(Constants.ENTERFLOORLVL);
						flrLevel = scan.nextLine();
						
						if(!location.isEmpty() && validLocation.contains(location)) {
							if(!flrLevel.isEmpty() && Pattern.compile("\\d+").matcher(flrLevel).matches()) {
								isValidInputs = true;
								listOfSeatsByQuadrants = seatplanService.viewSeatplanByLocation(location, flrLevel);
							}
						} else {
							System.out.println("Only valid inputs are PTC, PIC, PLC");
						}
						
						if(!isValidInputs) {
							System.out.println("Invalid input floor input. Try again!");
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
						
						if(!location.isEmpty() && validLocation.contains(location)) {
							if(!flrLevel.isEmpty() && Pattern.compile("\\d+").matcher(flrLevel).matches()) {
								isValidInputs = true;
								listOfSeatsByQuadrants = seatplanService.viewSeatplanByQuadrant(location, flrLevel, quadrant);
							}
						} else {
							System.out.println("Only valid inputs are PTC, PIC, PLC");
						}
						
						if(!isValidInputs) {
							System.out.println("Invalid input floor input. Try again!");
						} else {
							displaySeatPlan(listOfSeatsByQuadrants);
						}
						
						
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
		
		
		boolean doOnce = false;
		
		for(List<EmployeeBean> listOfEmployeeSeats : listOfSeatsByQuadrants.values()) {
			int ctr = 0;
			
			List<String> seatDetailsPlaceHolder = new ArrayList<String>();
			List<String> namePlaceHolder = new ArrayList<String>();
			List<String> localNumberPlaceHolder = new ArrayList<String>();

			String quadrant = "";
			
			for(EmployeeBean employee : listOfEmployeeSeats) {
				
				
				if(!doOnce) {
					doOnce = true;
					
					String bldg_id = employee.getListOfSeats().get(0).getSeatBldgId();
					String bldg_address = employee.getListOfSeats().get(0).getSeatBldgAddress();
					int flrLevel = employee.getListOfSeats().get(0).getSeatFlrNum();
					System.out.println("\n\n" + Constants.DOUBLESHARP + " " + Constants.VIEWSEATPLAN + " " + Constants.DOUBLESHARP);
					System.out.println(Constants.DIV_HORIZONTAL);
					System.out.println("LOCATION: " + bldg_id + " (" + bldg_address + "), FLOOR: " + flrLevel + "\n\n");
				}
				
				ctr++;
				
				String currQuadrant = employee.getListOfSeats().get(0).getSeatQuadrant();
				
				if(!quadrant.equalsIgnoreCase(currQuadrant)) {
					quadrant = employee.getListOfSeats().get(0).getSeatQuadrant();
					System.out.println("[QUADRANT " + quadrant + "] " +Constants.DIV_HORIZONTAL + "\n\n");
					
				}

				String seat = employee.getListOfSeats().get(0).getSeatBldgId() 
							+ employee.getListOfSeats().get(0).getSeatFlrNum() + "F"
							+ employee.getListOfSeats().get(0).getSeatQuadrant()
							+ employee.getListOfSeats().get(0).getSeatColumnNum()
							+ Constants.DASH
							+ employee.getListOfSeats().get(0).getSeatRowNum();
				
				seatDetailsPlaceHolder.add(seat);
				
				String fName = employee.getEmployeeFirstName() != null ? employee.getEmployeeFirstName() : "UNOCCUPIED";
				String lName = "";
				
				if(employee.getEmployeeLastName() != null) {
					if(!fName.isEmpty()) {
						lName = ", " + employee.getEmployeeLastName();
					} else {
						lName = employee.getEmployeeLastName();
					}
				} else {
					lName = "";
				}
				
				String fullName = fName + lName;
				
				namePlaceHolder.add(fullName);
				
				String localNumber = employee.getListOfSeats().get(0).getLocalNumber() == 0 ? "" : Integer.toString(employee.getListOfSeats().get(0).getLocalNumber());
				
				localNumberPlaceHolder.add(localNumber);
				
				if(ctr % 3 == 0) {
					
					String format = "%1$35s";
					
					for(String seatDetail : seatDetailsPlaceHolder) {
//						System.out.format(format, seatDetail);
						System.out.printf(format, seatDetail);
					}
					
					System.out.println();
					
					for(String name : namePlaceHolder) {
						System.out.printf(format, name);
					}
					
					System.out.println();
					
					for(String localnum : localNumberPlaceHolder) {
						if(localnum.isEmpty()) {
							System.out.printf(format, "LOC# N/A");
						} else {
							System.out.printf(format, "loc." + localnum, format);
						}
					}
					
					System.out.println("\n\n");
					
					seatDetailsPlaceHolder.clear();
					namePlaceHolder.clear();
					localNumberPlaceHolder.clear();
				}
			}
		}
	}
}
