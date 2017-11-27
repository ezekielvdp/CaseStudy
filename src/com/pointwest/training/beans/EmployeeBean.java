package com.pointwest.training.beans;

import java.util.List;

public class EmployeeBean {
	
	// Employee Info
	private int employeeId;
	private String employeeRole;
	private String employeeFirstName;
	private String employeeLastName;
	private List<String> employeeProject;
	private SeatBean seat = new SeatBean();
	
	// Getters and Setters
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public SeatBean getSeat() {
		return seat;
	}
	public void setSeat(SeatBean seat) {
		this.seat = seat;
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
		return employeeProject;
	}
	public void setTeamProject(List<String> teamProject) {
		this.employeeProject = teamProject;
	}
}
