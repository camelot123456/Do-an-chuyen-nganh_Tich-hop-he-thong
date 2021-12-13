package com.pihotel.controller.modelview.home;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;

import com.pihotel.constant.SystemConstant;
import com.pihotel.entity.AccountEntity;
import com.pihotel.entity.InvoiceEntity;
import com.pihotel.entity.RoomTypeEntity;
import com.pihotel.entity.enums.EAuthenticationProvider;
import com.pihotel.entity.enums.ERoomState;
import com.pihotel.service.IAccountServ;
import com.pihotel.service.IInvoiceServ;
import com.pihotel.service.IRoomServ;
import com.pihotel.service.IRoomTypeServ;
import com.pihotel.service.IServiceServ;

import net.bytebuddy.utility.RandomString;

@Controller
public class HomeController {

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

	@RequestMapping(value = { "/", "/home" })
	public String home() {
		return "home/bodys/home";
	}

	@RequestMapping(value = "/home/room")
	public String showRoomTypeList(Model model) {
		model.addAttribute(SystemConstant.ROOMS_TYPE, roomTypeServ.findAll());
		return "home/bodys/room_list";
	}

	@RequestMapping(value = "/home/room/detail/{id}")
	public String showDetailRoom(Model model, @PathVariable("id") String id) {
		model.addAttribute(SystemConstant.ROOM_TYPE, roomTypeServ.findOneById(id));
		model.addAttribute("ID_INVOICE", RandomString.make(12));
		return "home/bodys/detail_room";
	}

	@RequestMapping(value = "/home/checkin/invoice")
	public String bookroomInvoice(Model model, @Param("idRoomType") String idRoomType,
			@Param("idInvoice") String idInvoice, Principal principal, HttpServletRequest request) {

		try {
			AccountEntity customer = (AccountEntity) request.getSession().getAttribute("account");
			if (customer != null) {
				model.addAttribute(SystemConstant.CUSTOMER, customer);
			}
		} catch (NullPointerException e) {
			// TODO: handle exception

		}

		InvoiceEntity invoice = invoiceServ.findOneById(idInvoice);

		model.addAttribute(SystemConstant.ROOMS, invoice.getRooms());
		model.addAttribute(SystemConstant.SERVICES, serviceServ.findAll());
		model.addAttribute(SystemConstant.ROOM_TYPE, roomTypeServ.findOneById(idRoomType));
		model.addAttribute(SystemConstant.INVOICE, invoice);
		model.addAttribute("COUNT_ROOM", invoice.getRooms().stream().count());
		model.addAttribute("TOTAL_PRICE_INCURRED", invoiceServ.getSumPriceIncurred(idInvoice));
		model.addAttribute("TOTAL_PRICE", invoiceServ.getSumPriceIncurredAndPriceRoomType(idInvoice, idRoomType));
		model.addAttribute("PRICE_SERVICE_5P",
				invoiceServ.getSumPriceIncurredAndPriceRoomType(idInvoice, idRoomType) * 0.05);
		model.addAttribute("PRICE_VAT_10P",
				invoiceServ.getSumPriceIncurredAndPriceRoomType(idInvoice, idRoomType) * 0.1);
		model.addAttribute("TOTAL_PRICE_ALL",
				invoiceServ.getSumPriceIncurredAndPriceRoomType(idInvoice, idRoomType)
						+ invoiceServ.getSumPriceIncurredAndPriceRoomType(idInvoice, idRoomType) * 0.05
						+ invoiceServ.getSumPriceIncurredAndPriceRoomType(idInvoice, idRoomType) * 0.1);

		return "home/bodys/checkin";
	}

//	---------------------------------------POST---------------------------------------
//	https://stackjava.com/spring/redirectattributes-chuyen-tiep-trang-voi-tham-trong-spring.html

	@RequestMapping(value = "/home/checkin", method = RequestMethod.POST, consumes = { "multipart/form-data",
			"application/json" })
	public String doSaveInvoice(@RequestPart("invoice") InvoiceEntity invoice,
			@RequestPart("roomType") RoomTypeEntity roomType, Principal principal) {
		try {
			AccountEntity customer = accountServ.findOneByUsername(principal.getName());
			invoiceServ.addRoomAndCustomerToInvoice(invoice, customer);
		} catch (NullPointerException e) {
			// TODO: handle exception
			invoiceServ.addRoomToInvoice(invoice);
		}
		return "redirect:/home/checkin/invoice?idRoomType=" + roomType.getId() + "&idInvoice=" + invoice.getId();
	}

//	---------------------------------------PUT---------------------------------------

	@RequestMapping(value = "/home/checkin/handle-invoice-no-account", method = RequestMethod.PUT, consumes = {
			"multipart/form-data", "application/json" })
	public String doSaveInvoiceWithCustomer(@RequestPart("customer") AccountEntity customer,
			@RequestPart("invoice") InvoiceEntity invoice, HttpServletRequest request) {
		AccountEntity accountCustomer = (AccountEntity) request.getSession().getAttribute("account");
		if (accountCustomer == null) {
			customer.setId(RandomString.make(12));
			customer.setAvatar(SystemConstant.AVATAR_ACCOUNT_DEFAULT_LINK);
			customer.setAuthProvider(EAuthenticationProvider.LOCAL);
			AccountEntity customerNew = accountServ.saveCustomer(customer);
			InvoiceEntity invoiceNew = invoiceServ.findOneById(invoice.getId());
			invoiceNew.setAccount(customerNew);
			invoiceNew.getRooms().forEach(room -> {
				room.setRoomState(ERoomState.USING);
			});
			invoiceServ.update(invoiceNew);
			return "redirect:/cart?idCustomer=" + customerNew.getId();
		} else {
			InvoiceEntity invoiceNew = invoiceServ.findOneById(invoice.getId());
			invoiceNew.setAccount(accountCustomer);
			invoiceNew.getRooms().forEach(room -> {
				room.setRoomState(ERoomState.USING);
			});
			invoiceServ.update(invoiceNew);
			return "redirect:/cart?idCustomer=" + accountCustomer.getId();
		}
	}

//	---------------------------------------PATCH---------------------------------------

//	---------------------------------------DELETE---------------------------------------

	@RequestMapping(value = "/home/checkin/handle-change-room", method = RequestMethod.DELETE, consumes = {
			"multipart/form-data", "application/json" })
	public String doDeleteInvoice(@RequestPart("invoice") InvoiceEntity invoice, Principal principal) {
		try {
			if (accountServ.findOneByUsername(principal.getName()) != null) {
				return "redirect:/home/room";
			}
		} catch (NullPointerException e) {
			// TODO: handle exception
		}

		InvoiceEntity invoiceNew = invoiceServ.findOneById(invoice.getId());
		invoiceNew.getRooms().forEach(room -> {
			roomServ.updateRoomState(ERoomState.EMPTY, room.getId());
		});
		invoiceServ.delete(invoice.getIds());
		return "redirect:/home/room";
	}

}