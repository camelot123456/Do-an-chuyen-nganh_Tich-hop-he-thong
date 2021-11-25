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

import com.pihotel.entity.InvoiceEntity;
import com.pihotel.service.IInvoiceServ;

@RestController
@RequestMapping(value = "/api", consumes = {"application/json"})
public class InvoiceApi {

	@Autowired
	private IInvoiceServ invoiceServ;
	
	@GetMapping(value = "/invoice")
	public ResponseEntity<List<InvoiceEntity>> show(){
		return ResponseEntity.ok().body(invoiceServ.findAll());
	}
	
	@PostMapping(value = "/invoice")
	public ResponseEntity<InvoiceEntity> insert(@RequestBody InvoiceEntity invoice){
		return ResponseEntity.ok().body(invoiceServ.save(invoice));
	}
	
	@PutMapping(value = "/invoice")
	public ResponseEntity<InvoiceEntity> update(@RequestBody InvoiceEntity invoice){
		return ResponseEntity.ok().body(invoiceServ.update(invoice));
	}
	
	@DeleteMapping(value = "/invoice")
	public ResponseEntity<String> delete(@RequestBody InvoiceEntity invoice){
		invoiceServ.delete(invoice.getIds());
		return new ResponseEntity<String>("Product deleted successfully!", HttpStatus.OK);
	}
	
}
