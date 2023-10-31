package com.stackroute.emailservice.controller;

//Java Program to Create Rest Controller that
//Defines various API for Sending Mail


//Importing required classes
import com.stackroute.emailservice.model.EmailDetails;
import com.stackroute.emailservice.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//Annotation
@RestController
//Class
@RequestMapping("/api/v1")
public class EmailController {

	@Autowired private EmailService emailService;

	// Sending a simple Email
	@PostMapping("/sendMail")
	public String sendMail(@RequestBody EmailDetails details){
		String status= emailService.sendSimpleMail(details);

		return status;
	}



    //Sending email with attachment
	@PostMapping("/sendMailWithAttachment")
	public String sendMailWithAttachment(@RequestBody EmailDetails details){
    String status= emailService.sendMailWithAttachment(details);

    return status;
}
	
	@GetMapping("/hello")
	public String hello() {
		return "hello world";
	}
}