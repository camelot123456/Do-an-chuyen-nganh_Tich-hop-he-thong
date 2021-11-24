package com.pihotel.service;

import java.util.List;

import com.pihotel.entity.AccountEntity;

public interface IAccountServ {

	public List<AccountEntity> findAll();
	
	public AccountEntity save(AccountEntity account);
	
	public AccountEntity update(AccountEntity account);
	
	public void delete(String[] ids);
	
}
