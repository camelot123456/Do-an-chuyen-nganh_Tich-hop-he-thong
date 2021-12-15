package com.pihotel.controller.modelview.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pihotel.constant.SystemConstant;
import com.pihotel.service.IServiceServ;

@Controller(value = "home-service")
public class ServiceController {
	
	@Autowired
	private IServiceServ serviceServ;

//	---------------------------------------GET---------------------------------------	
	
	@RequestMapping(value = {"/service"})
	public String showCartRedirect(Model model) {
		model.addAttribute(SystemConstant.SERVICES, serviceServ.findAll());
		return "home/bodys/service_list";
	}


//	---------------------------------------POST---------------------------------------

//	---------------------------------------PUT---------------------------------------

//	---------------------------------------PATCH---------------------------------------

//	---------------------------------------DELETE---------------------------------------
	
}
