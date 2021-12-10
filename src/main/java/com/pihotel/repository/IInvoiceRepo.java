package com.pihotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pihotel.entity.InvoiceEntity;

public interface IInvoiceRepo extends JpaRepository<InvoiceEntity, String>{
//	select top 1 rt.name, rt.id, rt.price, rt.logo from invoice i, room r, invoice_room ir, room_type rt where ir.id_room = r.id and ir.id_invoice = i.id and r.id_room_type = rt.id and i.id = 'jFBy0bJAAV7l'
	public InvoiceEntity findOneById(String id);
	
}
