package com.pointwest.training.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.pointwest.training.beans.EmployeeBean;
import com.pointwest.training.beans.SeatBean;
import com.pointwest.training.exception.DaoException;

public class ViewSeatPlanDao extends BaseDao {
	
	
	public HashMap<String, List<EmployeeBean>> viewSeatPlanByLocation(String location, String flrLevel) throws DaoException {
		HashMap<String, List<EmployeeBean>> seatsByQuadrant = new HashMap<String, List<EmployeeBean>>();
				
		try {
			conn = establishConnection();

			String query = "SELECT seat.seat_id, "
						   + "loc.bldg_address, "
						   + "emp_seat.emp_id, "
						   + "emp.first_name, "
						   + "emp.last_name, "
						   + "seat.bldg_id, "
						   + "seat.floor_number, "
						   + "seat.quadrant, "
						   + "seat.column_number, "
						   + "seat.row_number, "
						   + "seat.local_number "
						   + "FROM plsdb.seat AS seat "
						   + "LEFT JOIN plsdb.employee_seat AS emp_seat ON seat.seat_id = emp_seat.seat_id "
						   + "LEFT JOIN location AS loc ON seat.bldg_id = loc.bldg_id "
						   + "LEFT JOIN employee AS emp ON emp.emp_id = emp_seat.emp_id "
						   + "WHERE seat.bldg_id = ? AND seat.floor_number = ? "
						   + "ORDER by seat_id, bldg_id, quadrant, row_number";

			ps = conn.prepareStatement(query);
			ps.setString(1, location);
			ps.setString(2, flrLevel);
			rs = ps.executeQuery();

			if (rs.next()) {
				// Reset rs cursor to start
				rs.beforeFirst();
				
				// Reinstantiate all needed data
				EmployeeBean employee = null;
				List<EmployeeBean> listOfSeatOnQuadrant = null;
				
				// Get user data
				while (rs.next()) {
					String currQuadrant = rs.getString("quadrant");
					
					employee = new EmployeeBean();
					
					// if key already exist
					if(seatsByQuadrant.containsKey(currQuadrant)) {
						listOfSeatOnQuadrant = seatsByQuadrant.get(currQuadrant);
						
						employee = setEmployeeData();
						
						SeatBean seat = new SeatBean();
						seat = setSeatData();
						
						employee.addToSeatList(seat);
						
						listOfSeatOnQuadrant.add(employee);
						seatsByQuadrant.put(currQuadrant, listOfSeatOnQuadrant);
						
					} else {									
						listOfSeatOnQuadrant = new ArrayList<EmployeeBean>();
						
						employee = setEmployeeData();
						
						SeatBean seat = new SeatBean();
						seat = setSeatData();
						
						employee.addToSeatList(seat);
						
						listOfSeatOnQuadrant.add(employee);
						seatsByQuadrant.put(currQuadrant, listOfSeatOnQuadrant);
					}
				}
			}
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			String message = "Error: Invalid SQL Query";
			logger.error(message);
			throw new DaoException(se, message);
		} catch (Exception e) {
			String message = "Something went wrong.";
			logger.error(message);
			throw new DaoException(e, message);
		} finally {
			// Close conn, ps, rs
			closeResource();
		}

		return seatsByQuadrant;
	}

	public HashMap<String, List<EmployeeBean>> viewSeatPlanByQuadrant(String location, String flrLevel, String quadrant) throws DaoException {
		HashMap<String, List<EmployeeBean>> seatsByQuadrant = new HashMap<String, List<EmployeeBean>>();
				
		try {
			conn = establishConnection();

			String query = "SELECT seat.seat_id, "
						   + "loc.bldg_address, "
						   + "emp_seat.emp_id, "
						   + "emp.first_name, "
						   + "emp.last_name, "
						   + "seat.bldg_id, "
						   + "seat.floor_number, "
						   + "seat.quadrant, "
						   + "seat.column_number, "
						   + "seat.row_number, "
						   + "seat.local_number "
						   + "FROM plsdb.seat AS seat "
						   + "LEFT JOIN plsdb.employee_seat AS emp_seat ON seat.seat_id = emp_seat.seat_id "
						   + "LEFT JOIN location AS loc ON seat.bldg_id = loc.bldg_id "
						   + "LEFT JOIN employee AS emp ON emp.emp_id = emp_seat.emp_id "
						   + "WHERE seat.bldg_id = ? AND seat.floor_number = ? AND seat.quadrant = ? "
						   + "ORDER by seat_id, bldg_id, quadrant, row_number";

			ps = conn.prepareStatement(query);
			ps.setString(1, location);
			ps.setString(2, flrLevel);
			ps.setString(3, quadrant);
			rs = ps.executeQuery();

			if (rs.next()) {
				// Reset rs cursor to start
				rs.beforeFirst();
				
				// Reinstantiate all needed data
				EmployeeBean employee = null;
				List<EmployeeBean> listOfSeatOnQuadrant = null;
				
				// Get user data
				while (rs.next()) {
					String currQuadrant = rs.getString("quadrant");
					
					employee = new EmployeeBean();
					
					// if key already exist
					if(seatsByQuadrant.containsKey(currQuadrant)) {
						listOfSeatOnQuadrant = seatsByQuadrant.get(currQuadrant);
						
						employee = setEmployeeData();
						
						SeatBean seat = new SeatBean();
						seat = setSeatData();
						
						employee.addToSeatList(seat);
						
						listOfSeatOnQuadrant.add(employee);
						seatsByQuadrant.put(currQuadrant, listOfSeatOnQuadrant);
						
					} else {									
						listOfSeatOnQuadrant = new ArrayList<EmployeeBean>();
						
						employee = setEmployeeData();
						
						SeatBean seat = new SeatBean();
						seat = setSeatData();
						
						employee.addToSeatList(seat);
						
						listOfSeatOnQuadrant.add(employee);
						seatsByQuadrant.put(currQuadrant, listOfSeatOnQuadrant);
					}
				}
			}
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			String message = "Error: Invalid SQL Query";
			logger.error(message);
			throw new DaoException(se, message);
		} catch (Exception e) {
			String message = "Something went wrong.";
			logger.error(message);
			throw new DaoException(e, message);
		} finally {
			// Close conn, ps, rs
			closeResource();
		}

		return seatsByQuadrant;
	}
	
	@Override
	protected EmployeeBean setEmployeeData() throws SQLException {
		EmployeeBean employee = new EmployeeBean();
		
		employee.setEmployeeFirstName(rs.getString("first_name"));
		employee.setEmployeeLastName(rs.getString("last_name"));
		
		return employee;
	}
}
