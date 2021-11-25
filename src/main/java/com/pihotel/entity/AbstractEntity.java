package com.pihotel.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class AbstractEntity {

	@Id
	@Column(name = "[ID]", columnDefinition = "varchar(255)")
	private String id;
	
	@Column(name = "[CREATE_AT]", columnDefinition = "datetime")
	private Date createAt;
	
	@Column(name = "[MODIFIED_BY]", columnDefinition = "nvarchar(60)")
	private String modifiedBy;
	
	@Column(name = "[MODIFIED_AT]", columnDefinition = "datetime")
	private Date modifiedAt;
	
	@Column(name = "[CREATE_BY]", columnDefinition = "nvarchar(60)")
	private String createBy;
	
	@Column(name = "[DELETE_BY]", columnDefinition = "nvarchar(60)")
	private String deleteBy;
	
	@Column(name = "[DELETE_AT]", columnDefinition = "datetime")
	private Date deleteAt;
	
	@Column(name = "[DELETED]", columnDefinition = "bit default 0")
	private Boolean deleted;
	
	@Column(name = "[STATE]", columnDefinition = "bit default 1")
	private Boolean state;
	
	@Transient
	private String[] ids;
}
