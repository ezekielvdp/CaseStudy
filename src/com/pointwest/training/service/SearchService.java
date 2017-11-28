package com.pointwest.training.service;

import java.util.HashMap;

import com.pointwest.training.beans.EmployeeBean;
import com.pointwest.training.dao.SearchDao;
import com.pointwest.training.exception.DaoException;

public class SearchService {
	public HashMap<Integer, EmployeeBean> searchEmployeeById(String empId) throws DaoException {
		
		SearchDao searchDao = new SearchDao();
		
		HashMap<Integer, EmployeeBean> searchResultByEmployeeId = new HashMap<Integer, EmployeeBean>();
		
		searchResultByEmployeeId = searchDao.searchEmployeeById(empId);
		
		return searchResultByEmployeeId;
	}
}
