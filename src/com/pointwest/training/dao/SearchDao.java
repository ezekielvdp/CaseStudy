package com.pointwest.training.dao;

import java.sql.SQLException;
import java.util.HashMap;

import com.pointwest.training.beans.EmployeeBean;
import com.pointwest.training.beans.SeatBean;
import com.pointwest.training.exception.DaoException;

public class SearchDao extends BaseDao {
	
	public HashMap<Integer, EmployeeBean> searchEmployeeById(String searchTxt) throws DaoException {

		HashMap<Integer, EmployeeBean> employeesByEmpId = new HashMap<Integer, EmployeeBean>();
				
		try {
			conn = establishConnection();

			String query = "SELECT emp.emp_id, "
					+ "seat.seat_id, "
					+ "emp.role, "
					+ "first_name, "
					+ "last_name, "
					+ "seat.bldg_id, "
					+ "seat.floor_number, "
					+ "seat.quadrant, "
					+ "seat.column_number, "
					+ "seat.row_number, "
					+ "seat.local_number, "
					+ "emp.shift, "
					+ "emp_proj.proj_alias "
					+ "FROM plsdb.employee AS emp "
					+ "INNER JOIN plsdb.employee_project AS emp_proj ON emp.emp_id = emp_proj.employee_id "
					+ "INNER JOIN plsdb.employee_seat AS emp_seat ON emp.emp_id = emp_seat.emp_id "
					+ "INNER JOIN plsdb.seat AS seat ON emp_seat.seat_id = seat.seat_id " 
					+ "WHERE emp.emp_id LIKE ? AND NOT proj_alias = 'BogusProject'"
					+ "ORDER BY emp_id DESC";

			ps = conn.prepareStatement(query);
			ps.setString(1, '%' + searchTxt + '%');
			rs = ps.executeQuery();

			if (rs.next()) {
				// Reset rs cursor to start
				rs.beforeFirst();
				
				EmployeeBean employee = null;
				
				// Get user data
				while (rs.next()) {
					
					int empId = rs.getInt("emp_id");
					
					if(employeesByEmpId.containsKey(empId)) {
						
						// Load old employee
						employee = employeesByEmpId.get(empId);
						
						String project = rs.getString("proj_alias"); 
						boolean isProjectExistInList = employee.getEmployeeProjects().contains(project);
						
						// Add new project if does not project not exist in list
						if(!isProjectExistInList) {
							employee.addToProjectList(rs.getString("proj_alias"));
						}
						
						// Instantiate SeatBean
						SeatBean seat = new SeatBean();
						
						// Set seat values
						seat.setSeatId(rs.getInt("seat_id"));
						seat.setSeatBldgId(rs.getString("bldg_id"));
						seat.setSeatFlrNum(rs.getInt("floor_number"));
						seat.setSeatQuadrant(rs.getString("quadrant"));
						seat.setSeatColumnNum(rs.getInt("column_number"));
						seat.setSeatRowNum(rs.getInt("row_number"));
						seat.setLocalNumber(rs.getInt("local_number"));
						
						// Add seat to the employee seat list, if not the same
						if(employee.getListOfSeats().contains(seat)) {
							employee.addToSeatList(seat);
						}
					
						// Replace value
						employeesByEmpId.replace(empId, employee);
						
					} else { 
						// If emp doesn't exist on the hashmap GO HERE.
						
						// Instantiate EmployeeBean & SeatBean
						employee = new EmployeeBean();
						SeatBean seat = new SeatBean();
						
						// Set employee infos values
						employee.setEmployeeId(rs.getInt("emp_id"));
						employee.setEmployeeRole(rs.getString("role"));
						employee.setEmployeeFirstName(rs.getString("first_name"));
						employee.setEmployeeLastName(rs.getString("last_name"));
						employee.setEmployeeShift(rs.getString("shift"));
						employee.addToProjectList(rs.getString("proj_alias"));
						
						// Set seat values
						seat.setSeatId(rs.getInt("seat_id"));
						seat.setSeatBldgId(rs.getString("bldg_id"));
						seat.setSeatFlrNum(rs.getInt("floor_number"));
						seat.setSeatQuadrant(rs.getString("quadrant"));
						seat.setSeatColumnNum(rs.getInt("column_number"));
						seat.setSeatRowNum(rs.getInt("row_number"));
						seat.setLocalNumber(rs.getInt("local_number"));
						
						// Add seat to the employee seat list
						employee.addToSeatList(seat);
		
						employeesByEmpId.put(empId, employee);
					}
				}
			}
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			String message = "Error: Invalid SQL Query";
			logger.error(message);
			throw new DaoException(se, message);
		} catch (Exception e) {
			String message = "Something went wrong while trying log in.";
			logger.error(message);
			throw new DaoException(e, message);
		} finally {
			// Close conn, ps, rs
			closeResource();
		}

		return employeesByEmpId;
	}
	
