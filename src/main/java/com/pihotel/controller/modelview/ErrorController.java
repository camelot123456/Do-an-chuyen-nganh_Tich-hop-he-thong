package com.pihotel.controller.modelview;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController {

	@RequestMapping(value = "/error/403")
	public String err403(Model model) {
		model.addAttribute("ERROR_TITLE", "Lỗi 403!");
		model.addAttribute("ERROR_CONTENT", "Tài khoản của bạn chưa được cấp quyền vào khu vực này");
		return "error/layouts/index";
	}
	
}
