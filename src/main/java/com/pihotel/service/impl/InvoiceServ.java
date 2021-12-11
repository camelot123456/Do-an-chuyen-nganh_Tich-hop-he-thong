package com.pihotel.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pihotel.entity.AccountEntity;
import com.pihotel.entity.InvoiceEntity;
import com.pihotel.entity.RoomEntity;
import com.pihotel.entity.enums.ERoomState;
import com.pihotel.repository.IAccountRepo;
import com.pihotel.repository.IInvoiceRepo;
import com.pihotel.repository.IRoomRepo;
import com.pihotel.service.IInvoiceServ;

import net.bytebuddy.utility.RandomString;

@Service
@Transactional
public class InvoiceServ implements IInvoiceServ {

	@Autowired
	private IInvoiceRepo invoiceRepo;

	@Autowired
	private IRoomRepo roomRepo;

	@Autowired
	private IAccountRepo accountRepo;

//	---------------------------------------SELECT---------------------------------------

	@Override
	public List<InvoiceEntity> findAll() {
		// TODO Auto-generated method stub
		return invoiceRepo.findAll();
	}

	@Override
	public InvoiceEntity findOneById(String id) {
		// TODO Auto-generated method stub
		return invoiceRepo.findOneById(id);
	}
	


	@Override
	public Double getSumPriceIncurredAndPriceRoomType(String idInvoice, String idRoomType) {
		// TODO Auto-generated method stub
		return invoiceRepo.getSumPriceIncurredAndPriceRoomType(idInvoice, idRoomType);
	}

//	---------------------------------------INSERT---------------------------------------

	@Override
	public InvoiceEntity save(InvoiceEntity invoice) {
		// TODO Auto-generated method stub
		invoice.setId(RandomString.make(12));
		if (!invoiceRepo.existsById(invoice.getId())) {
			invoice.setCreateAt(new Date());
			invoice.setModifiedAt(new Date());
			return invoiceRepo.save(invoice);
		} else
			return null;
	}

	@Override
	public void addRoomAndCustomerToInvoice(InvoiceEntity invoice, AccountEntity customer) {
		// TODO Auto-generated method stub
		invoice.setCreateAt(new Date());
		invoice.setModifiedAt(new Date());

		invoice.setAccount(accountRepo.findByUsername(customer.getUsername()));
		InvoiceEntity invoiceNew = invoiceRepo.save(invoice);

		List<RoomEntity> rooms = new ArrayList<RoomEntity>();
		invoice.getRooms().forEach(room -> {
			RoomEntity roomNew = roomRepo.findOneById(room.getId());
			roomRepo.updateRoomState(ERoomState.CHECKIN, roomNew.getId());
			rooms.add(roomNew);
		});

		invoiceNew.setRooms(rooms);
		invoiceRepo.save(invoiceNew);
	}

	@Override
	public void addRoomToInvoice(InvoiceEntity invoice) {
		// TODO Auto-generated method stub
		invoice.setCreateAt(new Date());
		invoice.setModifiedAt(new Date());

		InvoiceEntity invoiceNew = invoiceRepo.save(invoice);

		List<RoomEntity> rooms = new ArrayList<RoomEntity>();
		invoice.getRooms().forEach(room -> {
			RoomEntity roomNew = roomRepo.findOneById(room.getId());
			roomRepo.updateRoomState(ERoomState.CHECKIN, roomNew.getId());
			rooms.add(roomNew);
		});

		invoiceNew.setRooms(rooms);
		invoiceRepo.save(invoiceNew);
	}

//	---------------------------------------UPDATE---------------------------------------

	@Override
	public InvoiceEntity update(InvoiceEntity invoice) {
		// TODO Auto-generated method stub
		if (invoiceRepo.existsById(invoice.getId())) {
			return invoiceRepo.save(invoice);
		} else
			return null;
	}

//	---------------------------------------DELETE---------------------------------------

	@Override
	public void delete(String[] ids) {
		// TODO Auto-generated method stub
		for (String id : ids) {
			invoiceRepo.deleteById(id);
		}
	}

}
