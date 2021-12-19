package com.pihotel.entity.custom;

import lombok.Data;

@Data
public class BillCustom {

	private String idInvoice;
	
	private Integer quantityService;
	
	private Double totalPriceService;
	
	private Double serviceTax5Percent;
	
	private Integer quantityRoom;
	
	private Integer night;
	
	private Double priceRoomType;
	
	private Double totalPriceIncurred;
	
	private Double totalPriceRoom;
	
	private Double totalPrice;
	
	private Double VATTax10Percent;
	
	private Double totalAllPrice;
	
}
