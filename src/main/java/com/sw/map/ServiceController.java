package com.sw.map;

import java.net.URI;
import java.util.Collection;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sw.map.services.Location;
import com.sw.map.services.Service;
import com.sw.map.services.SwServiceExists;
import com.sw.map.services.SwServiceNotFoundException;

@RestController
@RequestMapping("services/{userId}")
public class ServiceController {
	
	private ServiceRepository serviceRepository;
	private LocationRepository locationRepository;
	
	@Autowired
	ServiceController(ServiceRepository serviceRepository, LocationRepository locationRepository) {
		this.serviceRepository = serviceRepository;
		this.locationRepository = locationRepository;
	}
	/**
	 * Find all services
	 * 
	 * @return the collection of all services
	 */
	Collection<Service> getAll(){
		return this.serviceRepository.findAll();
	}
	/**
	 * Add a service with specified name
	 * 
	 * @param name the name of the service
	 * 
	 * @return r	
	 */
	@RequestMapping(method=RequestMethod.POST)
	ResponseEntity<?> add(@RequestBody Service input){
		
		this.serviceRepository.findByName(input.getName()).ifPresent(s-> {
			throw new SwServiceExists(input.getName());
			});
		
		Service service = new Service(input.getName());
		this.serviceRepository.save(service);
		
		Set<Location> locations = input.getLocations();
		locations.stream().forEach(l->{
			Location location = new Location(service, l.getLatitude(), l.getLongitude());
			this.locationRepository.save(location);
		});
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(service.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	ResponseEntity<?> removeService(@PathVariable String userId){
		
		// TODO: validate
		long id = Long.parseLong(userId);
		Service service = this.serviceRepository.findOne(id);
		Set<Location> locations = service.getLocations();
		locations.forEach(l->{
			this.locationRepository.delete(l);
		});
		
		this.serviceRepository.delete(service);
		return ResponseEntity.ok().build();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	Service getServiceById(@PathVariable String userId) {
		//TODO: validate id
		return this.serviceRepository.findOne(Long.parseLong(userId));
	}
	
	 /**
	  * Validate the user id	
	  * @param userId
	  */
	private void validateService(String userId) {
		this.serviceRepository.findByName(userId).orElseThrow( () ->
			new SwServiceNotFoundException(userId));
	}
}
