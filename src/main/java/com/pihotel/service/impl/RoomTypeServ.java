package com.pihotel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pihotel.entity.RoomTypeEntity;
import com.pihotel.repository.IRoomTypeRepo;
import com.pihotel.service.IRoomTypeServ;

@Service
public class RoomTypeServ implements IRoomTypeServ{

	@Autowired
	private IRoomTypeRepo roomTypeRepo;
	
//	---------------------------------------SELECT---------------------------------------
	
	@Override
	public List<RoomTypeEntity> findAll() {
		// TODO Auto-generated method stub
		return roomTypeRepo.findAll();
	}
	
	@Override
	public RoomTypeEntity findOneById(String id) {
		// TODO Auto-generated method stub
		return roomTypeRepo.findOneById(id);
	}

	@Override
	public RoomTypeEntity findOneByIdRoom(String idRoom) {
		// TODO Auto-generated method stub
		return roomTypeRepo.findOneByIdRoom(idRoom);
	}
	
//	---------------------------------------INSERT---------------------------------------
	
	@Override
	public RoomTypeEntity save(RoomTypeEntity roomType) {
		// TODO Auto-generated method stub
		if (roomTypeRepo.existsById(roomType.getId())) {
			return roomTypeRepo.save(roomType);
		}
		return null;
	}
	
//	---------------------------------------UPDATE---------------------------------------

	@Override
	public RoomTypeEntity update(RoomTypeEntity roomType) {
		// TODO Auto-generated method stub
		if (!roomTypeRepo.existsById(roomType.getId())) {
			return roomTypeRepo.save(roomType);
		}
		return null;
	}

//	---------------------------------------DELETE---------------------------------------
	
	@Override
	public void delete(String[] ids) {
		// TODO Auto-generated method stub
		for (String id : ids) {
			roomTypeRepo.deleteById(id);
		}
	}

}
