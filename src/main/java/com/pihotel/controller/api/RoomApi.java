package com.pihotel.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pihotel.entity.RoomEntity;
import com.pihotel.service.IRoomServ;

@RestController
@RequestMapping(value = {"/api"})
public class RoomApi {

	@Autowired
	private IRoomServ roomServ;

//	---------------------------------------GET---------------------------------------
	
	@GetMapping(value = "/room/page/{currentPage}")
	public ResponseEntity<List<RoomEntity>> showWithPage(Model model, 
			@PathVariable("currentPage") int currentPage,
			@Param("sortField") String sortField, 
			@Param("sortDir") String sortDir, 
			@Param("keyword") String keyword){
		Page<RoomEntity> page = roomServ.findAll(currentPage, sortField, sortDir, keyword);
		return ResponseEntity.ok().body(page.getContent());
	}
	
	@GetMapping(value = "/room")
	public ResponseEntity<List<RoomEntity>> show(Model model){
		return showWithPage(model, 1, "id", "asc", "");
	}
	
	@GetMapping(value = "/room/room-show-admin")
	public ResponseEntity<List<RoomEntity>> showAdmin(){
		return ResponseEntity.ok().body(roomServ.findAllShowRoom());
	}
	
	@GetMapping(value = "/room/{id}")
	public ResponseEntity<RoomEntity> findById(@PathVariable("id") String id){
		return ResponseEntity.ok().body(roomServ.findOneById(id));
	}
	
	@GetMapping(value = "/room/{id_room_type}/{customers_num}")
	public ResponseEntity<List<RoomEntity>> findByIdRoomType(@PathVariable("id_room_type") String idRoomType, 
			@PathVariable("customers_num") int customersNum){
		return ResponseEntity.ok().body(roomServ.findAllByIdRoomType(idRoomType, customersNum));
	}

//	---------------------------------------POST---------------------------------------	
	
	@PostMapping(value = "/room")
	public ResponseEntity<RoomEntity> insert(@RequestBody RoomEntity room){
		return ResponseEntity.ok().body(roomServ.save(room));
	}

//	---------------------------------------PUT---------------------------------------	
	
	@PutMapping(value = "/room")
	public ResponseEntity<RoomEntity> update(@RequestBody RoomEntity room){
		return ResponseEntity.ok().body(roomServ.update(room));
	}

//	---------------------------------------DELETE---------------------------------------	
	
	@DeleteMapping(value = "/room")
	public ResponseEntity<String> delete(@RequestBody RoomEntity room){
		roomServ.delete(room.getIds());
		return new ResponseEntity<String>("Product deleted successfully!", HttpStatus.OK);
	}
	
}
