package com.pihotel.controller.modelview.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StatisticalController {

	@RequestMapping(value = {"/admin/statistical-managements/statistical"})
	public String adminHome() {
		return "admin/bodys/statistical_managements/sm_statistical";
	}
	
}
