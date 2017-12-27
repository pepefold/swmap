package com.sw.map.services;

public class SwServiceExists extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SwServiceExists(String name) {
		super("Service " + name + " already exists");
	}
	
	
}
