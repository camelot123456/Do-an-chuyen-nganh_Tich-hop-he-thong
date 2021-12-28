package com.pihotel.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.pihotel.entity.RoleEntity;

public interface IRoleRepo extends JpaRepository<RoleEntity, String>{

	public RoleEntity findOneById(String id);
	
	public RoleEntity findOneByCode(String code);
	
	@Query("select a from RoleEntity a where a.name like %?1% "
			+ "or a.code like %?1% "
			+ "or a.id like %?1%")
	public Page<RoleEntity> search(String keyword, Pageable pageable);

	@Query(value = "select * \n" + 
			"from [role] r \n" + 
			"where r.id like %?1% \n" + 
			"or r.code like %?1% \n" + 
			"or r.[description] like %?1% \n" + 
			"or r.name like %?1% ",
			nativeQuery = true)
	public List<RoleEntity> searchRole(String keyword);
	
	@Modifying
	@Transactional
//	@Query(value = "update account set "
//			+ "name=?2, email=?3, "
//			+ "address=?4, phone_num=?5, "
//			+ "birthday=?6, gender=?7 where id=?1",
//			nativeQuery = true)
	@Query(value = "update RoleEntity r set "
			+ "r.name=?2, r.code=?3 where r.id=?1")
	public int updateCustom(
			String id,
			String name,
			String code
	);

}
