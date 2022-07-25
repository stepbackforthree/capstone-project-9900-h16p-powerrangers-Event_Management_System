package com.powerrangers.system.modules.eventManagement.domain;

import lombok.Data;

@Data
public class Ticket {

    private String hostName;

    private String eventName;

    private String ticketType;

    private Integer ticketAmount;

    private Double ticketPrice;
}
