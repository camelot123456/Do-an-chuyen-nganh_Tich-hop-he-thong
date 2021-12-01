package com.pihotel.repository;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pihotel.entity.AccountEntity;

@Repository
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
	
	@Modifying
	@Transactional
//	@Query(value = "update account set "
//			+ "name=?2, email=?3, "
//			+ "address=?4, phone_num=?5, "
//			+ "birthday=?6, gender=?7 where id=?1",
//			nativeQuery = true)
	@Query(value = "update AccountEntity a set "
			+ "a.name=?2, a.email=?3, "
			+ "a.address=?4, a.phoneNum=?5, "
			+ "a.birthday=?6, a.gender=?7 where a.id=?1")
	public int updateCustom(
			String id,
			String name,
			String email,
			String address,
			String phoneNum,
			Date birthday,
			Boolean gender
	);
}
