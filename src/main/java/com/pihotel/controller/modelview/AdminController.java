package com.pihotel.controller.modelview;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;

import com.pihotel.entity.AccountEntity;
import com.pihotel.service.IAccountServ;
import com.pihotel.service.IRoleServ;

@Controller
public class AdminController {
	
	@Autowired
	private IRoleServ roleServ;
	
	@Autowired
	private IAccountServ accountServ;

	@RequestMapping(value = {"/admin/dashboard", "/admin"})
	public String adminHome() {
		return "admin/bodys/dashboard";
	}
	
	@RequestMapping(value = "/admin/internal-managements")
	public String adminIMShow(Model model) {
		model.addAttribute("roles", roleServ.findAll());
		model.addAttribute("accounts", accountServ.findAll());
		return "admin/bodys/internal_managements/im";
	}
	
	@RequestMapping(value = "/admin/internal-managements/account/{id}")
	public String adminIMDetailAccount(Model model, @PathVariable("id") String id) {
		AccountEntity account = accountServ.findOneById(id);
		model.addAttribute("account", account);
		return "admin/bodys/internal_managements/im_detail_account";
	}
	
	@RequestMapping(value = "/admin/internal-managements/role/{id}")
	public String adminIMDetailRole(Model model, @PathVariable("id") String id) {
		model.addAttribute("role", roleServ.findOneById(id));
		return "admin/bodys/internal_managements/im_detail_role";
	}
	
	@RequestMapping(value = "/admin/internal-managements/account-role", method = RequestMethod.POST)
	public ResponseEntity<?> doAdminIMAddRoleToAccount(@RequestPart("account") AccountEntity account) {
		accountServ.addRoleToAccount(account.getId(), account.getRoles());
		return ResponseEntity.ok().build();
	}
	
}
