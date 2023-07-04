package com.iaktech.weddingweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.iaktech.weddingweb.model.Rsvp;
import com.iaktech.weddingweb.repo.RsvpRepository;

import jakarta.mail.MessagingException;

@Service
public class RsvpService {
	
	@Value("${rsvp.code}")
	private String rsvpCode;
	
	@Autowired
	private Notification notification;
	
	@Autowired
	private RsvpRepository rsvpRepository;
	
	public  List<Rsvp> getAll(){
		return rsvpRepository.findAll();
	}

	public boolean addRsvp( Rsvp rsvp) throws MessagingException {

		return addRsvps(rsvp);
	}
	
	public List<Rsvp> findEmail(String email) {
		return rsvpRepository.findByContact(email);
	}
	
	private boolean addRsvps(Rsvp addRsvp) throws MessagingException {
		String  name=	addRsvp.getName();
		String email=	addRsvp.getContact();
	 
		var rsvp =  findEmail(email);
		  if (rsvp.isEmpty()) {
			  boolean checkName =name.matches("^[A-Za-z]+([\\ A-Za-z]+)*");
			if (checkName && addRsvp.getRsvpCode().contentEquals(rsvpCode)){
						addRsvp.getNumberOfGuest();
						rsvpRepository.save(addRsvp);
						notification.notificationTemplate(email,name);
						return true;
					}
			
		}
 	return false;
	}
}
