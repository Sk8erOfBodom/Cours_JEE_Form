package com.bge.exception;

public class DBException extends Exception {
	private static final long serialVersionUID = 1L;

	public DBException() {
		super();
	}
	
	public DBException(String message) {
		super(message);
	}
}
