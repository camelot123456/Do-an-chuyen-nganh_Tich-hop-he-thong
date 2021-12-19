package com.pihotel.entity.custom;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.pihotel.entity.AccountEntity;


public class MyUserCustom extends User{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MyUserCustom(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		// TODO Auto-generated constructor stub
	}

	private AccountEntity account;
	
	public void setAccount(AccountEntity account) {
		this.account = account;
	}
	
	public AccountEntity getAccount() {
		return this.account;
	}
}
