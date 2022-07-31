package com.powerrangers.system.modules.OrderManagement.service.impl;

import com.alibaba.fastjson.JSON;
import com.powerrangers.system.modules.EventManagement.dao.EventMapper;
import com.powerrangers.system.modules.EventManagement.service.dto.EventDTO;
import com.powerrangers.system.modules.OrderManagement.dao.CommentMapper;
import com.powerrangers.system.modules.OrderManagement.domain.Comment;
import com.powerrangers.system.modules.OrderManagement.service.CommentService;
import com.powerrangers.system.modules.OrderManagement.service.dto.CommentDTO;
import com.powerrangers.system.modules.UserAccess.dao.UserMapper;
import com.powerrangers.system.modules.UserAccess.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    CommentMapper commentMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    EventMapper eventMapper;

    @Value("${StarLevel.baseLevel}")
    private Float baseLevel;

    @Value("${StarLevel.baseAmount}")
    private Integer baseAmount;

    Map<String, String> responseBody = new HashMap<>();

    public void updateStarLevel(CommentDTO commentDTO) {
        List<Comment> comments = getComments(commentDTO);

        if (comments != null && comments.size() != 0) {
            float sum = 0f;

            for (Comment comment : comments) {
                sum += comment.getStarLevel();
            }

            sum += baseLevel * baseAmount;

            float avg = sum / (comments.size() + baseAmount);
            float exact = (float) Math.floor(avg);
            float remain = (float) (avg - exact);
            if (remain >= 0.5) {
                exact += 0.5;
            }

            EventDTO eventDTO = new EventDTO();
            eventDTO.setHostId(userMapper.getUserIdByUserName(commentDTO.getHostName()));
            eventDTO.setEventName(commentDTO.getEventName());
            eventDTO.setStarLevel(exact);

            eventMapper.updateStarLevel(eventDTO);
        }
    }

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

        updateStarLevel(commentDTO);

        responseBody.put("msg", "add comment succeed!");
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> editComment(String token, CommentDTO commentDTO) {

        User currUser = JSON.parseObject(redisTemplate.opsForValue().get("token_" + token), User.class);
        responseBody.clear();

        if (currUser == null) {
            responseBody.put("error", "token is invalid!");
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }

        commentDTO.setCustomerId(currUser.getId());
        commentMapper.editComment(commentDTO);

        updateStarLevel(commentDTO);

        responseBody.put("msg", "edit comment succeed!");
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> deleteComment(String token, CommentDTO commentDTO) {

        User currUser = JSON.parseObject(redisTemplate.opsForValue().get("token_" + token), User.class);
        responseBody.clear();

        if (currUser == null) {
            responseBody.put("error", "token is invalid!");
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }

        if (commentMapper.getCustomerComment(commentDTO).size() > 0) {
            responseBody.put("error", "customer has already left a comment!");
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }

        commentDTO.setCustomerId(currUser.getId());
        commentMapper.deleteComment(commentDTO);

        updateStarLevel(commentDTO);

        responseBody.put("msg", "delete comment succeed!");
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @Override
    public List<Comment> getComments(CommentDTO commentDTO) {

        return commentMapper.getComments(commentDTO);
    }
}