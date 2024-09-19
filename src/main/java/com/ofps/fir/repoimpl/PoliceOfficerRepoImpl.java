package com.ofps.fir.repoimpl;



import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;

import java.util.List;

import org.aspectj.lang.annotation.SuppressAjWarnings;
import org.springframework.stereotype.Repository;

import com.ofps.fir.model.Complainant;
import com.ofps.fir.model.PoliceOfficer;
import com.ofps.fir.repo.PoliceOfficerRepository;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public class PoliceOfficerRepoImpl implements PoliceOfficerRepository {

    private final EntityManager entityManager;

    public PoliceOfficerRepoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    
    @Override
	public PoliceOfficer save(PoliceOfficer officer) {
		return entityManager.merge(officer);
			
	}
    

    @Override
    public PoliceOfficer findByEmail(String email) {
        String hql = "FROM PoliceOfficer WHERE email = :email";
        Query query = entityManager.createQuery(hql, PoliceOfficer.class);
        query.setParameter("email", email);

        try {
            return (PoliceOfficer) query.getSingleResult();
        }  catch (NoResultException e) {
            return null; // Return null if no complainant is found with the given email
        }
    }


	@Override
	public List<PoliceOfficer> findAll() {
		// TODO Auto-generated method stub
		return entityManager.createQuery("from PoliceOfficer", PoliceOfficer.class).getResultList();
	}

	
	
	@SuppressAjWarnings("unchecked")
	@Override
	public List<PoliceOfficer> findPoliceforSingleStaion(long id) {
		String hql = "FROM PoliceOfficer p WHERE p.station.stationId = :id";
        Query query = entityManager.createQuery(hql, PoliceOfficer.class);
        query.setParameter("id", id);
		return query.getResultList() ;
		
	}
	
	  @Override
	    public List<PoliceOfficer> findByBranch(String branch) {
	        String hql = "FROM PoliceOfficer p WHERE p.branch = :branch";
	        Query query = entityManager.createQuery(hql, PoliceOfficer.class);
	        query.setParameter("branch", branch);
	        return query.getResultList();
	    }
	
}


