package com.powerrangers.system.modules.eventManagement.rest;

import com.powerrangers.system.modules.eventManagement.service.EventService;
import com.powerrangers.system.modules.eventManagement.service.dto.EventModifyDTO;
import com.powerrangers.system.modules.eventManagement.service.dto.SmallEventDTO;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping(value = "/updateEventName")
    public ResponseEntity<Object> updateEventName(@RequestBody EventModifyDTO eventModifyDTO,
                                                  @RequestHeader("Authorization") String token) {
        return eventService.updateEventName(token, eventModifyDTO);
    }

    @PostMapping(value = "/updateEventTime")
    public ResponseEntity<Object> updateEventTime(@RequestHeader("Authorization") String token,
                                                  @RequestBody EventModifyDTO eventModifyDTO) {
        return eventService.updateEventTime(token, eventModifyDTO);
    }

    @PostMapping(value = "/updateEventDescription")
    public ResponseEntity<Object> updateEventDescription(@RequestHeader("Authorization") String token,
                                                  @RequestBody EventModifyDTO eventModifyDTO) {
        return eventService.updateEventDescription(token, eventModifyDTO);
    }

    @PostMapping(value = "/updateEventAddress")
    public ResponseEntity<Object> updateEventAddress(@RequestHeader("Authorization") String token,
                                                         @RequestBody EventModifyDTO eventModifyDTO) {
        return eventService.updateEventAddress(token, eventModifyDTO);
    }

    @PostMapping(value = "/updateEventType")
    public ResponseEntity<Object> updateEventType(@RequestHeader("Authorization") String token,
                                                     @RequestBody EventModifyDTO eventModifyDTO) {
        return eventService.updateEventType(token, eventModifyDTO);
    }

    @PostMapping(value = "/changeEventCancelState")
    public ResponseEntity<Object> changeEventCancelState(@RequestHeader("Authorization") String token,
                                                  @RequestBody EventModifyDTO eventModifyDTO) {
        return eventService.changeEventCancelState(token, eventModifyDTO);
    }

    @PostMapping(value = "/changeEventDisplayState")
    public ResponseEntity<Object> changeEventDisplayState(@RequestHeader("Authorization") String token,
                                                         @RequestBody EventModifyDTO eventModifyDTO) {
        return eventService.changeEventDisplayState(token, eventModifyDTO);
    }

    @PostMapping(value = "/changeEventTag")
    public ResponseEntity<Object> changeEventTag(@RequestHeader("Authorization") String token,
                                                          @RequestBody EventModifyDTO eventModifyDTO) {
        return eventService.changeEventTag(token, eventModifyDTO);
    }

    @PostMapping(value = "/queryEvent")
    public ResponseEntity<Object> queryEvent(@RequestHeader("Authorization") String token, @RequestParam String eventName) {
        return eventService.queryEvent(token, eventName);
    }
}
