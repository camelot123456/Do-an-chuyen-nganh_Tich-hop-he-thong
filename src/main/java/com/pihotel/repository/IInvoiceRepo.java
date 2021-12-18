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
			+ "where i.id_account = ?1 and r.room_state = ?2 "
			+ "group by rt.price, i.id, i.create_at, i.create_by, i.delete_at, i.delete_by, "
			+ "i.deleted, i.[enabled], i.modified_at, i.modified_by, i.adults, i.children, "
			+ "i.end_date, i.[start_date], i.id_account, rt.id, i.verify_room",
			nativeQuery = true)
	public List<Object[]> findAllByIdCustomerRoomState(String idCustomer, String roomstate);
	
	@Query(value = "select i.id, i.[start_date], i.end_date, i.adults, i.children, rt.id as idRoomType, "
			+ "a.id as idAccount, a.phone_num as phoneNum, (sum(r.price_incurred) + rt.price) as totalPriceAll "
			+ "from invoice i inner join invoice_room ir "
			+ "on i.id = ir.id_invoice inner join room r "
			+ "on ir.id_room = r.id inner join room_type rt "
			+ "on r.id_room_type = rt.id inner join account a "
			+ "on i.id_account = a.id "
			+ "where i.[enabled] = ?1 "
			+ "group by i.id,  i.[start_date], i.end_date, i.adults, i.children, rt.id, i.id_account, rt.price, a.id,  a.phone_num, i.verify_room",
			nativeQuery = true)
	public List<Object[]> findAllPaidInvoices(Boolean isPaid);
	
	public InvoiceEntity findObeByVerifyRoom(String verifyRoom);
	
	@Query(value = "select count(i.id)"
			+ "from room r inner join invoice_room ir "
			+ "on r.id = ir.id_room inner join invoice i "
			+ "on ir.id_invoice = i.id inner join account a "
			+ "on i.id_account = a.id "
			+ "where i.[enabled] = 0 and i.id_account = ?1",
			nativeQuery = true)
	public Integer getSumCartByIdCustomer(String idCustomer);
	
//	@Query(value = "select i.id, i.[start_date], i.end_date, i.adults, i.children, rt.id as idRoomType, "
//			+ "a.id as idAccount, a.phone_num as phoneNum, (sum(r.price_incurred) + rt.price) as totalPriceAll "
//			+ "from invoice i inner join invoice_room ir "
//			+ "on i.id = ir.id_invoice inner join room r "
//			+ "on ir.id_room = r.id inner join room_type rt "
//			+ "on r.id_room_type = rt.id inner join account a "
//			+ "on i.id_account = a.id "
//			+ "where i.[enabled] = ?2 "
//			+ "and i.id like '%?1%' "
//			+ "or concat(i.adults, '') like '%?1%' "
//			+ "or concat(i.children, '') like '%?1%' "
//			+ "or rt.id like '%?1%' "
//			+ "or a.id like '%?1%' "
//			+ "or a.phone_num like '%?1%' "
//			+ "group by i.id,  i.[start_date], i.end_date, i.adults, i.children, rt.id, i.id_account, rt.price, a.id,  a.phone_num, i.verify_room",
//			nativeQuery = true)
//	public Page<Object[]> search(String keyword , Boolean isPaid, Pageable pageable);
	

}

/*
select i.id, i.[start_date], i.end_date, i.adults, i.children,
a.id as id_custommer, a.name,
r.id as id_room, r.name, 
s.id, s.name, iss.id as invoice_service, iss.quantity,
s.price, (iss.quantity * s.price) as total_price
from invoice i inner join invoice_service iss 
on i.id = iss.id_invoice inner join [service] s
on iss.id_service = s.id inner join account a
on i.id_account = a.id inner join invoice_room ir 
on i.id = ir.id_invoice inner join room r
on r.id = ir.id_room inner join room_type rt
on rt.id = r.id_room_type
where i.id = 'ECqsjCM5aD7U' and r.verify_room = i.verify_room


select sum(iss.quantity) as quantity_service,
sum(iss.quantity * s.price) as total_price_service,
(sum(iss.quantity * s.price) * 0.05) as service_tax_5_percent,
count(r.id) as quantity_room, 
DATEDIFF(DAY, i.[start_date], i.end_date) as night,
rt.price ,
sum(r.price_incurred) as total_price_incurred,
((DATEDIFF(DAY, i.[start_date], i.end_date) * rt.price ) + sum(r.price_incurred)) as total_room,
(sum(iss.quantity * s.price) + (DATEDIFF(DAY, i.[start_date], i.end_date) * rt.price ) + sum(r.price_incurred)) as total_price,
((sum(iss.quantity * s.price) + (DATEDIFF(DAY, i.[start_date], i.end_date) * rt.price ) + sum(r.price_incurred)) * 0.1) as VAT_tax_10_percent,
(((sum(iss.quantity * s.price) + (DATEDIFF(DAY, i.[start_date], i.end_date) * rt.price ) + sum(r.price_incurred)) * 0.1) + (sum(iss.quantity * s.price) + (DATEDIFF(DAY, i.[start_date], i.end_date) * rt.price ) + sum(r.price_incurred))) as total_all_price
from invoice i inner join invoice_service iss 
on i.id = iss.id_invoice inner join [service] s
on iss.id_service = s.id inner join account a
on i.id_account = a.id inner join invoice_room ir 
on i.id = ir.id_invoice inner join room r
on r.id = ir.id_room inner join room_type rt
on rt.id = r.id_room_type
where i.id = 'ECqsjCM5aD7U' and r.verify_room = i.verify_room
group by rt.price , i.[start_date], i.end_date
*/
