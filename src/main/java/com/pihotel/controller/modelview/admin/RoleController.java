package com.pihotel.controller.modelview.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;

import com.pihotel.constant.SystemConstant;
import com.pihotel.entity.RoleEntity;
import com.pihotel.service.IRoleServ;

@Controller
public class RoleController {

	@Autowired
	private IRoleServ roleServ;
	
	@RequestMapping(value = "/admin/internal-managements/role")
	public String roleIMInsertShow(Model model) {
		model.addAttribute(SystemConstant.ROLES, roleServ.findAll());
		return "admin/bodys/internal_managements/im_role";
	}
	
	@RequestMapping(value = "/admin/internal-managements/role/{id}")
	public String roleIMDetailAccount(Model model, @PathVariable("id") String id) {
		RoleEntity role = roleServ.findOneById(id);
		model.addAttribute(SystemConstant.ROLE, role);
		return "admin/bodys/internal_managements/im_detail_role";
	}

	@RequestMapping(value = "/admin/internal-managements/role/tran", method = RequestMethod.PUT)
	public String roleIMUpdateAccount(@RequestPart("role") RoleEntity role) {
		roleServ.update(role);
		return "redirect:/admin/internal-managements/role/" + role.getId();
	}

	@RequestMapping(value = "/admin/internal-managements/role/tran", method = RequestMethod.DELETE)
	public String roleDeleteAccount(@RequestPart("role") RoleEntity role) {
		roleServ.deleteById(role.getId());
		return "redirect:/admin/internal-managements/role";
	}
	
	@RequestMapping(value = "/admin/internal-managements/roles/tran", method = RequestMethod.DELETE)
	public String roleDeleteManyAccount(@RequestBody RoleEntity role) {
		roleServ.delete(role.getIds());
		return "redirect:/admin/internal-managements/role";
	}
	
}
