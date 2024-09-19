package com.ofps.fir.model;

import java.util.Set;

import jakarta.persistence.*;

@Entity
public class PoliceOfficer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long officerId;

	private String name;
	private String email;
	private String phone;
	private String password;

//    @Lob
//    @Column(columnDefinition = "LONGBLOB")
//    private byte[] image; // Storing image as BLOB

	private String designation;
	@ManyToOne
	@JoinColumn(name = "station_id")
	private Station station;

	public Long getOfficerId() {
		return officerId;
	}

	public void setOfficerId(Long officerId) {
		this.officerId = officerId;
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

//	public byte[] getImage() {
//		return image;
//	}
//
//	public void setImage(byte[] image) {
//		this.image = image;
//	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Station getStation() {
		return station;
	}

	public void setStation(Station station) {
		this.station = station;
	}

	public PoliceOfficer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PoliceOfficer(Long officerId, String name, String email, String phone, String password, byte[] image,
			String designation, Station station) {
		super();
		this.officerId = officerId;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.password = password;
//		this.image = image;
		this.designation = designation;
		this.station = station;
	}

	@Override
	public String toString() {
		return "PoliceOfficer [officerId=" + officerId + ", name=" + name + ", email=" + email + ", phone=" + phone
				+ ", password=" + password + ", designation=" + designation + ", station=" + station + "]";
	}

	// Getters and Setters

}
