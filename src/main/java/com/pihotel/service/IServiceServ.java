package com.pihotel.service;

import java.util.List;

import com.pihotel.entity.ServiceEntity;

public interface IServiceServ {

	public List<ServiceEntity> findAll();

	public ServiceEntity save(ServiceEntity service);

	public ServiceEntity update(ServiceEntity service);

	public void delete(String[] ids);

}
