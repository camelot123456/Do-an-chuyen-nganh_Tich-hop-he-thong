package com.pihotel.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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
@Builder
@ToString
@Entity
@Table(name="[ROOM_TYPE]")
public class RoomTypeEntity extends AbstractEntity{

	@Column(name = "[NAME]", columnDefinition = "nvarchar(100)")
	private String name;
	
	@Column(name = "[DESCRIPTION]", columnDefinition = "nvarchar(max)")
	private String description;
	
	@Column(name = "[LOGO]", columnDefinition = "nvarchar(max)")
	private String logo;
	
	@Column(name = "[PRICE]", columnDefinition = "float default 0")
	private Double price;
	
	@OneToMany(mappedBy = "roomType")
	@JsonManagedReference("room-roomtype")
	private List<RoomEntity> rooms;
	
	@Transient
	public String getLogo() {
		if (logo.startsWith("http://") || logo.startsWith("https://")) {
			return logo;
		}
		return "/img/roomtype/" + logo;
	}
	
	@Transient
	private Integer totalRoom;
	
}
