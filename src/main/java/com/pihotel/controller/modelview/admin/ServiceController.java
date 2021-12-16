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
import com.pihotel.entity.ServiceEntity;
import com.pihotel.service.IAccountServ;
import com.pihotel.service.IServiceServ;
import com.pihotel.utils.UploadFileUtil;

import net.bytebuddy.utility.RandomString;

@Controller
public class ServiceController {

	public static final String PATH_SERVICE = "src/main/resources/static/img/service/";
	
	@Autowired
	private IAccountServ accountServ;
	
	@Autowired
	private IServiceServ serviceServ;
	
//	---------------------------------------GET---------------------------------------
	
	@RequestMapping(value = "/admin/service-managements/service")
	public String roomRedirectPagination(Model model) {
		return this.roomPagination(model, 1, "id", "asc", "");
	}
	
	@RequestMapping(value = "/admin/service-managements/service/page/{currentPage}")
	public String roomPagination(Model model, 
			@PathVariable("currentPage") int currentPage,
			@Param("sortField") String sortField, 
			@Param("sortDir") String sortDir, 
			@Param("keyword") String keyword) {

		Page<ServiceEntity> page = serviceServ.findAll(currentPage, sortField, sortDir, keyword);
		String reverseSort = sortDir.equalsIgnoreCase("asc") ? "desc" : "asc";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("currentPage", (int) currentPage);
		map.put("sortField", (String) sortField);
		map.put("reverseSort", (String) reverseSort);
		map.put("sortDir", (String) sortDir);
		map.put("keyword", (String) keyword);
		map.put("totalPage", (int) page.getTotalPages() < 1 ? 1 : page.getTotalPages());
		map.put("totalElement", (int) page.getTotalElements());
		map.put(SystemConstant.SERVICES, page.getContent());
		
		model.addAllAttributes(map);

		return "admin/bodys/service_managements/sm_service";
	}
	
	@RequestMapping(value = "/admin/service-managements/service/{idService}")
	public String roomRedirectPagination(Model model, @PathVariable("idService") String idService) {
		model.addAttribute(SystemConstant.SERVICE, serviceServ.findOneById(idService));
		return "admin/bodys/service_managements/sm_detail_service";
	}
	
	@RequestMapping(value = "/admin/service-managements/service/add")
	public String accountInsertShow(Model model, Principal principal) {
		model.addAttribute("CREATE_AT", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").format(new Date()));
		model.addAttribute("RANDOM_ID", RandomString.make(12));
		model.addAttribute("EMAIL", accountServ.findOneByUsername(principal.getName()).getEmail());
		return "admin/bodys/service_managements/sm_service_insert";
	}
	
//	---------------------------------------POST---------------------------------------

	@RequestMapping(value = "/admin/service-managements/service/tran", method = RequestMethod.POST, consumes = {"multipart/form-data", "application/json"})
	public String doAccountAddRoleToAccount(@RequestPart("service") ServiceEntity service,
			@RequestPart("image") MultipartFile multipartFile,
			Principal principal) throws IOException {
		service.setImage(multipartFile.getOriginalFilename());
		service.setCreateAt(new Date());
		service.setCreateBy(accountServ.findOneByUsername(principal.getName()).getEmail());
		service.setModifiedAt(new Date());
		service.setModifiedBy(accountServ.findOneByUsername(principal.getName()).getEmail());
		UploadFileUtil.saveFile(PATH_SERVICE, multipartFile.getOriginalFilename(), multipartFile);
		serviceServ.save(service);
		return "redirect:/admin/service-managements/service/" + service.getId();
	}
	
//	---------------------------------------PUT---------------------------------------
	
	@RequestMapping(value = "/admin/service-managements/service/tran", method = RequestMethod.PUT)
	public String accountUpdate(@RequestPart("service") ServiceEntity service,
			@RequestPart("image") MultipartFile multipartFile,
			Principal principal) throws IOException {
		service.setImage(multipartFile.getOriginalFilename());
		service.setCreateAt(new Date());
		service.setCreateBy(accountServ.findOneByUsername(principal.getName()).getEmail());
		service.setModifiedAt(new Date());
		service.setModifiedBy(accountServ.findOneByUsername(principal.getName()).getEmail());
		UploadFileUtil.saveFile(PATH_SERVICE, multipartFile.getOriginalFilename(), multipartFile);
		serviceServ.update(service);
		return "redirect:/admin/service-managements/service/" + service.getId();
	}
	
//	---------------------------------------POST---------------------------------------
	
//	---------------------------------------DELETE---------------------------------------
	
	@RequestMapping(value = "/admin/service-managements/service/tran", method = RequestMethod.DELETE)
	public String accountDelete(@RequestPart("service") ServiceEntity service) {
		serviceServ.deleteById(service.getId());
		return "redirect:/admin/service-managements/service";
	}

	@RequestMapping(value = "/admin/service-managements/services/tran", method = RequestMethod.DELETE)
	public String accountDeleteMany(@RequestBody ServiceEntity service) {
		serviceServ.delete(service.getIds());
		return "redirect:/admin/service-managements/service";
	}
	
}
