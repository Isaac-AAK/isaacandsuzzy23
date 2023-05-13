package com.iaktech.weddingweb.controller;



import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.validation.Valid;
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

	private String name;
	private String email;
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
	
	@PostMapping("/rsvp-form")
	public String  addRsvp(@Valid @ModelAttribute("rsvpForm") Rsvp addRsvp, BindingResult result) throws MessagingException {
		if (result.hasErrors()) {
			return "booking";
		}

		 name=	addRsvp.getName();
		 email=	addRsvp.getContact();
        boolean checkName =name.matches("^[A-Za-z]+([\\ A-Za-z]+)*");
			if (checkName){
				addRsvp.getNumberOfGuest();
				rsvpService.addRsvp(addRsvp);
				notification.notificationTemplate(email,name);
				return "redirect:/";
			}
		return "redirect:/rsvp";
	}
}
