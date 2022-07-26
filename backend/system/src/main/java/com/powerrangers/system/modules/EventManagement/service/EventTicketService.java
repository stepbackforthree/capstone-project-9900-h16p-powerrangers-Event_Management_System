package com.powerrangers.system.modules.EventManagement.service;

import com.powerrangers.system.modules.EventManagement.service.dto.TicketDTO;
import org.springframework.http.ResponseEntity;

public interface EventTicketService {
    ResponseEntity<Object> addTicketType(String token, TicketDTO ticketDTO);

    ResponseEntity<Object> getTicketType(String token, TicketDTO ticketDTO);

    ResponseEntity<Object> updateTicketAmount(String token, TicketDTO ticketDTO);
}
