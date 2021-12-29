package com.pihotel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pihotel.entity.CommentEntity;
import com.pihotel.repository.ICommentRepo;
import com.pihotel.service.ICommentServ;

@Service
public class CommentServ implements ICommentServ{
	
	@Autowired
	private ICommentRepo commentRepo;

//	---------------------------------------SELECT---------------------------------------
	
	@Override
	public CommentEntity findOneById(String id) {
		// TODO Auto-generated method stub
		return commentRepo.findOneById(id);
	}

	@Override
	public List<CommentEntity> findAll() {
		// TODO Auto-generated method stub
		return commentRepo.findAll();
	}

//	---------------------------------------INSERT---------------------------------------
	
	@Override
	public CommentEntity save(CommentEntity comment) {
		// TODO Auto-generated method stub
		if (!commentRepo.existsById(comment.getId())) {
			return commentRepo.save(comment);
		}
		return null;
	}

//	---------------------------------------UPDATE---------------------------------------
	
	@Override
	public CommentEntity update(CommentEntity comment) {
		// TODO Auto-generated method stub
		if (commentRepo.existsById(comment.getId())) {
			return commentRepo.save(comment);
		}
		return null;
	}

//	---------------------------------------DELETE---------------------------------------
	
	@Override
	public void delete(String[] ids) {
		// TODO Auto-generated method stub
		for (String id : ids) {
			commentRepo.deleteById(id);
		}
	}

	@Override
	public void deleteById(String id) {
		// TODO Auto-generated method stub
		commentRepo.deleteById(id);
	}

}
