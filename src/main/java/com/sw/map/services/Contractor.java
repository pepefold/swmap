package com.sw.map.services;
/**
 * The unit for the service
 * For example, service ABCD Painting may have 3 painters. That is services/1/contractors/1 will be the end point
 * @author Dali
 *
 */

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Contractor {
	@ManyToOne
	@JsonIgnore
	private Service service;
	
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
