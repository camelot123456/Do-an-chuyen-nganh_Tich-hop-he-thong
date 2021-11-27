package com.pihotel.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.pihotel.entity.enums.EAuthenticationProvider;
import com.pihotel.entity.enums.EMessageType;

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
@Table(name = "[ACCOUNT]")
public class AccountEntity extends AbstractEntity {

	@Column(name = "[NAME]", columnDefinition = "nvarchar(60) not null")
	private String name;

	@Column(name = "[GENDER]", columnDefinition = "bit default 1")
	private Boolean gender;

	@Column(name = "[BIRTHDAY]", columnDefinition = "datetime")
	private Date birthday;

	@Column(name = "[PHONE_NUM]", columnDefinition = "varchar(15)")
	private String phoneNum;

	@Column(name = "[EMAIL]", columnDefinition = "varchar(255) not null")
	private String email;

	@Column(name = "[AVATAR]", columnDefinition = "ntext")
	private String avatar;

	@Column(name = "[USERNAME]", columnDefinition = "varchar(255) not null unique")
	private String username;

	@Column(name = "[PASSWORD]", columnDefinition = "varchar(255) not null")
	private String password;

	@Enumerated(EnumType.STRING)
	@Column(name = "[AUTH_PROVIDER]", columnDefinition = "varchar(15)")
	private EAuthenticationProvider authProvider;

	@Transient
	private EMessageType type;
	
	@Transient
	private String content;
	
	@Transient
	private String sender;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "ACCOUNT_ROLE", 
			joinColumns = @JoinColumn(
					name = "[ID]", 
					referencedColumnName = "[ID]"), 
			inverseJoinColumns = @JoinColumn(
					name = "ID_ROLE", 
					referencedColumnName = "[ID]")
			)
	private List<RoleEntity> roles;
	
	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	private List<InvoiceEntity> invoices;
	
}
