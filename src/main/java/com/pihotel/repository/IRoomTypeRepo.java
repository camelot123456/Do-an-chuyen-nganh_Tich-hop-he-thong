package com.pihotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pihotel.entity.RoomTypeEntity;

public interface IRoomTypeRepo extends JpaRepository<RoomTypeEntity, String>{

	public RoomTypeEntity findOneById(String id);
	
}
