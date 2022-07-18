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

//    @PostMapping(value = "updateEventName")
//    public ResponseEntity<Object> updateEventName(@RequestHeader("Authorization") String token,
//                                                 @RequestParam String eventName, @RequestParam String newName) {
//        return eventService.updateEventName(token, eventName, newName);
//    }

    @PostMapping(value = "/updateEventName")
    public ResponseEntity<Object> updateEventName(@RequestBody EventModifyDTO eventModifyDTO,
                                                  @RequestHeader("Authorization") String token) {
        System.out.println(eventModifyDTO.getEventName() + eventModifyDTO.getNewString()+token);
        return eventService.updateEventName(token, eventModifyDTO);
    }

    @PostMapping(value = "updateEventTime")
    public ResponseEntity<Object> updateEventTime(@RequestHeader("Authorization") String token,
                                                  @RequestBody EventModifyDTO eventModifyDTO) {
        return eventService.updateEventTime(token, eventModifyDTO);
    }


}
