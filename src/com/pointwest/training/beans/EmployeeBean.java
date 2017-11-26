package com.pointwest.training.beans;

import java.util.HashMap;
import java.util.List;

public class EmployeeBean {
	
	// Employee Info
	private int employeeId;
	private String employeeRole;
	private String employeeFirstName;
	private String employeeLastName;
	private List<String> teamProject;
	
	// Employee Seat Info
	private String seatBldg;
	private int seatFlrNum;
	private String seatQuadrant;
	private int seatRowNum;
	private int seatColumnNum;
	private int localNumber;
	
	private HashMap<String, Seat> seatByBuilding = new HashMap<String, SeatInfo>();
	
	// Getters and Setters
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeRole() {
		return employeeRole;
	}
	public void setEmployeeRole(String employeeRole) {
		this.employeeRole = employeeRole;
	}
	public String getEmployeeFirstName() {
		return employeeFirstName;
	}
	public void setEmployeeFirstName(String employeeFirstName) {
		this.employeeFirstName = employeeFirstName;
	}
	public String getEmployeeLastName() {
		return employeeLastName;
	}
	public void setEmployeeLastName(String employeeLastName) {
		this.employeeLastName = employeeLastName;
	}
	public List<String> getTeamProject() {
		return teamProject;
	}
	public void setTeamProject(List<String> teamProject) {
		this.teamProject = teamProject;
	}
	public String getSeatBldg() {
		return seatBldg;
	}
	public void setSeatBldg(String seatBldg) {
		this.seatBldg = seatBldg;
	}
	public int getSeatFlrNum() {
		return seatFlrNum;
	}
	public void setSeatFlrNum(int seatFlrNum) {
		this.seatFlrNum = seatFlrNum;
	}
	public String getSeatQuadrant() {
		return seatQuadrant;
	}
	public void setSeatQuadrant(String seatQuadrant) {
		this.seatQuadrant = seatQuadrant;
	}
	public int getSeatRowNum() {
		return seatRowNum;
	}
	public void setSeatRowNum(int seatRowNum) {
		this.seatRowNum = seatRowNum;
	}
	public int getSeatColumnNum() {
		return seatColumnNum;
	}
	public void setSeatColumnNum(int seatColumnNum) {
		this.seatColumnNum = seatColumnNum;
	}
	public int getLocalNumber() {
		return localNumber;
	}
	public void setLocalNumber(int localNumber) {
		this.localNumber = localNumber;
	}
	
}
