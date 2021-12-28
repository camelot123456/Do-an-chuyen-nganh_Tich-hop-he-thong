package com.pihotel.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "[INVOICE_SERVICE]")
public class InvoiceServiceEntity extends AbstractEntity {
	
	@ManyToOne()
	@JoinColumn(name = "id_invoice")
	@JsonBackReference
	private InvoiceEntity invoice;
	
	@ManyToOne
	@JoinColumn(name = "id_service")
	@JsonBackReference
	private ServiceEntity service;
	
	@Column(name = "[QUANTITY]", columnDefinition = "int default 0")
	private Integer quantity;
	
	@Transient
	private List<ServiceEntity> services;
}
