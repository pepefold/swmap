package com.sw.map.services;

public class LocationFactory {
	public static String AHML = "AHML";
	public static String CHICAGO_EXECUTIVE_AIRPORT = "Chicago Executive Airport";
	public static String RIVERS_CASINO = "Rivers Casino";
	
	
	
	
	public static Location getLocation(String serviceName) {
		if (serviceName.equals(AHML)) {
			return new Location(42.087641f,-87.9857143f);
		}else if (serviceName.equals(CHICAGO_EXECUTIVE_AIRPORT)) {
			return new Location(42.114501f,-87.9339974f);
		}else {
			// RIVERS CASINO
			return new Location(42.0231316f,-88.0025056f);
		}
		
	}
}