	public HashMap<Integer, EmployeeBean> searchEmployeeByName(String searchTxt) throws DaoException {

		HashMap<Integer, EmployeeBean> employeesByEmpId = new HashMap<Integer, EmployeeBean>();
				
		try {
			conn = establishConnection();

			String query = "SELECT emp.emp_id, "
					+ "seat.seat_id, "
					+ "emp.role, "
					+ "first_name, "
					+ "last_name, "
					+ "seat.bldg_id, "
					+ "seat.floor_number, "
					+ "seat.quadrant, "
					+ "seat.column_number, "
					+ "seat.row_number, "
					+ "seat.local_number, "
					+ "emp.shift, "
					+ "emp_proj.proj_alias "
					+ "FROM plsdb.employee AS emp "
					+ "INNER JOIN plsdb.employee_project AS emp_proj ON emp.emp_id = emp_proj.employee_id "
					+ "INNER JOIN plsdb.employee_seat AS emp_seat ON emp.emp_id = emp_seat.emp_id "
					+ "INNER JOIN plsdb.seat AS seat ON emp_seat.seat_id = seat.seat_id " 
					+ "WHERE (emp.first_name LIKE ? OR emp.last_name LIKE ?) AND (NOT proj_alias = 'BogusProject') "
					+ "ORDER BY emp.emp_id";

			ps = conn.prepareStatement(query);
			ps.setString(1, '%' + searchTxt + '%');
			ps.setString(2, '%' + searchTxt + '%');
			rs = ps.executeQuery();

			if (rs.next()) {
				// Reset rs cursor to start
				rs.beforeFirst();
				
				EmployeeBean employee = null;
				
				// Get user data
				while (rs.next()) {
					
					int empId = rs.getInt("emp_id");
					
					if(employeesByEmpId.containsKey(empId)) {
						
						// Load old employee
						employee = employeesByEmpId.get(empId);
						
						String project = rs.getString("proj_alias"); 
						boolean isProjectExistInList = employee.getEmployeeProjects().contains(project);
						
						// Add new project if does not project not exist in list
						if(!isProjectExistInList) {
							employee.addToProjectList(rs.getString("proj_alias"));
						}
						
						// Instantiate SeatBean
						SeatBean seat = new SeatBean();
						
						// Set seat values
						seat.setSeatId(rs.getInt("seat_id"));
						seat.setSeatBldgId(rs.getString("bldg_id"));
						seat.setSeatFlrNum(rs.getInt("floor_number"));
						seat.setSeatQuadrant(rs.getString("quadrant"));
						seat.setSeatColumnNum(rs.getInt("column_number"));
						seat.setSeatRowNum(rs.getInt("row_number"));
						seat.setLocalNumber(rs.getInt("local_number"));
						
						// Add seat to the employee seat list, if not the same
						if(employee.getListOfSeats().contains(seat)) {
							employee.addToSeatList(seat);
						}
					
						// Replace value
						employeesByEmpId.replace(empId, employee);
						
					} else { 
						// If emp doesn't exist on the hashmap GO HERE.
						
						// Instantiate EmployeeBean & SeatBean
						employee = new EmployeeBean();
						SeatBean seat = new SeatBean();
						
						// Set employee infos values
						employee.setEmployeeId(rs.getInt("emp_id"));
						employee.setEmployeeRole(rs.getString("role"));
						employee.setEmployeeFirstName(rs.getString("first_name"));
						employee.setEmployeeLastName(rs.getString("last_name"));
						employee.setEmployeeShift(rs.getString("shift"));
						employee.addToProjectList(rs.getString("proj_alias"));
						
						// Set seat values
						seat.setSeatId(rs.getInt("seat_id"));
						seat.setSeatBldgId(rs.getString("bldg_id"));
						seat.setSeatFlrNum(rs.getInt("floor_number"));
						seat.setSeatQuadrant(rs.getString("quadrant"));
						seat.setSeatColumnNum(rs.getInt("column_number"));
						seat.setSeatRowNum(rs.getInt("row_number"));
						seat.setLocalNumber(rs.getInt("local_number"));
						
						// Add seat to the employee seat list
						employee.addToSeatList(seat);
		
						employeesByEmpId.put(empId, employee);
					}
				}
			}
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			String message = "Error: Invalid SQL Query";
			logger.error(message);
			throw new DaoException(se, message);
		} catch (Exception e) {
			String message = "Something went wrong while trying log in.";
			logger.error(message);
			throw new DaoException(e, message);
		} finally {
			// Close conn, ps, rs
			closeResource();
		}

		return employeesByEmpId;
	}
	
