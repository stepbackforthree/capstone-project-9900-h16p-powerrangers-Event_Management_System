package com.powerrangers.system.modules.eventManagement.rest;

import com.powerrangers.system.modules.eventManagement.service.EventTicketService;
import com.powerrangers.system.modules.eventManagement.service.dto.TicketDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(description = "interface of event ticket")
@RestController
@RequestMapping(value = "/tickets")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class EventTicketController {

    @Autowired
    private final EventTicketService eventTicketService;

    @ApiOperation(value = "Add ticket type and related information for specific event")
    @PostMapping(value = "addTicketType")
    public ResponseEntity<Object> addTicketType(@RequestHeader("Authorization") String token, @RequestBody TicketDTO ticketDTO) {
        return eventTicketService.addTicketType(token, ticketDTO);
    }

    @ApiOperation(value = "get all ticket type for specific event")
    @PostMapping(value = "getTicketType")
    public ResponseEntity<Object> getTicketType(@RequestHeader("Authorization") String token, @RequestBody TicketDTO ticketDTO) {
        return eventTicketService.getTicketType(token, ticketDTO);
    }
}
