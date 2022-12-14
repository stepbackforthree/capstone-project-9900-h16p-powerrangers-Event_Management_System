package com.powerrangers.system.modules.OrderManagement.dao;

import com.powerrangers.system.modules.OrderManagement.domain.Comment;
import com.powerrangers.system.modules.OrderManagement.service.dto.CommentDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {

    void addComment(CommentDTO commentDTO);

    void editComment(CommentDTO commentDTO);

    void deleteComment(CommentDTO commentDTO);

    List<Comment> getComments(CommentDTO commentDTO);

    List<Comment> getCustomerComment(CommentDTO commentDTO);
}