package com.pihotel.controller.modelview.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RoomController {

	@RequestMapping(value = "/admin/room-managements/room")
	public String showRoom() {
		return "admin/bodys/room_managements/rm_room";
	}
	
}
