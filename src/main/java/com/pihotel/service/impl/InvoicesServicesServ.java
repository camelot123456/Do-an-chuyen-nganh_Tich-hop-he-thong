package com.pihotel.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pihotel.entity.InvoiceEntity;
import com.pihotel.entity.InvoiceServiceEntity;
import com.pihotel.entity.ServiceEntity;
import com.pihotel.repository.IInvoiceRepo;
import com.pihotel.repository.IInvoicesServicesRepo;
import com.pihotel.repository.IServiceRepo;
import com.pihotel.service.IInvoicesServicesServ;

import net.bytebuddy.utility.RandomString;

@Service
public class InvoicesServicesServ implements IInvoicesServicesServ{

	@Autowired
	private IInvoiceRepo invoiceRepo;

	@Autowired
	private IServiceRepo serviceRepo;
	
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
	public void saveWithInvoiceService(InvoiceEntity invoice, ServiceEntity service) {
		InvoiceEntity invoiceNew = invoiceRepo.findOneById(invoice.getId(), Boolean.FALSE);
		
		if (invoiceNew.getInvoicesServices().size() > 0) {
			invoiceNew.getInvoicesServices().forEach(invoiceService -> {
				invoicesServicesRepo.deleteById(invoiceService.getId());
			});
		}
		
		InvoiceServiceEntity invoiceServiceNew = new InvoiceServiceEntity();

		service.getInvoicesServices().forEach(invoicesServices -> {
			ServiceEntity serviceNew = serviceRepo.findOneById(invoicesServices.getId());
			invoiceServiceNew.setService(serviceNew);
			invoiceServiceNew.setQuantity(invoicesServices.getQuantity());
			invoiceServiceNew.setInvoice(invoiceNew);
			invoiceServiceNew.setId(RandomString.make(12));
			invoiceServiceNew.setCreateAt(new Date());
			invoiceServiceNew.setModifiedAt(new Date());
			invoicesServicesRepo.save(invoiceServiceNew);
		});
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
