package com.ofps.fir.repoimpl;



import java.util.List;

import org.springframework.stereotype.Repository;

import com.ofps.fir.model.Complainant;
import com.ofps.fir.model.FIR;
import com.ofps.fir.model.PoliceOfficer;
import com.ofps.fir.repo.FIRRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public class FIRRepoImpl implements FIRRepository{
	
	
	 private final EntityManager entityManager;

	    public FIRRepoImpl(EntityManager entityManager) {
	        this.entityManager = entityManager;
	    }

	    @Override
	    public void save(FIR fir) {
	        entityManager.merge(fir);
	    }

		@Override
		public List<FIR> getAllFirs() {
			// TODO Auto-generated method stub
			return  entityManager.createQuery("from FIR", FIR.class).getResultList();
		}

		@Override
		public FIR findById(Long id) {
			// TODO Auto-generated method stub
			return entityManager.find(FIR.class, id) ;
		}

		@Override
		public FIR update(FIR request) {
			
			return entityManager.merge(request);
		}

		 @Override
		    public List<FIR> findByStatus(String status) {
		        String hql = "FROM FIR f WHERE f.status = :status";
		        Query query = entityManager.createQuery(hql, FIR.class);
		        query.setParameter("status", status);
		        return query.getResultList();
		    }

		@Override
		public FIR findByIncidentPlace(String incidentPlace) {
			// TODO Auto-generated method stub
			return null;
		}
		
		public void assignPoliceOfficerToFIR(int firId, PoliceOfficer officer) {
	        FIR fir = entityManager.find(FIR.class, firId);
	        if (fir != null) {
	            fir.setAssignedOfficer(officer);
	            entityManager.merge(fir);
	        }
	    }

		 @Override
		    public List<FIR> findFIRsByComplainantId(Long complainantId) {
		        String hql = "FROM FIR f WHERE f.complainant.complainantId = :complainantId";
		        Query query = entityManager.createQuery(hql);
		        query.setParameter("complainantId", complainantId);
		        
		        return query.getResultList();
		    }

	
		  @Override
		    public List<FIR> findFIRsByAssignedOfficerId(Long officerId) {
		        String hql = "FROM FIR f WHERE f.assignedOfficer.officerId = :officerId";
		        Query query = entityManager.createQuery(hql, FIR.class);
		        query.setParameter("officerId", officerId);
		        return query.getResultList();
		    }
		  
		  
		
	

}
