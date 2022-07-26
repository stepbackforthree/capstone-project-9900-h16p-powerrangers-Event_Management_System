package com.powerrangers.system.modules.EventManagement.domain;

import com.powerrangers.common.base.BaseEntity;
import lombok.Data;

@Data
public class Ticket extends BaseEntity {

    private String hostName;

    private String eventName;

    private String ticketType;

    private Integer ticketAmount;

    private Double ticketPrice;
}
