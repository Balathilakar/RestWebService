package com.service.java.database;

import com.service.java.modal.Message;
import com.service.java.modal.Profile;

import java.util.Map;
import java.util.HashMap;

// Store and process messages and profiles in HashMap, HashMap act as database.

public class DatabaseClass {
	
	private static Map<Long, Message> messages = new HashMap<Long,Message>();
	private static Map<String, Profile> profiles = new HashMap<String,Profile>();
	public static Map<Long, Message> getMessages() {
		return messages;
	}
	public static void setMessages(Map<Long, Message> messages) {
		DatabaseClass.messages = messages;
	}
	public static Map<String, Profile> getProfiles() {
		return profiles;
	}
	public static void setProfiles(Map<String, Profile> profiles) {
		DatabaseClass.profiles = profiles;
	}
	
		
}
