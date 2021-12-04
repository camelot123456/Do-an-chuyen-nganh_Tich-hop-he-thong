package com.pihotel.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
@Table(name="[ROLE]")
public class RoleEntity extends AbstractEntity{

	@Column(name = "[NAME]", columnDefinition = "nvarchar(128) not null unique")
	private String name;
	
	@Column(name = "[CODE]", columnDefinition = "varchar(128) not null unique")
	private String code;
	
	@Column(name = "[DESCRIPTION]", columnDefinition = "ntext")
	private String description;
	
	@Column(name = "[LOGO]", columnDefinition = "ntext")
	private String logo;
	
	@ManyToMany(mappedBy = "roles")
	@JsonIgnoreProperties("roles")
	private List<AccountEntity> accounts;
	
	@Transient
	public String getLogo() {
		if (logo.startsWith("http://") || logo.startsWith("https://")) {
			return logo;
		} 
		return "/img/role/" + logo;
	}
	
}