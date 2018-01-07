package com.sw.map.location;

import java.sql.Timestamp;

public class LocationFactory {
	public static String AHML = "AHML";
	public static String CHICAGO_EXECUTIVE_AIRPORT = "Chicago Executive Airport";
	public static String RIVERS_CASINO = "Rivers Casino";
	
	
	
	
	public static Location getLocation(String serviceName) {
		Location location;
		if (serviceName.equals(AHML)) {
			location = new Location(42.087641f,-87.9857143f);
		}else if (serviceName.equals(CHICAGO_EXECUTIVE_AIRPORT)) {
			location = new Location(42.114501f,-87.9339974f);
		}else {
			// RIVERS CASINO
			location = new Location(42.0231316f,-88.0025056f);
		}
		
		location.setTime(System.currentTimeMillis());
		
		return location;
	}
}
