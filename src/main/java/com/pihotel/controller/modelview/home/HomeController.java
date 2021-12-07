package com.pihotel.controller.modelview.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping(value = {"/", "/home"})
	public String home() {
		return "home/bodys/home";
	}
	
	@RequestMapping(value = "/checkin")
	public String bookroom() {
		return "home/bodys/checkin";
	}
	
}
