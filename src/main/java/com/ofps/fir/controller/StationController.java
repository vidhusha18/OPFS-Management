package com.ofps.fir.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ofps.fir.model.PoliceOfficer;
import com.ofps.fir.model.Station;
import com.ofps.fir.service.StationService;

@RestController
@RequestMapping("/station")
@CrossOrigin("http://localhost:3000")
public class StationController {
	
	@Autowired
	private StationService stationservice;
	
	 @PostMapping("/register")
	    public String station(@RequestBody Station station) {
	        String msg = "";

	        try {
	            stationservice.addStation(station);
	            msg += "addSuccess";
	        } catch (Exception e) {
	            msg += "addFailure";
	        }
	        return msg;
	    }
	
	
	 @GetMapping("/all")
		public List<Station> getAllStaions() {
			return stationservice.getAllStations();
		}
	
}
