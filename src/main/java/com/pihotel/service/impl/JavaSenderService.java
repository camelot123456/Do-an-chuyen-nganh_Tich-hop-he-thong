package com.pihotel.service.impl;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.pihotel.service.IJavaSenderService;

@Service
public class JavaSenderService implements IJavaSenderService{

	@Autowired
	private JavaMailSender mailSender;
	
	@Override
	public void sendSimpleEmail(String toEmail, String body, String subject) {
		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setFrom("nguyensybao1403@gmail.com");
		message.setTo(toEmail);
		message.setText(body);
		message.setSubject(subject);
		
		mailSender.send(message);
		System.out.println("Mail send");
	}
	
	@Override
	public void sendAttachmentMail(String toEmail, String body, String subject, String attachment) throws MessagingException {
		MimeMessage message = mailSender.createMimeMessage();
		
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		
		
		helper.setFrom("nguyensybao1403@gmail.com");
		helper.setTo(toEmail);
		helper.setText(body);
		helper.setSubject(subject);
		
		FileSystemResource resource = new FileSystemResource(new File(attachment));
		helper.addAttachment(resource.getFilename(), resource);
		
		mailSender.send(message);
		System.out.println("Mail send");
	}
	
	@Override
	public void sendHtmlMail(String toEmail, String body, String subject, String htmlTag, String encoding) throws MessagingException {
		MimeMessage message = mailSender.createMimeMessage();
		
		MimeMessageHelper helper = new MimeMessageHelper(message, true, encoding);
		
		message.setContent(htmlTag, "text/html");
		
		helper.setFrom("nguyensybao1403@gmail.com");
		helper.setTo(toEmail);
		
		mailSender.send(message);
		System.out.println("Mail send");
	}
}
