package com.pihotel.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="[SERVICE]")
public class ServiceEntity extends AbstractEntity{

	@Column(name = "[NAME]", columnDefinition = "nvarchar(64)")
	private String name;
	
	@Column(name = "[IMAGE]", columnDefinition = "ntext")
	private String image;
	
	@Column(name = "[DESCRIPTION]", columnDefinition = "ntext")
	private String description;
	
	@Column(name = "[PRICE]", columnDefinition = "float default 0")
	private Double price;
	
	@Column(name = "[QUANTITY]", columnDefinition = "tinyint default 0")
	private Integer quantity;
	
	@ManyToMany(mappedBy = "services")
	private List<InvoiceEntity> invoices;
}
