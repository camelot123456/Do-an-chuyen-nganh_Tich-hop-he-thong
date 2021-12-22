package com.pihotel.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pihotel.entity.ServiceEntity;

public interface IServiceRepo extends JpaRepository<ServiceEntity, String>{

	@Query(value = "select s from ServiceEntity s "
			+ "where s.name like %?1% "
			+ "or s.id like %?1% "
			+ "or concat(s.price, '') like %?1% "
			+ "or s.description like %?1%",
			nativeQuery = true)
	public Page<ServiceEntity> search(String keywork, Pageable pageable);
	
	public ServiceEntity findOneById(String id);
	
	@Query(value = "select s.id, s.name, s.[image], isnull (iss.quantity ,0) as quantity, s.price, s.[description] ,isnull ((iss.quantity * s.price), 0) as totalPrice \n" + 
			"from [service] s inner join invoice_service [iss]\n" + 
			"on s.id = iss.id_service inner join invoice i\n" + 
			"on iss.id_invoice = i.id inner join invoice_room ir \n" + 
			"on ir.id_invoice = i.id inner join room r\n" + 
			"on r.id = ir.id_room\n" + 
			"where r.verify_room = i.verify_room and r.id = ?1",
			nativeQuery = true)
	public List<Object[]> findAllByIdRoom(String idRoom);
	
//	new query
	@Query(value = "select distinct s.id, s.name, s.[image], s.[description], s.price, "
			+ "iss.id as idInvoiceService, isnull (iss.quantity ,0) as quantity, "
			+ "isnull ((iss.quantity * s.price), 0) as totalPrice "
			+ "from invoice i inner join invoice_service iss "
			+ "on i.id = iss.id_invoice inner join [service] s "
			+ "on iss.id_service = s.id inner join account a "
			+ "on i.id_account = a.id inner join invoice_room ir "
			+ "on i.id = ir.id_invoice inner join room r "
			+ "on r.id = ir.id_room inner join room_type rt "
			+ "on rt.id = r.id_room_type "
			+ "where i.id = ?1 and i.[enabled] = ?2",
			nativeQuery = true)
	public List<Object[]> findAllByIdInvoice(String idInvoice, Boolean isPaid);
}
