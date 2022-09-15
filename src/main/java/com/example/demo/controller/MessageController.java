package com.example.demo.controller;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.MailInformations;
import com.example.demo.service.MailSenderService;

@RestController
public class MessageController {
	
	@Autowired
	MailSenderService service;
	
	/**
	 * this method used for send a mail without attachment
	 * @param details
	 * @return
	 */
	@PostMapping("mailSender")
	public  ResponseEntity<?> mailSender(@RequestBody MailInformations details){
		String from  = details.getFrom();
		String to = details.getTo();
		String subject = details.getSubject();
		String body = details.getMessage();
		try {
			service.sendMail(from,to,subject,body);
			return new ResponseEntity<>("success",HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);

		}
		
	}
	
	/**
	 * this method used for send a mail with attachment
	 * @param mailId
	 * @param body
	 * @param subject
	 * @param attachment
	 * @return
	 */
	@PostMapping("sendMailWithAttachment")
	public ResponseEntity<?>sendMailWithAttachment(@RequestParam("mailId") String mailId,@RequestParam("body") String body,@RequestParam("subject") String subject,@RequestParam("attachment") String attachment,@RequestBody MultipartFile file){
		try {
			service.sendEmailWithAttachment(mailId, body, subject, attachment,file);
			return new ResponseEntity<>("success",HttpStatus.OK);
		} catch (MessagingException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);

		}
	}

}
