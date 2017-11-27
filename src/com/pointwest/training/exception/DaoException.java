package com.pointwest.training.exception;

public class DaoException extends Exception {
	
	private final String userFriendlyErrorMessage;
	
	public DaoException(Exception e, String message) {
		super(e);
		this.userFriendlyErrorMessage = message;
	}
	
	public DaoException(String message) {
		this.userFriendlyErrorMessage = message;
	}
	
	public String getUserFriendlyErrorMessage() {
		return userFriendlyErrorMessage;
	}
	
}
