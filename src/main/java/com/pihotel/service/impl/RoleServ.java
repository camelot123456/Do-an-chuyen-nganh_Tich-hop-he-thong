package com.pihotel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.pihotel.entity.RoleEntity;
import com.pihotel.repository.IRoleRepo;
import com.pihotel.service.IRoleServ;

@Service
public class RoleServ implements IRoleServ{

	@Autowired
	private IRoleRepo roleRepo;
	
	@Override
	public List<RoleEntity> findAll() {
		// TODO Auto-generated method stub
		return roleRepo.findAll();
	}

	@Override
	public RoleEntity save(RoleEntity role) {
		// TODO Auto-generated method stub
		if (!roleRepo.existsById(role.getId())) {
			return roleRepo.save(role);
		}
		else return null;
	}

	@Override
	public RoleEntity update(RoleEntity role) {
		// TODO Auto-generated method stub
		if (!roleRepo.existsById(role.getId())) {
			return roleRepo.save(role);
		}
		else return null;
	}

	@Override
	public void delete(String[] ids) {
		// TODO Auto-generated method stub
		for (String id : ids) {
			roleRepo.deleteById(id);
		}
	}
	
	@Override
	public RoleEntity findOneById(String id) {
		// TODO Auto-generated method stub
		return roleRepo.findOneById(id);
	}

	@Override
	public void deleteById(String id) {
		// TODO Auto-generated method stub
		roleRepo.deleteById(id);
	}

	@Override
	public Page<RoleEntity> findAll(int numPage, String sortField, String sortDir, String keyword) {
		// TODO Auto-generated method stub
		Sort sort = Sort.by(sortField);
		sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
		PageRequest pageable = PageRequest.of(numPage - 1, 10, sort);
		if (keyword != null) {
			return roleRepo.search(keyword, pageable);
		}
		return roleRepo.findAll(pageable);
	}

	@Override
	public int updateCustom(String id, String name, String code) {
		// TODO Auto-generated method stub
		return roleRepo.updateCustom(id, name, code);
	}

}
