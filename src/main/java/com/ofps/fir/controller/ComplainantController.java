////////package com.vidhu.fir.controller;
////////
////////
////////
////////import com.vidhu.fir.model.Complainant;
////////import com.vidhu.fir.service.ComplainantService;
////////import com.vidhu.fir.serviceimpl.ComplainantServiceImpl;
////////
////////import org.springframework.beans.factory.annotation.Autowired;
////////import org.springframework.http.HttpStatus;
////////import org.springframework.http.ResponseEntity;
////////import org.springframework.web.bind.annotation.*;
////////
////////@RestController
////////@RequestMapping("/complainant")
////////@CrossOrigin("http://localhost:3000")
////////public class ComplainantController {
////////
////////    @Autowired
////////    private ComplainantServiceImpl complainantService;
////////
////////  
////////
////////    	@PostMapping("/register")
////////    	public String insertComplainant(@RequestBody Complainant comp) {
////////    		String msg = "";
////////
////////    		try {
////////
////////    			
////////    			complainantService.addComplainant(comp);
////////    			msg += "addSuccess";
////////
////////    		} catch (Exception e) {
////////    			msg += "addFailure";
////////    		}
////////    		return msg;
////////    	}
////////
////////    	
////////    	@GetMapping("/email/{email}")
////////    		public Complainant doFindByEmail(@PathVariable("email") String email) {
////////    			return complainantService.findByEmail(email);
////////    		
////////    	}
////////}
//////
////////
////////package com.vidhu.fir.controller;
////////
////////import com.vidhu.fir.model.Complainant;
////////import com.vidhu.fir.service.ComplainantService;
////////import com.vidhu.fir.serviceimpl.ComplainantServiceImpl;
////////import org.springframework.beans.factory.annotation.Autowired;
////////import org.springframework.http.HttpStatus;
////////import org.springframework.http.ResponseEntity;
////////import org.springframework.web.bind.annotation.*;
////////
////////@RestController
////////@RequestMapping("/complainant")
////////@CrossOrigin("http://localhost:3000")
////////public class ComplainantController {
////////
////////    @Autowired
////////    private ComplainantServiceImpl complainantService;
////////
//////////    @PostMapping("/register")
//////////    public String insertComplainant(@RequestBody Complainant comp) {
//////////        String msg = "";
//////////
//////////        try {
//////////            complainantService.addComplainant(comp);
//////////            msg += "addSuccess";
//////////        } catch (Exception e) {
//////////            msg += "addFailure";
//////////        }
//////////        return msg;
//////////    }
////////
////////    
////////    @PostMapping("/register")
////////    public ResponseEntity<?> register(@RequestParam("name") String name,
////////                                       @RequestParam("phone") String phone,
////////                                       @RequestParam("email") String email,
////////                                       @RequestParam("password") String password,
////////                                       @RequestParam("image") MultipartFile image) {
////////        // Handle registration logic here
////////        // Store image using image.getBytes()
////////        return ResponseEntity.ok("Registration successful");
////////    }
////////    
////////
////////    @PostMapping("/login")
////////    public ResponseEntity<?> Complainant(@RequestBody LoginRequest loginRequest) {
////////        Complainant complainant = complainantService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());
////////        if (complainant != null) {
////////            return ResponseEntity.ok().body(new AuthResponse("complainant-dashboard"));
////////        }
////////        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
////////    }
////////
////////    
////////    @GetMapping("{id}")
////////	public Complainant getComplainantById(@PathVariable("id") int id) {
////////		return complainantService.getComplainantId(id);
////////	}
////////    
//////////    @GetMapping("")
////////    
////////    // DTO for login requests
////////    public static class LoginRequest {
////////        private String email;
////////        private String password;
////////
////////        // Getters and setters
////////        public String getEmail() {
////////            return email;
////////        }
////////
////////        public void setEmail(String email) {
////////            this.email = email;
////////        }
////////
////////        public String getPassword() {
////////            return password;
////////        }
////////
////////        public void setPassword(String password) {
////////            this.password = password;
////////        }
////////    }
////////
////////    // DTO for authentication response
////////    public static class AuthResponse {
////////        private String dashboard;
////////
////////        public AuthResponse(String dashboard) {
////////            this.dashboard = dashboard;
////////        }
////////
////////        public String getDashboard() {
////////            return dashboard;
////////        }
////////
////////        public void setDashboard(String dashboard) {
////////            this.dashboard = dashboard;
////////        }
////////    }
////////}
//////
//////
//////package com.vidhu.fir.controller;
//////
//////import com.vidhu.fir.model.Complainant;
//////import com.vidhu.fir.service.ComplainantService;
//////import org.springframework.beans.factory.annotation.Autowired;
//////import org.springframework.beans.factory.annotation.Value;
//////import org.springframework.http.HttpStatus;
//////import org.springframework.http.ResponseEntity;
//////import org.springframework.util.StringUtils;
//////import org.springframework.web.bind.annotation.*;
//////import org.springframework.web.multipart.MultipartFile;
//////
//////import java.io.File;
//////import java.io.FileOutputStream;
//////import java.io.IOException;
//////import java.nio.file.Files;
//////import java.nio.file.Path;
//////import java.nio.file.Paths;
//////import java.time.LocalDate;
//////import java.util.UUID;
//////
//////@RestController
//////@RequestMapping("/complainant")
//////@CrossOrigin("http://localhost:3000")
//////public class ComplainantController {
//////
//////    @Autowired
//////    private ComplainantService complainantService;
//////
//////    @Value("${image.upload.dir}")
//////    private String imageUploadDir; // Directory where images will be stored
//////
//////    @PostMapping("/register")
//////    public ResponseEntity<String> register(@RequestParam("name") String name,
//////                                            @RequestParam("phone") String phone,
//////                                            @RequestParam("email") String email,
//////                                            @RequestParam("password") String password,
//////                                            @RequestParam("address") String address,
//////                                            @RequestParam("image") MultipartFile image,
//////                                            @RequestParam("gender") String gender,
//////                                            @RequestParam("dob") String dob, // Date format should match
//////                                            @RequestParam("identificationType") String identificationType,
//////                                            @RequestParam("idNumber") String idNumber,
//////                                            @RequestParam("relationType") String relationType,
//////                                            @RequestParam("relativeName") String relativeName) {
//////        try {
//////            // Handle image storage
//////            String imageUrl = storeImage(image);
//////            Complainant complainant = new Complainant();
//////            complainant.setName(name);
//////            complainant.setPhone(phone);
//////            complainant.setEmail(email);
//////            complainant.setPassword(password);
//////            complainant.setAddress(address);
//////            complainant.setImageUrl(imageUrl);
//////            complainant.setGender(gender);
//////            complainant.setDob(LocalDate.parse(dob));
//////            complainant.setIdentificationType(identificationType);
//////            complainant.setIdNumber(idNumber);
//////            complainant.setRelationType(relationType);
//////            complainant.setRelativeName(relativeName);
//////
//////            complainantService.addComplainant(complainant);
//////
//////            return ResponseEntity.ok("Registration successful");
//////
//////        } catch (Exception e) {
//////            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Registration failed: " + e.getMessage());
//////        }
//////    }
//////
//////    private String storeImage(MultipartFile file) throws IOException {
//////        if (file.isEmpty()) {
//////            throw new IOException("File is empty");
//////        }
//////
//////        // Generate a unique filename
//////        String filename = UUID.randomUUID().toString() + "_" + StringUtils.cleanPath(file.getOriginalFilename());
//////        Path filePath = Paths.get(imageUploadDir, filename);
//////
//////        // Create directories if they don't exist
//////        Files.createDirectories(filePath.getParent());
//////
//////        // Save the file
//////        try (FileOutputStream fos = new FileOutputStream(filePath.toFile())) {
//////            fos.write(file.getBytes());
//////        }
//////
//////        // Return the URL (assuming you have a public server path to serve images)
//////        return "/images/" + filename;
//////    }
//////
//////    @PostMapping("/login")
//////    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
//////        Complainant complainant = complainantService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());
//////        if (complainant != null) {
//////            return ResponseEntity.ok(new AuthResponse("complainant-dashboard"));
//////        }
//////        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
//////    }
//////
//////    @GetMapping("{id}")
//////    public ResponseEntity<Complainant> getComplainantById(@PathVariable("id") Long id) {
//////        Complainant complainant = complainantService.getComplainantId(id);
//////        if (complainant != null) {
//////            return ResponseEntity.ok(complainant);
//////        }
//////        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//////    }
//////
//////    // DTO for login requests
//////    public static class LoginRequest {
//////        private String email;
//////        private String password;
//////
//////        // Getters and setters
//////        public String getEmail() {
//////            return email;
//////        }
//////
//////        public void setEmail(String email) {
//////            this.email = email;
//////        }
//////
//////        public String getPassword() {
//////            return password;
//////        }
//////
//////        public void setPassword(String password) {
//////            this.password = password;
//////        }
//////    }
//////
//////    // DTO for authentication response
//////    public static class AuthResponse {
//////        private String dashboard;
//////
//////        public AuthResponse(String dashboard) {
//////            this.dashboard = dashboard;
//////        }
//////
//////        public String getDashboard() {
//////            return dashboard;
//////        }
//////
//////        public void setDashboard(String dashboard) {
//////            this.dashboard = dashboard;
//////        }
//////    }
//////}
////
////
////
////package com.vidhu.fir.controller;
////
////import com.vidhu.fir.model.Complainant;
////import com.vidhu.fir.service.ComplainantService;
////
////
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.beans.factory.annotation.Value;
////import org.springframework.http.HttpStatus;
////import org.springframework.http.ResponseEntity;
////import org.springframework.util.StringUtils;
////import org.springframework.web.bind.annotation.*;
////import org.springframework.web.multipart.MultipartFile;
////import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
////
////import java.io.FileOutputStream;
////import java.io.IOException;
////import java.nio.file.Files;
////import java.nio.file.Path;
////import java.nio.file.Paths;
////import java.time.LocalDate;
////import java.util.UUID;
////
////@RestController
////@RequestMapping("/complainant")
////@CrossOrigin("http://localhost:3000")
////public class ComplainantController {
////
////    @Autowired
////    private ComplainantService complainantService;
////  
////    
////
////    private static final String UPLOAD_DIR = "uploads/";
////
////    @PostMapping
////    public String addVenue(@RequestParam("name") String name,
////                           @RequestParam("email") String email,
////                           @RequestParam("phone") String phone,
////                           @RequestParam("address") String address,
////                           @RequestParam("gender") String gender,
////                           @RequestParam("password") String password,
////                           @RequestParam("dob") LocalDate dob,
////                           @RequestParam("identificationType") String identificationType,
//////                           @RequestParam("idNumber") Long idNumber,
////                           @RequestParam("relationType") String relationType,
////                           @RequestParam("relativeName") String relativeName,
////                           @RequestParam(value = "file", required = false) MultipartFile file) {
////        String imageUrl = "";
////        if (file != null && !file.isEmpty()) {
////            imageUrl = saveFile(file);
////        }
////
////        Complainant comp = new Complainant();
////        comp.setName(name);
////        comp.setEmail(email);
////        comp.setPhone(phone);
////        comp.setAddress(address);
////        comp.setPassword(password);
////        comp.setGender(gender);
////        comp.setImageUrl(imageUrl);
////        comp.setDob(dob);
////        comp.setIdentificationType(identificationType);
////        comp.setRelationType(relationType);
//////        comp.setIdNumber(idNumber);
////        comp.setRelativeName(relativeName);
////        
////        complainantService.addComplainant(comp);
////        return "Complainant added successfully";
////    }
////
////    private String saveFile(MultipartFile file) {
////        String fileName = file.getOriginalFilename();
////        Path path = Paths.get(UPLOAD_DIR + fileName);
////        try {
////            Files.createDirectories(path.getParent());
////            Files.write(path, file.getBytes());
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
////        return ServletUriComponentsBuilder.fromCurrentContextPath()
////                .path("/" + UPLOAD_DIR + fileName)
////                .toUriString();
////    }
////    
////
////    @PostMapping("/login")
////    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
////        Complainant complainant = complainantService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());
////        if (complainant != null) {
////            return ResponseEntity.ok(new AuthResponse("complainant-dashboard"));
////        }
////        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
////    }
////
////    @GetMapping("{id}")
////    public ResponseEntity<Complainant> getComplainantById(@PathVariable("id") Long id) {
////        Complainant complainant = complainantService.getComplainantId(id);
////        if (complainant != null) {
////            return ResponseEntity.ok(complainant);
////        }
////        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
////    }
////
////    // DTO for login requests
////    public static class LoginRequest {
////        private String email;
////        private String password;
////
////        // Getters and setters
////        public String getEmail() {
////            return email;
////        }
////
////        public void setEmail(String email) {
////            this.email = email;
////        }
////
////        public String getPassword() {
////            return password;
////        }
////
////        public void setPassword(String password) {
////            this.password = password;
////        }
////    }
////
////    // DTO for authentication response
////    public static class AuthResponse {
////        private String dashboard;
////
////        public AuthResponse(String dashboard) {
////            this.dashboard = dashboard;
////        }
////
////        public String getDashboard() {
////            return dashboard;
////        }
////
////        public void setDashboard(String dashboard) {
////            this.dashboard = dashboard;
////        }
////    }
////}
//
//
//
//package com.vidhu.fir.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.util.StringUtils;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
//import com.vidhu.fir.model.Complainant;
//import com.vidhu.fir.service.ComplainantService;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.time.LocalDate;
//
//@RestController
//@RequestMapping("/complainant")
//@CrossOrigin("http://localhost:3000")
//public class ComplainantController {
//
//    @Autowired
//    private ComplainantService complainantService;
//
//    private static final String UPLOAD_DIR = "/uploads/";
//
//    @PostMapping
//    public ResponseEntity<String> addComplainant(@RequestParam("name") String name,
//                                                 @RequestParam("email") String email,
//                                                 @RequestParam("phone") String phone,
//                                                 @RequestParam("address") String address,
//                                                 @RequestParam("gender") String gender,
//                                                 @RequestParam("password") String password,
//                                                 @RequestParam("dob") LocalDate dob,
//                                                 @RequestParam("identificationType") String identificationType,
//                                                 @RequestParam("idNumber") Long idNumber,
//                                                 @RequestParam("relationType") String relationType,
//                                                 @RequestParam("relativeName") String relativeName,
//                                                 @RequestParam(value = "file", required = false) MultipartFile file) {
//        String imageUrl = "";
//        if (file != null && !file.isEmpty()) {
//            imageUrl = saveFile(file);
//            System.out.println("Image URL: " + imageUrl); // Log the URL for debugging
//        }
//
//        Complainant complainant = new Complainant();
//        complainant.setName(name);
//        complainant.setEmail(email);
//        complainant.setPhone(phone);
//        complainant.setAddress(address);
//        complainant.setPassword(password);
//        complainant.setGender(gender);
//        complainant.setImageUrl(imageUrl);
//        complainant.setDob(dob);
//        complainant.setIdentificationType(identificationType);
//        complainant.setIdNumber(idNumber);
//        complainant.setRelationType(relationType);
//        complainant.setRelativeName(relativeName);
//
//        System.out.println("Complainant: " + complainant); // Log the Complainant object for debugging
//
//        complainantService.addComplainant(complainant);
//        return ResponseEntity.ok("Complainant added successfully");
//    }
//
//    private String saveFile(MultipartFile file) {
//        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//        Path path = Paths.get(UPLOAD_DIR + fileName);
//        try {
//            Files.createDirectories(path.getParent());
//            Files.write(path, file.getBytes());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return ServletUriComponentsBuilder.fromCurrentContextPath()
//                .path(UPLOAD_DIR + fileName)
//                .toUriString();
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
//        Complainant complainant = complainantService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());
//        if (complainant != null) {
//            return ResponseEntity.ok(new AuthResponse("complainant-dashboard"));
//        }
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
//    }
//
//    @GetMapping("{id}")
//    public ResponseEntity<Complainant> getComplainantById(@PathVariable("id") Long id) {
//        Complainant complainant = complainantService.getComplainantId(id);
//        if (complainant != null) {
//            return ResponseEntity.ok(complainant);
//        }
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//    }
//
//    // DTO for login requests
//    public static class LoginRequest {
//        private String email;
//        private String password;
//
//        // Getters and setters
//        public String getEmail() {
//            return email;
//        }
//
//        public void setEmail(String email) {
//            this.email = email;
//        }
//
//        public String getPassword() {
//            return password;
//        }
//
//        public void setPassword(String password) {
//            this.password = password;
//        }
//    }
//
//    // DTO for authentication response
//    public static class AuthResponse {
//        private String dashboard;
//
//        public AuthResponse(String dashboard) {
//            this.dashboard = dashboard;
//        }
//
//        public String getDashboard() {
//            return dashboard;
//        }
//
//        public void setDashboard(String dashboard) {
//            this.dashboard = dashboard;
//        }
//    }
//}


