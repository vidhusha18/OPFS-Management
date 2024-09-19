package com.ofps.fir.service;

import java.util.List;

import com.ofps.fir.model.InvestigationReport;

public interface InvestigationReportService {

    List<InvestigationReport> getAllReports();
    
    List<InvestigationReport> getReportsByComplainantId(Long complainantId);

    void addInvestigationReport(InvestigationReport report);
}
