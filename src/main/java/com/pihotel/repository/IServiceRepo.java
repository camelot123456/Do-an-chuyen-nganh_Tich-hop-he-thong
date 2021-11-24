package com.pihotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pihotel.entity.ServiceEntity;

public interface IServiceRepo extends JpaRepository<ServiceEntity, String>{

}
