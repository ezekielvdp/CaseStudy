package com.pointwest.training.ui;

import com.pointwest.training.beans.UserBean;
import com.pointwest.training.exception.DaoException;

public class MainHandlerUI {

	public void Main() {
		LogInUI loginUI = new LogInUI();

		UserBean user = null;
		
		boolean isCorrectCredential = false;
		
		do {
			try {
				user = loginUI.LogInMenu();
				isCorrectCredential = user != null;
			} catch (DaoException de) {
				System.out.println(de.getMessage());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
			if(!isCorrectCredential) {
				loginUI.displayInvalidLogIn();
			}
		} while (!isCorrectCredential);
		
		// Display Homepage when correctCredential
		if (isCorrectCredential) {
			HomePageUI homepageUI = new HomePageUI();

			homepageUI.displayHomePage(user);
		}

	}
}
