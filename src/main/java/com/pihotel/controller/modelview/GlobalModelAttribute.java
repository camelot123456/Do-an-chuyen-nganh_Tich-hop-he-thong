package com.pihotel.controller.modelview;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.pihotel.entity.AccountEntity;

@ControllerAdvice
public class GlobalModelAttribute {

	@ModelAttribute("account")
    public AccountEntity login(HttpServletRequest request) {
		AccountEntity account = (AccountEntity) request.getSession().getAttribute("account");
        return account;
    }
	
}
