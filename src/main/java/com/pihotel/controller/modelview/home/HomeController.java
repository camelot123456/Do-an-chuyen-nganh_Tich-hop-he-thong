package com.pihotel.controller.modelview.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pihotel.constant.SystemConstant;
import com.pihotel.service.IRoomTypeServ;

@Controller
public class HomeController {

	@Autowired
	private IRoomTypeServ roomTypeServ;
	
	@RequestMapping(value = {"/", "/home"})
	public String home() {
		return "home/bodys/home";
	}
	
	@RequestMapping(value = "/home/checkin")
	public String bookroom() {
		return "home/bodys/checkin";
	}
	
	@RequestMapping(value = "/home/room")
	public String showRoomTypeList(Model model) {
		model.addAttribute(SystemConstant.ROOMS_TYPE, roomTypeServ.findAll());
//		model.addAttribute("rooms_type_count", roomTypeServ.findAll().stream().filter(room -> room.getId().equals(room.getRooms().ge)));
		return "home/bodys/room_list";
	}
	
	@RequestMapping(value = "/home/room/detail/{id}")
	public String showDetailRoom(Model model, @PathVariable("id") String id) {
		model.addAttribute(SystemConstant.ROOM_TYPE, roomTypeServ.findOneById(id));
		return "home/bodys/detail_room";
	}
}
