package com.pihotel.controller.modelview;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebSocketController {

	@RequestMapping("/chat")
	public String doChat() {
		return "test/layouts/chat";
	}
	
}
