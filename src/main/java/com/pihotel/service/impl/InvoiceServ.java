package com.pihotel.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pihotel.entity.AccountEntity;
import com.pihotel.entity.InvoiceEntity;
import com.pihotel.entity.InvoiceServiceEntity;
import com.pihotel.entity.RoomEntity;
import com.pihotel.entity.ServiceEntity;
import com.pihotel.entity.enums.ERoomState;
import com.pihotel.repository.IAccountRepo;
import com.pihotel.repository.IInvoiceRepo;
import com.pihotel.repository.IInvoicesServicesRepo;
import com.pihotel.repository.IRoomRepo;
import com.pihotel.repository.IServiceRepo;
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

	@Autowired
	private IInvoicesServicesRepo invoiceServiceRepo;

	@Autowired
	private IServiceRepo serviceRepo;

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
	public Double getSumPriceIncurred(String idInvoice) {
		// TODO Auto-generated method stub
		return invoiceRepo.getSumPriceIncurred(idInvoice);
	}

	@Override
	public Double getSumPriceIncurredAndPriceRoomType(String idInvoice, String idRoomType) {
		// TODO Auto-generated method stub
		return invoiceRepo.getSumPriceIncurredAndPriceRoomType(idInvoice, idRoomType);
	}

	@Override
	public List<InvoiceEntity> findAllPaidInvoices(Boolean isPaid) {
		// TODO Auto-generated method stub
		List<Object[]> invoicesArray = invoiceRepo.findAllPaidInvoices(isPaid);
		List<InvoiceEntity> invoicesNew = null;
		if (invoicesArray.size() > 0) {
			invoicesNew = new ArrayList<>();
			for (Object[] invoiceDetail : invoicesArray) {
				InvoiceEntity invoice = new InvoiceEntity();
				invoice.setId((String) invoiceDetail[0]);
				invoice.setStartDate((Date) invoiceDetail[1]);
				invoice.setEndDate((Date) invoiceDetail[2]);
				invoice.setAdults((Integer) invoiceDetail[3]);
				invoice.setChildren((Integer) invoiceDetail[4]);
				invoice.setIdRoomType((String) invoiceDetail[5]);
				invoice.setIdAccount((String) invoiceDetail[6]);
				invoice.setPhoneNum((String) invoiceDetail[7]);
				invoice.setTotalPriceAll((Double) invoiceDetail[8]);
				invoicesNew.add(invoice);
			}
		}
		return invoicesNew;
	}

	@Override
	public InvoiceEntity findOneByVerifyRoom(String verifyRoom) {
		return invoiceRepo.findObeByVerifyRoom(verifyRoom);
	}

	@Override
	public Integer getSumCartByIdCustomer(String idCustomer) {
		return invoiceRepo.getSumCartByIdCustomer(idCustomer);
	}

	@Override
	public List<InvoiceEntity> findAllByIdCustomerRoomState(String idCustomer, String roomState) {
		List<Object[]> invoiceArray = invoiceRepo.findAllByIdCustomerRoomState(idCustomer, roomState);
		List<InvoiceEntity> invoiceNew = null;
		if (invoiceArray.size() > 0) {
			invoiceNew = new ArrayList<InvoiceEntity>();
			for (Object[] invoiceDetail : invoiceArray) {
				InvoiceEntity invoice = new InvoiceEntity();
				invoice.setId((String) invoiceDetail[0]);
				invoice.setAdults((Integer) invoiceDetail[9]);
				invoice.setChildren((Integer) invoiceDetail[10]);
				invoice.setEndDate((Date) invoiceDetail[11]);
				invoice.setStartDate((Date) invoiceDetail[12]);
				invoice.setIdRoomType((String) invoiceDetail[15]);
				invoice.setTotalPriceIncurred((Double) invoiceDetail[16]);
				invoice.setTotalPriceAll((Double) invoiceDetail[17]);
				invoiceNew.add(invoice);
			}
		}
		return invoiceNew;
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
		String verifyRoom = RandomString.make(64);
		invoice.setVerifyRoom(verifyRoom);
		invoice.setCreateAt(new Date());
		invoice.setModifiedAt(new Date());
		invoice.setEnabled(Boolean.FALSE);
		invoice.setAccount(accountRepo.findOneById(customer.getId()));
		InvoiceEntity invoiceNew = invoiceRepo.save(invoice);

		List<RoomEntity> rooms = new ArrayList<RoomEntity>();
		invoice.getRooms().forEach(room -> {
			RoomEntity roomNew = roomRepo.findOneById(room.getId());
			roomNew.setVerifyRoom(verifyRoom);
			roomNew.setRoomState(ERoomState.CHECKIN);
			rooms.add(roomNew);
		});

		invoiceNew.setRooms(rooms);
		invoiceRepo.save(invoiceNew);
	}

	@Override
	public void addRoomToInvoice(InvoiceEntity invoice) {
		// TODO Auto-generated method stub
		String verifyRoom = RandomString.make(64);

		invoice.setVerifyRoom(verifyRoom);
		invoice.setCreateAt(new Date());
		invoice.setModifiedAt(new Date());
		invoice.setEnabled(Boolean.FALSE);

		InvoiceEntity invoiceNew = invoiceRepo.save(invoice);

		List<RoomEntity> rooms = new ArrayList<RoomEntity>();
		invoice.getRooms().forEach(room -> {
			RoomEntity roomNew = roomRepo.findOneById(room.getId());
			roomNew.setVerifyRoom(verifyRoom);
			roomNew.setRoomState(ERoomState.CHECKIN);
			rooms.add(roomNew);
		});

		invoiceNew.setRooms(rooms);
		invoiceRepo.save(invoiceNew);
	}

	@Override
	public InvoiceEntity saveWithInvoiceService(InvoiceEntity invoice, ServiceEntity service) {
		InvoiceEntity invoiceNew = invoiceRepo.findOneById(invoice.getId());
		InvoiceServiceEntity invoiceServiceNew = new InvoiceServiceEntity();

		service.getInvoicesServices().forEach(invoicesServices -> {
			ServiceEntity serviceNew = serviceRepo.findOneById(invoicesServices.getId());
			invoiceServiceNew.setService(serviceNew);
			invoiceServiceNew.setQuantity(invoicesServices.getQuantity());
			invoiceServiceNew.setInvoice(invoiceNew);
			invoiceServiceNew.setId(RandomString.make(12));
			invoiceServiceNew.setCreateAt(new Date());
			invoiceServiceNew.setModifiedAt(new Date());
			invoiceServiceRepo.save(invoiceServiceNew);
		});

		return null;
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
