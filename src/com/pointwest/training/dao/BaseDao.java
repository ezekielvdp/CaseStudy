package com.pointwest.training.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class BaseDao {
	
	protected Connection establishConnection() {
		
		// Inputs for database
		String host = "jdbc:mysql://localhost:3306/PLSDB";
		String uName = "root";
		String uPass = "password123";
		
		Connection conn = null;
	
		try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(host, uName, uPass);   
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
}
