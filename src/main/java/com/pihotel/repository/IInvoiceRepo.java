package com.pihotel.repository;

import java.util.List;

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

	@Query(value = "select distinct i.*, rt.id as idRoomType, "
			+ "sum(r.price_incurred) as totalPriceIncurred, (sum(r.price_incurred) + rt.price) as totalPriceAll "
			+ "from invoice i inner join invoice_room ir "
			+ "on i.id = ir.id_invoice inner join room r "
			+ "on ir.id_room = r.id inner join room_type rt "
			+ "on r.id_room_type = rt.id "
			+ "where i.id_account = ?1 and r.room_state = 'CHECKIN' "
			+ "group by rt.price, i.id, i.create_at, i.create_by, i.delete_at, i.delete_by, "
			+ "i.deleted, i.[enabled], i.modified_at, i.modified_by, i.adults, i.children, "
			+ "i.end_date, i.[start_date], i.id_account, rt.id",
			nativeQuery = true)
	public List<Object[]> findAllByIdCustomerCheckin(String idCustomer);
	
	@Query(value = "select distinct i.*, rt.id as idRoomType, "
			+ "sum(r.price_incurred) as totalPriceIncurred, (sum(r.price_incurred) + rt.price) as totalPriceAll "
			+ "from invoice i inner join invoice_room ir "
			+ "on i.id = ir.id_invoice inner join room r "
			+ "on ir.id_room = r.id inner join room_type rt "
			+ "on r.id_room_type = rt.id "
			+ "where i.id_account = ?1 and r.room_state = 'USING' "
			+ "group by rt.price, i.id, i.create_at, i.create_by, i.delete_at, i.delete_by, "
			+ "i.deleted, i.[enabled], i.modified_at, i.modified_by, i.adults, i.children, "
			+ "i.end_date, i.[start_date], i.id_account, rt.id",
			nativeQuery = true)
	public List<Object[]> findAllByIdCustomerUsing(String idCustomer);
	
	@Query(value = "select i.id, i.[start_date], i.end_date, i.adults, i.children, rt.id as idRoomType, "
			+ "a.id as idAccount, a.phone_num as phoneNum, (sum(r.price_incurred) + rt.price) as totalPriceAll "
			+ "from invoice i inner join invoice_room ir "
			+ "on i.id = ir.id_invoice inner join room r "
			+ "on ir.id_room = r.id inner join room_type rt "
			+ "on r.id_room_type = rt.id inner join account a "
			+ "on i.id_account = a.id "
			+ "where i.[enabled] = ?1 "
			+ "group by i.id,  i.[start_date], i.end_date, i.adults, i.children, rt.id, i.id_account, rt.price, a.id,  a.phone_num",
			nativeQuery = true)
	public List<Object[]> findAllPaidInvoices(Boolean isPaid);
}
