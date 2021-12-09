package com.pihotel.controller.modelview.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NewsController {

	@RequestMapping(value = {"/admin/news", "/admin"})
	public String adminHome() {
		return "admin/bodys/news";
	}
	
}
