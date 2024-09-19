package com.ofps.fir.service;

import java.util.List;

import com.ofps.fir.model.Station;

public interface StationService {

	void addStation(Station station);

	List<Station> getAllStations();

}
