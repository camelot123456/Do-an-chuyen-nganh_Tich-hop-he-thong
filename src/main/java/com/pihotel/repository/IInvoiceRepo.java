package com.pihotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pihotel.entity.InvoiceEntity;

public interface IInvoiceRepo extends JpaRepository<InvoiceEntity, String>{
//	select top 1 rt.name, rt.id, rt.price, rt.logo from invoice i, room r, invoice_room ir, room_type rt where ir.id_room = r.id and ir.id_invoice = i.id and r.id_room_type = rt.id and i.id = 'jFBy0bJAAV7l'
	public InvoiceEntity findOneById(String id);
	
	@Query(value = "select sum(r.price_incurred) + rt.price "
			+ "from invoice i inner join invoice_room ir "
			+ "on i.id = ir.id_invoice inner join room r "
			+ "on ir.id_room = r.id inner join room_type rt "
			+ "on r.id_room_type = rt.id "
			+ "where i.id = ?1 and rt.id = ?2 "
			+ "group by rt.price", 
			nativeQuery = true)
	public Double getSumPriceIncurredAndPriceRoomType(String idInvoice, String idRoomType);
	
	@Query(value = "select sum(r.price_incurred) "
			+ "from invoice i inner join invoice_room ir "
			+ "on i.id = ir.id_invoice inner join room r "
			+ "on ir.id_room = r.id inner join room_type rt "
			+ "on r.id_room_type = rt.id "
			+ "where i.id = ?1", 
			nativeQuery = true)
	public Double getSumPriceIncurred(String idInvoice);

}
