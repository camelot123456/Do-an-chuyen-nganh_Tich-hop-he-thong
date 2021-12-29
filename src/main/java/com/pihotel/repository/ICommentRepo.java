package com.pihotel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pihotel.entity.CommentEntity;

public interface ICommentRepo extends JpaRepository<CommentEntity, String>{

	public CommentEntity findOneById(String id);
	
	@Query(value = "select distinct c.* \n" + 
			"from invoice i inner join invoice_room ir \n" + 
			"on i.id = ir.id_invoice inner join account a \n" + 
			"on a.id = i.id_account inner join comment c \n" + 
			"on a.id = c.id_account inner join room r \n" + 
			"on r.id = c.id_room \n" + 
			"where i.id = ?1 and i.[enabled] = 1  and i.verify_comment = c.verify_comment", 
			nativeQuery = true)
	public List<Object[]> findAllByIdInvoice(String idInvoice);
	
	@Query(value = "select distinct c.*\n" + 
			"from invoice i inner join invoice_room ir \n" + 
			"on i.id = ir.id_invoice inner join account a \n" + 
			"on a.id = i.id_account inner join comment c \n" + 
			"on a.id = c.id_account inner join room r \n" + 
			"on r.id = c.id_room inner join room_type rt \n" + 
			"on rt.id = r.id_room_type \n" + 
			"where rt.id = ?1 and i.[enabled] = 1 and c.commented = 1  and i.verify_comment = c.verify_comment", 
			nativeQuery = true)
	public List<CommentEntity> findAllByIdRoomTypeAndVerify(String idRoomType);
	
	@Query(value = "select distinct c.* \n" + 
			"from account a inner join comment c \n" + 
			"on a.id = c.id_account inner join room r \n" + 
			"on r.id = c.id_room inner join room_type rt \n" + 
			"on rt.id = r.id_room_type \n" + 
			"where rt.id = ?1 and c.commented = 1", 
			nativeQuery = true)
	public List<CommentEntity> findAllByIdRoomType(String idRoomType);
	
}

/*
 * select distinct c.* from invoice i inner join invoice_room ir on i.id =
 * ir.id_invoice inner join account a on a.id = i.id_account inner join comment
 * c on a.id = c.id_account inner join room r on r.id = c.id_room where i.id =
 * 'escwYA58f07f' and i.[enabled] = 1 and c.commented = 0
 */