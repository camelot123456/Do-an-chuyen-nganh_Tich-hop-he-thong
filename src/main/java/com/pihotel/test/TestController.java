package com.pihotel.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class TestController {

	@RequestMapping("/one")
	public RedirectView doOne() {
		log.info("This is page: {}", 1);
		RedirectView view = new RedirectView();
		view.setUrl("/two");
		return view;
	}
	
	@RequestMapping("/two")
	public String doTwo() {
		log.info("This is page: {}", 2);
		return "test/layouts/two";
	}
	
}
