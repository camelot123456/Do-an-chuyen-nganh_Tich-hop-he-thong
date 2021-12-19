package com.pihotel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pihotel.entity.InvoiceEntity;

public interface IInvoiceRepo extends JpaRepository<InvoiceEntity, String>{
//	select top 1 rt.name, rt.id, rt.price, rt.logo from invoice i, room r, invoice_room ir, room_type rt where ir.id_room = r.id and ir.id_invoice = i.id and r.id_room_type = rt.id and i.id = 'jFBy0bJAAV7l'
	
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
	

	
	
//	new query
	@Query(value = "select distinct i.* "
			+ "from invoice i left join invoice_service iss "
			+ "on i.id = iss.id_invoice left join [service] s "
			+ "on iss.id_service = s.id inner join invoice_room ir "
			+ "on i.id = ir.id_invoice inner join room r "
			+ "on r.id = ir.id_room inner join room_type rt "
			+ "on rt.id = r.id_room_type "
			+ "where i.id = ?1 and i.[enabled] = ?2",
			nativeQuery = true)
	public InvoiceEntity findOneById(String id, Boolean isPaid);
	
	@Query(value = "select i.id as idInvoice,\n" + 
			"ISNULL ((select sum(iss.quantity) from invoice_service iss inner join [service] s  on iss.id_service = s.id inner join invoice i on i.id = iss.id_invoice where i.id = ?1 ) ,0) as quantityService,\n" + 
			"ISNULL ((select sum(iss.quantity * s.price) from invoice_service iss inner join [service] s on iss.id_service = s.id inner join invoice i on i.id = iss.id_invoice where i.id = ?1 ), 0) as totalPriceService,\n" + 
			"ISNULL ((select sum(iss.quantity * s.price) * 0.05 from invoice_service iss inner join [service] s on iss.id_service = s.id inner join invoice i on i.id = iss.id_invoice where i.id = ?1 ), 0)as serviceTax5Percent,\n" + 
			"(select count(r.id) from invoice_room ir inner join room r on ir.id_room = r.id inner join invoice i on i.id = ir.id_invoice where i.id = ?1) as quantityRoom,\n" + 
			"DATEDIFF(DAY, i.[start_date], i.end_date) as night,\n" + 
			"rt.price as priceRoomType,\n" + 
			"(select sum(r.price_incurred) from invoice_room ir right outer join room r on ir.id_room = r.id right outer join invoice i on i.id = ir.id_invoice right outer join room_type rt on rt.id = r.id_room_type where i.id = ?1 group by i.[start_date], i.end_date, rt.price) as totalPriceIncurred,\n" + 
			"(select DATEDIFF(DAY, i.[start_date], i.end_date) * rt.price  + sum(r.price_incurred) from invoice_room ir right outer join room r on ir.id_room = r.id right outer join invoice i on i.id = ir.id_invoice right outer join room_type rt on rt.id = r.id_room_type where i.id = ?1 group by i.[start_date], i.end_date, rt.price) as totalPriceRoom,\n" + 
			"(ISNULL ((select sum(iss.quantity * s.price) from invoice_service iss inner join [service] s on iss.id_service = s.id inner join invoice i on i.id = iss.id_invoice where i.id = ?1 ), 0) \n" + 
			"+\n" + 
			"ISNULL ((select DATEDIFF(DAY, i.[start_date], i.end_date) * rt.price  + sum(r.price_incurred) from invoice_room ir right outer join room r on ir.id_room = r.id right outer join invoice i on i.id = ir.id_invoice right outer join room_type rt on rt.id = r.id_room_type where i.id = ?1 group by i.[start_date], i.end_date, rt.price), 0 )) as totalPrice,\n" + 
			"((ISNULL ((select sum(iss.quantity * s.price) from invoice_service iss inner join [service] s on iss.id_service = s.id inner join invoice i on i.id = iss.id_invoice where i.id = ?1 ), 0 ) \n" + 
			"+\n" + 
			"ISNULL ((select DATEDIFF(DAY, i.[start_date], i.end_date) * rt.price  + sum(r.price_incurred) from invoice_room ir right outer join room r on ir.id_room = r.id right outer join invoice i on i.id = ir.id_invoice right outer join room_type rt on rt.id = r.id_room_type where i.id = ?1 group by i.[start_date], i.end_date, rt.price),0)) * 0.1) as VATTax10Percent,\n" + 
			"(((ISNULL ((select sum(iss.quantity * s.price) from invoice_service iss inner join [service] s on iss.id_service = s.id inner join invoice i on i.id = iss.id_invoice where i.id = ?1 ) ,0)\n" + 
			"+\n" + 
			"ISNULL ((select DATEDIFF(DAY, i.[start_date], i.end_date) * rt.price  + sum(r.price_incurred) from invoice_room ir right outer join room r on ir.id_room = r.id right outer join invoice i on i.id = ir.id_invoice right outer join room_type rt on rt.id = r.id_room_type where i.id = ?1 group by i.[start_date], i.end_date, rt.price), 0)) * 0.1)\n" + 
			"+\n" + 
			"ISNULL ((select sum(iss.quantity * s.price) from invoice_service iss inner join [service] s on iss.id_service = s.id inner join invoice i on i.id = iss.id_invoice where i.id = ?1 ) ,0)\n" + 
			"+\n" + 
			"ISNULL ((select DATEDIFF(DAY, i.[start_date], i.end_date) * rt.price  + sum(r.price_incurred) from invoice_room ir right outer join room r on ir.id_room = r.id right outer join invoice i on i.id = ir.id_invoice right outer join room_type rt on rt.id = r.id_room_type where i.id = ?1 group by i.[start_date], i.end_date, rt.price),0)) as totalAllPrice \n" + 
			"from invoice i left join invoice_service iss \n" + 
			"on i.id = iss.id_invoice left join [service] s \n" + 
			"on iss.id_service = s.id inner join account a \n" + 
			"on i.id_account = a.id inner join invoice_room ir \n" + 
			"on i.id = ir.id_invoice left outer join room r \n" + 
			"on r.id = ir.id_room inner join room_type rt \n" + 
			"on rt.id = r.id_room_type \n" + 
			"where i.id = ?1 and r.verify_room = i.verify_room and i.[enabled] = ?2 \n" + 
			"group by rt.price , i.[start_date], i.end_date, i.id",
			nativeQuery = true)
	public List<Object[]> findOneBillCustomByIdInvoice(String idInvoice, Boolean isPaid);
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



select i.id as idInvoice,
--quantityService
	ISNULL ((select sum(iss.quantity) 
	from invoice_service iss inner join [service] s 
	on iss.id_service = s.id inner join invoice i 
	on i.id = iss.id_invoice 
	where i.id = ?1 ) ,0) as quantityService,
--totalPriceService
	ISNULL ((select sum(iss.quantity * s.price)  
	from invoice_service iss inner join [service] s 
	on iss.id_service = s.id inner join invoice i 
	on i.id = iss.id_invoice 
	where i.id = ?1 ), 0) as totalPriceService,
--serviceTax5Percent
	ISNULL ((select sum(iss.quantity * s.price) * 0.05 
	from invoice_service iss inner join [service] s 
	on iss.id_service = s.id inner join invoice i 
	on i.id = iss.id_invoice 
	where i.id = ?1 ), 0)as serviceTax5Percent,
--quantityRoom
	(select count(r.id) 
	from invoice_room ir inner join room r 
	on ir.id_room = r.id inner join invoice i 
	on i.id = ir.id_invoice 
	where i.id = ?1) as quantityRoom,
--night
	DATEDIFF(DAY, i.[start_date], i.end_date) as night,
--priceRoomType
	rt.price as priceRoomType,
--totalPriceIncurred
	(select sum(r.price_incurred) from invoice_room ir right outer join room r 
	on ir.id_room = r.id right outer join invoice i 
	on i.id = ir.id_invoice right outer join room_type rt 
	on rt.id = r.id_room_type 
	where i.id = ?1
	group by i.[start_date], i.end_date, rt.price) as totalPriceIncurred,
--totalPriceRoom
	(select DATEDIFF(DAY, i.[start_date], i.end_date) * rt.price  + sum(r.price_incurred)
	from invoice_room ir right outer join room r 
	on ir.id_room = r.id right outer join invoice i 
	on i.id = ir.id_invoice right outer join room_type rt 
	on rt.id = r.id_room_type 
	where i.id = ?1
	group by i.[start_date], i.end_date, rt.price) as totalPriceRoom,
--totalPrice
	(ISNULL ((select sum(iss.quantity * s.price)  
	from invoice_service iss inner join [service] s 
	on iss.id_service = s.id inner join invoice i 
	on i.id = iss.id_invoice 
	where i.id = ?1 ), 0) 
	+
	ISNULL ((select DATEDIFF(DAY, i.[start_date], i.end_date) * rt.price  + sum(r.price_incurred)
	from invoice_room ir right outer join room r 
	on ir.id_room = r.id right outer join invoice i 
	on i.id = ir.id_invoice right outer join room_type rt 
	on rt.id = r.id_room_type 
	where i.id = ?1
	group by i.[start_date], i.end_date, rt.price), 0 )) as totalPrice,
--VATTax10Percent
	((ISNULL ((select sum(iss.quantity * s.price)  
	from invoice_service iss inner join [service] s 
	on iss.id_service = s.id inner join invoice i 
	on i.id = iss.id_invoice 
	where i.id = ?1 ), 0 ) 
	+
	ISNULL ((select DATEDIFF(DAY, i.[start_date], i.end_date) * rt.price  + sum(r.price_incurred)
	from invoice_room ir right outer join room r 
	on ir.id_room = r.id right outer join invoice i 
	on i.id = ir.id_invoice right outer join room_type rt 
	on rt.id = r.id_room_type 
	where i.id = ?1
	group by i.[start_date], i.end_date, rt.price),0)) * 0.1) as VATTax10Percent,
--totalAllPrice
	(((ISNULL ((select sum(iss.quantity * s.price)  
	from invoice_service iss inner join [service] s 
	on iss.id_service = s.id inner join invoice i 
	on i.id = iss.id_invoice 
	where i.id = ?1 ) ,0)
	+
	ISNULL ((select DATEDIFF(DAY, i.[start_date], i.end_date) * rt.price  + sum(r.price_incurred)
	from invoice_room ir right outer join room r 
	on ir.id_room = r.id right outer join invoice i 
	on i.id = ir.id_invoice right outer join room_type rt 
	on rt.id = r.id_room_type 
	where i.id = ?1
	group by i.[start_date], i.end_date, rt.price), 0)) * 0.1)
	+
	ISNULL ((select sum(iss.quantity * s.price)  
	from invoice_service iss inner join [service] s 
	on iss.id_service = s.id inner join invoice i 
	on i.id = iss.id_invoice 
	where i.id = ?1 ) ,0)
	+
	ISNULL ((select DATEDIFF(DAY, i.[start_date], i.end_date) * rt.price  + sum(r.price_incurred)
	from invoice_room ir right outer join room r 
	on ir.id_room = r.id right outer join invoice i 
	on i.id = ir.id_invoice right outer join room_type rt 
	on rt.id = r.id_room_type 
	where i.id = ?1
	group by i.[start_date], i.end_date, rt.price),0)) as totalAllPrice

from invoice i left join invoice_service iss 
on i.id = iss.id_invoice left join [service] s
on iss.id_service = s.id inner join account a
on i.id_account = a.id inner join invoice_room ir 
on i.id = ir.id_invoice left outer join room r
on r.id = ir.id_room inner join room_type rt
on rt.id = r.id_room_type
where i.id = ?1 and r.verify_room = i.verify_room and i.[enabled] = ?2
group by rt.price , i.[start_date], i.end_date, i.id

*/
