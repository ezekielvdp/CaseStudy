package com.pointwest.training.beans;

public class SeatBean {
	
	// TODO: Edit SeatBean to have dynamic seats etc etc lol.
	// Employee Seat Info
	private String seatBldg;
	private int seatFlrNum;
	private String seatQuadrant;
	private int seatRowNum;
	private int seatColumnNum;
	private int localNumber;

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
