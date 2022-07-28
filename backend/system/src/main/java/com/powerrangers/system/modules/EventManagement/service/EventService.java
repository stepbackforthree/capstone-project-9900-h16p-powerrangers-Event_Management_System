package com.powerrangers.system.modules.EventManagement.service;

import com.powerrangers.system.modules.EventManagement.service.dto.EventDTO;
import com.powerrangers.system.modules.EventManagement.service.dto.EventFilterDTO;
import com.powerrangers.system.modules.EventManagement.service.dto.EventModifyDTO;
import com.powerrangers.system.modules.EventManagement.service.dto.SmallEventDTO;
import org.springframework.http.ResponseEntity;


public interface EventService {

    ResponseEntity<Object> createEvent(String token, SmallEventDTO smallEventDTO);

    Boolean checkExist(EventModifyDTO eventModifyDTO);

    ResponseEntity<Object> updateEventTime(String token, EventModifyDTO eventModifyDTO);

    ResponseEntity<Object> updateEventName(String token, EventModifyDTO eventModifyDTO);

    ResponseEntity<Object> updateEventDescription(String token, EventModifyDTO eventModifyDTO);

    ResponseEntity<Object> updateEventAddress(String token, EventModifyDTO eventModifyDTO);

    ResponseEntity<Object> updateEventType(String token, EventModifyDTO eventModifyDTO);

    ResponseEntity<Object> changeEventCancelState(String token, EventModifyDTO eventModifyDTO);

    ResponseEntity<Object> changeEventDisplayState(String token, EventModifyDTO eventModifyDTO);

    ResponseEntity<Object> changeEventTag(String token, EventModifyDTO eventModifyDTO);

    ResponseEntity<Object> queryEvent(String token, String eventName, String hostName);

    ResponseEntity<Object> getEvents(String token, String hostName);

    ResponseEntity<Object> getAllEvents(String token, EventFilterDTO eventFilterDTO);

    ResponseEntity<Object> searchEvents(String keyWords);

    ResponseEntity<Object> checkSpendingHistory(String userName);

    void updateStarLevel(EventDTO eventDTO);
}

