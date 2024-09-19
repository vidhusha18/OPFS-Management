package com.ofps.fir.model;

import jakarta.persistence.*;
import java.util.Date;

import java.time.LocalDate;

@Entity
public class InvestigationReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reportId;

    @ManyToOne
    @JoinColumn(name = "fir_id", nullable = false)
    private FIR fir;

    
    @ManyToOne
    @JoinColumn(name = "officer_id", nullable = false)
    private PoliceOfficer officer;

    private String reportDetails;
    private LocalDate date;
    
    public Long getReportId() {
		return reportId;
	}
	public void setReportId(Long reportId) {
		this.reportId = reportId;
	}
	public FIR getFir() {
		return fir;
	}
	public void setFir(FIR fir) {
		this.fir = fir;
	}
	public PoliceOfficer getOfficer() {
		return officer;
	}
	public void setOfficer(PoliceOfficer officer) {
		this.officer = officer;
	}
	public String getReportDetails() {
		return reportDetails;
	}
	public void setReportDetails(String reportDetails) {
		this.reportDetails = reportDetails;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	

	public InvestigationReport(Long reportId, FIR fir, PoliceOfficer officer, String reportDetails, LocalDate date) {
		super();
		this.reportId = reportId;
		this.fir = fir;
		this.officer = officer;
		this.reportDetails = reportDetails;
		this.date = date;
	}
	public InvestigationReport() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "InvestigationReport [reportId=" + reportId + ", fir=" + fir + ", officer=" + officer
				+ ", reportDetails=" + reportDetails + ", date=" + date + "]";
	}

	
  // add, fetchallby complainantid, , get all 
}



//package com.vidhu.fir.model;
//
//import jakarta.persistence.*;
//import java.util.Date;
//
//import java.time.LocalDate;
//
//@Entity
//public class InvestigationReport {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long reportId;
//
//    @ManyToOne
//    @JoinColumn(name = "fir_id", nullable = false)
//    private FIR fir;
//
//    
//    @ManyToOne
//    @JoinColumn(name = "officer_id", nullable = false)
//    private PoliceOfficer officer;
//
//    private String ingroundDetails;
//    private String suspectDetails;
//    private String reportDetails;
//    private LocalDate date;
//    
//    
//	public Long getReportId() {
//		return reportId;
//	}
//	public void setReportId(Long reportId) {
//		this.reportId = reportId;
//	}
//	public FIR getFir() {
//		return fir;
//	}
//	public void setFir(FIR fir) {
//		this.fir = fir;
//	}
//	public PoliceOfficer getOfficer() {
//		return officer;
//	}
//	public void setOfficer(PoliceOfficer officer) {
//		this.officer = officer;
//	}
//	public String getIngroundDetails() {
//		return ingroundDetails;
//	}
//	public void setIngroundDetails(String ingroundDetails) {
//		this.ingroundDetails = ingroundDetails;
//	}
//	public String getSuspectDetails() {
//		return suspectDetails;
//	}
//	public void setSuspectDetails(String suspectDetails) {
//		this.suspectDetails = suspectDetails;
//	}
//	public String getReportDetails() {
//		return reportDetails;
//	}
//	public void setReportDetails(String reportDetails) {
//		this.reportDetails = reportDetails;
//	}
//	public LocalDate getDate() {
//		return date;
//	}
//	public void setDate(LocalDate date) {
//		this.date = date;
//	}
//	public InvestigationReport() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
//	public InvestigationReport(Long reportId, FIR fir, PoliceOfficer officer, String ingroundDetails,
//			String suspectDetails, String reportDetails, LocalDate date) {
//		super();
//		this.reportId = reportId;
//		this.fir = fir;
//		this.officer = officer;
//		this.ingroundDetails = ingroundDetails;
//		this.suspectDetails = suspectDetails;
//		this.reportDetails = reportDetails;
//		this.date = date;
//	}
//	@Override
//	public String toString() {
//		return "InvestigationReport [reportId=" + reportId + ", fir=" + fir + ", officer=" + officer
//				+ ", ingroundDetails=" + ingroundDetails + ", suspectDetails=" + suspectDetails + ", reportDetails="
//				+ reportDetails + ", date=" + date + "]";
//	}
//    
//  
//	
//
//	
//  // add, fetchallby complainantid, , get all 
//}
