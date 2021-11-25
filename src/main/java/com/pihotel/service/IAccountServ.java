package com.pihotel.service;

import java.util.List;

import com.pihotel.entity.AccountEntity;
import com.pihotel.entity.enums.EAuthenticationProvider;

public interface IAccountServ {

	public List<AccountEntity> findAll();
	
	public AccountEntity save(AccountEntity account);
	
	public AccountEntity update(AccountEntity account);
	
	public void delete(String[] ids);
	
	public AccountEntity findOneById(String id);
	
	public void saveOneNewAccountByOAuth2(String id, String name, String email, String avatar, EAuthenticationProvider provider);
	
	public void updateOneAccountByOAuth2(AccountEntity account, String name, String email, String avatar, EAuthenticationProvider provider);
}
