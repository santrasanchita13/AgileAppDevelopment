package com.pakhi.agile.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.pakhi.agile.constants.ROLES;

@Entity
@Table(name = "Agile_user")
public class AgileUser {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "emailId", nullable = false, unique = true)
	private String emailId;
	
	@Column(name = "roleId", nullable = false)
	private ROLES roleId;
	
	@Column(name = "imagePath")
	private String imagePath;
	
	@Column(name = "firstName", nullable = false)
	private String firstName;
	
	@Column(name = "middleName")
	private String middleName;
	
	@Column(name = "lastName", nullable = false)
	private String lastName;
	
	@Column(name = "mobileNumber", nullable = true, unique = true)
	private String mobileNumber;
	
	@Column(name = "website")
	private String website;
	
	public AgileUser() {
	}
	
	public AgileUser(String emailId, ROLES roleId, String firstName, String lastName) {
		super();
		this.emailId = emailId;
		this.roleId = roleId;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public AgileUser(String emailId, ROLES roleId, String imagePath, String firstName, String middleName,
			String lastName, String mobileNumber, String website) {
		super();
		this.emailId = emailId;
		this.roleId = roleId;
		this.imagePath = imagePath;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.mobileNumber = mobileNumber;
		this.website = website;
	}

	public Long getId() {
		return id;
	}

	public String getEmailId() {
		return emailId;
	}

	public ROLES getRoleId() {
		return roleId;
	}



	public String getImagePath() {
		return imagePath;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public String getWebsite() {
		return website;
	}

	@Override
	public String toString() {
		return "AgileUser [id=" + id + ", emailId=" + emailId + ", roleId=" + roleId + ", imagePath=" + imagePath
				+ ", firstName=" + firstName + ", middleName=" + middleName + ", lastName=" + lastName
				+ ", mobileNumber=" + mobileNumber + ", website=" + website + "]";
	}
}
