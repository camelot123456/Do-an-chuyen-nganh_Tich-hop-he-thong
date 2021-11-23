package com.pihotel.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name="[ACCOUNT]")
public class AccountEntity extends AbstractEntity{
	
	@Column(name = "[NAME]", columnDefinition = "nvarchar(60) not null")
	private String name;
	
	@Column(name = "GENDER", columnDefinition = "bit default 1")
	private Boolean gender;
	
	@Column(name = "[BIRTHDAY]", columnDefinition = "datetime")
	private Date birthday;
	
	@Column(name = "[PHONE_NUM]", columnDefinition = "varchar(15)")
	private String phoneNum;
	
	@Column(name = "[EMAIL]", columnDefinition = "varchar(255) not null")
	private String email;
	
	@Column(name = "[AVATAR]", columnDefinition = "ntext default ")
	private String avatar;
	
	@Column(name = "[USERNAME]", columnDefinition = "varchar(255) not null unique")
	private String username;
	
	@Column(name = "[PASSWORD]", columnDefinition = "varchar(255) not null")
	private String password;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "[ACCOUNT_ROLE]",
			joinColumns = @JoinColumn(name = "ID_ACCOUNT"),
			inverseJoinColumns = @JoinColumn(name = "ID_ROLE")
	)
	private List<RoleEntity> roles;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "[INVOICE]")
	private List<InvoiceEntity> invoices;
	
	@Transient
	private Integer age;
}
