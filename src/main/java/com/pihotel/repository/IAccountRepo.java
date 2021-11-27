package com.pihotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pihotel.entity.AccountEntity;

public interface IAccountRepo extends JpaRepository<AccountEntity, String>{

	public AccountEntity findByUsername(String username);
	
	public AccountEntity findOneById(String id);
	
	
	
}