package com.ofps.fir.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.ofps.fir.model.Complainant;
import com.ofps.fir.service.ComplainantService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/complainant")
@CrossOrigin("http://localhost:3000")
public class ComplainantController {

    @Autowired
    private ComplainantService complainantService;

    @PostMapping
    public ResponseEntity<Long> addComplainant(@RequestParam("name") String name,
                                                 @RequestParam("email") String email,
                                                 @RequestParam("phone") String phone,
                                                 @RequestParam("address") String address,
                                                 @RequestParam("gender") String gender,
                                                 @RequestParam("password") String password,
                                                 @RequestParam("dob") LocalDate dob,
                                                 @RequestParam("identificationType") String identificationType,
                                                 @RequestParam("idNumber") String idNumber,
                                                 @RequestParam("relationType") String relationType,
                                                 @RequestParam("relativeName") String relativeName,
                                                 @RequestParam("image")MultipartFile image) {
        byte[] imageBytes = null;
        if (image != null && !image.isEmpty()) {
            try {
                imageBytes = image.getBytes();
            }
             catch (IOException e) {
                e.printStackTrace();
//                return e;
            }
        

        Complainant complainant = new Complainant();
        complainant.setName(name);
        complainant.setEmail(email);
        complainant.setPhone(phone);
        complainant.setAddress(address);
        complainant.setPassword(password);
        complainant.setGender(gender);
        complainant.setImage(imageBytes);
        complainant.setDob(dob);
        complainant.setIdentificationType(identificationType);
        complainant.setIdNumber(idNumber);
        complainant.setRelationType(relationType);
        complainant.setRelativeName(relativeName);

        complainant = complainantService.addComplainant(complainant);
        Long id = complainant.getComplainantId();
        System.out.print(id);
        return ResponseEntity.ok(id);
        }
        else {
        	return null;        	
        }
    
       }

//    @PostMapping("/login")
//    public ResponseEntity<Long> login(@RequestBody LoginRequest loginRequest) {
//        Complainant complainant = complainantService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());
//        if (complainant != null) {
//            return ResponseEntity.ok(complainant.getComplainantId());
//        }
//        return ResponseEntity.status(400);
//    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        Complainant complainant = complainantService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());

