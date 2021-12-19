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
	
//	new query
	@Query(value = "select distinct s.id, s.name, s.[image], s.[description], s.price, "
			+ "iss.id as idInvoiceService, iss.quantity, "
			+ "(iss.quantity * s.price) as totalPrice "
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
