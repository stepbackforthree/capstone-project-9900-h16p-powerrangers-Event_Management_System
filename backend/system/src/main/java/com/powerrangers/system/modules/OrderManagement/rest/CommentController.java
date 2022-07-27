package com.powerrangers.system.modules.OrderManagement.rest;

import com.powerrangers.system.modules.OrderManagement.service.CommentService;
import com.powerrangers.system.modules.OrderManagement.service.dto.CommentDTO;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("comment")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CommentController {

    @Autowired
    CommentService commentService;

    @ApiOperation(value = "add comment for specific event by current user")
    @PostMapping("/addComment")
    public ResponseEntity<Object> addComment(@RequestHeader("Authorization") String token, @RequestBody CommentDTO commentDTO) {
        return commentService.addComment(token, commentDTO);
    }

    @ApiOperation(value = "get all comments for specific event")
    @PostMapping("getComments")
    public ResponseEntity<Object> getComments(@RequestHeader("Authorization") String token, @RequestBody CommentDTO commentDTO) {
        return commentService.getComments(token, commentDTO);
    }
}