        if (complainant != null) {
            LoginResponse response = new LoginResponse(200, complainant.getComplainantId());
            return ResponseEntity.ok(response);
        } else {
            LoginResponse response = new LoginResponse(400, null);
            return ResponseEntity.badRequest().body(response);
        }
    }
    
    
    
    
    
    @GetMapping("/email/{email}")
    public ResponseEntity<Complainant> getComplainantByEmail(@PathVariable String email) {
        Complainant complainant = complainantService.findByEmail(email);
        if (complainant != null) {
            return ResponseEntity.ok(complainant);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    
	
	@GetMapping("/all")
	public List<Complainant> getComplainants() {
		return complainantService.getAllComplainants();
	}
	
	
	
	//to update record use put mapping 
	
	@PutMapping("/email/{email}")
	public String updateComplainant(@RequestBody Complainant comp) {
		
		String msg="";
		try {
			
			complainantService.updateComplainant(comp);
			msg="success";
		}
		
		catch(Exception e) {
			
			e.printStackTrace();
			msg="failure";
			
		}
		return msg;
	}
	
	

//	 @PutMapping("/email/{email}")
//	    public String updateComplainant(
//	        @PathVariable String email,
//	        @RequestParam(value = "name", required = false) String name,
//	        @RequestParam(value = "phone", required = false) String phone,
//	        @RequestParam(value = "address", required = false) String address,
//	        @RequestParam(value = "dob", required = false) LocalDate dob,
//	        @RequestParam(value = "identificationType", required = false) String identificationType,
//	        @RequestParam(value = "password", required = false) String password,
//	        @RequestParam(value = "image", required = false) MultipartFile image
//	    ) {
//	        try {
//	            // Fetch existing complainant and update fields
//	            Complainant complainant = complainantService.findByEmail(email);
//	            if (complainant == null) {
//	                return "failure"; // Or throw an exception
//	            }
//
//	            // Update fields if provided
//	            if (name != null) complainant.setName(name);
//	            if (phone != null) complainant.setPhone(phone);
//	            if (address != null) complainant.setAddress(address);
//	            if (dob != null) complainant.setDob(dob);
//	            if (identificationType != null) complainant.setIdentificationType(identificationType);
//	            if (password != null) complainant.setPassword(password);
//	            if (image != null) {
//	                // Handle file upload
//	                complainant.setImage(image.getBytes());
//	            }
//
//	            complainantService.updateComplainant(complainant);
//	            return "success";
//	        } catch (Exception e) {
//	            e.printStackTrace();
//	            return "failure";
//	        }
//	    }
//	
    

    @GetMapping("{id}")
    public ResponseEntity<Complainant> getComplainantById(@PathVariable("id") Long id) {
        Complainant complainant = complainantService.getComplainantId(id);
        if (complainant != null) {
            return ResponseEntity.ok(complainant);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    // DTO for login requests
    public static class LoginRequest {
        private String email;
        private String password;

        // Getters and setters
        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    // DTO for authentication response
    public static class AuthResponse {
        private String dashboard;

        public AuthResponse(String dashboard) {
            this.dashboard = dashboard;
        }

        public String getDashboard() {
            return dashboard;
        }

        public void setDashboard(String dashboard) {
            this.dashboard = dashboard;
        }
    }
}

class LoginResponse {
    private int statusCode;
    private Long complainantId;

    public LoginResponse(int statusCode, Long complainantId) {
        this.statusCode = statusCode;
        this.complainantId = complainantId;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public Long getComplainantId() {
        return complainantId;
    }

    public void setComplainantId(Long complainantId) {
        this.complainantId = complainantId;
    }
}
