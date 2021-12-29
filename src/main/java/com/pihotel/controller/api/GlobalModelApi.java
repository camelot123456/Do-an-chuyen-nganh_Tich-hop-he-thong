package com.pihotel.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pihotel.service.IAccountServ;
import com.pihotel.service.IInvoiceServ;
import com.pihotel.service.IRoleServ;
import com.pihotel.service.IRoomServ;
import com.pihotel.service.IRoomTypeServ;
import com.pihotel.service.IServiceServ;

@RestController
public class GlobalModelApi {

	@Autowired
	private IAccountServ accountServ;
	
	@Autowired 
	private IInvoiceServ invoiceServ;
	
	@Autowired
	private IRoleServ roleServ;
	
	@Autowired
	private IRoomServ roomServ;
	
	@Autowired
	private IRoomTypeServ roomTypeServ;
	
	@Autowired
	private IServiceServ serviceServ;
	
	
	@GetMapping(value = "/seo/public")
	public Object[] doSearchPublic(@Param("keywordSearch") String keywordSearch) {
		Object[] record = new Object[] {
				roomServ.searchRoom(keywordSearch),
				roomTypeServ.searchRoomType(keywordSearch),
				serviceServ.searchService(keywordSearch)
				};
		return record;
	}
	
	@GetMapping(value = "/admin/seo/public")
	public Object[] doSearchAdmin(@Param("keywordSearch") String keywordSearch) {
		Object[] record = new Object[] {
				accountServ.searchAccount(keywordSearch), 
				invoiceServ.searchInvoice(keywordSearch),
				roleServ.searchRole(keywordSearch),
				roomServ.searchRoom(keywordSearch),
				roomTypeServ.searchRoomType(keywordSearch),
				serviceServ.searchService(keywordSearch)
				};
		return record;
	}
	
}
