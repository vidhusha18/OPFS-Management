package com.ofps.fir.model;

import jakarta.persistence.*;

import java.util.Arrays;
import java.util.Date;

import java.time.LocalDate;

@Entity
public class FIR {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int firId;

    @ManyToOne
    @JoinColumn(name = "complainant_id")
    private Complainant complainant;
    

    private String incidentType;

    private String incidentDescription;
    private LocalDate incidentDate;
    
    private String incidentPlace;
    private String incidentDistrict;
    private String suspectType;
    
	private String incidentSuspect;
	
	
    public String getSuspectType() {
		return suspectType;
	}

	public void setSuspectType(String suspectType) {
		this.suspectType = suspectType;
	}

	public FIR(String suspectType) {
		super();
		this.suspectType = suspectType;
	}



    
    public String getIncidentSuspect() {
		return incidentSuspect;
	}

	public void setIncidentSuspect(String incidentSuspect) {
		this.incidentSuspect = incidentSuspect;
	}



	@Lob
	
    @Column(columnDefinition = "LONGBLOB")
    private byte[] incidentProof; // Storing documents as BLOB
    
    private String status = "pending";



	public FIR(int firId, Complainant complainant, String incidentType, String incidentDescription,
			LocalDate incidentDate, String incidentPlace, String incidentDistrict, byte[] incidentProof, String status,
			PoliceOfficer assignedOfficer,String incidentSuspect) {
		super();
		this.firId = firId;
		this.complainant = complainant;
		this.incidentType = incidentType;
		this.incidentDescription = incidentDescription;
		this.incidentDate = incidentDate;
		this.incidentPlace = incidentPlace;
		this.incidentDistrict = incidentDistrict;
		this.incidentProof = incidentProof;
		this.status = status;
		this.assignedOfficer = assignedOfficer;
		this.incidentSuspect=incidentSuspect;
	}

	public FIR() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public int getFirId() {
		return firId;
	}

	public void setFirId(int firId) {
		this.firId = firId;
	}

	public Complainant getComplainant() {
		return complainant;
	}

	public void setComplainant(Complainant complainant) {
		this.complainant = complainant;
	}

	public String getIncidentType() {
		return incidentType;
	}

	public void setIncidentType(String incidentType) {
		this.incidentType = incidentType;
	}

	public String getIncidentDescription() {
		return incidentDescription;
	}

	public void setIncidentDescription(String incidentDescription) {
		this.incidentDescription = incidentDescription;
	}

	public LocalDate getIncidentDate() {
		return incidentDate;
	}

	public void setIncidentDate(LocalDate incidentDate) {
		this.incidentDate = incidentDate;
	}

	public String getIncidentPlace() {
		return incidentPlace;
	}

	public void setIncidentPlace(String incidentPlace) {
		this.incidentPlace = incidentPlace;
	}

	public String getIncidentDistrict() {
		return incidentDistrict;
	}

	public void setIncidentDistrict(String incidentDistrict) {
		this.incidentDistrict = incidentDistrict;
	}

	public byte[] getIncidentProof() {
		return incidentProof;
	}

	public void setIncidentProof(byte[] incidentProof) {
		this.incidentProof = incidentProof;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public PoliceOfficer getAssignedOfficer() {
		return assignedOfficer;
	}

	public void setAssignedOfficer(PoliceOfficer assignedOfficer) {
		this.assignedOfficer = assignedOfficer;
	}



	@ManyToOne
    @JoinColumn(name = "assigned_officer_id")
    private PoliceOfficer assignedOfficer;

	@Override
	public String toString() {
		return "FIR [firId=" + firId + ", complainant=" + complainant + ", incidentType=" + incidentType
				+ ", incidentDescription=" + incidentDescription + ", incidentDate=" + incidentDate + ", incidentPlace="
				+ incidentPlace + ", incidentDistrict=" + incidentDistrict + ", suspectType=" + suspectType
				+ ", incidentSuspect=" + incidentSuspect + ", incidentProof=" + Arrays.toString(incidentProof)
				+ ", status=" + status + ", assignedOfficer=" + assignedOfficer + "]";
	}

    // Getters and Setters
	
	
}
