package com.powerrangers.system.modules.OrderManagement.domain;


import com.powerrangers.common.base.BaseEntity;
import lombok.Data;

@Data
public class Comment extends BaseEntity {

    private Integer eventId;

    private Integer customerId;

    private String customerName;

    private String comment;

    private Float starLevel;
}
