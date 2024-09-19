package com.ofps.fir.service;

import java.util.List;

import org.springframework.mail.SimpleMailMessage;

import com.ofps.fir.model.Complainant;
import com.ofps.fir.model.FIR;
import com.ofps.fir.model.PoliceOfficer;

public interface FIRService {

	

	void addFir(FIR fir);

	List<FIR> getAllFirs();

	FIR approveRequest(int id, String status);

	FIR rejectRequest(int id);

//	FIR updateFIRStatus(Long id, String string);
	List<PoliceOfficer> getAllPolicefromAstation(long id);

	 void assignPoliceOfficerToFIR(int firId, PoliceOfficer officer);

	
	List<FIR> getFIRsByComplainantId(Long complainantId);

//	List<FIR> getFIRsByOfficerId(Long officerId);

	List<FIR> getFIRsByAssignedOfficerId(Long officerId);
	 
}
