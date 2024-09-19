package com.ofps.fir.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ofps.fir.model.Complainant;
import com.ofps.fir.model.FIR;
import com.ofps.fir.model.PoliceOfficer;
import com.ofps.fir.service.ComplainantService;
import com.ofps.fir.service.FIRService;




@RestController
@RequestMapping("/fir")
@CrossOrigin("http://localhost:3000")
public class FIRController {

	@Autowired
	 private  FIRService firService;
	
	 @Autowired
	    private ComplainantService complainantService;
	

	
	 @PostMapping("/addfir")
	    public ResponseEntity<String> addFir(
	            @RequestParam("incidentType") String incidentType,
	            @RequestParam("incidentDescription") String incidentDescription,
	            @RequestParam("incidentDate") LocalDate incidentDate,
	            @RequestParam("incidentPlace") String incidentPlace,
	            @RequestParam("incidentDistrict") String incidentDistrict,
	            @RequestParam("incidentSuspect") String incidentSuspect,
	            @RequestParam("suspectType") String suspectType,
	            @RequestParam("id") Long complainantId,
	            @RequestParam("incidentProof") MultipartFile incidentProof) {

	        try {
	            byte[] proofBytes = null;
	            if (incidentProof != null && !incidentProof.isEmpty()) {
	                proofBytes = incidentProof.getBytes();
	            }

	            FIR fir = new FIR();
	            fir.setIncidentType(incidentType);
	            fir.setIncidentDistrict(incidentDistrict);
	            fir.setIncidentPlace(incidentPlace);
	            fir.setIncidentDescription(incidentDescription);
	            fir.setIncidentProof(proofBytes);
	            fir.setIncidentDate(incidentDate);
	            fir.setIncidentSuspect(incidentSuspect);
	            fir.setSuspectType(suspectType);

	            Complainant com = complainantService.getComplainantId(complainantId);
	            
	            if(com!=null) {
	            	fir.setComplainant(com);	            	
	            	firService.addFir(fir);
	            	return ResponseEntity.ok("FIR added successfully");
	            }
	            else {
	            	return ResponseEntity.ok("FIR Not Added!");
	            }

	        } catch (IOException e) {
	            e.printStackTrace();
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to process file upload");
	        } catch (Exception e) {
	            e.printStackTrace();
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
	        }
	    }
	 
	 @GetMapping("/all")
		public List<FIR> getFirs() {
			return firService.getAllFirs();
		}
	 
	 
	 @PutMapping("/approve/{id}/{status}")
	    public String approveRequest(@PathVariable int id ,@PathVariable String status) {
	        FIR updatedStatus = firService.approveRequest(id,status);
	        String status1  = updatedStatus.getStatus();
	        return status1;
	        

			
	        
	    }
	  

	    @PutMapping("/reject/{id}")
	    public String rejectRequest(@PathVariable int id) {
	    	FIR updatedRequest1 = firService.rejectRequest(id);
	        String status  = updatedRequest1.getStatus();
	    	
//	        if(status=="rejected") {
//	       	 String content = String.format(
//	    	   		    "Dear %s,\n\n" +
//	    	   		    "Your Venue Booking has been Rejected. \n\n" +
//	    	   		    		"Event Name : %s, \n\n"+ 
//	    	   		    "Event Date : %s, \n \n"+
//	    	   		    		
//	    	   		    "Best regards,\n" +
//	    	   		    "Vidhu & Team",
//	    	   		 updatedRequest.getHostEmail().split("@")[0],
//	    	   		updatedRequest.getEventName(),
//	    	   		updatedRequest.getDate()
//	    	   		
//	    	   		   
//	    	   		);
//	           String subject = "Your Seat Booking was rejected";
//	           mailservice.sendEmail(updatedRequest.getHostEmail(), subject, content);
//	       	return "Failed";
//	       }
			return status;
	    }
	    
	    
	    @PostMapping("/assignOfficer/{firId}")
	    public void assignPoliceOfficer(@PathVariable int firId, @RequestBody PoliceOfficer officer) {
	        firService.assignPoliceOfficerToFIR(firId, officer);
	    }
	    
	    
	    @GetMapping("/firOf/complainant/{complainantId}")
	    public List<FIR> getFIRsByComplainantId(@PathVariable Long complainantId) {
	        return firService.getFIRsByComplainantId(complainantId);
	    }
	    	
	    
	    @GetMapping("/byOfficer/{officerId}")
	    public List<FIR> getFIRsByAssignedOfficerId(@PathVariable Long officerId) {
	        return firService.getFIRsByAssignedOfficerId(officerId);
	    }
	    
	    }
	