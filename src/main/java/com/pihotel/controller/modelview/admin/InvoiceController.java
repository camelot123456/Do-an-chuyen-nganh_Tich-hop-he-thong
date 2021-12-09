package com.pihotel.controller.modelview.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class InvoiceController {

	@RequestMapping(value = {"/admin/invoice-managements/invoice"})
	public String adminHome() {
		return "admin/bodys/invoice_managements/im_invoice";
	}
	
}
