package com.service.java.modal;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="",propOrder={"profileName","fName","lastName"})

@XmlRootElement
public class Profile {
	
	public Profile(){
	}
	
	public Profile(String profName, String fName, String lName){
		this.profileName = profName;
		this.lastName = lName;
		this.fName = fName;
	}
	
	private String profileName;
	@XmlElement(name="fName")
	private String fName;
	private String lastName;
	@XmlTransient
	private Date created;
	
	public String getProfileName() {
		return profileName;
	}
	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}
	public String getFirstName() {
		return fName;
	}
	public void setFirstName(String firstName) {
		this.fName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	
	
	
}
