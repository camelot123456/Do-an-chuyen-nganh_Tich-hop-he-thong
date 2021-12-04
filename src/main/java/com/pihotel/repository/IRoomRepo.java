package com.pihotel.repository;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pihotel.entity.RoomEntity;

@Repository
@Transactional
public interface IRoomRepo extends JpaRepository<RoomEntity, String>{

	@Query("select r from RoomEntity r where r.name like %?1% "
			+ "or concat(r.price, '') like %?1% ")
	public Page<RoomEntity> search(String keyword, Pageable pageable);
	
	public RoomEntity findOneById(String id);
}
