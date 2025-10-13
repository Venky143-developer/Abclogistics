package com.alpha.ABClogistics.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class Mailservice {
	@Autowired
	JavaMailSender javamailSender;

	public void sendMail(String tomail, String subject, String content) {
		SimpleMailMessage message= new SimpleMailMessage();
		message.setTo(tomail);
		message.setSubject(subject);
		message.setText(content);
		
		javamailSender.send(message);
	}
	

}
