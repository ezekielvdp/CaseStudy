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
			logger.error("Error: Invalid SQL Query");
			throw new DaoException(se, "Error: Invalid SQL Query");
		} catch (Exception e) {
			throw new DaoException(e, "Error: " + e.getMessage());
		} finally {
			// Close conn, ps, rs
			closeResource();
		}
		
		return user;
	}
}
