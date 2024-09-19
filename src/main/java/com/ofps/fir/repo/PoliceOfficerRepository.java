package com.ofps.fir.repo;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ofps.fir.model.PoliceOfficer;

@Repository
public interface PoliceOfficerRepository {
	
	    PoliceOfficer findByEmail(String email);

		PoliceOfficer save(PoliceOfficer officer);

		List<PoliceOfficer> findAll();
	
		List<PoliceOfficer> findPoliceforSingleStaion(long id);

		 List<PoliceOfficer> findByBranch(String branch);
}
