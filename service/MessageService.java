package com.service.java.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.service.java.database.DatabaseClass;
import com.service.java.modal.Message;


public class MessageService {
	
	public MessageService(){
		messages.put(1L, new Message(1,"Hello Thilak","TK",new Date()));
		messages.put(2L, new Message(2,"Hello rock","RK",new Date()));
	}

	private Map<Long, Message> messages = DatabaseClass.getMessages();
	
	public List<Message> getAllMessage(){
		return new ArrayList<Message>(messages.values());
	}
	
	public Message getMessage(long ls){
		return messages.get(ls);
	}
	
	public Message addMessage(Message amsg){
		amsg.setId(messages.size()+1);
		messages.put(amsg.getId(), amsg);
		return amsg;
	}
	
	public Message updateMessage(Message uMessage){
		messages.put(uMessage.getId(), uMessage);
		return uMessage;
	}
	
	public void removeMessage(long id){
		messages.remove(id);
	}
	
	public List<Message> addAllMessages(List<Message> msgs){
		for(Message message:msgs){
			messages.put(message.getId(), message);
		}
		return new ArrayList<Message>(messages.values());
	}
	
	public List<Message> getAllMessagesForYear(int year){
		List<Message> messageForYear = new ArrayList<Message>();
		Calendar cal = Calendar.getInstance();
		for(Message message : messages.values()){
			cal.setTime(message.getCreated());
			if(cal.get(Calendar.YEAR)==year){
				messageForYear.add(message);
			}
		}
		return messageForYear;
	}
	
	public List<Message> getAllMessagePaginated(int start, int size){
		List<Message> list = new ArrayList<Message>(messages.values());
		if(start+size > list.size()) return new ArrayList<Message>();
		return list.subList(start, start+size);
	}
	
}
