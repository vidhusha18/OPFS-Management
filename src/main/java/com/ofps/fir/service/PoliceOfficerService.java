package com.ofps.fir.service;



import java.util.List;

import com.ofps.fir.model.PoliceOfficer;

public interface PoliceOfficerService {
	
    void addPoliceOfficer(PoliceOfficer officer);
    PoliceOfficer findByEmail(String email);
	List<PoliceOfficer> getAllPoliceOfficers();
	List<PoliceOfficer> getPoliceOfficersByStationId(Long id);
}
