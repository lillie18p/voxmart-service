package com.voxmart.exception;

public class CustomException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private final String mesage;

	public CustomException(String mesage) {
		super();
		this.mesage = mesage;
	}

	@Override
	public String toString() {
		return "CustomException [mesage=" + mesage + "]";
	}
	
}