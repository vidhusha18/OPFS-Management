package com.ofps.fir.repo;

import java.util.List;

import com.ofps.fir.model.Station;

public interface StationRepository {

	void save(Station station);

	List<Station> findAll();

//	Object findById(Long stationId);

	void assignPoliceOfficer(Long firId);

	Station fetchById(long id);

}
