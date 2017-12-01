package com.pointwest.training.service;

import java.util.HashMap;
import java.util.List;

import com.pointwest.training.beans.EmployeeBean;
import com.pointwest.training.dao.SearchDao;
import com.pointwest.training.dao.ViewSeatPlanDao;
import com.pointwest.training.exception.DaoException;

public class ViewSeatPlanService {
	
	public HashMap<String, List<EmployeeBean>> viewSeatplanByLocation (String location, String flrLevel) throws DaoException {
		
		ViewSeatPlanDao viewSeatplanByLocation = new ViewSeatPlanDao();
		
		HashMap<String, List<EmployeeBean>> seatsByQuadrant = new HashMap<String, List<EmployeeBean>>();
		
		seatsByQuadrant = viewSeatplanByLocation.viewSeatPlanByLocation(location, flrLevel);
		
		return seatsByQuadrant;
	}
	
	public HashMap<String, List<EmployeeBean>> viewSeatplanByQuadrant (String location, String flrLevel, String quadrant) throws DaoException {
		
		ViewSeatPlanDao viewSeatplanByQuadrant = new ViewSeatPlanDao();
		
		HashMap<String, List<EmployeeBean>> seatsByQuadrant = new HashMap<String, List<EmployeeBean>>();

		return null;
	}
}
