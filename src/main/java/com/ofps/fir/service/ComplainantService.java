package com.ofps.fir.service;



import java.util.List;

import com.ofps.fir.model.Complainant;

public interface ComplainantService {
	
	public Complainant addComplainant(Complainant comp);

	Complainant findByEmail(String email);
	
	
	
	public Complainant getComplainantId (Long id);
//	
//	public List<Complainant> getAllComplainant();
//	
//	public void updateComplainant(Complainant comp);
//	
//	public void deleteComplainant(int id);

	public Complainant authenticate(String email, String password);

	public void updateComplainant(Complainant comp);

	public List<Complainant> getAllComplainants();
}
