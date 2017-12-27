package com.sw.map.services;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Location {
	@JsonIgnore
	@ManyToOne
	private Service service;
	
	@Id
    @GeneratedValue
    private Long id;
	
	private float latitude;
	private float longitude;

	public Location() {
		super();
	}
	
	

	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public Location(Service service, float latitude, float longitude) {
		super();
		this.service = service;
		this.latitude = latitude;
		this.longitude = longitude;
	}



	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
	
	
	
	
}
