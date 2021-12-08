package com.pihotel.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
	
	@Column(name = "[DESCRIPTION]", columnDefinition = "ntext")
	private String description;
	
	@Column(name = "[LOGO]", columnDefinition = "ntext")
	private String logo;
	
	@Column(name = "[PRICE]", columnDefinition = "float default 0")
	private Double price;
	
	@OneToMany(mappedBy = "room")
	@JsonManagedReference
	private List<RoomEntity> rooms;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
		name = "[INVOICE_ROOMTYPE]",
		joinColumns = @JoinColumn(
				name = "[ID_INVOICE]",
				referencedColumnName = "[ID]"),
		inverseJoinColumns = @JoinColumn(
				name = "[ID_ROOMTYPE]",
				referencedColumnName = "[ID]")
	)
	private List<InvoiceEntity> invoices;
}
