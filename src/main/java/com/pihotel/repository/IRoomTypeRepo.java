package com.pihotel.repository;



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
}
