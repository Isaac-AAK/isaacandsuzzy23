package com.iaktech.weddingweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class Notification {
	
	@Autowired
	private JavaMailSender mailSender;

	public Boolean notificationTemplate(String email, String name ) throws MessagingException {
 		
		String from = "donotreply@gmail.com";
		 
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message,true);

		try{
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

//			File attachment = new File("src//main//resources//static//assets//img//rsvp.jpg");
//			FileSystemResource file = new FileSystemResource(attachment);
//			helper.addAttachment("rsvp.jpg", file);
			mailSender.send(message);
			return true;

		}catch (MessagingException e){
			return false;
		}


	}
	  
}
