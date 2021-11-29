package com.pihotel.service.impl;

import java.io.File;
import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.pihotel.entity.AccountEntity;
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
		
		MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
		
		helper.setFrom("nguyensybao1403@gmail.com");
		helper.setTo(toEmail);
		helper.setText(body, "UTF-8");
		helper.setSubject(subject);
		
		FileSystemResource resource = new FileSystemResource(new File(attachment));
		helper.addAttachment(resource.getFilename(), resource);
		
		mailSender.send(message);
		System.out.println("Mail send");
	}
	
	@Override
	public void sendHtmlMail(String toEmail, String subject, String htmlTag) throws MessagingException {
		MimeMessage message = mailSender.createMimeMessage();
		
		MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
		
		message.setContent(htmlTag, "text/html; charset=UTF-8");
		
		helper.setFrom("nguyensybao1403@gmail.com");
		helper.setSubject(subject);
		helper.setTo(toEmail);
		
		mailSender.send(message);
		System.out.println("Mail send");
	}

	@Override
	public void sendVerificationEmail(AccountEntity account, String siteURL) throws UnsupportedEncodingException, MessagingException {
		// TODO Auto-generated method stub
		String htmlContent = ""
				+ "<h2 style='color: red;'>Xin chào, [[name]]</h2>"
				+ "<br />"
				+ "<p>Chúng tôi cần bạn xác minh email trước khi sử dụng My Account Pi Network</p>"
				+ "<br />"
				+ "<p>Vui lòng nhấp vào liên kết bên dưới để xác minh đăng ký của bạn</p>"
				+ "<a href='[[url]]' target='_self'>Xác minh</a>"
				+ "<p>Xin cảm ơn</p>"
				+ "<p>Chúc bạn thành công ^^</p>";
		
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
		
		helper.setFrom("nguyensybao1403@gmail.com", "Khách sạn COTS");
		helper.setTo(account.getEmail());
		helper.setSubject("Please verify your registration");
		
		String verifyURL = siteURL + "/register/verify?code=" + account.getVerificationCode();
		
		htmlContent = htmlContent.replace("[[name]]", account.getName());
		htmlContent = htmlContent.replace("[[url]]", verifyURL);
		
		message.setContent(htmlContent, "text/html; charset=UTF-8");
		
		mailSender.send(message);		
		System.out.println("Mail send");
	}
}
