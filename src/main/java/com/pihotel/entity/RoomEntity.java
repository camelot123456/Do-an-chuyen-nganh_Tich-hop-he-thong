package com.pihotel.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
	
	@Column(name = "[AVATAR]", columnDefinition = "nvarchar(255)")
	private String avatar;
	
	@Column(name = "[DESCRIPTION]", columnDefinition = "ntext")
	private String description;
	
	@Column(name = "[QUANTITY]", columnDefinition = "tinyint default 0")
	private Integer quantity;
	
	@Column(name = "[PRICE]", columnDefinition = "float default 0")
	private Double price;
	
	@Column(name = "[FLOOR]", columnDefinition = "tinyint default 1")
	private Integer floor;
	
	@Column(name = "[START_TIME]", columnDefinition = "datetime")
	private Date startTime;
	
	@Column(name = "[END_DATE]", columnDefinition = "datetime")
	private Date endTime;
	
	@Column(name = "[ADULTS]", columnDefinition = "tinyint default 0")
	private Integer adults;
	
	@Column(name = "[CHILDREN]", columnDefinition = "tinyint default 0")
	private Integer children;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_ROOM_TYPE")
	@JsonBackReference
	private RoomTypeEntity room;
}
