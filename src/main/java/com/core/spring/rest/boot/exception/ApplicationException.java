package com.core.spring.rest.boot.exception;


public class ApplicationException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1276186767850741102L;

	public ApplicationException(String message) {
        super(message);
    }

    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }
  

}
