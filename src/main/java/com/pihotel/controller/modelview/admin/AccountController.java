package com.pihotel.controller.modelview.admin;

import java.io.IOException;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.pihotel.entity.AccountEntity;
import com.pihotel.service.IAccountServ;
import com.pihotel.service.IRoleServ;
import com.pihotel.utils.UploadFileUtil;

import net.bytebuddy.utility.RandomString;

@Controller
public class AccountController {

	public static final String PATH_AVATAR = "src/main/resources/static/img/user/";
	
	@Autowired
	private IAccountServ accountServ;

	@Autowired
	private IRoleServ roleServ;

//	---------------------------------------GET---------------------------------------
	
	@RequestMapping(value = "/admin/internal-managements/account")
	public String accountRedirectPagination(Model model) {
		return this.accountPagination(model, 1, "id", "asc", "");
	}
	
	@RequestMapping(value = "/admin/internal-managements/account/page/{currentPage}")
	public String accountPagination(Model model, 
			@PathVariable("currentPage") int currentPage,
			@Param("sortField") String sortField, 
			@Param("sortDir") String sortDir, 
			@Param("keyword") String keyword) {

		Page<AccountEntity> page = accountServ.findAllAccountInternal(currentPage, sortField, sortDir, keyword);
		String reverseSort = sortDir.equalsIgnoreCase("asc") ? "desc" : "asc";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("currentPage", (int) currentPage);
		map.put("sortField", (String) sortField);
		map.put("reverseSort", (String) reverseSort);
		map.put("sortDir", (String) sortDir);
		map.put("keyword", (String) keyword);
		map.put("totalPage", (int) page.getTotalPages() < 1 ? 1 : page.getTotalPages());
		map.put("totalElement", (int) page.getTotalElements());
		map.put(SystemConstant.ACCOUNTS, page.getContent());

		model.addAllAttributes(map);

		return "admin/bodys/internal_managements/im_account";
	}
	
	@RequestMapping(value = "/admin/internal-managements/account/{id}")
	public String accountDetail(Model model, @PathVariable("id") String id) {
		AccountEntity account = accountServ.findOneById(id);
		model.addAttribute(SystemConstant.ACCOUNT, account);
		model.addAttribute(SystemConstant.ROLES, roleServ.findAll());
		return "admin/bodys/internal_managements/im_detail_account";
	}
	
	@RequestMapping(value = "/admin/internal-managements/account/add")
	public String accountInsertShow(Model model, Principal principal) {
		model.addAttribute("CREATE_AT", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").format(new Date()));
		model.addAttribute("RANDOM_ID", RandomString.make(12));
		model.addAttribute("AUTH_PROVIDER", "LOCAL");
		model.addAttribute("EMAIL", accountServ.findOneByUsername(principal.getName()).getEmail());
		model.addAttribute(SystemConstant.ROLES, roleServ.findAll());
		return "admin/bodys/internal_managements/im_account_insert";
	}
	
//	---------------------------------------POST---------------------------------------
	
	@RequestMapping(value = "/admin/internal-managements/account/tran", method = RequestMethod.POST, consumes = {"multipart/form-data", "application/json"})
	public String doAccountAddRoleToAccount(@RequestPart("account") AccountEntity account,
			@RequestPart("avatar") MultipartFile multipartFile) throws IOException {
		account.setAvatar(multipartFile.getOriginalFilename());
		UploadFileUtil.saveFile(PATH_AVATAR, multipartFile.getOriginalFilename(), multipartFile);
		accountServ.saveWithFile(account);
		return "redirect:/admin/internal-managements/account/" + account.getId();
	}
	
//	---------------------------------------PUT---------------------------------------
	
	@RequestMapping(value = "/admin/internal-managements/account/tran", method = RequestMethod.PUT)
	public String accountUpdate(@RequestPart("account") AccountEntity account) {
		accountServ.updateCustom(account.getId(), account.getName(), account.getEmail(), account.getAddress(),
				account.getPhoneNum(), account.getBirthday(), account.getGender(), account.getRoles());
		return "redirect:/admin/internal-managements/account/" + account.getId();
	}
	
//	---------------------------------------PATCH---------------------------------------
	
//	---------------------------------------DELETE---------------------------------------
	
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

}