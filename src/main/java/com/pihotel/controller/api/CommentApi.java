package com.pihotel.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pihotel.entity.CommentEntity;
import com.pihotel.service.ICommentServ;

@RestController
@RequestMapping(value = "/api")
public class CommentApi {

	@Autowired
	private ICommentServ commentServ;

	@GetMapping(value = "/comments")
	public List<CommentEntity> doShowListComment() {
		return commentServ.findAll();
	}
	
	@GetMapping(value = "/comment")
	public List<CommentEntity> doShowListCommentByIdInvoice(@Param("idInvoice") String idInvoice) {
		List<CommentEntity> comments = commentServ.findAllByIdInvoice(idInvoice);
		return comments;
	}

}
