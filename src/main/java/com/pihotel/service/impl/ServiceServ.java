package com.pihotel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pihotel.entity.ServiceEntity;
import com.pihotel.repository.IServiceRepo;
import com.pihotel.service.IServiceServ;

@Service
public class ServiceServ implements IServiceServ{

	@Autowired
	private IServiceRepo serviceRepo;
	
	@Override
	public List<ServiceEntity> findAll() {
		// TODO Auto-generated method stub
		return serviceRepo.findAll();
	}

	@Override
	public ServiceEntity save(ServiceEntity service) {
		// TODO Auto-generated method stub
		if (serviceRepo.existsById(service.getId())) {
			return serviceRepo.save(service);
		}
		return null;
	}

	@Override
	public ServiceEntity update(ServiceEntity service) {
		// TODO Auto-generated method stub
		if (!serviceRepo.existsById(service.getId())) {
			return serviceRepo.save(service);
		}
		return null;
	}

	@Override
	public void delete(String[] ids) {
		// TODO Auto-generated method stub
		for (String id : ids) {
			serviceRepo.deleteById(id);
		}
	}

}
