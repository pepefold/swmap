package com.sw.map.service;

public class NoLocationFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2926489516660396008L;

	public NoLocationFoundException(String message) {
		super(message);
	}
}
