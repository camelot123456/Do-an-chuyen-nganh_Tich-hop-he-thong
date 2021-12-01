package com.pihotel.controller.modelview.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
	
	
	@RequestMapping(value = "/admin/internal-managements/role/{id}")
	public String adminIMDetailRole(Model model, @PathVariable("id") String id) {
		model.addAttribute("role", roleServ.findOneById(id));
		return "admin/bodys/internal_managements/im_detail_role";
	}
	
	
}
