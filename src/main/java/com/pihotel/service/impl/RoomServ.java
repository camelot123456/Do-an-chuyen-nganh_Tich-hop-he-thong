package com.pihotel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pihotel.entity.RoomEntity;
import com.pihotel.repository.IRoomRepo;
import com.pihotel.service.IRoomServ;

@Service
public class RoomServ implements IRoomServ{

	@Autowired
	private IRoomRepo roomRepo;

	@Override
	public List<RoomEntity> findAll() {
		// TODO Auto-generated method stub
		return roomRepo.findAll();
	}

	@Override
	public RoomEntity save(RoomEntity room) {
		// TODO Auto-generated method stub
		if (roomRepo.existsById(room.getId())) {
			return roomRepo.save(room);
		}
		else return null;
	}

	@Override
	public RoomEntity update(RoomEntity room) {
		// TODO Auto-generated method stub
		if (!roomRepo.existsById(room.getId())) {
			return roomRepo.save(room);
		}
		else return null;
	}

	@Override
	public void delete(String[] ids) {
		// TODO Auto-generated method stub
		for (String id : ids) {
			roomRepo.deleteById(id);
		}
	}
	
	
	
}
