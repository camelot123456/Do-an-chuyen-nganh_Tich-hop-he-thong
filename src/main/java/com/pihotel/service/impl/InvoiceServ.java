package com.pihotel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pihotel.entity.InvoiceEntity;
import com.pihotel.repository.IInvoiceRepo;
import com.pihotel.service.IInvoiceServ;

@Service
public class InvoiceServ implements IInvoiceServ{

	@Autowired
	private IInvoiceRepo invoiceRepo;
	
	@Override
	public List<InvoiceEntity> findAll() {
		// TODO Auto-generated method stub
		return invoiceRepo.findAll();
	}

	@Override
	public InvoiceEntity save(InvoiceEntity invoice) {
		// TODO Auto-generated method stub
		return invoiceRepo.save(invoice);
	}

	@Override
	public InvoiceEntity update(InvoiceEntity invoice) {
		// TODO Auto-generated method stub
		if (!invoiceRepo.existsById(invoice.getId())) {
			return invoiceRepo.save(invoice);
		}
		return null;
	}

	@Override
	public void delete(String[] ids) {
		// TODO Auto-generated method stub
		for (String id : ids) {
			invoiceRepo.deleteById(id);
		}
	}

}
