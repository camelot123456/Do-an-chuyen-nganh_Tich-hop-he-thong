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
	
//	https://stackoverflow.com/questions/31857491/custom-query-in-spring-jpa-repository-with-pagination
//	@Query(value = "select a from AccountEntity a where a.authProvier=''")
//	public Page<AccountEntity> findAllAndPageCustom(Pageable pageable);
//	
//	public Page<AccountEntity> searchAndPageCustom(String keyword, Pageable pageable);
	
}
