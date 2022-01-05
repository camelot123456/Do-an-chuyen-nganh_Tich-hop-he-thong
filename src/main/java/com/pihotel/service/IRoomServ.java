package com.pihotel.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.pihotel.entity.AccountEntity;
import com.pihotel.entity.InvoiceEntity;
import com.pihotel.entity.InvoiceServiceEntity;
import com.pihotel.entity.RoomEntity;
import com.pihotel.entity.custom.RoomMonitorCustom;
import com.pihotel.entity.enums.ERoomState;

public interface IRoomServ {

	public List<RoomEntity> findAll();

	public RoomEntity save(RoomEntity room);

	public RoomEntity update(RoomEntity room);

	public void delete(String[] ids);
	
	public Page<RoomEntity> findAll(int numPage, String sortField, String sortDir, String keyword);
	
	public RoomEntity findOneById(String id);
	
	public List<RoomEntity> findAllByIdRoomType(String idRoomType, int customersNum);

	public void updateRoomState(ERoomState state, String verifyRoom, String id);
	
	public int maxFloor();
	
	public List<RoomEntity> findAllShowRoom();
	
	public void setRoomStateEmpty(ERoomState roomState, String verifyRoom, String idRoom);
	
	public List<RoomEntity> findAllByIdInvoice(String idInvoice, Boolean isPaid);
	
	public void saveBooking(AccountEntity customer, RoomEntity room, InvoiceEntity invoice, InvoiceServiceEntity invoiceService);
	
	public List<RoomEntity> searchRoom(String keyword);
	
	public List<RoomMonitorCustom> findAllByIdAccount(String idAccount);
	
}

//	public Page<RoomEntity> searchWithFloorAndRoomType(String floor, String roomType, int numPage, String sortField, String sortDir, String keyword);