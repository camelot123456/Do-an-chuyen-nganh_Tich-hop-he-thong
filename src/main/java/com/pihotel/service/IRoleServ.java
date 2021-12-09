package com.pihotel.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.pihotel.entity.RoleEntity;

public interface IRoleServ {

	public List<RoleEntity> findAll();
	
	public Page<RoleEntity> findAll(int numPage, String sortField, String sortDir, String keyword);
	
	public int updateCustom(String id, String name, String code);

	public RoleEntity save(RoleEntity role);

	public RoleEntity update(RoleEntity role);

	public void delete(String[] ids);
	
	public void deleteById(String id);

	public RoleEntity findOneById(String id);
	
}
