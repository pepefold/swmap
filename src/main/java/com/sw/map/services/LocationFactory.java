package com.sw.map.services;

public class LocationFactory {
	public static String AHML = "AHML";
	public static String CHICAGO_EXECUTIVE_AIRPORT = "Chicago Executive Airport";
	public static String RIVERS_CASINO = "Rivers Casino";
	
	
	
	
	public static Location getLocation(Service service) {
		String name = service.getName();
		
		if (name.equals(AHML)) {
			return new Location(service, 42.087641f,-87.9857143f);
		}else if (name.equals(CHICAGO_EXECUTIVE_AIRPORT)) {
			return new Location(service, 42.114501f,-87.9339974f);
		}else {
			// RIVERS CASINO
			return new Location(service, 42.0231316f,-88.0025056f);
		}
		
	}
}
