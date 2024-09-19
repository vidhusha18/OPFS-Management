

package com.ofps.fir.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Complainant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long complainantId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String phone;

    @Column(nullable = false)
    private String password;

    private String address;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] image; // Storing image as Blob

    private String gender;
    private LocalDate dob; // Date of Birth
    private String identificationType;
    
    @Column(nullable = false, unique = true)
    private String idNumber; // Added idNumber field
    private String relationType;
    private String relativeName;

    // Constructors
    public Complainant() {
        super();
    }

	public Complainant(Long complainantId, String name, String email, String phone, String password, String address,
			byte[] image, String gender, LocalDate dob, String identificationType, String idNumber, String relationType,
			String relativeName) {
		super();
		this.complainantId = complainantId;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.password = password;
		this.address = address;
		this.image = image;
		this.gender = gender;
		this.dob = dob;
		this.identificationType = identificationType;
		this.idNumber = idNumber;
		this.relationType = relationType;
		this.relativeName = relativeName;
	}

	public Long getComplainantId() {
		return complainantId;
	}

	public void setComplainantId(Long complainantId) {
		this.complainantId = complainantId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getIdentificationType() {
		return identificationType;
	}

	public void setIdentificationType(String identificationType) {
		this.identificationType = identificationType;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getRelationType() {
		return relationType;
	}

	public void setRelationType(String relationType) {
		this.relationType = relationType;
	}

	public String getRelativeName() {
		return relativeName;
	}

	public void setRelativeName(String relativeName) {
		this.relativeName = relativeName;
	}
}
