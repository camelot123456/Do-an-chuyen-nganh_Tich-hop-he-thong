package com.pihotel.websocket;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/server1")
public class MyServerEndpoint {

	@OnOpen
	public void handleOpen() {
		System.out.println("Ứng dụng khách hiện đã được kết nối");
	}
	
	@OnMessage
	public String handleMessage(String message) {
		System.out.println("Nhận từ khách hàng: "+message);
		String responseMessage = "Echo " + message;
		System.out.println("Gửi cho khách hàng: "+responseMessage);
		return responseMessage;
	}
	
	@OnClose
	public void handleClose() {
		System.out.println("Khách hàng đã đóng kết nối!");
	}
	
	@OnError
	public void handleError(Throwable t) {
		t.printStackTrace();
	}
}
