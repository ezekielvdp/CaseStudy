package com.pointwest.training.ui;

import java.util.Scanner;

import com.pointwest.training.constants.Constants;

public class ParentUI {
	
	Scanner scan = new Scanner(System.in);
	
	public String getChoice() {
		
		String choice = "";
		
		// Choice
		System.out.println(Constants.CHOOSE);
		choice = scan.next();
		
		return choice;
	}
}
