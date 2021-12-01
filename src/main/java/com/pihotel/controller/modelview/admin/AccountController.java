package com.pihotel.controller.modelview.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;

import com.pihotel.entity.AccountEntity;
import com.pihotel.service.IAccountServ;

@Controller
public class AccountController {

	@Autowired
	private IAccountServ accountServ;

	@RequestMapping(value = "/admin/internal-managements/account/{id}")
	public String adminIMDetailAccount(Model model, @PathVariable("id") String id) {
		AccountEntity account = accountServ.findOneById(id);
		model.addAttribute("account", account);
		return "admin/bodys/internal_managements/im_detail_account";
	}

	@RequestMapping(value = "/admin/internal-managements/account", method = RequestMethod.PUT)
	public String adminIMUpdateAccount(@RequestPart("account") AccountEntity account) {
		accountServ.updateCustom(
				account.getId(), 
				account.getName(), 
				account.getEmail(), 
				account.getAddress(),
				account.getPhoneNum(), 
				account.getBirthday(), 
				account.getGender());
		return "redirect:/admin/internal-managements/account/" + account.getId();
	}

	@RequestMapping(value = "/admin/internal-managements/account", method = RequestMethod.DELETE)
	public String adminIMDeleteAccount(@RequestPart("account") AccountEntity account) {
		accountServ.deleteById(account.getId());
		return "redirect:/admin/internal-managements";
	}
	
	@RequestMapping(value = "/admin/internal-managements/accounts", method = RequestMethod.DELETE)
	public String adminIMDeleteManyAccount(@RequestBody AccountEntity account) {
		accountServ.delete(account.getIds());
		return "redirect:/admin/internal-managements";
	}

	@RequestMapping(value = "/admin/internal-managements/account-role", method = RequestMethod.POST)
	public ResponseEntity<?> doAdminIMAddRoleToAccount(@RequestPart("account") AccountEntity account) {
		accountServ.addRoleToAccount(account.getId(), account.getRoles());
		return ResponseEntity.ok().build();
	}

}
