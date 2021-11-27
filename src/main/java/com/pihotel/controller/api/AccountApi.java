package com.pihotel.controller.api;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

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

import com.pihotel.entity.AccountEntity;
import com.pihotel.service.IAccountServ;

@RestController
@RequestMapping(value = { "/api" })
public class AccountApi {

	@Autowired
	private IAccountServ accountServ;
	
	@GetMapping(value = "/account")
	public ResponseEntity<List<AccountEntity>> show(HttpServletResponse response){
		return ResponseEntity.ok().body(accountServ.findAll());
	}
	
	@PostMapping(value = "/account")
	public ResponseEntity<AccountEntity> insert(@RequestBody AccountEntity account){
		return ResponseEntity.ok().body(accountServ.save(account));
	}
	
	@PutMapping(value = "/account")
	public ResponseEntity<AccountEntity> update(@RequestBody AccountEntity account){
		return ResponseEntity.ok().body(accountServ.update(account));
	}
	
	@DeleteMapping(value = "/account")
	public ResponseEntity<String> delete(@RequestBody AccountEntity account){
		accountServ.delete(account.getIds());
		return new ResponseEntity<String>("Product deleted successfully!", HttpStatus.OK);
	}
}
