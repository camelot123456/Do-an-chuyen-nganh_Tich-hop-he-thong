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

	@Query("select r from RoomEntity r where r.name like %?1% "
			+ "or r.roomState like %?1% "
			+ "or concat(r.area, '') like %?1% "
			+ "or concat(r.customersNum, '') like %?1% "
			+ "or concat(r.floor, '') like %?1% "
			+ "or r.name like %?1% "
			+ "or concat(r.priceIncurred, '') like %?1% ")
	public Page<RoomEntity> search(String keyword, Pageable pageable);
	
	public RoomEntity findOneById(String id);
	
	@Query(value = "select r from RoomEntity r where r.roomType.id = ?1 and r.customersNum >= ?2 and r.roomState not in ('USING', 'CHECKIN', 'DEPOSIT')")
	public List<RoomEntity> findAllByIdRoomType(String idRoomType, int customersNum);
	
	@Modifying
	@Transactional
	@Query(value = "update RoomEntity r set r.roomState = ?1 where r.id = ?2")
	public void updateRoomState(ERoomState state, String id);
}
