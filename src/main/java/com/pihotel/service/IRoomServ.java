package com.pihotel.service;

import java.util.List;

import com.pihotel.entity.RoomEntity;

public interface IRoomServ {

	public List<RoomEntity> findAll();

	public RoomEntity save(RoomEntity room);

	public RoomEntity update(RoomEntity room);

	public void delete(String[] ids);

}
