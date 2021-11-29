package com.pihotel.service;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

import com.pihotel.entity.AccountEntity;

public interface IJavaSenderService {

	public void sendSimpleEmail(String toEmail, String body, String subject);
	
	public void sendAttachmentMail(String toEmail, String body, String subject, String attachment) throws MessagingException;
	
	public void sendHtmlMail(String toEmail, String subject, String htmlTag) throws MessagingException;

	public void sendVerificationEmail(AccountEntity account, String siteURL) throws UnsupportedEncodingException, MessagingException;
}
