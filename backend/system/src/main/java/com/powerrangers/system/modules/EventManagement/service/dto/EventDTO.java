package com.powerrangers.system.modules.EventManagement.service.dto;

import com.powerrangers.system.modules.EventManagement.domain.Event;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class EventDTO extends Event {

    private Integer ticketType;

    private Integer ticketAmount;

    private BigDecimal ticketPrice;
}
