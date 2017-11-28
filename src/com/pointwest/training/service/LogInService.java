package com.pointwest.training.service;

import com.pointwest.training.beans.UserBean;
import com.pointwest.training.dao.LogInDao;
import com.pointwest.training.exception.DaoException;

public class LogInService {
	public UserBean retrieveUser(String inUserName, String inUserPass) throws DaoException {
		
		LogInDao login = new LogInDao();
		UserBean user = login.searchCredentials(inUserName, inUserPass);
		
		return user;
	}
}
