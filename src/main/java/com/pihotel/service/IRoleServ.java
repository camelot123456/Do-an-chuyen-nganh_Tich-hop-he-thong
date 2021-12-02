package com.pihotel.service;

import java.util.List;

import com.pihotel.entity.RoleEntity;

public interface IRoleServ {

	public List<RoleEntity> findAll();

	public RoleEntity save(RoleEntity role);

	public RoleEntity update(RoleEntity role);

	public void delete(String[] ids);
	
	public void deleteById(String id);

	public RoleEntity findOneById(String id);
}
