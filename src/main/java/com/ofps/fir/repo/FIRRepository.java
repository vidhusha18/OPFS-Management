package com.ofps.fir.repo;

import java.util.List;

import com.ofps.fir.model.FIR;
import com.ofps.fir.model.PoliceOfficer;

public interface FIRRepository {

	void save(FIR fir);
//	public void save(FIR fir);

	List<FIR> getAllFirs();

	FIR update(FIR request);

//	FIR findById(long firId);
	
	
	FIR findByIncidentPlace(String incidentPlace);

	List<FIR> findByStatus(String status);

	void assignPoliceOfficerToFIR(int firId, PoliceOfficer officer);

	FIR findById(Long id);

//	void findBycomplainantId();

	List<FIR> findFIRsByComplainantId(Long complainantId);

//	List<FIR> findByAssignedOfficerOfficerId(Long officerId);

	List<FIR> findFIRsByAssignedOfficerId(Long officerId);



	
	
}
