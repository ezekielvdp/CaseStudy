package com.pointwest.training.dao;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.pointwest.training.beans.UserBean;
import com.pointwest.training.exception.DaoException;

public class LogInDao extends BaseDao {
	
	Logger logger = Logger.getLogger(LogInDao.class);
	
	public UserBean searchCredentials(String userName, String userPass) throws DaoException {
		UserBean user = null;
		
		try {
			conn = establishConnection();
			
			String query = "SELECT "
							+ "employee.first_name, "
							+ "employee.last_name, "
							+ "employee.role from employee "
							+ "WHERE "
							+ "username = ? AND "
							+ "password = ?";
			
			ps = conn.prepareStatement(query);
			ps.setString(1, userName);
			ps.setString(2, userPass);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				// Reset rs cursor to start
				rs.beforeFirst();
				
				// Instantiate UserBean
				user = new UserBean();
				
				// Get user data
				while(rs.next()) {
					user.setUserFirstName(rs.getString("first_name"));
					user.setUserLastName(rs.getString("last_name"));
					user.setUserRole(rs.getString("role"));
				}
			}			
		} catch (SQLException se) {
			String message = "Error: Something went wrong to the database.";
			logger.error(message);
			throw new DaoException(se, message);
		} catch (Exception e) {
			String message = "Error: Something went wrong while trying log in.";
			logger.error(message);
			throw new DaoException(e, message);
		} finally {
			// Close conn, ps, rs
			closeResource();
		}
		
		return user;
	}
}
