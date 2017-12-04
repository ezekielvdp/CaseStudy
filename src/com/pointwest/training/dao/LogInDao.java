package com.pointwest.training.dao;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.pointwest.training.beans.EmployeeBean;
import com.pointwest.training.beans.SeatBean;
import com.pointwest.training.beans.UserBean;
import com.pointwest.training.exception.DaoException;

public class LogInDao extends BaseDao {
	
	Logger logger = Logger.getLogger(LogInDao.class);
	
	public UserBean searchCredentials(String userName, String userPass) throws DaoException {
		UserBean user = null;
		
		try {
			conn = establishConnection();
			
			String query = "SELECT emp.emp_id, "
						   + "username, "
						   + "emp.role, first_name, "
						   + "last_name, emp.shift, "
						   + "emp_proj.proj_alias, "
						   + "seat.seat_id, "
						   + "loc.bldg_address, "
						   + "seat.bldg_id, "
						   + "seat.floor_number, "
						   + "seat.quadrant, "
						   + "seat.column_number, "
						   + "seat.row_number, "
						   + "seat.local_number "
						   + "FROM plsdb.employee "
						   + "AS emp INNER JOIN plsdb.employee_project AS emp_proj ON emp.emp_id = emp_proj.employee_id "
						   + "INNER JOIN plsdb.employee_seat AS emp_seat ON emp.emp_id = emp_seat.emp_id "
						   + "INNER JOIN plsdb.seat AS seat ON emp_seat.seat_id = seat.seat_id "
						   + "INNER JOIN plsdb.location AS loc ON seat.bldg_id = loc.bldg_id "
						   + "WHERE username = ? AND password = ?";
			
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
					
					//int empId = rs.getInt("emp_id");
					
					if(user.getUserName() == rs.getString("username")) {
						
						String project = rs.getString("proj_alias"); 
						boolean isProjectExistInList = user.getEmployeeProjects().contains(project);
						
						// Add new project if does not project not exist in list
						if(!isProjectExistInList) {
							user.addToProjectList(rs.getString("proj_alias"));
						}
						
						// Instantiate SeatBean
						SeatBean seat = new SeatBean();
						
						// set seat info values
						seat = setSeatData();
						
						// Add seat to the employee seat list, if not the same
						if(user.getListOfSeats().contains(seat)) {
							user.addToSeatList(seat);
						}
					} else { 
						SeatBean seat = new SeatBean();
						EmployeeBean employee = new EmployeeBean();
						
						// Set employee infos values
						employee = setEmployeeData();
						
						// transfer employee info to user info
						user.setEmployeeId(employee.getEmployeeId());
						user.setUserRole(rs.getString("emp.role"));
						user.setEmployeeFirstName(employee.getEmployeeFirstName());
						user.setEmployeeLastName(employee.getEmployeeLastName());
						user.setEmployeeProjects(employee.getEmployeeProjects());
						user.setEmployeeShift(employee.getEmployeeShift());
											
						// set seat info values
						seat = setSeatData();
						
						// Add seat to the employee seat list
						user.addToSeatList(seat);
					}
				}
			}			
		} catch (SQLException se) {
			String message = "SQLException Error: There might be a problem in the way we have inputted the data.";
			logger.error(message + ", ExceptionMessage: " + se.getMessage());
			throw new DaoException(se, message);
		} catch (Exception e) {
			String message = "Database Error: Something went wrong with database connection.";
			logger.error(message + ", ExceptionMessage: " + e.getMessage());
			throw new DaoException(e, message);
		} finally {
			// Close conn, ps, rs
			closeResource();
		}
		
		return user;
	}

	@Override
	protected EmployeeBean setEmployeeData() throws SQLException {
		EmployeeBean employee = new EmployeeBean();
		
		employee.setEmployeeId(rs.getInt("emp_id"));
		employee.setEmployeeFirstName(rs.getString("first_name"));
		employee.setEmployeeLastName(rs.getString("last_name"));
		employee.setEmployeeShift(rs.getString("shift"));
		employee.addToProjectList(rs.getString("proj_alias"));
		
		return employee;
	}
}
