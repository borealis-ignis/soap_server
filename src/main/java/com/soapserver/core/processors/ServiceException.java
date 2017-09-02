package com.soapserver.core.processors;

public class ServiceException extends Exception {
	
	private static final long serialVersionUID = -7662734927617759065L;
	
	public ServiceException(String errorMessage) {
		super(errorMessage);
	}
}
