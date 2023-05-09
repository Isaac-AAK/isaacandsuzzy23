package com.iaktech.weddingweb.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.iaktech.weddingweb.model.Rsvp;
import com.iaktech.weddingweb.service.Notification;
import com.iaktech.weddingweb.service.RsvpService;

import jakarta.mail.MessagingException;



@Controller
public class WebController {
	@Autowired
	private RsvpService rsvpService;
	
	@Autowired
	private Notification notification;
	
	
	@RequestMapping(value = "/")
	public String homePage(Model model) {
		model.addAttribute("rsvpForm", new Rsvp());
		return "index";
	}
	
	@RequestMapping(value = "/rsvp")
	public String rsvpPage(Model model) {
		model.addAttribute("rsvpForm", new Rsvp());
		return "booking";
	}
	
	@PostMapping("/save")
	public String  addRsvp(@ModelAttribute Rsvp addRsvp, BindingResult result, Model model) throws MessagingException{
	
		
		String name=	addRsvp.getName();
		String email=	addRsvp.getContact();
		addRsvp.getNumberOfGuest();
		rsvpService.addRsvp(addRsvp);
		
		
		model.addAttribute("rsvpForm", new Rsvp());
		if(notification.notificationTemplate(email,name)) {
			return "redirect:/";
		}
		
		return "redirect:/rsvp";
		
	}
	
	/**
	 * TODO
	 * Page title
	 * hyperlink for address
	 * pop up to the rsvp
	 * email to attendance
	 * list of rsvp
	 * 
	 * 
	 */
	
}
