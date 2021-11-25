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

import com.pihotel.entity.RoomTypeEntity;
import com.pihotel.service.IRoomTypeServ;

@RestController
@RequestMapping(value = "/api", consumes = "application/json")
public class RoomTypeApi {

	@Autowired
	private IRoomTypeServ roomTypeServ;
	
	@GetMapping(value = "/roomtype")
	public ResponseEntity<List<RoomTypeEntity>> show(){
		return ResponseEntity.ok().body(roomTypeServ.findAll());
	}
	
	@PostMapping(value = "/roomtype")
	public ResponseEntity<RoomTypeEntity> insert(@RequestBody RoomTypeEntity roomType){
		return ResponseEntity.ok().body(roomTypeServ.save(roomType));
	}
	
	@PutMapping(value = "/roomtype")
	public ResponseEntity<RoomTypeEntity> update(@RequestBody RoomTypeEntity roomType){
		return ResponseEntity.ok().body(roomTypeServ.update(roomType));
	}
	
	@DeleteMapping(value = "/roomtype")
	public ResponseEntity<String> delete(@RequestBody RoomTypeEntity roomType){
		roomTypeServ.delete(roomType.getIds());
		return new ResponseEntity<String>("Product deleted successfully!", HttpStatus.OK);
	}
	
}
