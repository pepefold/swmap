package com.sw.map;

import java.net.URI;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;

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
import com.sw.map.services.NoContractorsFound;
import com.sw.map.services.Service;

@RestController
@RequestMapping("services/{serviceId}/contractors/{contractorId}")
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
		
		String sId = String.valueOf(inContractor.getService().getId());
		//check wether service is correct
		ServiceRepositoryUtils.validateService(this.serviceRepository,
				sId);
				
		
		Service service = this.serviceRepository.findOne(inContractor.getService().getId()); 
		Contractor c = new Contractor(service, inContractor.getName());
		this.contractorRepository.save(c);
		
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(service.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	/**
	 * Get the contractor of service
	 * @param serviceId the service id
	 * 
	 * @param contractorId the contractor id
	 * 
	 * @return the contractor
	 */
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
