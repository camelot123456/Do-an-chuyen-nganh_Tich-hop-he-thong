package com.pihotel.controller.modelview.admin;

import java.io.IOException;
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
import org.springframework.web.multipart.MultipartFile;

import com.pihotel.constant.SystemConstant;
import com.pihotel.entity.RoleEntity;
import com.pihotel.service.IRoleServ;
import com.pihotel.utils.UploadFileUtil;

import net.bytebuddy.utility.RandomString;

@Controller
public class RoleController {

	@Autowired
	private IRoleServ roleServ;

	public static final String PATH_IMG = "src/main/resources/static/img/role";

//	---------------------------------------GET---------------------------------------
	
	@RequestMapping(value = "/admin/internal-managements/role")
	public String accountRedirectPagination(Model model) {
		return this.rolePagination(model, 1, "id", "asc", "");
	}

	@RequestMapping(value = "/admin/internal-managements/role/page/{currentPage}")
	public String rolePagination(Model model, @PathVariable("currentPage") int currentPage,
			@Param("sortField") String sortField, @Param("sortDir") String sortDir, @Param("keyword") String keyword) {

		Page<RoleEntity> page = roleServ.findAll(currentPage, sortField, sortDir, keyword);
		String reverseSort = sortDir.equalsIgnoreCase("asc") ? "desc" : "asc";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("currentPage", (int) currentPage);
		map.put("sortField", (String) sortField);
		map.put("reverseSort", (String) reverseSort);
		map.put("sortDir", (String) sortDir);
		map.put("keyword", (String) keyword);
		map.put("totalPage", (int) page.getTotalPages() < 1 ? 1 : page.getTotalPages());
		map.put("totalElement", (int) page.getTotalElements());
		map.put(SystemConstant.ROLES, page.getContent());

		model.addAllAttributes(map);

		return "admin/bodys/internal_managements/im_role";
	}

	@RequestMapping(value = "/admin/internal-managements/role/add")
	public String showRoleInsert(Model model) {
		model.addAttribute("RANDOM_ID", RandomString.make(16));
		return "admin/bodys/internal_managements/im_role_insert";
	}

	@RequestMapping(value = "/admin/internal-managements/role/{id}")
	public String roleIMDetailAccount(Model model, @PathVariable("id") String id) {
		RoleEntity role = roleServ.findOneById(id);
		model.addAttribute(SystemConstant.ROLE, role);
		return "admin/bodys/internal_managements/im_detail_role";
	}
	
//	---------------------------------------POST---------------------------------------
	
	@RequestMapping(value = "/admin/internal-managements/role/tran", method = RequestMethod.POST, consumes = {
			"multipart/form-data", "application/json" })
	public String roleInsert(@RequestPart("logo") MultipartFile multipartFile, @RequestPart("role") RoleEntity role)
			throws IOException {
		role.setLogo(multipartFile.getOriginalFilename());
		UploadFileUtil.saveFile(PATH_IMG, multipartFile.getOriginalFilename(), multipartFile);
		roleServ.save(role);
		return "redirect:/admin/internal-managements/role/" + role.getId();
	}
	
//	---------------------------------------PUT---------------------------------------
	
	@RequestMapping(value = "/admin/internal-managements/role/tran", method = RequestMethod.PUT)
	public String roleIMUpdateAccount(@RequestPart("role") RoleEntity role) {
		roleServ.updateCustom(role.getId(), role.getName(), role.getCode());
		return "redirect:/admin/internal-managements/role/" + role.getId();
	}
	
//	---------------------------------------PATCH---------------------------------------
	
//	---------------------------------------DELETE---------------------------------------
	
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