package com.pihotel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.pihotel.entity.ServiceEntity;
import com.pihotel.repository.IServiceRepo;
import com.pihotel.service.IServiceServ;

@Service
public class ServiceServ implements IServiceServ{

	@Autowired
	private IServiceRepo serviceRepo;
	
//	---------------------------------------SELECT---------------------------------------
	
	@Override
	public List<ServiceEntity> findAll() {
		// TODO Auto-generated method stub
		return serviceRepo.findAll();
	}
	
	@Override
	public Page<ServiceEntity> findAll(int numPage, String sortField, String sortDir, String keyword) {
		// TODO Auto-generated method stub
		Sort sort = Sort.by(sortField);
		sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
		Pageable pageable = PageRequest.of(numPage - 1, 20, sort);
		if (keyword != "") {
			return serviceRepo.search(keyword, pageable);
		}
		return serviceRepo.findAll(pageable);
	}

	@Override
	public ServiceEntity findOneById(String id) {
		// TODO Auto-generated method stub
		return serviceRepo.findOneById(id);
	}

//	---------------------------------------INSERT---------------------------------------	
	
	@Override
	public ServiceEntity save(ServiceEntity service) {
		// TODO Auto-generated method stub
		if (!serviceRepo.existsById(service.getId())) {
			return serviceRepo.save(service);
		}
		return null;
	}

//	---------------------------------------UPDATE---------------------------------------	
	
	@Override
	public ServiceEntity update(ServiceEntity service) {
		// TODO Auto-generated method stub
		if (!serviceRepo.existsById(service.getId())) {
			return serviceRepo.save(service);
		}
		return null;
	}

//	---------------------------------------DELETE---------------------------------------	
	
	@Override
	public void delete(String[] ids) {
		// TODO Auto-generated method stub
		for (String id : ids) {
			serviceRepo.deleteById(id);
		}
	}

	@Override
	public void deleteById(String id) {
		// TODO Auto-generated method stub
		serviceRepo.deleteById(id);
	}

}
