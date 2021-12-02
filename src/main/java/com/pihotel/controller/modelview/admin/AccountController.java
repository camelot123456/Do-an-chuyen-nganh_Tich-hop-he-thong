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
import com.pihotel.entity.AccountEntity;
import com.pihotel.service.IAccountServ;

@Controller
public class AccountController {

	@Autowired
	private IAccountServ accountServ;

//	@Autowired
//	private IRoleServ roleServ;

	@RequestMapping(value = "/admin/internal-managements/account")
	public String accountInsertShow(Model model) {
		return this.accountPagination(model, 1, "id", "asc", "");
	}

	@RequestMapping(value = "/admin/internal-managements/account/page/{currentPage}")
	public String accountPagination(Model model, 
			@PathVariable("currentPage") int currentPage,
			@Param("sortField") String sortField, 
			@Param("sortDir") String sortDir, 
			@Param("keyword") String keyword) {

		Page<AccountEntity> page = accountServ.findAll(currentPage, sortField, sortDir, keyword);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("currentPage", (int) currentPage);
		map.put("sortField", (String) sortField);
		map.put("sortDir", (String) (sortDir.equalsIgnoreCase("desc") ? "asc" : "desc"));
		map.put("keyword", (String) keyword);
		map.put("totalPage", (int) page.getTotalPages());
		map.put("totalElement", (int) page.getTotalElements());
		map.put(SystemConstant.ACCOUNTS, page.getContent());

		model.addAllAttributes(map);

		return "admin/bodys/internal_managements/im_account";
	}

	@RequestMapping(value = "/admin/internal-managements/account/{id}")
	public String accountDetail(Model model, @PathVariable("id") String id) {
		AccountEntity account = accountServ.findOneById(id);
		model.addAttribute(SystemConstant.ACCOUNT, account);
		return "admin/bodys/internal_managements/im_detail_account";
	}

	@RequestMapping(value = "/admin/internal-managements/account/tran", method = RequestMethod.PUT)
	public String accountUpdate(@RequestPart("account") AccountEntity account) {
		accountServ.updateCustom(account.getId(), account.getName(), account.getEmail(), account.getAddress(),
				account.getPhoneNum(), account.getBirthday(), account.getGender());
		return "redirect:/admin/internal-managements/account/" + account.getId();
	}

	@RequestMapping(value = "/admin/internal-managements/account/tran", method = RequestMethod.DELETE)
	public String accountDelete(@RequestPart("account") AccountEntity account) {
		accountServ.deleteById(account.getId());
		return "redirect:/admin/internal-managements/account";
	}

	@RequestMapping(value = "/admin/internal-managements/accounts/tran", method = RequestMethod.DELETE)
	public String accountDeleteMany(@RequestBody AccountEntity account) {
		accountServ.delete(account.getIds());
		return "redirect:/admin/internal-managements/account";
	}

	@RequestMapping(value = "/admin/internal-managements/account-role", method = RequestMethod.POST)
	public String doAccountAddRoleToAccount(@RequestPart("account") AccountEntity account) {
		accountServ.addRoleToAccount(account.getId(), account.getRoles());
		return "redirect:/admin/internal-managements/account-role";
	}

}