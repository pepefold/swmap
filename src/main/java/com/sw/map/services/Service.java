package com.sw.map.services;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Service {
	
	@OneToMany(mappedBy= "service")
	private Set<Location> locations = new HashSet<Location>();
	
	@GeneratedValue
	@Id
	private Long id;
	/**
	 * Name of the service
	 */
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Service() {
		super();
	}

	
	public Service(String name) {
		super();
		setName(name);
	}

	public String getName() {
		return name;
	}

	private void setName(String name) {
		this.name = name;
	}
	/**
	 * Get location of the service
	 * 
	 * @return the location
	 */
	public Set<Location> getLocations() {
		return locations;
	}
	
	public void addLocation(Location location) {
		this.locations.add(location);
	}
}
