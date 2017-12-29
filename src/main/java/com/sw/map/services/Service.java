package com.sw.map.services;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Service can have multiple contractors under it. Additionally,
 * @author Dali
 *
 */
@Entity
public class Service {
	/**
	 * All contractors under this service
	 */
	@OneToMany(mappedBy= "service")
	private Set<Contractor> contractors = new HashSet<Contractor>();
	
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
	 * Add a contractor
	 * 
	 * @param contractor the contractor
	 */
	public void addContractor(Contractor contractor) {
		this.contractors.add(contractor);
	}

	public Set<Contractor> getContractors() {
		return this.contractors;
	}
	
	
	
}
