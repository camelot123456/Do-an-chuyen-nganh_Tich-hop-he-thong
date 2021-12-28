package com.pihotel.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
@Table(name="[INVOICE]")
public class InvoiceEntity extends AbstractEntity{
	
	@Column(name = "[START_DATE]", columnDefinition = "datetime")
	private Date startDate;
	
	@Column(name = "[END_DATE]", columnDefinition = "datetime")
	private Date endDate;
	
	@Column(name = "[ADULTS]", columnDefinition = "int default 0")
	private Integer adults;
	
	@Column(name = "[CHILDREN]", columnDefinition = "int default 0")
	private Integer children; 
	
	@Column(name = "[VERIFY_ROOM]", columnDefinition = "varchar(64)")
	private String verifyRoom;
		
	@OneToMany(mappedBy = "invoice")
	@JsonManagedReference
	private List<InvoiceServiceEntity> invoicesServices;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "[INVOICE_ROOM]",
			joinColumns = @JoinColumn(
					name = "[ID_INVOICE]",
					referencedColumnName = "[ID]"),
			inverseJoinColumns = @JoinColumn(
					name = "[ID_ROOM]",
					referencedColumnName = "[ID]")
		)
	@JsonIgnoreProperties("invoices")
	private List<RoomEntity> rooms;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "[ID_ACCOUNT]")
	private AccountEntity account;
	
	@Transient
	private String idRoomType;
	
	@Transient
	private String idAccount;
	
	@Transient
	private String phoneNum;
	
	@Transient
	private Double totalPriceAll;
	
	@Transient
	private Double totalPriceIncurred;
	
}

//	@ManyToMany(fetch = FetchType.LAZY)
//	@JoinTable(
//			name = "INVOICE_SERVICE",
//			joinColumns = @JoinColumn(name = "[ID_INVOICE]", referencedColumnName = "[ID]"),
//			inverseJoinColumns = @JoinColumn(name = "[ID_SERVICE]", referencedColumnName = "[ID]")
//	)
//	@JsonIgnoreProperties("invoices")
//	private List<ServiceEntity> services;