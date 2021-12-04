package com.pihotel.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

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
@EntityListeners(AuditingEntityListener.class)//Lắng nghe sự kiện auditing
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id")//Xử lý lỗi đệ quy vô hạn trong jackson khi đối tượng có thuộc tính tham chiếu đến đối tượng khác
@JsonIgnoreProperties({"hibernateLazyInitializer"})
//@JsonIgnoreProperties(ignoreUnknown = true)
//@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class AbstractEntity {
	

	@Id
	@Column(name = "[ID]", columnDefinition = "varchar(64)")
	private String id;
	
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "[CREATE_AT]", columnDefinition = "datetime", updatable = false)//cờ updatable giúp cập nhập bản khi thay vì bị ghi đè 
	private Date createAt;
	
	@CreatedBy
	@Column(name = "[CREATE_BY]", columnDefinition = "nvarchar(60)", updatable = false)
	private String createBy;
	
	@LastModifiedBy
	@Column(name = "[MODIFIED_BY]", columnDefinition = "nvarchar(60)")
	private String modifiedBy;
	
	@LastModifiedDate
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "[MODIFIED_AT]", columnDefinition = "datetime")
	private Date modifiedAt;
	
	@Column(name = "[DELETE_BY]", columnDefinition = "nvarchar(60)")
	private String deleteBy;
	
	@Column(name = "[DELETE_AT]", columnDefinition = "datetime")
	private Date deleteAt;
	
	@Column(name = "[DELETED]", columnDefinition = "bit default 0")
	private Boolean deleted;
	
	@Column(name = "[ENABLED]", columnDefinition = "bit default 0")
	private Boolean enabled;
	
	@Transient //Transient giúp thêm thuộc tính cho đối tượng nhưng sẽ không thêm thuộc tính vào table trong cơ sở sữ liệu
	private String[] ids;
	
	@Transient
	private String fileName;
}