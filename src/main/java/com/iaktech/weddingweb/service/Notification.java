package com.iaktech.weddingweb.service;



import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class Notification {
	
	@Autowired
	private JavaMailSender mailSender;
	 	
//	public void messageTemplate(String email ) {
//		
//		SimpleMailMessage simpleMailMessage =new SimpleMailMessage();
//		simpleMailMessage.setFrom("donotreply@iaktech.com");
//		simpleMailMessage.setTo(email);
//		simpleMailMessage.setSubject("Thank you");
//		simpleMailMessage.setText("lova");
//		
//		emailSender.send(simpleMailMessage);
//		
//	}
	
	public void notificationTemplate(String email, String name ) throws MessagingException {
 		String from = "donotreply@gmail.com";
		 
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		 
		helper.setSubject("RSVP Confirmation");
		helper.setFrom(from);
		helper.setTo(email);
		 
		boolean html = true;
		helper.setText("<b>Hello "+name+"</b>,<br><br><i>Thank you so much for your RSVP to our wedding. It means a lot to us that you will be joining us on  September 2,2023. We are excited to celebrate with you and look forward to seeing you soon!\r\n<br>"
				+ "<br>"
				+ "\r\n"
				+ "If you have any questions or concerns, please don't hesitate to reach out. Otherwise, we will see you on September 2,2023!\r\n"
				+ "\r\n<br><br>"
				+ "Best regards,\r\n<br>"
				+ "Isaac & Suzzy</i>", html);

		mailSender.send(message);
	}
	  
}
