package com.pointwest.training.beans;

public class SeatBean {
	
	// Employee Seat Info
	private int seatId;
	private String seatBldgId;
	private int seatFlrNum;
	private String seatQuadrant;
	private int seatRowNum;
	private int seatColumnNum;
	private int localNumber;

	public int getSeatId() {
		return seatId;
	}
	public void setSeatId(int seatId) {
		this.seatId = seatId;
	}
	public String getSeatBldgId() {
		return seatBldgId;
	}
	public void setSeatBldgId(String seatBldg) {
		this.seatBldgId = seatBldg;
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
