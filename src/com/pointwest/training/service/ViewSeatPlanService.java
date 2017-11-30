package com.pointwest.training.service;

import java.util.HashMap;

import com.pointwest.training.beans.EmployeeBean;
import com.pointwest.training.dao.SearchDao;
import com.pointwest.training.dao.ViewSeatPlanDao;
import com.pointwest.training.exception.DaoException;

public class ViewSeatPlanService {
	
	public HashMap<String, EmployeeBean> viewSeatplanByLocation (String location, String flrLevel) throws DaoException {
		
		ViewSeatPlanDao viewSeatplanByLocation = new ViewSeatPlanDao();
		
		HashMap<String, EmployeeBean> seatsByQuadrant = new HashMap<String, EmployeeBean>();
		
		return null;
	}
	
	public HashMap<String, EmployeeBean> viewSeatplanByQuadrant (String location, String flrLevel, String quadrant) throws DaoException {
		
		ViewSeatPlanDao viewSeatplanByQuadrant = new ViewSeatPlanDao();
		
		HashMap<String, EmployeeBean> seatsByQuadrant = new HashMap<String, EmployeeBean>();

		return null;
	}
}
