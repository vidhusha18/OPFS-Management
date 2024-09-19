package com.ofps.fir.repoimpl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ofps.fir.model.PoliceOfficer;
import com.ofps.fir.model.Station;
import com.ofps.fir.repo.StationRepository;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class StationRepoImpl implements StationRepository {

	
	

    private EntityManager entityManager;


	public StationRepoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    
	@Override
	public void save(Station station) {
		
		entityManager.merge(station);
	}


	@Override
	public List<Station> findAll() {
		
		return entityManager.createQuery("from Station", Station.class).getResultList();
		
	}


	@Override
	public void assignPoliceOfficer(Long firId) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public Station fetchById(long id) {
		
		return entityManager.find(Station.class, id);
	}
	

}
