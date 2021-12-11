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
@Transactional
public interface IAccountRepo extends JpaRepository<AccountEntity, String>{

	public AccountEntity findByUsername(String username);
	
	public AccountEntity findOneById(String id);
	
	@Query("select a from AccountEntity a where a.verificationCode = ?1")
	public AccountEntity findByVerificationCode(String code);
	
	@Query("select a from AccountEntity a where a.name like %?1% "
			+ "or a.email like %?1% "
			+ "or a.phoneNum like %?1% "
			+ "or a.id like %?1%")
	public Page<AccountEntity> search(String keyword, Pageable pageable);

//	select * from account a inner join account_role ar on a.id = ar.id inner join [role] r on r.id = ar.id_role 
//			where a.auth_provider = 'LOCAL' and r.code = 'MEMBER' and a.name like N'%?1%' or a.username like N'%?1%' or a.phone_num like N'%?1%' or a.email like N'%?1%' or a.id like N'%?1%' 
//			or a.auth_provider = 'FACEBOOK' and r.code = 'MEMBER' and a.name like N'%?1%' or a.username like N'%?1%' or a.phone_num like N'%?1%' or a.email like N'%?1%' or a.id like N'%?1%'  
//			or a.auth_provider = 'GOOGLE' and r.code = 'MEMBER' and a.name like N'%?1%' or a.username like N'%?1%' or a.phone_num like N'%?1%' or a.email like N'%?1%' or a.id like N'%?1%'  
	@Query(value = "select * from account a inner join account_role ar on a.id = ar.id inner join [role] r on r.id = ar.id_role "
			+ "where a.auth_provider = 'LOCAL' and r.code = 'MEMBER' and a.name like N'%?1%' or a.username like N'%?1%' or a.phone_num like N'%?1%' or a.email like N'%?1%' or a.id like N'%?1%' "
			+ "or a.auth_provider = 'FACEBOOK' and r.code = 'MEMBER' and a.name like N'%?1%' or a.username like N'%?1%' or a.phone_num like N'%?1%' or a.email like N'%?1%' or a.id like N'%?1%' "
			+ "or a.auth_provider = 'GOOGLE' and r.code = 'MEMBER' and a.name like N'%?1%' or a.username like N'%?1%' or a.phone_num like N'%?1%' or a.email like N'%?1%' or a.id like N'%?1%' ", 
			nativeQuery = true)
	public Page<AccountEntity> searchCustomer(String keyword, Pageable pageable);
	
	@Query(value = "select * from account a inner join account_role ar on a.id = ar.id inner join [role] r on r.id = ar.id_role "
			+ "where a.auth_provider = 'LOCAL' and r.code = 'MEMBER' "
			+ "or a.auth_provider = 'FACEBOOK' and r.code = 'MEMBER' "
			+ "or a.auth_provider = 'GOOGLE' and r.code = 'MEMBER' ", 
			nativeQuery = true)
	public Page<AccountEntity> findAllCustomer(Pageable pageable);
	
	@Modifying
	@Transactional
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
	
	@Query(value = "insert into account(id, name, email, [address], phone_num, birthday, avatar, gender) "
			+ "values('?1', N'?2', '?3', N'?4', '?5', cast('?6' as DATETIME2), '?7', '?8')", nativeQuery = true)
	public AccountEntity saveCustommer(
			String id,
			String name,
			String email,
			String address,
			String phoneNum,
			Date birthday,
			String avatar,
			Boolean gender
		);
	
	public Boolean existsByUsername(String username);
	
//	@Query(value = "select c from AccountEntity c where and "
//			+ "or c.name like %?1% "
//			+ "or c.email like %?1% "
//			+ "or c.phoneNum like %?1% "
//			+ "or c.id like %?1% "
//			+ "")
//	public findAllCustomers(String keyword, Pageable pageable);
	
//	https://stackoverflow.com/questions/31857491/custom-query-in-spring-jpa-repository-with-pagination
//	@Query(value = "select a from AccountEntity a where a.authProvier=''")
//	public Page<AccountEntity> findAllAndPageCustom(Pageable pageable);
//	
//	public Page<AccountEntity> searchAndPageCustom(String keyword, Pageable pageable);
	
}
