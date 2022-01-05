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

import com.pihotel.constant.SystemConstant;
import com.pihotel.entity.AccountEntity;
import com.pihotel.entity.InvoiceEntity;
import com.pihotel.entity.InvoiceServiceEntity;
import com.pihotel.entity.RoomEntity;
import com.pihotel.entity.ServiceEntity;
import com.pihotel.entity.custom.RoomMonitorCustom;
import com.pihotel.entity.enums.EAuthenticationProvider;
import com.pihotel.entity.enums.ERoomState;
import com.pihotel.repository.IAccountRepo;
import com.pihotel.repository.IInvoiceRepo;
import com.pihotel.repository.IInvoicesServicesRepo;
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
	
	@Autowired
	private IInvoicesServicesRepo invoiceServiceRepo;

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
		if (rooms.size() > 0) {
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
	
	@Override
	public List<RoomEntity> searchRoom(String keyword) {
		return roomRepo.searchRoom(keyword);
	}
	
	@Override
	public List<RoomMonitorCustom> findAllByIdAccount(String idAccount) {
		List<Object[]> rooms = roomRepo.findAllByIdAccount(idAccount);
		List<RoomMonitorCustom> roomMonitorCustoms = null;
		if (rooms.size() > 0) {
			roomMonitorCustoms = new ArrayList<RoomMonitorCustom>();
			for (Object[] record : rooms) {
				RoomMonitorCustom custom = new RoomMonitorCustom();
				custom.setIdRoom((String) record[0]);
				custom.setName((String) record[1]);
				custom.setArea((Integer) record[2]);
				custom.setCustomersNum((Integer) record[3]);
				custom.setRoomDescription((String) record[4]);
				custom.setFloor((Integer) record[5]);
				custom.setPriceIncurred((Double) record[6]);
				custom.setRoomState((String) record[7]);
				custom.setIdInvoice((String) record[8]);
				custom.setCreateAt((Date) record[9]);
				custom.setStartDate((Date) record[10]);
				custom.setEndDate((Date) record[11]);
				custom.setAdults((Integer) record[12]);
				custom.setChildren((Integer) record[13]);
				custom.setIdRoomType((String) record[14]);
				custom.setNameRoomType((String) record[15]);
				custom.setLogo((String) record[16]);
				custom.setDescription((String) record[17]);
				roomMonitorCustoms.add(custom);
			}
		}
		return roomMonitorCustoms;
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
	public void saveBooking(AccountEntity customer, RoomEntity room, InvoiceEntity invoice, InvoiceServiceEntity invoiceService) {
		// TODO Auto-generated method stub
		AccountEntity account = null;
		String verifyRoom = RandomString.make(64);
		
		if (!accountRepo.existsById(customer.getId())) {
			customer.setAvatar(SystemConstant.AVATAR_ACCOUNT_DEFAULT_LINK);
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
			roomNew.setVerifyRoom(verifyRoom);
			roomNew.setRoomState(ERoomState.USING);
			rooms.add(roomNew);
		}
		
		invoice.setEnabled(Boolean.TRUE);
		invoice.setAccount(account);
		invoice.setRooms(rooms);
		invoice.setVerifyRoom(verifyRoom);
		InvoiceEntity invoiceNew = invoiceRepo.save(invoice);
		
		for (ServiceEntity service : invoiceService.getServices()) {
			InvoiceServiceEntity invoiceServiceNew = new InvoiceServiceEntity();
			ServiceEntity serviceNew = serviceRepo.findOneById(service.getId());
			invoiceServiceNew.setQuantity(service.getQuantity());
			invoiceServiceNew.setService(serviceNew);
			invoiceServiceNew.setId(RandomString.make(12));
			invoiceServiceNew.setCreateAt(new Date());
			invoiceServiceNew.setModifiedAt(new Date());
			invoiceServiceNew.setInvoice(invoiceNew);
			invoiceServiceRepo.save(invoiceServiceNew);
		}
		
	}

//	---------------------------------------UPDATE---------------------------------------

	@Override
	public RoomEntity update(RoomEntity room) {
		// TODO Auto-generated method stub
		if (roomRepo.existsById(room.getId())) {
			RoomEntity roomNew = roomRepo.findOneById(room.getId()); 
			roomNew.setName(room.getName());
			roomNew.setArea(room.getArea());
			roomNew.setPriceIncurred(room.getPriceIncurred());
			roomNew.setCustomersNum(room.getCustomersNum());
			roomNew.setFloor(room.getFloor());
			roomNew.setDescription(room.getDescription());
			roomNew.setModifiedAt(new Date());
			return roomRepo.save(roomNew);
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



//@Override
//public Page<RoomEntity> searchWithFloorAndRoomType(String floor, String roomType, int numPage, String sortField,
//		String sortDir, String keyword) {
//	// TODO Auto-generated method stub
//	Sort sort = Sort.by(sortField);
//	sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
//	Pageable pageable = PageRequest.of(numPage - 1, 24, sort);
//	if (keyword != null) {
//		return roomRepo.searchWithFloorAndRoomType(floor, roomType, keyword, pageable);
//	}
//	return roomRepo.findAll(pageable);
//}
