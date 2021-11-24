package com.pihotel.service;

import java.util.List;

import com.pihotel.entity.InvoiceEntity;

public interface IInvoiceServ {

	public List<InvoiceEntity> findAll();

	public InvoiceEntity save(InvoiceEntity invoice);

	public InvoiceEntity update(InvoiceEntity invoice);

	public void delete(String[] ids);

}
