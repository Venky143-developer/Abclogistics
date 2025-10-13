package com.alpha.ABClogistics.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alpha.ABClogistics.Service.Mailservice;

@RestController
public class MailController {
	
	@Autowired Mailservice mailService;
	
	@PostMapping("/sendmail")
	
	public void sendmail() {
		String tomail = "jaadisanghavi123@gmail.com";
		String subject = "This is sample mail";
		String content= "This is a dummy mail please ignore";
		
		mailService.sendMail(tomail,subject,content);
	}

}
