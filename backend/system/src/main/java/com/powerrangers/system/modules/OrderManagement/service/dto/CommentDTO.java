package com.powerrangers.system.modules.OrderManagement.service.dto;

import com.powerrangers.system.modules.OrderManagement.domain.Comment;
import lombok.Data;

@Data
public class CommentDTO extends Comment {

    private String eventName;

    private String hostName;
}