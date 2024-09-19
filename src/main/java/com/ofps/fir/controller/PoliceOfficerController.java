

package com.ofps.fir.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ofps.fir.model.Complainant;
import com.ofps.fir.model.PoliceOfficer;
import com.ofps.fir.service.PoliceOfficerService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/police")
@CrossOrigin("http://localhost:3000")
public class PoliceOfficerController {

    @Autowired
    private PoliceOfficerService officerService;

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerPoliceOfficer(@RequestBody PoliceOfficer officer) {
        Map<String, String> response = new HashMap<>();
        try {
            officerService.addPoliceOfficer(officer);
            response.put("message", "Officer added successfully.");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            response.put("message", "Failed to add officer.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginPoliceOfficer(@RequestBody LoginRequest loginRequest) {
        PoliceOfficer officer = officerService.findByEmail(loginRequest.getEmail());

        if (officer != null && officer.getPassword().equals(loginRequest.getPassword())) {
            Map<String, String> response = new HashMap<>();
            response.put("dashboard", "/policeDashboard");
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    
    @GetMapping("/policeEmail/{email}")
    public ResponseEntity<PoliceOfficer> getComplainantByEmail(@PathVariable String email) {
        PoliceOfficer officer = officerService.findByEmail(email);
        if (officer != null) {
            return ResponseEntity.ok(officer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    
    @GetMapping("/all")
    public List<PoliceOfficer> getPoliceOfficers() {
        return officerService.getAllPoliceOfficers();
    }
    
    @GetMapping("/allPolice/station/{id}")
    public List<PoliceOfficer> getAllpoliceForStation(@PathVariable Long id) {
        return officerService.getPoliceOfficersByStationId(id);
    }
    
    
    
    public static class LoginRequest {
        private String email;
        private String password;

        // Getters and setters
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }
    
    
    
	 

    
}
