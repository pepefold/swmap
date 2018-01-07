package com.sw.map.contractor;
/**
 * The unit for the service
 * For example, service ABCD Painting may have 3 painters. That is services/1/contractors/1 will be the end point
 * @author Dali
 *
 */

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sw.map.location.Location;
import com.sw.map.service.Service;

@Entity
public class Contractor {
	@ManyToOne
	@JsonIgnore
	private Service service;
	
	@OneToMany (mappedBy = "contractor")
	private Set<Location> locations = new HashSet<Location>();
	
	private String name;
	
	@Id
	@GeneratedValue
	private Long id;

	public Contractor() {
		super();
	}

	public Contractor(Service service, String name) {
		super();
		this.service = service;
		this.name = name;
	}


	public Set<Location> getLocations() {
		return locations;
	}



	public void setLocation(Location location) {
		this.locations.add(location);
	}



	public Service getService() {
		return service;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
