package com.pihotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pihotel.entity.RoomEntity;

public interface IRoomRepo extends JpaRepository<RoomEntity, String>{

}
