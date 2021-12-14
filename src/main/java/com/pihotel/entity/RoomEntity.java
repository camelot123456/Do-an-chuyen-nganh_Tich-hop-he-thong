package com.pihotel.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pihotel.entity.enums.ERoomState;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
@Table(name = "[ROOM]")
public class RoomEntity extends AbstractEntity{

	@Column(name = "[NAME]", columnDefinition = "nvarchar(60)")
	private String name;

	@Enumerated(EnumType.STRING)
	@Column(name = "[ROOM_STATE]", columnDefinition = "varchar(16) default 'EMPTY'")
	private ERoomState roomState;
	
	@Column(name = "[DESCRIPTION]", columnDefinition = "nvarchar(max)")
	private String description;
	
	@Column(name = "[CUSTOMERS_NUM]", columnDefinition = "int default 0")
	private Integer customersNum;
	
	@Column(name = "[AREA]", columnDefinition = "int default 0")
	private Integer area;
	
	@Column(name = "[PRICE_INCURRED]", columnDefinition = "float default 0")
	private Double priceIncurred;
	
	@Column(name = "[FLOOR]", columnDefinition = "int default 1")
	private Integer floor;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_ROOM_TYPE")
	@JsonBackReference
	private RoomTypeEntity roomType;
	
	@ManyToMany(mappedBy = "rooms")
	@JsonIgnoreProperties("rooms")
	private List<InvoiceEntity> invoices;
}