	public HashMap<Integer, EmployeeBean> searchEmployeeByProject(String searchTxt) throws DaoException {

		HashMap<Integer, EmployeeBean> employeesByEmpId = new HashMap<Integer, EmployeeBean>();
				
		try {
			conn = establishConnection();

			String query = "SELECT emp.emp_id, "
					+ "seat.seat_id, "
					+ "emp.role, "
					+ "first_name, "
					+ "last_name, "
					+ "seat.bldg_id, "
					+ "seat.floor_number, "
					+ "seat.quadrant, "
					+ "seat.column_number, "
					+ "seat.row_number, "
					+ "seat.local_number, "
					+ "emp.shift, "
					+ "emp_proj.proj_alias "
					+ "FROM plsdb.employee AS emp "
					+ "INNER JOIN plsdb.employee_project AS emp_proj ON emp.emp_id = emp_proj.employee_id "
					+ "INNER JOIN plsdb.employee_seat AS emp_seat ON emp.emp_id = emp_seat.emp_id "
					+ "INNER JOIN plsdb.seat AS seat ON emp_seat.seat_id = seat.seat_id " 
					+ "WHERE emp_proj.proj_alias LIKE ?"
					+ "ORDER BY emp.emp_id";

			ps = conn.prepareStatement(query);
			ps.setString(1, '%' + searchTxt + '%');
			rs = ps.executeQuery();

			if (rs.next()) {
				// Reset rs cursor to start
				rs.beforeFirst();
				
				EmployeeBean employee = null;
				
				// Get user data
				while (rs.next()) {
					
					int empId = rs.getInt("emp_id");
					
					if(employeesByEmpId.containsKey(empId)) {
						
						// Load old employee
						employee = employeesByEmpId.get(empId);
						
						String project = rs.getString("proj_alias"); 
						boolean isProjectExistInList = employee.getEmployeeProjects().contains(project);
						
						// Add new project if does not project not exist in list
						if(!isProjectExistInList) {
							employee.addToProjectList(rs.getString("proj_alias"));
						}
						
						// Instantiate SeatBean
						SeatBean seat = new SeatBean();
						
						// Set seat values
						seat.setSeatId(rs.getInt("seat_id"));
						seat.setSeatBldgId(rs.getString("bldg_id"));
						seat.setSeatFlrNum(rs.getInt("floor_number"));
						seat.setSeatQuadrant(rs.getString("quadrant"));
						seat.setSeatColumnNum(rs.getInt("column_number"));
						seat.setSeatRowNum(rs.getInt("row_number"));
						seat.setLocalNumber(rs.getInt("local_number"));
						
						// Add seat to the employee seat list, if not the same
						if(employee.getListOfSeats().contains(seat)) {
							employee.addToSeatList(seat);
						}
					
						// Replace value
						employeesByEmpId.replace(empId, employee);
						
					} else { 
						// If emp doesn't exist on the hashmap GO HERE.
						
						// Instantiate EmployeeBean & SeatBean
						employee = new EmployeeBean();
						SeatBean seat = new SeatBean();
						
						// Set employee infos values
						employee.setEmployeeId(rs.getInt("emp_id"));
						employee.setEmployeeRole(rs.getString("role"));
						employee.setEmployeeFirstName(rs.getString("first_name"));
						employee.setEmployeeLastName(rs.getString("last_name"));
						employee.setEmployeeShift(rs.getString("shift"));
						employee.addToProjectList(rs.getString("proj_alias"));
						
						// Set seat values
						seat.setSeatId(rs.getInt("seat_id"));
						seat.setSeatBldgId(rs.getString("bldg_id"));
						seat.setSeatFlrNum(rs.getInt("floor_number"));
						seat.setSeatQuadrant(rs.getString("quadrant"));
						seat.setSeatColumnNum(rs.getInt("column_number"));
						seat.setSeatRowNum(rs.getInt("row_number"));
						seat.setLocalNumber(rs.getInt("local_number"));
						
						// Add seat to the employee seat list
						employee.addToSeatList(seat);
		
						employeesByEmpId.put(empId, employee);
					}
				}
			}
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			String message = "Error: Invalid SQL Query";
			logger.error(message);
			throw new DaoException(se, message);
		} catch (Exception e) {
			String message = "Something went wrong while trying log in.";
			logger.error(message);
			throw new DaoException(e, message);
		} finally {
			// Close conn, ps, rs
			closeResource();
		}

		return employeesByEmpId;
	}
}
