package com.pihotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pihotel.entity.RoomTypeEntity;

public interface IRoomTypeRepo extends JpaRepository<RoomTypeEntity, String>{

	public RoomTypeEntity findOneById(String id);
	
	@Query(value = "select distinct rt.id, rt.name, cast(rt.description as nvarchar(255)), rt.price, cast(rt.logo as nvarchar(255)) "
			+ "from room r  inner join room_type rt on r.id_room_type = rt.id  "
			+ "where r.id = ?1",
			nativeQuery = true)
	public RoomTypeEntity findOneByIdRoom(String idRoom);
}
