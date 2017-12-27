package com.sw.map;

import com.sw.map.services.SwServiceNotFoundException;

public class ServiceRepositoryUtils {
	 /**
	  * Validate the user id	
	  * @param userId
	  */
	public static void validateService(ServiceRepository repository, String userId) {
		repository.findByName(userId).orElseThrow( () ->
			new SwServiceNotFoundException(userId));
	}
	
}
