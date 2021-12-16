package com.pihotel.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.pihotel.entity.RoomEntity;
import com.pihotel.entity.enums.ERoomState;
import com.pihotel.repository.IRoomRepo;
import com.pihotel.service.IRoomServ;

@Service
public class RoomServ implements IRoomServ {

	@Autowired
	private IRoomRepo roomRepo;

//	---------------------------------------SELECT---------------------------------------

	@Override
	public List<RoomEntity> findAll() {
		// TODO Auto-generated method stub
		return roomRepo.findAll();
	}

	@Override
	public Page<RoomEntity> findAll(int numPage, String sortField, String sortDir, String keyword) {
		// TODO Auto-generated method stub
		Sort sort = Sort.by(sortField);
		sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
		Pageable pageable = PageRequest.of(numPage - 1, 24, sort);
		if (keyword != null) {
			return roomRepo.search(keyword, pageable);
		}
		return roomRepo.findAll(pageable);
	}

	@Override
	public Page<RoomEntity> searchWithFloorAndRoomType(String floor, String roomType, int numPage, String sortField,
			String sortDir, String keyword) {
		// TODO Auto-generated method stub
		Sort sort = Sort.by(sortField);
		sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
		Pageable pageable = PageRequest.of(numPage - 1, 24, sort);
		if (keyword != null) {
			return roomRepo.searchWithFloorAndRoomType(floor, roomType, keyword, pageable);
		}
		return roomRepo.findAll(pageable);
	}

	@Override
	public RoomEntity findOneById(String id) {
		// TODO Auto-generated method stub
		return roomRepo.findOneById(id);
	}

	@Override
	public List<RoomEntity> findAllByIdRoomType(String idRoomType, int customersNum) {
		// TODO Auto-generated method stub
		return roomRepo.findAllByIdRoomType(idRoomType, customersNum);
	}

	@Override
	public int maxFloor() {
		// TODO Auto-generated method stub
		return roomRepo.maxFloor();
	}

//	---------------------------------------INSERT---------------------------------------

	@Override
	public RoomEntity save(RoomEntity room) {
		// TODO Auto-generated method stub
		if (!roomRepo.existsById(room.getId())) {
			room.setRoomState(ERoomState.EMPTY);
			room.setCreateAt(new Date());
			room.setModifiedAt(new Date());
			return roomRepo.save(room);
		} else
			return null;
	}

//	---------------------------------------UPDATE---------------------------------------

	@Override
	public RoomEntity update(RoomEntity room) {
		// TODO Auto-generated method stub
		if (!roomRepo.existsById(room.getId())) {
			return roomRepo.save(room);
		} else
			return null;
	}

	@Override
	public void updateRoomState(ERoomState state, String id) {
		// TODO Auto-generated method stub
		roomRepo.updateRoomState(state, id);
	}

//	---------------------------------------DELETE---------------------------------------

	@Override
	public void delete(String[] ids) {
		// TODO Auto-generated method stub
		for (String id : ids) {
			roomRepo.deleteById(id);
		}
	}

}
