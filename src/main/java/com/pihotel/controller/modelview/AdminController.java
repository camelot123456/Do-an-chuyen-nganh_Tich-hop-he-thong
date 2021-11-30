package com.pihotel.controller.modelview;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pihotel.service.IRoleServ;

@Controller
public class AdminController {
	
	@Autowired
	private IRoleServ roleServ;

	@RequestMapping(value = {"/admin/dashboard", "/admin"})
	public String adminHome() {
		return "admin/bodys/dashboard";
	}
	
	@RequestMapping(value = "/admin/internal-managements/role")
	public String adminInternalManagements(Model model) {
		model.addAttribute("roles", roleServ.findAll());
		return "admin/bodys/internal_managements/im_role";
	}
	
}
