package com.pihotel.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.pihotel.entity.RoomTypeEntity;

public interface IRoomTypeServ {

	public List<RoomTypeEntity> findAll();
	
	public RoomTypeEntity save(RoomTypeEntity roomType);
	
	public RoomTypeEntity update(RoomTypeEntity roomType);
	
	public void delete(String[] ids);
	
	public RoomTypeEntity findOneById(String id);
	
	public RoomTypeEntity findOneByIdRoom(String idRoom);
	
	public Page<RoomTypeEntity> findAll(int numPage, String sortField, String sortDir, String keyword);
	
	public void deleteById(String id);
}
