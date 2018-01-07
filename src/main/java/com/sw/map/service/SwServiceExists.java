package com.sw.map.service;

public class SwServiceExists extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SwServiceExists(String name) {
		super("Service " + name + " already exists");
	}
	
	
}
