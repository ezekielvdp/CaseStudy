package com.pointwest.training.dao;

import java.sql.SQLException;
import java.util.HashMap;

import com.pointwest.training.beans.EmployeeBean;
import com.pointwest.training.beans.SeatBean;
import com.pointwest.training.exception.DaoException;

public class ViewSeatPlanDao extends BaseDao {
	public HashMap<String, EmployeeBean> searchEmployeeById(String searchTxt) throws DaoException {

		HashMap<String, EmployeeBean> seatsByQuadrant = new HashMap<String, EmployeeBean>();
				
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
