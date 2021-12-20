package com.pihotel.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.pihotel.entity.AccountEntity;
import com.pihotel.entity.InvoiceEntity;
import com.pihotel.entity.InvoiceServiceEntity;
import com.pihotel.entity.RoomEntity;
import com.pihotel.entity.ServiceEntity;
import com.pihotel.entity.enums.EAuthenticationProvider;
import com.pihotel.entity.enums.ERoomState;
import com.pihotel.repository.IAccountRepo;
import com.pihotel.repository.IInvoiceRepo;
import com.pihotel.repository.IRoomRepo;
import com.pihotel.repository.IServiceRepo;
import com.pihotel.service.IRoomServ;

import net.bytebuddy.utility.RandomString;

@Service
public class RoomServ implements IRoomServ {

	@Autowired
	private IRoomRepo roomRepo;
	
	@Autowired
	private IInvoiceRepo invoiceRepo;
	
	@Autowired
	private IAccountRepo accountRepo;
	
	@Autowired
	private IServiceRepo serviceRepo;

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
	
	@Override
	public List<RoomEntity> findAllByIdInvoice(String idInvoice, Boolean isPaid) {
		return roomRepo.findAllByIdInvoice(idInvoice, isPaid);
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

	@Transactional
	@Override
	public void saveBooking(AccountEntity customer, RoomEntity room, InvoiceEntity invoice) {
		// TODO Auto-generated method stub
		AccountEntity account = null;
		
		if (!accountRepo.existsById(customer.getId())) {
			customer.setId(RandomString.make(12));
			customer.setAuthProvider(EAuthenticationProvider.NO_ACCOUNT);
			customer.setCreateAt(new Date());
			customer.setModifiedAt(new Date());
			account = accountRepo.save(customer);
		} else {
			account = accountRepo.findOneById(customer.getId());
		}
		
		List<RoomEntity> rooms = new ArrayList<RoomEntity>();
		for (String id : room.getIds()) {
			RoomEntity roomNew = roomRepo.findOneById(id);
			rooms.add(roomNew);
		}
		
		List<InvoiceServiceEntity> invoiceServiceArray = new ArrayList<InvoiceServiceEntity>();
		invoice.getInvoicesServices().forEach(invoiceService -> {
			InvoiceServiceEntity invoiceServiceNew = new InvoiceServiceEntity();
			ServiceEntity serviceNew = serviceRepo.findOneById(invoiceService.getService().getId());
			invoiceServiceNew.setQuantity(invoiceService.getService().getQuantity());
			invoiceServiceNew.setService(serviceNew);
			invoiceServiceNew.setId(RandomString.make(12));
			invoiceServiceNew.setCreateAt(new Date());
			invoiceServiceNew.setModifiedAt(new Date());
			invoiceServiceArray.add(invoiceServiceNew);
		});
		
		invoice.setAccount(account);
		invoice.setRooms(rooms);
		invoice.setInvoicesServices(invoiceServiceArray);
		invoiceRepo.save(invoice);
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
