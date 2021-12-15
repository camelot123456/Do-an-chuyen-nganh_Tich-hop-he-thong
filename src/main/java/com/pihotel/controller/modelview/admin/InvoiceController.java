package com.pihotel.controller.modelview.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pihotel.constant.SystemConstant;
import com.pihotel.service.IAccountServ;
import com.pihotel.service.IInvoiceServ;
import com.pihotel.service.IRoomTypeServ;
import com.pihotel.service.IServiceServ;

@Controller
public class InvoiceController {

	@Autowired
	private IRoomTypeServ roomTypeServ;

	@Autowired
	private IInvoiceServ invoiceServ;

	@Autowired
	private IAccountServ accountServ;
	
	@Autowired
	private IServiceServ serviceServ;

//	---------------------------------------GET---------------------------------------		
	
	@RequestMapping(value = {"/admin/invoice-managements/invoice"})
	public String adminShowInvoice(Model model) {
		model.addAttribute(SystemConstant.INVOICES, invoiceServ.findAllPaidInvoices(Boolean.TRUE));
		return "admin/bodys/invoice_managements/im_invoice";
	}
	
	@RequestMapping(value = {"/admin/invoice-managements/invoice/{idAccount}"})
	public String adminShowInvoiceDetail(Model model,
			@PathVariable("idAccount") String idAccount, 
			@Param("idRoomType") String idRoomType, 
			@Param("idInvoice") String idInvoice) {
		model.addAttribute(SystemConstant.INVOICE, invoiceServ.findOneById(idInvoice));
		model.addAttribute(SystemConstant.ACCOUNT, accountServ.findOneById(idAccount));		
		model.addAttribute(SystemConstant.ROOM_TYPE, roomTypeServ.findOneById(idRoomType));
		model.addAttribute(SystemConstant.SERVICES, serviceServ.findAll());
		model.addAttribute(SystemConstant.ROOMS, invoiceServ.findOneById(idInvoice).getRooms());
		return "admin/bodys/invoice_managements/im_detail_invoice";
	}
	
//	---------------------------------------POST---------------------------------------
	
//	---------------------------------------PUT---------------------------------------
	
//	---------------------------------------PATCH---------------------------------------

//	---------------------------------------DELETE---------------------------------------

}
