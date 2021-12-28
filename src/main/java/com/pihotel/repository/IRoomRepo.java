package com.pihotel.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pihotel.entity.RoomEntity;
import com.pihotel.entity.enums.ERoomState;

@Repository
@Transactional
public interface IRoomRepo extends JpaRepository<RoomEntity, String>{

//	---------------------------------------SELECT---------------------------------------
	
	public RoomEntity findOneById(String id);
	
	@Query("select r from RoomEntity r where r.name like %?1% "
			+ "or r.roomState like %?1% "
			+ "or concat(r.area, '') like %?1% "
			+ "or concat(r.customersNum, '') like %?1% "
			+ "or concat(r.floor, '') like %?1% "
			+ "or r.name like %?1% "
			+ "or concat(r.priceIncurred, '') like %?1% "
			+ "or r.roomType.id like %?1%")
	public Page<RoomEntity> search(String keyword, Pageable pageable);
	
	@Query(value = "select r from RoomEntity r where r.roomType.id = ?1 and r.customersNum >= ?2 and r.roomState not in ('USING', 'REPAIR', 'DEPOSIT')")
	public List<RoomEntity> findAllByIdRoomType(String idRoomType, int customersNum);
	
	@Query(value = "select coalesce(max(r.floor), 0) from RoomEntity r")
	public int maxFloor();
	
	@Query(value = "select r.id, a.name as nameCustomer, i.[start_date], i.end_date "
			+ "from room r inner join invoice_room ir "
			+ "on r.id = ir.id_room inner join invoice i "
			+ "on ir.id_invoice = i.id inner join account a "
			+ "on i.id_account = a.id "
			+ "where i.[enabled] = 1 and r.room_state = 'USING' and i.verify_room = r.verify_room "
			+ "union "
			+ "select r.id, a.name as nameCustomer, i.[start_date], i.end_date "
			+ "from room r inner join invoice_room ir "
			+ "on r.id = ir.id_room inner join invoice i "
			+ "on ir.id_invoice = i.id inner join account a "
			+ "on i.id_account = a.id "
			+ "where i.[enabled] = 0 and i.verify_room = r.verify_room", 
			nativeQuery = true)
	public List<Object[]> findAllShowRoom();
	
	@Query(value = "select  *\n" + 
			"from room r\n" + 
			"where r.id like %?1% \n" + 
			"or r.name like %?1% \n" + 
			"or r.[description] like %?1% \n" + 
			"or concat(r.price_incurred, '') like %?1% \n" + 
			"or concat(r.area, '') like %?1% \n" + 
			"or concat(r.customers_num, '') like %?1% \n" + 
			"or concat(r.[floor], '') like %?1% \n" + 
			"or r.room_state like %?1%", 
			nativeQuery = true)
	public List<RoomEntity> searchRoom(String keyword);
	
//	new query
	@Query(value = "select distinct r.* "
			+ "from invoice i left join invoice_service iss "
			+ "on i.id = iss.id_invoice left join [service] s "
			+ "on iss.id_service = s.id inner join account a "
			+ "on i.id_account = a.id inner join invoice_room ir "
			+ "on i.id = ir.id_invoice inner join room r "
			+ "on r.id = ir.id_room inner join room_type rt "
			+ "on rt.id = r.id_room_type "
			+ "where i.id = ?1 and i.[enabled] = ?2 ",
			nativeQuery = true)
	public List<RoomEntity> findAllByIdInvoice(String idInvoice, Boolean isPaid); 

//	---------------------------------------INSERT---------------------------------------
	
//	---------------------------------------UPDATE---------------------------------------
	
	@Modifying
	@Transactional
	@Query(value = "update RoomEntity r set r.roomState = ?1, r.verifyRoom = ?2 where r.id = ?3")
	public void updateRoomState(ERoomState state, String verifyRoom, String id);
	
//	---------------------------------------DELETE---------------------------------------
	
}



//@Query(value = "select * from room r where r.floor = ?1 or r.id_room_type = ?2 and r.name like %?3% "
//		+ "or r.room_state like %?3% "
//		+ "or concat(r.area, '') like %?3% "
//		+ "or concat(r.customers_num, '') like %?3% "
//		+ "or concat(r.floor, '') like %?3% "
//		+ "or r.name like %?3% "
//		+ "or concat(r.price_incurred, '') like %?3% ", nativeQuery =  true)
//public Page<RoomEntity> searchWithFloorAndRoomType(String floor, String roomType, String keyword, Pageable pageable);
