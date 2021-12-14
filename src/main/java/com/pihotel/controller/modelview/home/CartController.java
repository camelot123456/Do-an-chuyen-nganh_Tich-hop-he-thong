package com.pihotel.controller.modelview.home;

import java.util.Date;
import java.util.List;

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
import com.pihotel.service.IAccountServ;
import com.pihotel.service.IInvoiceServ;
import com.pihotel.service.IRoomServ;
import com.pihotel.service.IRoomTypeServ;
import com.pihotel.service.IServiceServ;

@Controller
public class CartController {

	@Autowired
	private IRoomTypeServ roomTypeServ;

	@Autowired
	private IInvoiceServ invoiceServ;

	@Autowired
	private IServiceServ serviceServ;

	@Autowired
	private IAccountServ accountServ;

	@Autowired
	private IRoomServ roomServ;

//	---------------------------------------GET---------------------------------------	
	
	@RequestMapping(value = {"/cart/redirect"})
	public String showCartRedirect(HttpServletRequest request) {
		AccountEntity customer = (AccountEntity) request.getSession().getAttribute("account");
		return "redirect:/cart?idCustomer=" + customer.getId();
	}

	@RequestMapping(value = "/cart")
	public String showCart(Model model, @Param("idCustomer") String idCustomer) {
		model.addAttribute(SystemConstant.CUSTOMER, accountServ.findOneById(idCustomer));		
		model.addAttribute(SystemConstant.CARTS, invoiceServ.findAllByIdCustomerCheckin(idCustomer));
		model.addAttribute(SystemConstant.CARTS_HISTORY, invoiceServ.findAllByIdCustomerUsing(idCustomer));
		return "home/bodys/cart/cart";
	}

//	---------------------------------------POST---------------------------------------

//	---------------------------------------PUT---------------------------------------

//	---------------------------------------PATCH---------------------------------------

//	---------------------------------------DELETE---------------------------------------

//	Chưa hoàn tất
//	Chưa hoàn tất
//	Chưa hoàn tất
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
	
}
