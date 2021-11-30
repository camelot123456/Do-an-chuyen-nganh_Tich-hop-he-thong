package com.pihotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pihotel.entity.RoleEntity;

public interface IRoleRepo extends JpaRepository<RoleEntity, String>{

	public RoleEntity findOneById(String id);
	
}
