package com.pihotel.controller.modelview.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DashboardController {

	@RequestMapping(value = {"/admin/dashboard"})
	public String adminHome() {
		return "admin/bodys/dashboard";
	}
	
}
