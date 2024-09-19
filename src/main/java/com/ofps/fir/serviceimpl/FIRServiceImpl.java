//package com.vidhu.fir.serviceimpl;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.vidhu.fir.model.FIR;
//import com.vidhu.fir.repo.ComplainantRepository;
//import com.vidhu.fir.repo.FIRRepository;
//import com.vidhu.fir.service.FIRService;
//
//@Service
//public class FIRServiceImpl implements FIRService{
//	
//	private final FIRRepository firrepo;
//	
//	 @Autowired
//	    public FIRServiceImpl(FIRRepository firrepo) {
//	        this.firrepo = firrepo;
//	    }
//
//	@Override
//	public void addFir(FIR fir) {
//		firrepo.save(fir);
//		
//	}
//
//	@Override
//	public List<FIR> getAllFirs() {
//		// TODO Auto-generated method stub
//		return firrepo.getAllFirs();
//	}
//
//	@Override
//	public FIR updateFIRStatus(Long id, String string) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	
//
//}
package com.ofps.fir.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ofps.fir.model.FIR;
import com.ofps.fir.model.PoliceOfficer;
import com.ofps.fir.repo.ComplainantRepository;
import com.ofps.fir.repo.FIRRepository;
import com.ofps.fir.service.FIRService;

@Service
public class FIRServiceImpl implements FIRService{
	
	private final FIRRepository firrepo;
	

	@Autowired
	private Mail mailService;

	
	 @Autowired
	    public FIRServiceImpl(FIRRepository firrepo) {
	        this.firrepo = firrepo;
	    }

	@Override
	public void addFir(FIR fir) {
		firrepo.save(fir);
		
	}

	@Override
	public List<FIR> getAllFirs() {
		// TODO Auto-generated method stub
		return firrepo.getAllFirs();
	}

//	@Override
//	public FIR approveRequest(int id, String status) {
//	    FIR request = firrepo.findById((long)id);
//	    if (request == null) {
//	        throw new NullPointerException("FIR request not found for id: " + id);
//	    }
//	    request.setStatus(status);
//	    
//	    String body="Dear,"+request.getComplainant().getName()+"\n \t\t  your FIR status has been updated to : "
//	    +status + "\n \n For any details reach out to Police officer Mr./Ms. "+request.getAssignedOfficer().getName()+"  "+request.getAssignedOfficer().getDesignation()+
//	    " \n contact :"+request.getAssignedOfficer().getPhone();
//	    mailService.sendEmail(request.getComplainant().getEmail(), "Notification from "+request.getAssignedOfficer().getStation().getName() , body);
//	    
//	    return firrepo.update(request);
//	}

	
	@Override
	public FIR approveRequest(int id, String status) {
	    FIR request = firrepo.findById((long) id);
	    if (request == null) {
	        throw new NullPointerException("FIR request not found for id: " + id);
	    }
	    request.setStatus(status);

	    String body = "Dear " + request.getComplainant().getName() + ",\n\n"
	            + "Your FIR status has been updated to: " + status + ".\n\n"
	            + "For any details, reach out to Police Officer Mr./Ms. " + request.getAssignedOfficer().getName() + " ("
	            + request.getAssignedOfficer().getDesignation() + ").\n"
	            +"Station : "+request.getAssignedOfficer().getStation().getName()+
	            "\nBranch :" 
	            +request.getAssignedOfficer().getStation().getBranch()
	            
	            + "\nContact: " + request.getAssignedOfficer().getPhone() + "."+
	            "\n for each update of status you'll get information collected via phone call";

	    if ("FIR closed".equalsIgnoreCase(status)) {
	        body += "\n\n You can find your FIR closure by downloading the FIR in your OFPS Account.";
	    }

	    mailService.sendEmail(request.getComplainant().getEmail(), 
	                          "Notification from " + request.getAssignedOfficer().getStation().getName(), 
	                          body);

	    return firrepo.update(request);
	}

	
	
	@Override
	public FIR rejectRequest(int id) {
	    FIR request = firrepo.findById((long)id);
	    System.err.println(request);
	    if (request == null) {
	        throw new NullPointerException("FIR request not found for id: " + id);
	    }
	    request.setStatus("rejected");
	    return firrepo.update(request);
	}

	@Override
	public List<PoliceOfficer> getAllPolicefromAstation(long id) {
		// TODO Auto-generated method stub
		return null;
	}


	 @Override
	    public void assignPoliceOfficerToFIR(int firId, PoliceOfficer officer) {
	        firrepo.assignPoliceOfficerToFIR(firId, officer);
	    }

	@Override
	public List<FIR> getFIRsByComplainantId(Long complainantId) {
		// TODO Auto-generated method stub
		System.out.println(firrepo.findFIRsByComplainantId(complainantId));
		return firrepo.findFIRsByComplainantId(complainantId);
	}

	 @Override
	    public List<FIR> getFIRsByAssignedOfficerId(Long officerId) {
	        return firrepo.findFIRsByAssignedOfficerId(officerId);
	    }

	
}
