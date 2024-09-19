package com.ofps.fir.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ofps.fir.model.InvestigationReport;
import com.ofps.fir.service.InvestigationReportService;

import java.util.List;

@RestController
@RequestMapping("/report")
public class InvestigationReportController {

    @Autowired
    private InvestigationReportService reportService;

    @GetMapping("/all")
    public ResponseEntity<List<InvestigationReport>> getAllReports() {
        List<InvestigationReport> reports = reportService.getAllReports();
        return ResponseEntity.ok(reports);
    }

    @GetMapping("/complainant/{complainantId}")
    public ResponseEntity<List<InvestigationReport>> getReportsByComplainantId(@PathVariable("complainantId") Long complainantId) {
        List<InvestigationReport> reports = reportService.getReportsByComplainantId(complainantId);
        if (reports.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(reports);
    }
    

    @PostMapping
    public ResponseEntity<String> addInvestigationReport(@RequestBody InvestigationReport report) {
        reportService.addInvestigationReport(report);
        return ResponseEntity.status(201).body("Investigation report added successfully.");
    }
}
