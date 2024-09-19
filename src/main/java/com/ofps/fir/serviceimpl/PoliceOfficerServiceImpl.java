//package com.vidhu.fir.serviceimpl;
//
//import com.vidhu.fir.model.PoliceOfficer;
//import com.vidhu.fir.repo.PoliceOfficerRepository;
//import com.vidhu.fir.service.PoliceOfficerService;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class PoliceOfficerServiceImpl implements PoliceOfficerService {
//
//    @Autowired
//    private PoliceOfficerRepository officerRepo;
//
//    @Override
//    public void addPoliceOfficer(PoliceOfficer officer) {
//        officerRepo.save(officer);
//    }
//
//    @Override
//    public PoliceOfficer findByEmail(String email) {
//        return officerRepo.findByEmail(email);
//    }
//
//	@Override
//	public List<PoliceOfficer> getAllPoliceOfficers() {
//		// TODO Auto-generated method stub
//		return officerRepo.findAll();
//	}
//	
//	public List<PoliceOfficer> getAllPolicefromAstation(long id){
//		return officerRepo.findPoliceforSingleStaion(id);
//	}
//}

package com.ofps.fir.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ofps.fir.model.PoliceOfficer;
import com.ofps.fir.model.Station;
import com.ofps.fir.repo.PoliceOfficerRepository;
import com.ofps.fir.repo.StationRepository;
import com.ofps.fir.service.PoliceOfficerService;

import java.util.List;

@Service
public class PoliceOfficerServiceImpl implements PoliceOfficerService {

	@Autowired
	private PoliceOfficerRepository officerRepo;

	@Autowired
	private StationServiceImpl stationservice;

	@Autowired
	StationRepository stationRepository;

	@Autowired
	private Mail mailService;
	
	

//    @Override
//    public void addPoliceOfficer(PoliceOfficer officer) {
//        officerRepo.save(officer);
//        // Send email notification
//        String subject = "Welcome to the Police Department";
//        String text = "Dear " + officer.getName() + ",\n\n" +
//                "Congratulations! You have been successfully added to the police department.\n\n" +
//                "You have been assigned to the following station:\n" +
//                "Station Name: " + officer.getStation().getName() + "\n" +
//                "Branch: " + officer.getStation().getBranch() + "\n" +
//                "Location: " + officer.getStation().getLocation() + "\n\n" +
//                "Your email: " + officer.getEmail() + "\n" +
//                "Your password: " + officer.getPassword() + "\n\n" +
//                "Please use the above email ID and password to log in to the system.\n\n" +
//                "Best regards,\n" +
//                "Police Department";
//
//  mailService.sendEmail(officer.getEmail(), subject, text);
//
////        String text = "Dear " + officer.getName() + ",\n\n" +
////                      "You have been successfully added to the police department.\n" +
////                      "You have been assigned to the following station: " + officer.getStation().getName() + 
////                      " in the district "+officer.getStation().getLocation() + officer.getStation().getBranch()+            
////                      "Your email: " + officer.getEmail() + "\n" +
////                      "Your email: " + officer.getEmail() + "\n" +
////                      "Your password: " + officer.getPassword() + "\n\n" +
////                      "Please use the above email ID and password to log in to the system.\n\n" +
////                      "Best regards,\n" +
////                      "Police Department";
////        mailService.sendEmail(officer.getEmail(), subject, text);
//        
//    }

	@Override
	public void addPoliceOfficer(PoliceOfficer officer) {

		System.out.println("Station Name: " + officer);
		System.out.println("Station Branch: " + officer.getStation().getBranch());
		System.out.println("Station Location: " + officer.getStation().getLocation());
		
		Station station = stationRepository.fetchById(officer.getStation().getStationId());

		officer.setStation(station);
		
		PoliceOfficer policeOfficer = officerRepo.save(officer);
		// Log Station Details
		if (officer.getStation() != null) {
			System.out.println("Station Name: " + officer.getStation().getName());
			System.out.println("Station Branch: " + officer.getStation().getBranch());
			System.out.println("Station Location: " + officer.getStation().getLocation());
		} else {
			System.out.println("Station is null");
		}

//         Send email notification
		String subject = "Welcome to the Police Department";
		String text = "Dear " + officer.getName() + ",\n\n"
				+ "Congratulations! You have been successfully added to the police department.\n\n"
				+ "You have been assigned to the following station:\n" + "Station Name: "
				+ (officer.getStation() != null ? officer.getStation().getName() : "N/A") + "\n" + "Branch: "
				+ (officer.getStation() != null ? officer.getStation().getBranch() : "N/A") + "\n" + "Location: "
				+ (officer.getStation() != null ? officer.getStation().getLocation() : "N/A") + "\n\n" + "Your email: "
				+ officer.getEmail() + "\n" + "Your password: " + officer.getPassword() + "\n\n"
				+ "Please use the above email ID and password to log in to the system. \n "
				+ "Passwords are not updateable by Police Officer .\n \n If need of change, reach out to Admin via mail!\n\n" + "Best regards,\n"
				+ "Police Department";

		mailService.sendEmail(officer.getEmail(), subject, text);
	}

	@Override
	public PoliceOfficer findByEmail(String email) {
		return officerRepo.findByEmail(email);
	}

	@Override
	public List<PoliceOfficer> getAllPoliceOfficers() {
		return officerRepo.findAll();
	}

	public List<PoliceOfficer> getAllPolicefromAstation(long id) {
		return officerRepo.findPoliceforSingleStaion(id);
	}

	@Override
	public List<PoliceOfficer> getPoliceOfficersByStationId(Long id) {
		// TODO Auto-generated method stub
		return officerRepo.findPoliceforSingleStaion(id);
	}
}
