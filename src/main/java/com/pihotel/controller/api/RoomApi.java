package com.pihotel.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pihotel.entity.RoomEntity;
import com.pihotel.service.IRoomServ;

@RestController
@RequestMapping(value = "/api", consumes = "application/json")
public class RoomApi {

	@Autowired
	private IRoomServ roomServ;
	
	@GetMapping(value = "/room")
	public ResponseEntity<List<RoomEntity>> show(){
		return ResponseEntity.ok().body(roomServ.findAll());
	}
	
	@PostMapping(value = "/room")
	public ResponseEntity<RoomEntity> insert(@RequestBody RoomEntity room){
		return ResponseEntity.ok().body(roomServ.save(room));
	}
	
	@PutMapping(value = "/room")
	public ResponseEntity<RoomEntity> update(@RequestBody RoomEntity room){
		return ResponseEntity.ok().body(roomServ.update(room));
	}
	
	@DeleteMapping(value = "/room")
	public ResponseEntity<String> delete(@RequestBody RoomEntity room){
		roomServ.delete(room.getIds());
		return new ResponseEntity<String>("Product deleted successfully!", HttpStatus.OK);
	}
	
}
