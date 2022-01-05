package com.pihotel.entity.custom;

import java.util.Date;

import lombok.Data;

@Data
public class RoomMonitorCustom {

	private String idRoom;
	
	private String name;

	private String roomState;
	
	private String roomDescription;
	
	private Integer customersNum;
	
	private Integer area;
	
	private Double priceIncurred;
	
	private Integer floor;
	
	private String idInvoice;
	
	private Date createAt;
	
	private Date startDate;
	
	private Date endDate;
	
	private Integer adults;
	
	private Integer children; 
	
	private String idRoomType;
	
	private String nameRoomType;
	
	private String description;
	
	private String logo;
	
	private Double price;
}
