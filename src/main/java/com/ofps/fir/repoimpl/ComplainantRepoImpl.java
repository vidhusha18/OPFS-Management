//package com.vidhu.fir.repoimpl;
//
//import org.springframework.stereotype.Repository;
//
//import com.vidhu.fir.model.Complainant;
//import com.vidhu.fir.repo.ComplainantRepository;
//
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.Query;
//import jakarta.transaction.Transactional;
//
//
//@Repository
//@Transactional
//public class ComplainantRepoImpl  implements ComplainantRepository {
//	
//	public ComplainantRepoImpl(EntityManager entityManager) {
//		super();
//		this.entityManager = entityManager;
//	}
//	
//	
//	EntityManager entityManager;
//	 @Override
//	    public void save(Complainant comp) {
//	        entityManager.merge(comp);
//	    }
//	 
//	 @Override
//	 public Complainant findByEmail(String email) {
//		 String hql = "From Complainant comp where comp.email = :email";
//		 Query q = entityManager.createQuery (hql);
//		 q. setParameter ("email", email); 
//		 return (Complainant) q.getSingleResult();
//	 }
//}


package com.ofps.fir.repoimpl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ofps.fir.model.Complainant;
import com.ofps.fir.repo.ComplainantRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class ComplainantRepoImpl implements ComplainantRepository {

    private final EntityManager entityManager;

    public ComplainantRepoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Complainant save(Complainant comp) {
        return entityManager.merge(comp);
    }

    @Override
    public Complainant findByEmail(String email) {
        String hql = "FROM Complainant comp WHERE comp.email = :email";
        Query query = entityManager.createQuery(hql);
        query.setParameter("email", email);
        try {
            return (Complainant) query.getSingleResult();
        } catch (NoResultException e) {
            return null; // Return null if no complainant is found with the given email
        }
    }
    
    @Override
    public Complainant findById(Long id) {
        return entityManager.find(Complainant.class, id);
    }

	@Override
	public void update(Complainant comp) {
		entityManager.merge(comp);
	}

	@Override
	public List<Complainant> findAll() {
		// TODO Auto-generated method stub
		return entityManager.createQuery("from Complainant", Complainant.class).getResultList();
	}
	
	
}
