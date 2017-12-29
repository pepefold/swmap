package com.sw.map;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.sw.map.services.Contractor;
import com.sw.map.services.Location;
import com.sw.map.services.LocationFactory;
import com.sw.map.services.Service;

@SpringBootApplication
public class SwMapApplication {

	public static void main(String[] args) {
		SpringApplication.run(SwMapApplication.class, args);
	}
	
	@Bean
	CommandLineRunner init(ServiceRepository serviceRepository, 
			LocationRepository locationRepository, ContractorRepository contractorRepository) {
		
		List<String> list = Arrays.asList(LocationFactory.AHML, LocationFactory.CHICAGO_EXECUTIVE_AIRPORT,
				LocationFactory.RIVERS_CASINO);
		
		return (evt) -> {
			list.stream().forEach((s) -> {
				Service service = new Service(s);
				
				// adding main office locations
				
				serviceRepository.save(service);
				
				List<String> contractorList = Arrays.asList("Joe_" + s, "Mary_" + s ,"Joshua_" + s);
				contractorList.stream().forEach( (contractorName)->{
					Location location = LocationFactory.getLocation(service.getName());
					Contractor c = new Contractor(service, contractorName);
					
					// associate location and contractor
					location.setContractor(c);
					c.setLocation(location);
					
					contractorRepository.save(c);
					locationRepository.save(location);
				});
			
				
			});
		};
	}
}
