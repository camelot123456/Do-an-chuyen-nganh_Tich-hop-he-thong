package com.pihotel.controller.modelview.admin;

import java.util.HashMap;
import java.util.Map;

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
import com.pihotel.entity.RoleEntity;
import com.pihotel.service.IRoleServ;

@Controller
public class RoleController {

	@Autowired
	private IRoleServ roleServ;
	
	@RequestMapping(value = "/admin/internal-managements/role")
	public String accountRedirectPagination(Model model) {
		return this.accountPagination(model, 1, "id", "asc", "");
	}

	@RequestMapping(value = "/admin/internal-managements/role/page/{currentPage}")
	public String accountPagination(Model model, 
			@PathVariable("currentPage") int currentPage,
			@Param("sortField") String sortField, 
			@Param("sortDir") String sortDir, 
			@Param("keyword") String keyword) {

		Page<RoleEntity> page = roleServ.findAll(currentPage, sortField, sortDir, keyword);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("currentPage", (int) currentPage);
		map.put("sortField", (String) sortField);
		map.put("sortDir", (String) (sortDir.equalsIgnoreCase("desc") ? "asc" : "desc"));
		map.put("keyword", (String) keyword);
		map.put("totalPage", (int) page.getTotalPages());
		map.put("totalElement", (int) page.getTotalElements());
		map.put(SystemConstant.ROLES, page.getContent());

		model.addAllAttributes(map);

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
		roleServ.updateCustom(role.getId(), role.getName(), role.getCode());
		return "redirect:/admin/internal-managements/role";
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
