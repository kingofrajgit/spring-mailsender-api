package com.example.demo.service;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class MailSenderService {

	@Autowired
	private JavaMailSender mailSender;

	public String sendMail(String from, String to, String subject, String body) throws Exception {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("spring.email.from@gmail.com");
		message.setTo(to);
		message.setSubject(subject);
		message.setText(body);
		mailSender.send(message);
		return "Success";
	}

	public void sendEmailWithAttachment(String emailId, String body, String subject, String attachment, MultipartFile file)
			throws MessagingException {

		MimeMessage mimeMessage = mailSender.createMimeMessage();

		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

		mimeMessageHelper.setFrom("kingofraj.7092@gmail.com");
		mimeMessageHelper.setTo(emailId);
		mimeMessageHelper.setText(body);
		mimeMessageHelper.setSubject(subject);
		mimeMessageHelper.addAttachment(attachment, file);

		mailSender.send(mimeMessage);
		System.out.println("mail sended");

	}
}
