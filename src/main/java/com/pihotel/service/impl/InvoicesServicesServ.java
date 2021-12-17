package com.pihotel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pihotel.entity.InvoiceServiceEntity;
import com.pihotel.repository.IInvoicesServicesRepo;
import com.pihotel.service.IInvoicesServicesServ;

@Service
public class InvoicesServicesServ implements IInvoicesServicesServ{

	@Autowired
	private IInvoicesServicesRepo invoicesServicesRepo;
	
	@Override
	public List<InvoiceServiceEntity> findAll() {
		// TODO Auto-generated method stub
		return invoicesServicesRepo.findAll();
	}

	@Override
	public InvoiceServiceEntity findOneById(String id) {
		// TODO Auto-generated method stub
		return invoicesServicesRepo.findOneById(id);
	}
	
	@Override
	public List<InvoiceServiceEntity> findAllByIdInvoice(String idInvoice) {
		return invoicesServicesRepo.findAllByIdInvoice(idInvoice);
	}

	@Override
	public InvoiceServiceEntity save(InvoiceServiceEntity entity) {
		// TODO Auto-generated method stub
		if (!invoicesServicesRepo.existsById(entity.getId())) {
			return invoicesServicesRepo.save(entity);
		}
		return null;
	}

	@Override
	public InvoiceServiceEntity update(InvoiceServiceEntity entity) {
		// TODO Auto-generated method stub
		if (invoicesServicesRepo.existsById(entity.getId())) {
			return invoicesServicesRepo.save(entity);
		}
		return null;
	}

	@Override
	public void delete(String[] ids) {
		// TODO Auto-generated method stub
		for (String id : ids) {
			invoicesServicesRepo.deleteById(id);
		}
	}

	@Override
	public void deleteById(String id) {
		// TODO Auto-generated method stub
		invoicesServicesRepo.deleteById(id);
	}

}
