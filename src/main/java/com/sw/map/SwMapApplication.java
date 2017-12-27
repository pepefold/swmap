package com.sw.map;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.mysql.fabric.xmlrpc.base.Array;
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
		
		return (evt) -> {
			List<String> list = Arrays.asList(LocationFactory.AHML, LocationFactory.CHICAGO_EXECUTIVE_AIRPORT,
					LocationFactory.RIVERS_CASINO);
			list.stream().forEach((s) -> {
				Service service = new Service(s);
				
				// adding main office locations
				Location location = LocationFactory.getLocation(service);
				serviceRepository.save(service);
				locationRepository.save(location);
				
				List<String> contractorList = Arrays.asList("Joe_" + s, "Mary_" + s ,"Joshua_" + s);
				contractorList.stream().forEach( (contractorName)->{
					Contractor c = new Contractor(service, contractorName);
					contractorRepository.save(c);
				});
			
				
			});
		};
	}
}
