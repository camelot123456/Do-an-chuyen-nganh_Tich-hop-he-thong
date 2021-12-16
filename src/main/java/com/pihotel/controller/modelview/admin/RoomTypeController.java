package com.pihotel.controller.modelview.admin;

import java.io.IOException;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.pihotel.constant.SystemConstant;
import com.pihotel.entity.RoomTypeEntity;
import com.pihotel.service.IAccountServ;
import com.pihotel.service.IRoomTypeServ;
import com.pihotel.utils.UploadFileUtil;

import net.bytebuddy.utility.RandomString;

@Controller
public class RoomTypeController {
	
	public static final String PATH_ROOMTYPE = "src/main/resources/static/img/roomtype/";
	
	@Autowired
	private IAccountServ accountServ;
	
	@Autowired
	private IRoomTypeServ roomTypeServ;

//	---------------------------------------GET---------------------------------------
	
	@RequestMapping(value = "/admin/roomtype-managements/roomtype")
	public String roomRedirectPagination(Model model) {
		return this.roomPagination(model, 1, "id", "asc", "");
	}
	
	@RequestMapping(value = "/admin/roomtype-managements/roomtype/page/{currentPage}")
	public String roomPagination(Model model, 
			@PathVariable("currentPage") int currentPage,
			@Param("sortField") String sortField, 
			@Param("sortDir") String sortDir, 
			@Param("keyword") String keyword) {

		Page<RoomTypeEntity> page = roomTypeServ.findAll(currentPage, sortField, sortDir, keyword);
		String reverseSort = sortDir.equalsIgnoreCase("asc") ? "desc" : "asc";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("currentPage", (int) currentPage);
		map.put("sortField", (String) sortField);
		map.put("reverseSort", (String) reverseSort);
		map.put("sortDir", (String) sortDir);
		map.put("keyword", (String) keyword);
		map.put("totalPage", (int) page.getTotalPages() < 1 ? 1 : page.getTotalPages());
		map.put("totalElement", (int) page.getTotalElements());
		map.put(SystemConstant.ROOMS_TYPE, page.getContent());
		
		model.addAllAttributes(map);

		return "admin/bodys/roomtype_managements/rtm_roomtype";
	}
	
	@RequestMapping(value = "/admin/roomtype-managements/roomtype/{idRoomtype}")
	public String roomRedirectPagination(Model model, @PathVariable("idRoomtype") String idRoomtype) {
		model.addAttribute(SystemConstant.ROOM_TYPE, roomTypeServ.findOneById(idRoomtype));
		return "admin/bodys/roomtype_managements/rtm_detail_roomtype";
	}
	
	@RequestMapping(value = "/admin/roomtype-managements/roomtype/add")
	public String accountInsertShow(Model model, Principal principal) {
		model.addAttribute("CREATE_AT", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").format(new Date()));
		model.addAttribute("RANDOM_ID", RandomString.make(12));
		model.addAttribute("EMAIL", accountServ.findOneByUsername(principal.getName()).getEmail());
		return "admin/bodys/roomtype_managements/rtm_roomtype_insert";
	}
	
//	---------------------------------------POST---------------------------------------
	
	@RequestMapping(value = "/admin/roomtype-managements/roomtype/tran", method = RequestMethod.POST, consumes = {"multipart/form-data", "application/json"})
	public String doAccountAddRoleToAccount(@RequestPart("roomType") RoomTypeEntity roomType,
			@RequestPart("logo") MultipartFile multipartFile,
			Principal principal) throws IOException {
		roomType.setLogo(multipartFile.getOriginalFilename());
		roomType.setCreateAt(new Date());
		roomType.setCreateBy(accountServ.findOneByUsername(principal.getName()).getEmail());
		roomType.setModifiedAt(new Date());
		roomType.setModifiedBy(accountServ.findOneByUsername(principal.getName()).getEmail());
		UploadFileUtil.saveFile(PATH_ROOMTYPE, multipartFile.getOriginalFilename(), multipartFile);
		roomTypeServ.save(roomType);
		return "redirect:/admin/roomtype-managements/roomtype/" + roomType.getId();
	}
	
//	---------------------------------------PUT---------------------------------------
	
	@RequestMapping(value = "/admin/roomtype-managements/roomtype/tran", method = RequestMethod.PUT)
	public String accountUpdate(@RequestPart("roomType") RoomTypeEntity roomType,
			@RequestPart("image") MultipartFile multipartFile,
			Principal principal) throws IOException {
		roomType.setLogo(multipartFile.getOriginalFilename());
		roomType.setCreateAt(new Date());
		roomType.setCreateBy(accountServ.findOneByUsername(principal.getName()).getEmail());
		roomType.setModifiedAt(new Date());
		roomType.setModifiedBy(accountServ.findOneByUsername(principal.getName()).getEmail());
		UploadFileUtil.saveFile(PATH_ROOMTYPE, multipartFile.getOriginalFilename(), multipartFile);
		roomTypeServ.update(roomType);
		return "redirect:/admin/roomtype-managements/roomtype/" + roomType.getId();
	}
	
//	---------------------------------------PATCH---------------------------------------
	
//	---------------------------------------DELETE---------------------------------------
	
	@RequestMapping(value = "/admin/roomtype-managements/roomtype/tran", method = RequestMethod.DELETE)
	public String accountDelete(@RequestPart("roomType") RoomTypeEntity roomType) {
		roomTypeServ.deleteById(roomType.getId());
		return "redirect:/admin/roomtype-managements/roomtype";
	}

	@RequestMapping(value = "/admin/roomtype-managements/roomtypes/tran", method = RequestMethod.DELETE)
	public String accountDeleteMany(@RequestBody RoomTypeEntity roomType) {
		roomTypeServ.delete(roomType.getIds());
		return "redirect:/admin/roomtype-managements/roomtype";
	}
	
}
