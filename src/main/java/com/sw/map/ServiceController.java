package com.sw.map;

import java.net.URI;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sw.map.services.Contractor;
import com.sw.map.services.Location;
import com.sw.map.services.Service;
import com.sw.map.services.SwServiceExists;
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
	ServiceController(ServiceRepository serviceRepository, ContractorRepository contractorRepo, LocationRepository locationRepository) {
		this.serviceRepository = serviceRepository;
		this.locationRepository = locationRepository;
		this.contractorRepository = contractorRepo;
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
		
//		Set<Location> locations = input.getLocations();
//		locations.stream().forEach(l->{
//			Location location = new Location(service, l.getLatitude(), l.getLongitude());
//			this.locationRepository.save(location);
//		});
		
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
		
		ServiceRepositoryUtils.validateService(this.serviceRepository, userId);
		// TODO: validate
		long id = Long.parseLong(userId);

		Service service = this.serviceRepository.findOne(id);
		
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
		//TODO: validate id
		return this.serviceRepository.findOne(Long.parseLong(userId));
	}
}
