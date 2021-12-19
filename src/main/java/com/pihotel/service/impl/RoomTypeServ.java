package com.pihotel.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

	@Override
	public Page<RoomTypeEntity> findAll(int numPage, String sortField, String sortDir, String keyword) {
		// TODO Auto-generated method stub
		Sort sort = Sort.by(sortField);
		sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
		Pageable pageable = PageRequest.of(numPage - 1, 20, sort);
		if (keyword != "") {
			return roomTypeRepo.search(keyword, pageable);
		}
		return roomTypeRepo.findAll(pageable);
	}

	@Override
	public void deleteById(String id) {
		// TODO Auto-generated method stub
		roomTypeRepo.deleteById(id);
	}
	
	@Override
	public RoomTypeEntity findOneByIdInvoice(String idInvoice, Boolean isPaid) {
		List<Object[]> roomTypeArray = roomTypeRepo.findOneByIdInvoice(idInvoice, isPaid);
		List<RoomTypeEntity> roomTypeArrayNew = null;
		if (roomTypeArray.size() > 0) {
			roomTypeArrayNew = new ArrayList<RoomTypeEntity>();
			for (Object[] record : roomTypeArray) {
				RoomTypeEntity roomType = new RoomTypeEntity();
				roomType.setId((String) record[0]);
				roomType.setDescription((String) record[9]);
				roomType.setLogo((String) record[10]);
				roomType.setName((String) record[11]);
				roomType.setPrice((Double) record[12]);
				roomTypeArrayNew.add(roomType);
			}
		}
		return roomTypeArrayNew.get(0);
	}
	
//	---------------------------------------INSERT---------------------------------------
	
	@Override
	public RoomTypeEntity save(RoomTypeEntity roomType) {
		// TODO Auto-generated method stub
		if (!roomTypeRepo.existsById(roomType.getId())) {
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
