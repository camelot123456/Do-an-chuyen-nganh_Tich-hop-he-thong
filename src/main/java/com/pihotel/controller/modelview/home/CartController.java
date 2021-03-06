package com.pihotel.controller.modelview.home;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;

import com.pihotel.constant.SystemConstant;
import com.pihotel.entity.AccountEntity;
import com.pihotel.entity.InvoiceEntity;
import com.pihotel.entity.RoomTypeEntity;
import com.pihotel.entity.ServiceEntity;
import com.pihotel.service.IAccountServ;
import com.pihotel.service.IInvoiceServ;
import com.pihotel.service.IInvoicesServicesServ;

@Controller
public class CartController {

	@Autowired
	private IInvoiceServ invoiceServ;

	@Autowired
	private IAccountServ accountServ;
	
	@Autowired
	private IInvoicesServicesServ invoiceServiceServ;

//	---------------------------------------GET---------------------------------------	
	
	@RequestMapping(value = {"/cart/redirect"})
	public String showCartRedirect(HttpServletRequest request) {
		AccountEntity customer = (AccountEntity) request.getSession().getAttribute("account");
		return "redirect:/cart?idCustomer=" + customer.getId();
	}

	@RequestMapping(value = "/cart")
	public String showCart(Model model, @Param("idCustomer") String idCustomer) {
		model.addAttribute(SystemConstant.CUSTOMER, accountServ.findOneById(idCustomer));		
		model.addAttribute(SystemConstant.CARTS, invoiceServ.findAllByIdCustomerRoomState(idCustomer, "CHECKIN"));
		model.addAttribute(SystemConstant.CARTS_HISTORY, invoiceServ.findAllByIdCustomer(idCustomer));
		return "home/bodys/cart/cart";
	}

//	---------------------------------------POST---------------------------------------

	@RequestMapping(value = "/home/cart/services", method = RequestMethod.POST, consumes = {"multipart/form-data", "application/json"})
	public String doSaveServiceCart(@RequestPart("invoice") InvoiceEntity invoice,
			@RequestPart("service") ServiceEntity service, 
			@RequestPart("roomType") RoomTypeEntity roomType) {
		invoiceServiceServ.saveWithInvoiceService(invoice, service);
		return "redirect:/home/checkin/invoice?idRoomType=" + roomType.getId() + "&idInvoice=" + invoice.getId();
	}
	
//	---------------------------------------PUT---------------------------------------

//	---------------------------------------PATCH---------------------------------------

//	---------------------------------------DELETE---------------------------------------

	@RequestMapping(value = "/cart/carts", method = RequestMethod.DELETE, consumes = {"multipart/form-data", "application/json"})
	public String doDeleteCart(@RequestPart("cart") InvoiceEntity cart) {
		AccountEntity customer = accountServ.findOneById(cart.getIdAccount());
		invoiceServ.delete(cart.getIds());
		return "redirect:/cart?idCustomer=" + customer.getId();
	}
	
	@RequestMapping(value = "/cart/carts-history", method = RequestMethod.DELETE, consumes = {"multipart/form-data", "application/json"})
	public String doDeleteCartHistory(@RequestPart("cartHistory") InvoiceEntity cartHistory) {
		AccountEntity customer = accountServ.findOneById(cartHistory.getIdAccount());
		invoiceServ.delete(cartHistory.getIds());
		return "redirect:/cart?idCustomer=" + customer.getId();
	}
	
	@RequestMapping(value = "/home/checkin/handle-del-invoice", method = RequestMethod.DELETE, consumes = {
			"multipart/form-data", "application/json" })
	public String doDeleteInvoice(@RequestPart("invoice") InvoiceEntity invoice) {
		AccountEntity customer = accountServ.findOneById(invoice.getIdAccount());
		invoiceServ.delete(invoice.getIds());
		return "redirect:/cart?idCustomer=" + customer.getId();
	}
	
}
