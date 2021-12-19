package com.pihotel.controller.modelview;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.pihotel.entity.AccountEntity;
import com.pihotel.service.IAccountServ;

@ControllerAdvice
public class GlobalModelAttribute {
	
	@Autowired
	private IAccountServ accountServ;

	@ModelAttribute("account")
    public AccountEntity login(HttpServletRequest request) {
		AccountEntity account = (AccountEntity) request.getSession().getAttribute("account");
        return account;
    }
	
}
