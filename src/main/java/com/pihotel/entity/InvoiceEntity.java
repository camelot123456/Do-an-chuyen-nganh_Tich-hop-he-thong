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
import javax.persistence.Table;

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

	@Column(name = "[START_TIME]", columnDefinition = "datetime")
	private Date startTime;
	
	@Column(name = "[END_DATE]", columnDefinition = "datetime")
	private Date endTime;
	
	@Column(name = "[RENTAL_HOURS]", columnDefinition = "int default 0")
	private Integer rentalHours;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "INVOICE_SERVICE",
			joinColumns = @JoinColumn(name = "ID_INVOICE"),
			inverseJoinColumns = @JoinColumn(name = "ID_SERVICE")
	)
	private List<ServiceEntity> services;
	
	@ManyToMany(mappedBy = "invoices")
	private List<RoomTypeEntity> roomTypes;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "[ID_ACCOUNT]")
	private AccountEntity account;
	
}
