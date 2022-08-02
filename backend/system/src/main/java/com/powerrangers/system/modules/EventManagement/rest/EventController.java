package com.powerrangers.system.modules.EventManagement.rest;

import com.powerrangers.system.modules.EventManagement.service.EventService;
import com.powerrangers.system.modules.EventManagement.service.dto.EventFilterDTO;
import com.powerrangers.system.modules.EventManagement.service.dto.EventModifyDTO;
import com.powerrangers.system.modules.EventManagement.service.dto.SmallEventDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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

    @PostMapping(value = "/updateEventCancelState")
    public ResponseEntity<Object> changeEventCancelState(@RequestHeader("Authorization") String token,
                                                  @RequestBody EventModifyDTO eventModifyDTO) {
        return eventService.changeEventCancelState(token, eventModifyDTO);
    }

    @PostMapping(value = "/updateEventDisplayState")
    public ResponseEntity<Object> changeEventDisplayState(@RequestHeader("Authorization") String token,
                                                         @RequestBody EventModifyDTO eventModifyDTO) {
        return eventService.changeEventDisplayState(token, eventModifyDTO);
    }

    @PostMapping(value = "/updateEventTag")
    public ResponseEntity<Object> changeEventTag(@RequestHeader("Authorization") String token,
                                                          @RequestBody EventModifyDTO eventModifyDTO) {
        return eventService.changeEventTag(token, eventModifyDTO);
    }

    @ApiOperation(value = "get specific event through event name created by specific host")
    @PostMapping(value = "/queryEvent")
    public ResponseEntity<Object> queryEvent(@RequestHeader("Authorization") String token, @RequestBody Map<String, String> params) {
        return eventService.queryEvent(token, params.get("eventName"), params.get("hostName"));
    }

    @ApiOperation(value = "get all events created by specific host")
    @GetMapping(value = "getEvents")
    @ApiParam(value = "hostName")
    public ResponseEntity<Object> getEvents(@RequestHeader("Authorization") String token, @RequestParam String hostName) {
        return eventService.getEvents(token, hostName);
    }

    @ApiOperation(value = "get all events")
    @PostMapping(value = "getAllEvents")
    public ResponseEntity<Object> getAllEvents(@RequestBody EventFilterDTO eventFilterDTO) {
        return eventService.getAllEvents(eventFilterDTO);
    }

    @ApiOperation(value = "search the events")
    @GetMapping(value = "searchEvents")
    @ApiParam(value = "keyWords")
    public ResponseEntity<Object> searchEvents(@RequestHeader("Authorization") String token, @RequestParam String keyWords) {
        return eventService.searchEvents(token, keyWords);
    }

    @ApiOperation(value = "check out one particular user's spending history")
    @GetMapping(value = "checkSpendingHistory")
    @ApiParam(value = "userName")
    public ResponseEntity<Object> checkSpendingHistory(@RequestHeader("Authorization") String token, @RequestParam String userName) {
        return eventService.checkSpendingHistory(token, userName);
    }

    @ApiOperation(value = "get events within one month")
    @GetMapping(value = "getOneMonthEvents")
    public ResponseEntity<Object> getOneMonthEvents(@RequestBody EventFilterDTO eventFilterDTO) {
        return eventService.getOneMonthEvents(eventFilterDTO);

    }
    @GetMapping(value = "getRecommendation")
    public ResponseEntity<Object> getRecommendation(@RequestHeader("Authorization") String token) {
        return eventService.getRecommendation(token);
    }
}
