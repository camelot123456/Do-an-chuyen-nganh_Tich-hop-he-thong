package com.pihotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pihotel.entity.CommentEntity;

public interface ICommentRepo extends JpaRepository<CommentEntity, String>{

	public CommentEntity findOneById(String id);
	
}
