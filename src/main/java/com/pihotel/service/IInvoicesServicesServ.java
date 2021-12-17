package com.pihotel.service;

import java.util.List;

import com.pihotel.entity.InvoiceServiceEntity;

public interface IInvoicesServicesServ {

	public List<InvoiceServiceEntity> findAll();
	
	public InvoiceServiceEntity findOneById(String id);
	
	public InvoiceServiceEntity save(InvoiceServiceEntity entity);
	
	public InvoiceServiceEntity update(InvoiceServiceEntity entity);
	
	public void delete(String[] ids);
	
	public void deleteById(String id);
	
	public List<InvoiceServiceEntity> findAllByIdInvoice(String idInvoice);
	
}
