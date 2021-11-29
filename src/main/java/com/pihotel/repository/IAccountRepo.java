package com.pihotel.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pihotel.entity.AccountEntity;

public interface IAccountRepo extends JpaRepository<AccountEntity, String>{

	public AccountEntity findByUsername(String username);
	
	public AccountEntity findOneById(String id);
	
	@Query("select a from AccountEntity a where a.verificationCode = ?1")
	public AccountEntity findByVerificationCode(String code);
	
	@Query("select a from AccountEntity a where a.name like %?1% "
			+ "or a.authProvider like %?1% "
			+ "or a.email like %?1% "
			+ "or concat(a.gender, '') like %?1% "
			+ "or a.phoneNum like %?1%")
	public Page<AccountEntity> search(String keyword, Pageable pageable);
}
