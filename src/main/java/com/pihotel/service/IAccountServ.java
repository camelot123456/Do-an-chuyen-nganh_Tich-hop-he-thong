package com.pihotel.service;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.data.domain.Page;

import com.pihotel.entity.AccountEntity;
import com.pihotel.entity.RoleEntity;
import com.pihotel.entity.enums.EAuthenticationProvider;

public interface IAccountServ {

	public List<AccountEntity> findAll();

	public Page<AccountEntity> findAll(int numPage, String sortField, String sortDir, String keyword);

	public Boolean verify(String verificationCode);

	public AccountEntity save(AccountEntity account);

	public AccountEntity update(AccountEntity account);

	public int updateCustom(String id, String name, String email, String address, String phoneNum, Date birthday,
			Boolean gender, List<RoleEntity> roles);

	public void saveWithFile(AccountEntity account);

	public AccountEntity findOneByUsername(String username);

	public void delete(String[] ids);

	public void deleteById(String id);

	public void addRoleToAccount(String idAccount, List<RoleEntity> roles);

	public AccountEntity findOneById(String id);

	public void saveOneNewAccountByOAuth2(String id, String name, String email, String avatar,
			EAuthenticationProvider provider);

	public void updateOneAccountByOAuth2(AccountEntity account, String name, String email, String avatar,
			EAuthenticationProvider provider);

	public void register(AccountEntity account, String siteURL) throws MessagingException, UnsupportedEncodingException;

	public AccountEntity saveCustomer(AccountEntity customer);
	
	public AccountEntity updateCustomer(String id, String name, String email, String address,
			String phoneNum, Date birthday, Boolean gender);
	
	public Page<AccountEntity> findAllCustomer(int numPage, String sortField, String sortDir, String keyword);
	
}
