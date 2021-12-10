package com.pihotel.controller.modelview.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;

import com.pihotel.constant.SystemConstant;
import com.pihotel.entity.InvoiceEntity;
import com.pihotel.entity.RoomTypeEntity;
import com.pihotel.service.IInvoiceServ;
import com.pihotel.service.IRoomTypeServ;
import com.pihotel.service.IServiceServ;

@Controller
public class HomeController {

	@Autowired
	private IRoomTypeServ roomTypeServ;
	
	@Autowired
	private IInvoiceServ invoiceServ;
	
	@Autowired
	private IServiceServ serviceServ;
	
//	---------------------------------------GET---------------------------------------	
	
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
		return "home/bodys/room_list";
	}
	
	@RequestMapping(value = "/home/room/detail/{id}")
	public String showDetailRoom(Model model, @PathVariable("id") String id) {
		model.addAttribute(SystemConstant.ROOM_TYPE, roomTypeServ.findOneById(id));
		return "home/bodys/detail_room";
	}
	
	@RequestMapping(value = "/home/checkin/{id}")
	public String bookroomInvoice(Model model, @PathVariable("id") String id) {
		model.addAttribute(SystemConstant.SERVICES, serviceServ.findAll());
		model.addAttribute(SystemConstant.ROOM_TYPE, roomTypeServ.findOneById(id));
		return "home/bodys/checkin";
	}
	
//	---------------------------------------POST---------------------------------------
	
	@RequestMapping(value = "/home/checkin", method = RequestMethod.POST, consumes = {
			"multipart/form-data", "application/json" })
	public String doSaveInvoice(@RequestPart("invoice") InvoiceEntity invoice,
			@RequestPart("roomType") RoomTypeEntity roomType) {
		invoiceServ.addRoomToInvoice(invoice);
		return "redirect:/home/checkin/" + roomType.getId();
	}
	
//	---------------------------------------PUT---------------------------------------
	
//	---------------------------------------PATCH---------------------------------------
	
//	---------------------------------------DELETE---------------------------------------
}
