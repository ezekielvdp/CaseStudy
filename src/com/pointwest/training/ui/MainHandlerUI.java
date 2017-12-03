package com.pointwest.training.ui;

import com.pointwest.training.beans.UserBean;
import com.pointwest.training.exception.DaoException;

public class MainHandlerUI {

	public void Main() {
		LogInUI loginUI = new LogInUI();

		UserBean user = null;

		boolean isCorrectCredential = false;
		boolean isExceptionOccured = false;
		boolean isHome = false;
		
		String choice = "";
		
		do {
			// Log-In Module
			do {
				try {
					user = loginUI.logIn();
					isCorrectCredential = user != null;
	
					if (!isCorrectCredential) {
						loginUI.displayInvalidLogIn();
					}
	
				} catch (DaoException de) {
					isExceptionOccured = true;
					System.out.println(de.getUserFriendlyErrorMessage());
					System.out.println("Terminating program.");
				}
	
			} while (!isExceptionOccured && !isCorrectCredential);
	
			// Display Homepage when Credential is correct
			if (isCorrectCredential) {
				
				// Initialization
				HomePageUI homepageUI = new HomePageUI();
				boolean isValidInput = false;
				
				// Loops until valid input is entered.
				do {
					homepageUI.displayHomePage(user);
					
					choice = homepageUI.getChoice();
					isValidInput = HomePageUI.validInputs.contains(choice);
					
					switch (choice) {
					case "1": // Search
						SearchUI searchUI = new SearchUI();
						try {
							choice = searchUI.searchUIHandler();
						} catch (DaoException e) {
							System.out.println(e.getUserFriendlyErrorMessage());
						}
						break;
					case "2": // View Seatplan
						ViewSeatplanUI viewUI = new ViewSeatplanUI();						
						try {
							choice = viewUI.viewHandler();
						} catch (DaoException e) {
							System.out.println(e.getUserFriendlyErrorMessage());
						}
						break;
					case "3": // Logout
						choice = "LOGOUT";
						break;
					case "4":
						choice = "EXIT";
						break;
					default: // Error handling
						System.out.println("Invalid input. Try Again!"); 
					}
					
					isHome = "HOME".equalsIgnoreCase(choice) || "BACK".equalsIgnoreCase(choice);
					
				} while(!isValidInput || isHome);
			}
		} while(!"EXIT".equalsIgnoreCase(choice));
		
		System.out.println("Program Ended.");
	}
}
