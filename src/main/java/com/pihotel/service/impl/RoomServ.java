package com.pihotel.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.pihotel.entity.InvoiceEntity;
import com.pihotel.entity.RoomEntity;
import com.pihotel.entity.enums.ERoomState;
import com.pihotel.repository.IInvoiceRepo;
import com.pihotel.repository.IRoomRepo;
import com.pihotel.service.IRoomServ;

@Service
public class RoomServ implements IRoomServ {

	@Autowired
	private IRoomRepo roomRepo;
	
	@Autowired
	private IInvoiceRepo invoiceRepo;

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
	
	@Override
	public List<RoomEntity> findAllShowRoom() {
		List<Object[]> rooms = roomRepo.findAllShowRoom();
		List<RoomEntity> roomsNew = null;
		if (rooms.size() > 1) {
			roomsNew = new ArrayList<RoomEntity>();
			for (Object[] col : rooms) {
				RoomEntity room = new RoomEntity();
				room.setId((String) col[0]);
				room.setNameCustomer((String) col[1]);
				room.setStartDate((Date) col[2]);
				room.setEndDate((Date) col[3]);
				roomsNew.add(room);
			}
		}
		return roomsNew;
	}
	
	//Xử lý đổi checkin sang empty , trước tiên tìm phòng cần set empty, 
	//sau đó lấy tất cả hóa đơn của phòng đó vì trong hóa đơn có chứa sanh sách các phòng,
	//tìm hóa đơn của phòng đó hiện tại thông qua verifyRoom
	//tìm ra thì duyệt hết danh sách room của hóa đơn đó , nếu trùng id của room thì bỏ qua,
	//ngược lại thì lưu vào mảng mới, lấy mảng mới cập nhập lại hóa đơn
	@Override
	public void setRoomStateEmpty(ERoomState roomState, String verifyRoom, String idRoom) {
		RoomEntity room = roomRepo.findOneById(idRoom);
		for(InvoiceEntity invoice : room.getInvoices()) {
			if (invoice.getVerifyRoom().equals(room.getVerifyRoom()) ) {
				InvoiceEntity invoiceNew = invoiceRepo.findObeByVerifyRoom(room.getVerifyRoom());
				List<RoomEntity> roomsArray = new ArrayList<RoomEntity>();
				
				for (RoomEntity roomInvoice : invoiceNew.getRooms()) {
					if (roomInvoice.getId().equals(room.getId())) {
						continue;
					}
					roomInvoice.setVerifyRoom(verifyRoom);
					roomsArray.add(roomInvoice);
				}
				invoiceNew.setVerifyRoom(verifyRoom);
				invoiceNew.setRooms(roomsArray);
				invoiceRepo.save(invoiceNew);
				break;
			}
		};
		roomRepo.updateRoomState(roomState, verifyRoom, idRoom);
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
	public void updateRoomState(ERoomState state, String verifyRoom, String id) {
		// TODO Auto-generated method stub
		roomRepo.updateRoomState(state, verifyRoom, id);
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
