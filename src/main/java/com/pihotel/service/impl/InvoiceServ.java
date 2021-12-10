package com.pihotel.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pihotel.entity.InvoiceEntity;
import com.pihotel.repository.IInvoiceRepo;
import com.pihotel.service.IInvoiceServ;

import net.bytebuddy.utility.RandomString;

@Service
public class InvoiceServ implements IInvoiceServ{

	@Autowired
	private IInvoiceRepo invoiceRepo;
	
//	---------------------------------------SELECT---------------------------------------
	
	@Override
	public List<InvoiceEntity> findAll() {
		// TODO Auto-generated method stub
		return invoiceRepo.findAll();
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
		}
		else return null;
	}
	
//	---------------------------------------UPDATE---------------------------------------

	@Override
	public InvoiceEntity update(InvoiceEntity invoice) {
		// TODO Auto-generated method stub
		if (invoiceRepo.existsById(invoice.getId())) {
			return invoiceRepo.save(invoice);
		}
		else return null;
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
