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

import com.pihotel.entity.RoleEntity;
import com.pihotel.service.IRoleServ;

@RestController
@RequestMapping(value = "/api")
public class RoleApi {

	@Autowired
	private IRoleServ roleServ;
	
	@GetMapping(value = "/role")
	public ResponseEntity<List<RoleEntity>> show(){
		return ResponseEntity.ok().body(roleServ.findAll());
	}
	
	@PostMapping(value = "/role")
	public ResponseEntity<RoleEntity> insert(@RequestBody RoleEntity role){
		return ResponseEntity.ok().body(roleServ.save(role));
	}
	
	@PutMapping(value = "/role")
	public ResponseEntity<RoleEntity> update(@RequestBody RoleEntity role){
		return ResponseEntity.ok().body(roleServ.update(role));
	}
	
	@DeleteMapping(value = "/role")
	public ResponseEntity<String> delete(@RequestBody RoleEntity role){
		roleServ.delete(role.getIds());
		return new ResponseEntity<String>("Product deleted successfully!", HttpStatus.OK);
	}
	
}
