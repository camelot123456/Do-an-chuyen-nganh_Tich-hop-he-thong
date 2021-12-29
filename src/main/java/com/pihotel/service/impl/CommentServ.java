package com.pihotel.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pihotel.entity.CommentEntity;
import com.pihotel.repository.IAccountRepo;
import com.pihotel.repository.ICommentRepo;
import com.pihotel.repository.IRoomRepo;
import com.pihotel.service.ICommentServ;

@Service
public class CommentServ implements ICommentServ{
	
	@Autowired
	private ICommentRepo commentRepo;
	
	@Autowired
	private IAccountRepo accountRepo;
	
	@Autowired
	private IRoomRepo roomRepo;

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
	
	@Override
	public List<CommentEntity> findAllByIdInvoice(String idInvoice) {
		List<Object[]> comments = commentRepo.findAllByIdInvoice(idInvoice);
		List<CommentEntity> commentsNew = null;
		if (comments.size() > 0) {
			commentsNew = new ArrayList<CommentEntity>();
			for (Object[] record : comments) {
				CommentEntity commentNew = new CommentEntity();
				commentNew.setId((String) record[0]);
				commentNew.setCreateAt((Date) record[1]);
				commentNew.setEnabled((Boolean) record[6]);
				commentNew.setModifiedAt((Date) record[7]);
				commentNew.setComment((String) record[9]);
				commentNew.setCommented((Boolean) record[13]);
				commentNew.setRate((int) record[10]);
				commentNew.setAccount(accountRepo.findOneById((String) record[11]));
				commentNew.setRoom(roomRepo.findOneById((String) record[12]));
				commentNew.setIdAccount((String) record[11]);
				commentNew.setIdRoom((String) record[12]);
				commentsNew.add(commentNew);
			}
		}
		
		return commentsNew;
		
	}
	
	@Override
	public List<CommentEntity> findAllByIdRoomTypeAndVerify(String idRoomType) {
		return commentRepo.findAllByIdRoomTypeAndVerify(idRoomType);
	}
	
	@Override
	public List<CommentEntity> findAllByIdRoomType(String idRoomType) {
		return commentRepo.findAllByIdRoomType(idRoomType);
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
			CommentEntity commentNew = this.findOneById(comment.getId());
			commentNew.setModifiedAt(new Date());
			commentNew.setCommented(Boolean.TRUE);
			commentNew.setComment(comment.getComment());
			commentNew.setRate(comment.getRate());
			return commentRepo.save(commentNew);
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
