package com.pihotel.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pihotel.entity.ServiceEntity;

public interface IServiceRepo extends JpaRepository<ServiceEntity, String>{

	@Query(value = "select s from ServiceEntity s "
			+ "where s.name like %?1% "
			+ "or s.id like %?1% "
			+ "or concat(s.price, '') like %?1% "
			+ "or s.description like %?1%",
			nativeQuery = true)
	public Page<ServiceEntity> search(String keywork, Pageable pageable);
	
	public ServiceEntity findOneById(String id);
}
