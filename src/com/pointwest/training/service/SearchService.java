package com.pointwest.training.service;

import java.util.HashMap;

import com.pointwest.training.beans.EmployeeBean;
import com.pointwest.training.beans.SeatBean;
import com.pointwest.training.constants.Constants;
import com.pointwest.training.dao.SearchDao;
import com.pointwest.training.exception.DaoException;

public class SearchService {
	public HashMap<Integer, EmployeeBean> searchEmployeeById(String searchTxt) throws DaoException {
		
		SearchDao searchDao = new SearchDao();
		
		HashMap<Integer, EmployeeBean> searchResultByEmployeeId = new HashMap<Integer, EmployeeBean>();
		
		searchResultByEmployeeId = searchDao.searchEmployeeById(searchTxt);
		
		return searchResultByEmployeeId;
	}
}
