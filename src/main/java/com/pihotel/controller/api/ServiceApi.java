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

import com.pihotel.entity.ServiceEntity;
import com.pihotel.service.IServiceServ;

@RestController
@RequestMapping(value = "/api")
public class ServiceApi {

	@Autowired
	private IServiceServ serviceServ;
	
	@GetMapping(value = "/service")
	public ResponseEntity<List<ServiceEntity>> show(){
		return ResponseEntity.ok().body(serviceServ.findAll());
	}
	
	@PostMapping(value = "/service")
	public ResponseEntity<ServiceEntity> insert(@RequestBody ServiceEntity room){
		return ResponseEntity.ok().body(serviceServ.save(room));
	}
	
	@PutMapping(value = "/service")
	public ResponseEntity<ServiceEntity> update(@RequestBody ServiceEntity room){
		return ResponseEntity.ok().body(serviceServ.update(room));
	}
	
	@DeleteMapping(value = "/service")
	public ResponseEntity<String> delete(@RequestBody ServiceEntity room){
		serviceServ.delete(room.getIds());
		return new ResponseEntity<String>("Product deleted successfully!", HttpStatus.OK);
	}
	
}
