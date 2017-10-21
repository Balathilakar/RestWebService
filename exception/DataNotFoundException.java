package com.service.java.exception;

public class DataNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8916026389022222093L;
	
	public DataNotFoundException(String message){
		super(message);
	}
}
