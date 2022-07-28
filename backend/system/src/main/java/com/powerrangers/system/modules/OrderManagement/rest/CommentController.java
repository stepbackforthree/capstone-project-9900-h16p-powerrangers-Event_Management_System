package com.powerrangers.system.modules.OrderManagement.rest;

import com.alibaba.fastjson.JSON;
import com.powerrangers.system.modules.OrderManagement.service.CommentService;
import com.powerrangers.system.modules.OrderManagement.service.dto.CommentDTO;
import com.powerrangers.system.modules.UserAccess.domain.User;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("comment")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CommentController {

    @Autowired
    CommentService commentService;

    @Autowired
    StringRedisTemplate redisTemplate;

    @ApiOperation(value = "add comment for specific event by current user")
    @PostMapping("/addComment")
    public ResponseEntity<Object> addComment(@RequestHeader("Authorization") String token, @RequestBody CommentDTO commentDTO) {
        return commentService.addComment(token, commentDTO);
    }

    @ApiOperation(value = "edit comment for specific event by current user")
    @PostMapping("/editComment")
    public ResponseEntity<Object> editComment(@RequestHeader("Authorization") String token, @RequestBody CommentDTO commentDTO) {
        return commentService.editComment(token, commentDTO);
    }

    @ApiOperation(value = "delete comment for specific event for current user")
    @PostMapping("/deleteComment")
    public ResponseEntity<Object> deleteComment(@RequestHeader("Authorization") String token, @RequestBody CommentDTO commentDTO) {
        return commentService.deleteComment(token, commentDTO);
    }

    @ApiOperation(value = "get all comments for specific event")
    @PostMapping("getComments")
    public ResponseEntity<Object> getComments(@RequestHeader("Authorization") String token, @RequestBody CommentDTO commentDTO) {
        User currUser = JSON.parseObject(redisTemplate.opsForValue().get("token_" + token), User.class);

        Map<String, String> responseBody = new HashMap<>();

        if (currUser == null) {
            responseBody.put("error", "token is invalid");
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(commentService.getComments(commentDTO), HttpStatus.OK);
    }
}
