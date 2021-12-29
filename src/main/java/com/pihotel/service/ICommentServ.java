package com.pihotel.service;

import java.util.List;

import com.pihotel.entity.CommentEntity;

public interface ICommentServ {

	public CommentEntity findOneById(String id);
	
	public List<CommentEntity> findAll();
	
	public CommentEntity save(CommentEntity comment);
	
	public CommentEntity update(CommentEntity comment);
	
	public void delete(String[] ids);
	
	public void deleteById(String id);
	
}
