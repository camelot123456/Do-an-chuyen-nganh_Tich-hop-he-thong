package com.pihotel.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pihotel.entity.RoomTypeEntity;

public interface IRoomTypeRepo extends JpaRepository<RoomTypeEntity, String>{

	public RoomTypeEntity findOneById(String id);
	
	@Query(value = "select rt.* "
			+ "from room r inner join invoice_room ir "
			+ "on r.id = ir.id_room inner join room_type rt "
			+ "on r.id_room_type = rt.id inner join invoice i "
			+ "on ir.id_invoice = i.id inner join account a "
			+ "on a.id = i.id_account "
			+ "where r.id = ?1 "
			+ "union "
			+ "select rt.* "
			+ "from room r inner join room_type rt "
			+ "on r.id_room_type = rt.id "
			+ "where r.id = ?1",
			nativeQuery = true)
	public RoomTypeEntity findOneByIdRoom(String idRoom);
	
	@Query(value = "select s from RoomType s "
			+ "where s.name like %?1% "
			+ "or s.id like %?1% "
			+ "or concat(s.price, '') like %?1% "
			+ "or s.description like %?1%",
			nativeQuery = true)
	public Page<RoomTypeEntity> search(String keyword, Pageable pageable);
	
	@Query(value = "select distinct rt.*, count(r.id) as totalRoom "
			+ "from room_type rt right join room r  "
			+ "on rt.id = r.id_room_type "
			+ "group by rt.id, rt.create_at, rt.create_by, rt.delete_at, "
			+ "rt.delete_by, rt.deleted, rt.[description], rt.[enabled], "
			+ "rt.logo, rt.modified_at, rt.modified_by, rt.name, rt.price",
	nativeQuery = true)
	public List<Object[]> findAllWithTotalRoom();
	
//	new query
	@Query(value = "select distinct rt.* "
			+ "from invoice i left join invoice_service iss  "
			+ "on i.id = iss.id_invoice left join [service] s "
			+ "on iss.id_service = s.id inner join invoice_room ir "
			+ "on i.id = ir.id_invoice inner join room r "
			+ "on r.id = ir.id_room inner join room_type rt "
			+ "on rt.id = r.id_room_type "
			+ "where i.id = ?1 and i.[enabled] = ?2",
	nativeQuery = true)
	public List<Object[]> findOneByIdInvoice(String idInvoice, Boolean isPaid);
}
