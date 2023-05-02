package com.iaktech.weddingweb.controller;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.iaktech.weddingweb.model.Rsvp;
import com.iaktech.weddingweb.service.Notification;
import com.iaktech.weddingweb.service.RsvpService;


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
	public String  addRsvp(@ModelAttribute Rsvp addRsvp, Model model) {
		notification.messageTemplate(addRsvp.getContact());
		rsvpService.addRsvp(addRsvp);
		model.addAttribute("rsvpForm", new Rsvp());
		
		return "redirect:/";
		
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
