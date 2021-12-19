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

	@Query(value = "select a.* from account a inner join account_role ar on a.id = ar.id inner join [role] r on r.id = ar.id_role "
			+ "where a.auth_provider = 'LOCAL' and r.code = 'MEMBER' and a.name like %?1% or a.username like %?1% or a.phone_num like %?1% or a.email like %?1% or a.id like %?1% "
			+ "or a.auth_provider = 'FACEBOOK' and r.code = 'MEMBER' and a.name like %?1% or a.username like %?1% or a.phone_num like %?1% or a.email like %?1% or a.id like %?1% "
			+ "or a.auth_provider = 'GOOGLE' and r.code = 'MEMBER' and a.name like %?1% or a.username like %?1% or a.phone_num like %?1% or a.email like %?1% or a.id like %?1% "
			+ "union "
			+ "select * " 
			+ "from account a " 
			+ "where a.auth_provider = 'NO_ACCOUNT' and a.name like %?1% or a.username like %?1% or a.phone_num like %?1% or a.email like %?1% or a.id like %?1%",
			nativeQuery = true)
	public Page<AccountEntity> searchCustomer(String keyword, Pageable pageable);
	
	@Query(countQuery = "select count(*) "
			+ "from account a inner join account_role ar "
			+ "on a.id = ar.id inner join [role] r "
			+ "on ar.id_role = r.id  "
			+ "where a.auth_provider = 'FACEBOOK' or a.auth_provider = 'GOOGLE' "
			+ "or a.auth_provider = 'LOCAL' and r.code = 'MEMBER'",
			value = "select a.* "
			+ "from account a inner join account_role ar "
			+ "on a.id = ar.id inner join [role] r "
			+ "on ar.id_role = r.id  "
			+ "where a.auth_provider = 'FACEBOOK' or a.auth_provider = 'GOOGLE' "
			+ "or a.auth_provider = 'LOCAL' and r.code = 'MEMBER' "
			+ "union "
			+ "select * " 
			+ "from account a " 
			+ "where a.auth_provider = 'NO_ACCOUNT'",
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
	
	@Query(value = "select a.* "
			+ "from room r inner join invoice_room ir "
			+ "on r.id = ir.id_room inner join room_type rt "
			+ "on r.id_room_type = rt.id inner join invoice i "
			+ "on ir.id_invoice = i.id inner join account a "
			+ "on a.id = i.id_account "
			+ "where r.id = ?1 and r.verify_room = i.verify_room",
			nativeQuery = true)
	public AccountEntity findOneByIdForRoom(String idRoom);
	
//	new query
	@Query(value = "select distinct a.* "
			+ "from invoice i inner join invoice_service iss "
			+ "on i.id = iss.id_invoice inner join [service] s "
			+ "on iss.id_service = s.id inner join account a "
			+ "on i.id_account = a.id inner join invoice_room ir "
			+ "on i.id = ir.id_invoice inner join room r "
			+ "on r.id = ir.id_room inner join room_type rt "
			+ "on rt.id = r.id_room_type "
			+ "where i.id = ?1  and i.[enabled] = ?2", 
			nativeQuery = true)
	public AccountEntity findOneByIdInvoice(String idInvoice, Boolean isPaid);
}
