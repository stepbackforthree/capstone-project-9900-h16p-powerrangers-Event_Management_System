package com.powerrangers.system.modules.eventManagement.service.dto;

import com.powerrangers.system.modules.eventManagement.domain.Event;
import lombok.Data;

@Data
public class EventDTO extends Event {

    private Integer ticketType;

    private Integer ticketAmount;

    private Double ticketPrice;
}
