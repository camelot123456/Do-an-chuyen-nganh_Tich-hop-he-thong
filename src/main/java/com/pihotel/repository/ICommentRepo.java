package com.pihotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pihotel.entity.CommentEntity;

public interface ICommentRepo extends JpaRepository<CommentEntity, String>{

	public CommentEntity findOneById(String id);
	
}

/*
 * select distinct c.* from invoice i inner join invoice_room ir on i.id =
 * ir.id_invoice inner join account a on a.id = i.id_account inner join comment
 * c on a.id = c.id_account inner join room r on r.id = c.id_room where i.id =
 * 'escwYA58f07f' and i.[enabled] = 1 and c.commented = 0
 */