package com.pihotel.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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

	@Column(name = "[ROOM_STATE]", columnDefinition = "bit default 0")
	private Boolean roomState;
	
	@Column(name = "[AVATAR]", columnDefinition = "nvarchar(255)")
	private String avatar;
	
	@Column(name = "[DESCRIPTION]", columnDefinition = "ntext")
	private String description;
	
	@Column(name = "[QUANTITY]", columnDefinition = "int default 0")
	private Integer quantity;
	
	@Column(name = "[PRICE]", columnDefinition = "float default 0")
	private Double price;
	
	@Column(name = "[FLOOR]", columnDefinition = "int default 1")
	private Integer floor;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_ROOM_TYPE")
	private RoomTypeEntity room;
}
