package com.powerrangers.system.modules.OrderManagement.service;

import com.powerrangers.system.modules.OrderManagement.service.dto.CommentDTO;
import org.springframework.http.ResponseEntity;

public interface CommentService {

    ResponseEntity<Object> addComment(String token, CommentDTO commentDTO);

    ResponseEntity<Object> getComments(String token, CommentDTO commentDTO);
}