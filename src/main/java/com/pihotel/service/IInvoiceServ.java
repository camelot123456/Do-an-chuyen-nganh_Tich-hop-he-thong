package com.pihotel.service;

import java.util.List;

import com.pihotel.entity.AccountEntity;
import com.pihotel.entity.InvoiceEntity;

public interface IInvoiceServ {

	public List<InvoiceEntity> findAll();

	public InvoiceEntity save(InvoiceEntity invoice);

	public InvoiceEntity update(InvoiceEntity invoice);

	public void delete(String[] ids);
	
	public void addRoomAndCustomerToInvoice(InvoiceEntity invoice, AccountEntity customer);

	public void addRoomToInvoice(InvoiceEntity invoice);
	
	public InvoiceEntity findOneById(String id);
	
	public Double getSumPriceIncurredAndPriceRoomType(String idInvoice, String idRoomType);
	
	public Double getSumPriceIncurred(String idInvoice);
	
	public List<InvoiceEntity> findAllPaidInvoices(Boolean isPaid);
	
	public List<InvoiceEntity> findAllByIdCustomerRoomState(String idCustomer, String roomState);
	
	public InvoiceEntity findOneByVerifyRoom(String verifyRoom);
	
	public Integer getSumCartByIdCustomer(String idCustomer);
	
	
}
