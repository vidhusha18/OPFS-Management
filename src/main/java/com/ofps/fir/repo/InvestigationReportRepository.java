package com.ofps.fir.repo;


import java.util.List;

import com.ofps.fir.model.InvestigationReport;

public interface InvestigationReportRepository {

    List<InvestigationReport> fetchAllReports();
    
    List<InvestigationReport> fetchReportsByComplainantId(Long complainantId);

    void addInvestigationReport(InvestigationReport report);
}
