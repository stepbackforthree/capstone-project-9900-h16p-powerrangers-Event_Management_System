package com.powerrangers.system.modules.OrderManagement.service;

import com.powerrangers.system.modules.OrderManagement.domain.Comment;
import com.powerrangers.system.modules.OrderManagement.service.dto.CommentDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CommentService {

    void updateStarLevel(CommentDTO commentDTO);

    ResponseEntity<Object> addComment(String token, CommentDTO commentDTO);

    ResponseEntity<Object> editComment(String token, CommentDTO commentDTO);

    ResponseEntity<Object> deleteComment(String token, CommentDTO commentDTO);

    List<Comment> getComments(CommentDTO commentDTO);
}