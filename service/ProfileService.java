package com.service.java.service;

import com.service.java.database.DatabaseClass;
import com.service.java.modal.Profile;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class ProfileService {
	
	private Map<String, Profile>  profiles = DatabaseClass.getProfiles();
	
	public ProfileService(){
		profiles.put("Mark", new Profile("Mark","Bill","Hurd"));
		profiles.put("JACK", new Profile("JACk","Terry","Wiebango"));
	}
	
	public List<Profile> getProfiles(){
		return new ArrayList<Profile>(profiles.values());
	}
	
}
