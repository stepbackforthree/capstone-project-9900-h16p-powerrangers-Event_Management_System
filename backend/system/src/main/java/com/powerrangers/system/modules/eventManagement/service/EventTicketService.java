package com.powerrangers.system.modules.eventManagement.service;

import com.powerrangers.system.modules.eventManagement.service.dto.TicketDTO;
import org.springframework.http.ResponseEntity;

public interface EventTicketService {
    ResponseEntity<Object> addTicketType(String token, TicketDTO ticketDTO);
}
