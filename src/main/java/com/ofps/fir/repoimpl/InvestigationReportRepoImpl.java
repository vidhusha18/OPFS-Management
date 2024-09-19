package com.ofps.fir.repoimpl;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
//import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.ofps.fir.model.InvestigationReport;
import com.ofps.fir.repo.InvestigationReportRepository;

import java.util.List;

@Transactional
@Repository
public class InvestigationReportRepoImpl implements InvestigationReportRepository {

    @Autowired
	private  EntityManager entityManager;

    @Override
    public List<InvestigationReport> fetchAllReports() {
        String hql = "FROM InvestigationReport";
        return entityManager.createQuery(hql, InvestigationReport.class).getResultList();
    }

    @Override
    public List<InvestigationReport> fetchReportsByComplainantId(Long complainantId) {
        String hql = "SELECT r FROM InvestigationReport r JOIN r.fir f WHERE f.complainant.complainantId = :complainantId";
        Query query = entityManager.createQuery(hql);
        query.setParameter("complainantId", complainantId);
        return query.getResultList();
    }


   
    @Override
    public void addInvestigationReport(InvestigationReport report) {
        entityManager.persist(report);
    }
}
