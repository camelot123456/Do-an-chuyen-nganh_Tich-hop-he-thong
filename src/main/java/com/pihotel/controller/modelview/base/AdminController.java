package com.pihotel.controller.modelview.base;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {

	@RequestMapping(value = {"/admin/dashboard", "/admin"})
	public String adminHome() {
		return "admin/bodys/dashboard";
	}
	
}