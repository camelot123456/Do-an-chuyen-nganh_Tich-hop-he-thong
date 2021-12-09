package com.pihotel.controller.modelview.admin;

import java.io.IOException;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;

import com.pihotel.constant.SystemConstant;
import com.pihotel.entity.AccountEntity;
import com.pihotel.entity.enums.EAuthenticationProvider;
import com.pihotel.service.IAccountServ;
import com.pihotel.service.IRoleServ;

import net.bytebuddy.utility.RandomString;

@Controller
public class CustomerController {

public static final String PATH_AVATAR = "src/main/resources/static/img/user/";
	
	@Autowired
	private IAccountServ accountServ;

	@Autowired
	private IRoleServ roleServ;
	
//	---------------------------------------GET---------------------------------------
	
	@RequestMapping(value = "/admin/customer-managements/account-customer")
	public String accountRedirectPagination(Model model) {
		return this.accountPagination(model, 1, "id", "asc", "");
	}

	@RequestMapping(value = "/admin/customer-managements/account-customer/page/{currentPage}")
	public String accountPagination(Model model, 
			@PathVariable("currentPage") int currentPage,
			@Param("sortField") String sortField, 
			@Param("sortDir") String sortDir, 
			@Param("keyword") String keyword) {

		Page<AccountEntity> page = accountServ.findAll(currentPage, sortField, sortDir, keyword);
		String reverseSort = sortDir.equalsIgnoreCase("asc") ? "desc" : "asc";
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("currentPage", (int) currentPage);
		map.put("sortField", (String) sortField);
		map.put("reverseSort", (String) reverseSort);
		map.put("sortDir", (String) sortDir);
		map.put("keyword", (String) keyword);
		map.put("totalPage", (int) page.getTotalPages() < 1 ? 1 : page.getTotalPages());
		map.put("totalElement", (int) page.getTotalElements());
		map.put(SystemConstant.CUSTOMERS, page.getContent().stream()
				.filter(account -> account.getAuthProvider()==EAuthenticationProvider.FACEBOOK||account.getAuthProvider()==EAuthenticationProvider.GOOGLE)
				.collect(Collectors.toList()));

		model.addAllAttributes(map);

		return "admin/bodys/customer_managements/cm_customer";
	}
	
	@RequestMapping(value = "/admin/customer-managements/account-customer/{id}")
	public String accountDetail(Model model, @PathVariable("id") String id) {
		AccountEntity customer = accountServ.findOneById(id);
		model.addAttribute(SystemConstant.CUSTOMER, customer);
		model.addAttribute(SystemConstant.ROLES, roleServ.findAll());
		return "admin/bodys/customer_managements/cm_detail_customer";
	}
	
	@RequestMapping(value = "/admin/customer-managements/account-customer/add")
	public String accountInsertShow(Model model, Principal principal) {
		model.addAttribute("CREATE_AT", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").format(new Date()));
		model.addAttribute("RANDOM_ID", RandomString.make(16));
		model.addAttribute("AUTH_PROVIDER", "LOCAL");
		model.addAttribute("EMAIL", "nguyensybao1403@gmail.com");//accountServ.findOneByUsername(principal.getName()).getEmail()
		model.addAttribute(SystemConstant.ROLES, roleServ.findAll().stream().filter(code -> code.getCode().equals("MEMBER")).collect(Collectors.toList()));
		return "admin/bodys/customer_managements/cm_customer_insert";
	}
	
//	---------------------------------------POST---------------------------------------
	
	@RequestMapping(value = "/admin/customer-managements/account-customer/tran", method = RequestMethod.POST)
	public String doAccountAddRoleToAccount(@RequestPart("customer") AccountEntity customer) throws IOException {
		customer.setAvatar(SystemConstant.AVATAR_ACCOUNT_DEFAULT_LINK);
		customer.setAuthProvider(EAuthenticationProvider.LOCAL);
		accountServ.saveCustomer(customer);
		return "redirect:/admin/customer-managements/account/customer/" + customer.getId();
	}
	
//	---------------------------------------PUT---------------------------------------
	
	@RequestMapping(value = "/admin/customer-managements/account-customer/tran", method = RequestMethod.PUT)
	public String accountUpdate(@RequestPart("customer") AccountEntity customer) {
		accountServ.updateCustomer(customer.getId(), customer.getName(), customer.getEmail(), customer.getAddress(),
				customer.getPhoneNum(), customer.getBirthday(), customer.getGender());
		return "redirect:/admin/customer-managements/account/customer/" + customer.getId();
	}
	
//	---------------------------------------PATCH---------------------------------------
	
//	---------------------------------------DELETE---------------------------------------
	
	@RequestMapping(value = "/admin/customer-managements/account-customer/tran", method = RequestMethod.DELETE)
	public String accountDelete(@RequestPart("customer") AccountEntity customer) throws IOException {
		accountServ.deleteById(customer.getId());
		return "redirect:/admin/customer-managements/account/customer";
	}

	@RequestMapping(value = "/admin/customer-managements/account-customers/tran", method = RequestMethod.DELETE)
	public String accountDeleteMany(@RequestBody AccountEntity customer) {
		accountServ.delete(customer.getIds());
		return "redirect:/admin/customer-managements/account/customer";
	}

}
