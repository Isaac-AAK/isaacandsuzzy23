package com.iaktech.weddingweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class Notification {
	
	 @Autowired
	 private JavaMailSender mailSender;
	
	public void messageTemplate(String email ) {
		
		SimpleMailMessage simpleMailMessage =new SimpleMailMessage();
		simpleMailMessage.setFrom("donotreply@iaktech.com");
		simpleMailMessage.setTo(email);
		simpleMailMessage.setSubject("Thank you");
		simpleMailMessage.setText("lova");
		
	mailSender.send(simpleMailMessage);
		
	}

}
