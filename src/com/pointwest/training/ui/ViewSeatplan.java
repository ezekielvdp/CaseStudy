package com.pointwest.training.ui;

import com.pointwest.training.constants.Constants;

public class ViewSeatplan {
	public void displayViewSeatPlanMenu() {
		System.out.println(Constants.DOUBLESHARP + " " + Constants.VIEWSEATPLAN + " " + Constants.DOUBLESHARP);
		System.out.println(Constants.HEADER_MENU);
		System.out.println(Constants.OPT_1 + Constants.BY + Constants.LOCATION);
		System.out.println(Constants.OPT_2 + Constants.BY + Constants.QUADRANT);
	}
}
