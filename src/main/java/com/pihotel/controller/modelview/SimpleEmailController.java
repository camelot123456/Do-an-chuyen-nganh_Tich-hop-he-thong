package com.pihotel.controller.modelview;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pihotel.service.IJavaSenderService;

@Controller
public class SimpleEmailController {

	@Autowired
	private IJavaSenderService senderService;
	
//	Soạn mail đơn giản
	@ResponseBody
	@RequestMapping("/sendSimpleEmail")
	public String sendSimpleEmail() {
		senderService.sendSimpleEmail(
				"a@gmail.com", 
				"Spring boot mail sender", 
				"Test mail nha anh trai");
		return "Email Sent!";
	}
	
//	soạn mail có tệp tin đính kèm
	@ResponseBody
	@RequestMapping("/sendAttachmentEmail")
	public String sendAttachmentEmail() throws MessagingException {
		senderService.sendAttachmentMail(
				"a@gmail.com", 
				"Test với tệp đính kèm nha anh trai <3", 
				"Test email with attachments", 
				"C:\\Users\\nguye\\BaoNguyenData\\Old Data\\CS445\\Project_Group_5_Bao_Thong\\QuanLyKhachSan\\src\\main\\resources\\static\\img\\user\\pub\\user.png");
		return "Email Sent!";
	}
	
//	Gửi mail định dạng html
	@ResponseBody
	@RequestMapping("/sendHtmlEmail")
	public String sendHtmlEmail() throws MessagingException {
		String htmlMsg = "<h3>Im testing send a HTML email</h3>"
                +"<img src='http://www.apache.org/images/asf_logo_wide.gif'>";
		senderService.sendHtmlMail(
				"a@gmail.com",
				"Please confirm your Email account",
				htmlMsg);
		return "Email Sent!";
	}
}
