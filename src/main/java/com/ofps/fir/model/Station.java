package com.ofps.fir.model;

import jakarta.persistence.*;
import java.util.Set;


@Entity
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stationId;

    private String location;
    private String branch;
    public Station(Long stationId, String location, String branch, String name) {
		super();
		this.stationId = stationId;
		this.location = location;
		this.branch = branch;
		this.name = name;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	private String name;
    
	public Station() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Station(Long stationId, String location, String name) {
		super();
		this.stationId = stationId;
		this.location = location;
		this.name = name;
	}
	public Long getStationId() {
		return stationId;
	}
	public void setStationId(Long stationId) {
		this.stationId = stationId;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

    // Getters and Setters
}
