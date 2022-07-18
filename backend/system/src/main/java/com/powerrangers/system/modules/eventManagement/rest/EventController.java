package com.powerrangers.system.modules.eventManagement.rest;

import com.powerrangers.system.modules.eventManagement.service.EventService;
import com.powerrangers.system.modules.eventManagement.service.dto.SmallEventDTO;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(description = "Interface of event management")
@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class EventController {

    @Autowired
    private final EventService eventService;

    @PostMapping(value = "createEvent")
    public ResponseEntity<Object> createEvent(@RequestHeader("Authorization") String token,
                                              @RequestBody SmallEventDTO smallEventDTO) {
        return eventService.createEvent(token, smallEventDTO);
    }

    @PostMapping(value = "updateEventName")
    public ResponseEntity<Object> updateEventName(@RequestHeader("Authorization") String token,
                                                 @RequestParam String eventName, @RequestParam String newName) {
        return new ResponseEntity<>(eventService.updateEventName(token, eventName, newName), HttpStatus.OK);
    }



}
