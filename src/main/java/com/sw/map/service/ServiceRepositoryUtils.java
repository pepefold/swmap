package com.sw.map.service;


import java.util.*;

import com.sw.utils.InputValidation;

public class ServiceRepositoryUtils {
	 /**
	  * Validate the user id	
	  * @param serviceName
	  */
	public static void validateServiceByName(ServiceRepository repository, String serviceName) {
		repository.findByName(serviceName).orElseThrow( () ->
			new SwServiceNotFoundException(serviceName));
	}
	
	public static Service getServiceById(ServiceRepository repo, String userId) {
		ServiceRepositoryUtils.validateServiceId(userId);
		
		//validate input to throw 404
		Optional<Service> serviceOpt = repo.
				findById(Long.parseLong(userId));
		
		// throw if resource is not found
		serviceOpt.orElseThrow(ServiceRepositoryUtils::throwSwServiceNotFound);
		
		return serviceOpt.get();
	}
	
	public static void validateServiceId(String id) {
		//validate the id so it return 404 later
		InputValidation.validateNumber(id).orElseThrow(ServiceRepositoryUtils::throwSwServiceNotFound);
	}
	
	
	public static SwServiceNotFoundException throwSwServiceNotFound() {
		return new SwServiceNotFoundException("Invalid service id ");
	}
}
