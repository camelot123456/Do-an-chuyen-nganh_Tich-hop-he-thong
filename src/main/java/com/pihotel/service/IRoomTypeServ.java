package com.pihotel.service;

import java.util.List;

import com.pihotel.entity.RoomTypeEntity;

public interface IRoomTypeServ {

	public List<RoomTypeEntity> findAll();
	
	public RoomTypeEntity save(RoomTypeEntity roomType);
	
	public RoomTypeEntity update(RoomTypeEntity roomType);
	
	public void delete(String[] ids);
	
	public RoomTypeEntity findOneById(String id);
	
}
