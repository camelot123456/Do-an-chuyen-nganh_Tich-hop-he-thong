package com.pihotel.controller.modelview.admin;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pihotel.constant.SystemConstant;
import com.pihotel.entity.RoomEntity;
import com.pihotel.entity.enums.ERoomState;
import com.pihotel.service.IRoomServ;
import com.pihotel.service.IRoomTypeServ;

@Controller
public class RoomController {

	@Autowired
	private IRoomServ roomServ;
	
	@Autowired
	private IRoomTypeServ roomTypeServ;

//	---------------------------------------GET---------------------------------------
	
	@RequestMapping(value = "/admin/room-managements/room")
	public String roomRedirectPagination(Model model) {
		return this.roomPagination(model, 1, "id", "asc", "");
	}
	
	@RequestMapping(value = "/admin/room-managements/room/page/{currentPage}")
	public String roomPagination(Model model, 
			@PathVariable("currentPage") int currentPage,
			@Param("sortField") String sortField, 
			@Param("sortDir") String sortDir, 
			@Param("keyword") String keyword) {

		Page<RoomEntity> page = roomServ.findAll(currentPage, sortField, sortDir, keyword);
		String reverseSort = sortDir.equalsIgnoreCase("asc") ? "desc" : "asc";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("currentPage", (int) currentPage);
		map.put("sortField", (String) sortField);
		map.put("reverseSort", (String) reverseSort);
		map.put("sortDir", (String) sortDir);
		map.put("keyword", (String) keyword);
		map.put("totalPage", (int) page.getTotalPages() < 1 ? 1 : page.getTotalPages());
		map.put("totalElement", (int) page.getTotalElements());
		map.put(SystemConstant.ROOMS, page.getContent());
		map.put(SystemConstant.ROOMS_TYPE, roomTypeServ.findAll());
		map.put("MAX_FLOOR", roomServ.maxFloor());
		map.put("COUNT_ROOMTYPE", roomTypeServ.findAll().stream().count());
		map.put("COUNT_ROOM", roomServ.findAll().stream().count());
		
		model.addAllAttributes(map);

		return "admin/bodys/room_managements/rm_room";
	}
	
//	---------------------------------------POST---------------------------------------
	
//	---------------------------------------PUT---------------------------------------
	
	@RequestMapping(value = "/admin/room-managements/room/tran/handle-cancel", method = RequestMethod.PUT, consumes = "application/json")
	public String doCancelUpdateRoomState(@RequestBody RoomEntity room) {
		roomServ.updateRoomState(ERoomState.EMPTY, room.getId());
		return "redirect:/admin/room-managements/room";
	}
	
	@RequestMapping(value = "/admin/room-managements/room/tran/handle-repair", method = RequestMethod.PUT, consumes = "application/json")
	public String doRepairUpdateRoomState(@RequestBody RoomEntity room) {
		roomServ.updateRoomState(ERoomState.REPAIR, room.getId());
		return "redirect:/admin/room-managements/room";
	}
	
//	---------------------------------------PATCH---------------------------------------
	
//	---------------------------------------DELETE---------------------------------------
	
	
	
}
