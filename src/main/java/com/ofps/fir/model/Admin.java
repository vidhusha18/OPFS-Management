package com.ofps.fir.model;
import java.util.Set;

import jakarta.persistence.*;


@Entity
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adminId;
    private String username;
    private String password;
    
    
   
}
