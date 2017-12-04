package com.pointwest.training.beans;

import java.util.ArrayList;
import java.util.List;

public class EmployeeBean {
	
	// Employee Info
	private int employeeId;
	private String employeeFirstName;
	private String employeeLastName;
	private String employeeShift;
	private List<String> employeeProjects = new ArrayList<String>();
	private List<SeatBean> employeeSeats = new ArrayList<SeatBean>();
	
	// Getters and Setters
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
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
	public String getEmployeeShift() {
		return employeeShift;
	}
	public void setEmployeeShift(String employeeShift) {
		this.employeeShift = employeeShift;
	}
	
	// Seats
	public List<SeatBean> getListOfSeats() {
		return employeeSeats;
	}
	public void setListOfSeats(List<SeatBean> listOfSeats) {
		this.employeeSeats = listOfSeats;
	}
	
	// Projects
	public List<String> getEmployeeProjects() {
		return employeeProjects;
	}
	public void setEmployeeProjects(List<String> employeeProject) {
		this.employeeProjects = employeeProject;
	}
	
	// Add Function to Lists
	public void addToProjectList(String toAdd) {
		this.employeeProjects.add(toAdd);
	}
	
	public void addToSeatList(SeatBean toAdd) {
		this.employeeSeats.add(toAdd);
	}
}
