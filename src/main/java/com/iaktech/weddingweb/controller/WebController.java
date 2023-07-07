package com.iaktech.weddingweb.controller;




import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.iaktech.weddingweb.model.Rsvp;

import com.iaktech.weddingweb.service.RsvpService;
import jakarta.mail.MessagingException;



@Controller
public class WebController {
	@Autowired
	private RsvpService rsvpService;
		
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
		if(rsvpService.addRsvp(addRsvp)) {
			return "redirect:/";
		}
		
		return "redirect:/rsvp";
	}
}
