package com.pihotel.controller.modelview.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping(value = {"/", "/home"})
	public String home() {
		return "home/bodys/home";
	}
	
	@RequestMapping(value = "/home/checkin")
	public String bookroom() {
		return "home/bodys/checkin";
	}
	
	@RequestMapping(value = "/home/room")
	public String showRoom() {
		return "home/bodys/room_list";
	}
	
	@RequestMapping(value = "/home/room/detail")
	public String showDetailRoom() {
		return "home/bodys/detail_room";
	}
}
