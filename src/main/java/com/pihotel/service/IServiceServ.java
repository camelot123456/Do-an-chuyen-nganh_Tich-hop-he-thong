package com.pihotel.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.pihotel.entity.ServiceEntity;

public interface IServiceServ {

	public List<ServiceEntity> findAll();

	public ServiceEntity save(ServiceEntity service);

	public ServiceEntity update(ServiceEntity service);

	public void delete(String[] ids);
	
	public Page<ServiceEntity> findAll(int numPage, String sortField, String sortDir, String keyword);

	public ServiceEntity findOneById(String id);
	
	public void deleteById(String id);
	
	public List<ServiceEntity> findAllByIdInvoice(String idInvoice, Boolean isPaid);
	
	public List<ServiceEntity> findAllByIdRoom(String idRoom);
	
	public List<ServiceEntity> searchService(String keyword);
}
