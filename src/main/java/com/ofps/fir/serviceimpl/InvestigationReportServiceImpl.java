package com.ofps.fir.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ofps.fir.model.FIR;
import com.ofps.fir.model.InvestigationReport;
import com.ofps.fir.model.PoliceOfficer;
import com.ofps.fir.repo.FIRRepository;
import com.ofps.fir.repo.InvestigationReportRepository;
import com.ofps.fir.service.InvestigationReportService;

import java.time.LocalDate;
import java.util.List;

@Service
public class InvestigationReportServiceImpl implements InvestigationReportService {

    @Autowired
    private InvestigationReportRepository reportRepository;

    @Autowired
    private  FIRRepository firrepo;
    
    @Override
    public List<InvestigationReport> getAllReports() {
        return reportRepository.fetchAllReports();
    }
    

    @Override
    public List<InvestigationReport> getReportsByComplainantId(Long complainantId) {
        return reportRepository.fetchReportsByComplainantId(complainantId);
    }

    @Override
    public void addInvestigationReport(InvestigationReport report) {
    	System.err.println(report);
    	FIR fir=firrepo.findById((long)report.getFir().getFirId());
    	PoliceOfficer officer=fir.getAssignedOfficer();
    	report.setFir(fir);
    	report.setOfficer(officer);
    	report.setDate(LocalDate.now());
        reportRepository.addInvestigationReport(report);
    }
}
