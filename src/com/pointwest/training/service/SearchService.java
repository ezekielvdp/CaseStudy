package com.pointwest.training.service;

import java.util.ArrayList;
import java.util.List;

import com.pointwest.training.beans.EmployeeBean;
import com.pointwest.training.dao.SearchDao;

public class SearchService {
	public List<EmployeeBean> searchEmployeeById(String empId) {
		
		SearchDao searchDao = new SearchDao();
		
		List<EmployeeBean> searchResultByEmployeeId = new ArrayList<EmployeeBean>();
		
		//searchResultByEmployeeId = searchDao;
		
		return searchResultByEmployeeId;
	}
}
