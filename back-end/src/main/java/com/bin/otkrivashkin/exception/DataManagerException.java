package com.bin.otkrivashkin.exception;

public class DataManagerException  extends RuntimeException{

	private String message;

	public DataManagerException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}
}


