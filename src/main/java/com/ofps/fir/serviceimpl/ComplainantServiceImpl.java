//package com.vidhu.fir.serviceimpl;
//
//
//
//import com.vidhu.fir.model.Complainant;
//import com.vidhu.fir.repo.ComplainantRepository;
//import com.vidhu.fir.service.ComplainantService;
//import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//public class ComplainantServiceImpl implements ComplainantService {
//
//	
//
//	ComplainantRepository comprepo;
//	
//	public ComplainantServiceImpl(ComplainantRepository comprepo) {
//		super();
//		this.comprepo = comprepo;
//	}
//	
//	public void addComplainant(Complainant comp) {
//		comprepo.save(comp);		
//	}
//
//	public Complainant findByEmail(String email) {
//		
//		return comprepo.findByEmail(email);
//	}
//
//}

package com.ofps.fir.serviceimpl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ofps.fir.model.Complainant;
import com.ofps.fir.repo.ComplainantRepository;
import com.ofps.fir.service.ComplainantService;

@Service
public class ComplainantServiceImpl implements ComplainantService {

    private final ComplainantRepository comprepo;

    @Autowired
    public ComplainantServiceImpl(ComplainantRepository comprepo) {
        this.comprepo = comprepo;
    }

    @Override
    public Complainant addComplainant(Complainant comp) {
        return comprepo.save(comp);
    }

    @Override
    public Complainant findByEmail(String email) {
        return comprepo.findByEmail(email);
    }

    public Complainant authenticate(String email, String password) {
        Complainant complainant = comprepo.findByEmail(email);
        if (complainant != null && complainant.getPassword().equals(password)) {
            return complainant;
        }
        return null;
    }

	public Complainant getComplainantId(Long id) {
		// TODO Auto-generated method stub
		return comprepo.findById(id);
	}

//	@Override
//	public Complainant getComplainantId(Long id) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public void updateComplainant(Complainant comp) {
		// TODO Auto-generated method stub
		comprepo.update(comp);
		
	}

	@Override
	public List<Complainant> getAllComplainants() {
		return comprepo.findAll();
	}

	
	

	
}
