package com.powerrangers.system.modules.OrderManagement.service.impl;

import com.alibaba.fastjson.JSON;
import com.powerrangers.system.modules.OrderManagement.dao.CommentMapper;
import com.powerrangers.system.modules.OrderManagement.service.CommentService;
import com.powerrangers.system.modules.OrderManagement.service.dto.CommentDTO;
import com.powerrangers.system.modules.UserAccess.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    CommentMapper commentMapper;

    Map<String, String> responseBody = new HashMap<>();

    @Override
    public ResponseEntity<Object> addComment(String token, CommentDTO commentDTO) {

        User currUser = JSON.parseObject(redisTemplate.opsForValue().get("token_" + token), User.class);
        responseBody.clear();

        if (currUser == null) {
            responseBody.put("error", "token is invalid!");
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }

        commentDTO.setCustomerId(currUser.getId());
        commentMapper.addComment(commentDTO);

        responseBody.put("msg", "add comment succeed!");
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> getComments(String token, CommentDTO commentDTO) {

        User currUser = JSON.parseObject(redisTemplate.opsForValue().get("token_" + token), User.class);
        responseBody.clear();

        if (currUser == null) {
            responseBody.put("error", "token is invalid");
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(commentMapper.getComments(commentDTO), HttpStatus.OK);
    }
}