package com.sw.map.service;

import java.lang.reflect.Field;
import java.net.URI;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sw.map.contractor.Contractor;
import com.sw.map.contractor.ContractorRepository;
import com.sw.map.location.Location;
import com.sw.map.location.LocationRepository;
/**dele
 * Handle following actions for the service
 * GET - get the information about the service
 * POST - add the service
 * DELETE - delete the service
 * @author Dali
 *
 */
@RestController
@RequestMapping("services/{userId}")
public class ServiceController {
	
	private ServiceRepository serviceRepository;
	private LocationRepository locationRepository;
	private ContractorRepository contractorRepository;
	
	@Autowired
	private ObjectMapper mapper;
	
	@Autowired
	ServiceController(ServiceRepository serviceRepository, ContractorRepository contractorRepo, LocationRepository locationRepository) {
		this.serviceRepository = serviceRepository;
		this.locationRepository = locationRepository;
		this.contractorRepository = contractorRepo;
	}
	@RequestMapping(method=RequestMethod.PATCH)
	ResponseEntity<?> update(@PathVariable String userId, @RequestBody Map<String, String> fields){
		Service service = ServiceRepositoryUtils.getServiceById(this.serviceRepository, userId);
		Service serviceToBe = this.mapper.convertValue(fields, Service.class);
//		service.p
	    this.serviceRepository.save(service);
	    
		return ResponseEntity.ok().build();
	}
	
	 public void patch(String userId, Service toBePatched) {
		 Service service = ServiceRepositoryUtils.getServiceById(this.serviceRepository, userId);
//		 beanUtils.copyProperties(fromDb, toBePatched);
//         updateManager(fromDb);
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
		
		if (input.getName() == null) {
			return ResponseEntity.badRequest().body("Service name is not specified 2");
		}
		
		Service service = new Service(input.getName());
		this.serviceRepository.save(service);
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(service.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	/**
	 * Remove service
	 * @param userId
	 * @return
	 */
	@RequestMapping(method = RequestMethod.DELETE)
	ResponseEntity<?> removeService(@PathVariable String userId){
		
		ServiceRepositoryUtils.validateServiceId(userId);
		// TODO: validate

		Service service = getServiceById(userId);
		
		Set<Contractor> contractors = service.getContractors();
		contractors.forEach(c ->{
			
			Set<Location> locations = c.getLocations();
			locations.forEach(l->{
				this.locationRepository.delete(l);
			});
			
			this.contractorRepository.delete(c);
		});
		
		
		this.serviceRepository.delete(service);
		return ResponseEntity.ok().build();
	}
	/**
	 * Get service by its id
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET)
	Service getServiceById(@PathVariable String userId) {
		return ServiceRepositoryUtils.getServiceById(this.serviceRepository, userId);
	}
}
