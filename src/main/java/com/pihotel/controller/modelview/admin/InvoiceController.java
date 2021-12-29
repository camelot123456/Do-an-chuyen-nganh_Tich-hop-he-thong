package com.pihotel.controller.modelview.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pihotel.constant.SystemConstant;
import com.pihotel.service.IAccountServ;
import com.pihotel.service.ICommentServ;
import com.pihotel.service.IInvoiceServ;
import com.pihotel.service.IRoomServ;
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
	
	@Autowired
	private IRoomServ roomServ;
	
	@Autowired
	private ICommentServ commentServ;

//	---------------------------------------GET---------------------------------------		
	
	
	
	@RequestMapping(value = {"/admin/invoice-managements/invoice"})
	public String adminShowInvoice(Model model) {
		model.addAttribute(SystemConstant.INVOICES, invoiceServ.findAllPaidInvoices(Boolean.TRUE));
		return "admin/bodys/invoice_managements/im_invoice";
	}
	
	@RequestMapping(value = {"/admin/invoice-managements/invoice/{idInvoice}"})
	public String adminShowInvoiceDetail(Model model, 
			@PathVariable("idInvoice") String idInvoice) {
		model.addAttribute(SystemConstant.BILL_CUSTOM, invoiceServ.findOneBillCustomByIdInvoice(idInvoice, Boolean.TRUE));
		model.addAttribute(SystemConstant.INVOICE, invoiceServ.findOneById(idInvoice, Boolean.TRUE));
		model.addAttribute(SystemConstant.CUSTOMER, accountServ.findOneByIdInvoice(idInvoice, Boolean.TRUE));		
		model.addAttribute(SystemConstant.ROOM_TYPE, roomTypeServ.findOneByIdInvoice(idInvoice, Boolean.TRUE));
		model.addAttribute(SystemConstant.SERVICES, serviceServ.findAllByIdInvoice(idInvoice, Boolean.TRUE));
		model.addAttribute(SystemConstant.ROOMS, roomServ.findAllByIdInvoice(idInvoice, Boolean.TRUE));
		model.addAttribute(SystemConstant.COMMENTS, commentServ.findAllByIdRoomTypeAndVerify(idInvoice));
		return "admin/bodys/invoice_managements/im_detail_invoice";
	}
	
	
	
//	---------------------------------------POST---------------------------------------
	
//	---------------------------------------PUT---------------------------------------
	
//	---------------------------------------PATCH---------------------------------------

//	---------------------------------------DELETE---------------------------------------

}
