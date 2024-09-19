package com.ofps.fir.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ofps.fir.model.Station;
import com.ofps.fir.repo.StationRepository;
import com.ofps.fir.service.StationService;

@Service
public class StationServiceImpl implements StationService {

	
	@Autowired
	private StationRepository stationrepo;
	
	
	
	
	@Override
	public void addStation(Station station) {
		 
		        stationrepo.save(station);
		    }




	@Override
	public List<Station> getAllStations() {
		// TODO Auto-generated method stub
		return stationrepo.findAll();
	}
		
		
	

}
