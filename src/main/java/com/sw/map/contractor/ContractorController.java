package com.sw.map.contractor;

import java.net.URI;
import java.util.Optional;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sw.map.location.Location;
import com.sw.map.service.NoLocationFoundException;
import com.sw.map.service.Service;
import com.sw.map.service.ServiceRepository;
import com.sw.map.service.ServiceRepositoryUtils;
//test 1
@RestController
@RequestMapping("/contractors/{contractorId}")
public class ContractorController {
	
	private ServiceRepository serviceRepository;
	private ContractorRepository contractorRepository;
	
	
	@Autowired
	public ContractorController(ServiceRepository serviceRepository, ContractorRepository contractorRepository) {
		super();
		this.serviceRepository = serviceRepository;
		this.contractorRepository = contractorRepository;
	}

	/**
	 * Add a contractor to the service
	 * 
	 * @param serviceId the service id
	 * 
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST)
	ResponseEntity<?> addContractor(
			@RequestBody Contractor inContractor){
		
		String sId = String.valueOf(inContractor.getId());
		
		//TODO: add validation
//		//check whether service is correct
//		ServiceRepositoryUtils.validateServiceByName(this.serviceRepository,
//				sId);
				
		
		
		//TODO: to optimize assign the last added
		Optional<Location> locOpt = inContractor.getLocations().stream().findFirst();
		
		locOpt.orElseThrow(() -> new NoLocationFoundException("No location for contractor: " + sId));
			
		Service service = this.serviceRepository.findOne(inContractor.getService().getId());
		Contractor c = new Contractor(service, inContractor.getName());
		c.setLocation(locOpt.get());
		
		this.contractorRepository.save(c);
		
		
		URI locationURI = ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(service.getId()).toUri();
		
		return ResponseEntity.created(locationURI).build();

	}
	/**
	 * Get the contractor of service
	 * @param serviceId the service id
	 * 
	 * @param contractorId the contractor id
	 * 
	 * @return the contractor
	 */
	//TODO: change validation to return 404 if invalid id is passed
	@RequestMapping (method=RequestMethod.GET)
	Contractor getContractor(@PathVariable String serviceId, @PathVariable String contractorId) {
	
		Optional<Contractor> findByServiceAndId = this.contractorRepository.findByServiceIdAndId(
				Long.parseLong(serviceId), 
				Long.parseLong(contractorId));
		findByServiceAndId.
			orElseThrow(()-> new NoContractorsFound("serviceId:" + serviceId + " contractorId: " + 
						contractorId));
		return findByServiceAndId.get();
	}
	
	
		
}
