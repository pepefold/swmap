package com.sw.map.location;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sw.map.contractor.Contractor;

@Entity
public class Location {
	@JsonIgnore
	@ManyToOne
	private Contractor contractor;
	
	@Id
    @GeneratedValue
    private Long id;
	
	private float latitude;
	private float longitude;
	private long time;
	
	public Location() {
		super();
	}
	
	

	public long getTime() {
		return time;
	}



	public void setTime(long time) {
		this.time = time;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public Location(float latitude, float longitude) {
		super();
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

	public Contractor getContractor() {
		return contractor;
	}

	public void setContractor(Contractor contractor) {
		this.contractor = contractor;
	}
}
