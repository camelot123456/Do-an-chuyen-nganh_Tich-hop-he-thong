package com.pihotel.service;

import javax.mail.MessagingException;

public interface IJavaSenderService {

	public void sendSimpleEmail(String toEmail, String body, String subject);
	
	public void sendAttachmentMail(String toEmail, String body, String subject, String attachment) throws MessagingException;
	
	public void sendHtmlMail(String toEmail, String body, String subject, String htmlTag, String encoding) throws MessagingException;
	
}
