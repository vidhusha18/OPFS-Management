package com.ofps.fir.repo;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ofps.fir.model.Complainant;

@Repository
public interface ComplainantRepository  {

	public Complainant save(Complainant comp);

	Complainant findByEmail(String email);

	Complainant findById(Long id);

	

	public void update(Complainant comp);

	public List<Complainant> findAll();
	
//	public Sales findById(int id) ;
//
//	public List<Sales> findAll();
//
//
//	public void update(Sales sales);
//
//	public void deleteById(int id);
	}
