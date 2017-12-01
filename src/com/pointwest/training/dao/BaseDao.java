package com.pointwest.training.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.pointwest.training.beans.EmployeeBean;
import com.pointwest.training.beans.SeatBean;
import com.pointwest.training.exception.DaoException;

public abstract class BaseDao {

	Logger logger = Logger.getLogger(BaseDao.class);

	protected Connection conn = null;
	protected PreparedStatement ps = null;
	protected ResultSet rs = null;	
	
	protected Connection establishConnection() throws DaoException {

		// Inputs for database
		String host = "jdbc:mysql://localhost:3306/PLSDB";
		String uName = "root";
		String uPass = "password123";

		Connection conn = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(host, uName, uPass);

			logger.info("Connected to Database: " + conn);
		} catch (ClassNotFoundException e) {
			throw new DaoException(e, "ClassNotFound: Error connecting to Database.");
		} catch (Exception e) {
			throw new DaoException(e, "Error Connecting to Database");
		}

		return conn;
	}

	protected void closeResource() {
		try {
			if (conn != null) {
				conn.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (rs != null) {
				rs.close();
			}

		} catch (SQLException se) {
			logger.error("Failed to close connection objects");
		} catch (Exception e) {

		}
	}
	
	protected SeatBean setSeatData() throws SQLException {
		SeatBean seat = new SeatBean();
		
		seat.setSeatId(rs.getInt("seat_id"));
		seat.setSeatBldgId(rs.getString("bldg_id"));
		seat.setSeatFlrNum(rs.getInt("floor_number"));
		seat.setSeatQuadrant(rs.getString("quadrant"));
		seat.setSeatColumnNum(rs.getInt("column_number"));
		seat.setSeatRowNum(rs.getInt("row_number"));
		seat.setLocalNumber(rs.getInt("local_number"));
		
		return seat;
	}
	
	protected abstract EmployeeBean setEmployeeData() throws SQLException;
}
