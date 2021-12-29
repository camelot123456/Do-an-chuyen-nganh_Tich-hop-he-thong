package com.pihotel.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "[COMMENT]")
public class CommentEntity extends AbstractEntity{

	@Column(name = "[COMMENT]", columnDefinition = "nvarchar(max)")
	private String comment;
	
	@Column(name = "[RATE]", columnDefinition = "int")
	private int rate;
	
	@Column(name = "[COMMENTED]", columnDefinition = "bit default 0")
	private Boolean commented;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "[ID_ACCOUNT]")
	@JsonBackReference("comment-account")
	private AccountEntity account;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "[ID_ROOM]")
	@JsonBackReference("comment-room")
	private RoomEntity room;
	
	@Transient
	private String idAccount;
	
	@Transient
	private String idRoom;
	
	@Column(name = "verify_comment", columnDefinition = "varchar(64)")
	private String verifyComment;
	
}
