package com.pihotel.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

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
	
	@Column(name = "[IMAGE]", columnDefinition = "nvarchar(max)")
	private String image;
	
	@Column(name = "[DESCRIPTION]", columnDefinition = "nvarchar(max)")
	private String description;
	
	@Column(name = "[PRICE]", columnDefinition = "float default 0")
	private Double price;
	
	@Column(name = "[QUANTITY]", columnDefinition = "int default 0")
	private Integer quantity;
		
	@OneToMany(mappedBy = "service")
	private Set<InvoiceServiceEntity> invoicesServices;
	
	@Transient
	public String getImage() {
		if (image.startsWith("http://") || image.startsWith("https://")) {
			return image;
		}
		return "/img/service/" + image;
	}

}
//	@ManyToMany(mappedBy = "services")
//	@JsonIgnoreProperties("services")
//	private List<InvoiceEntity> invoices;
