package com.powerrangers.system.modules.eventManagement.service;

import com.powerrangers.system.modules.eventManagement.service.dto.EventModifyDTO;
import com.powerrangers.system.modules.eventManagement.service.dto.SmallEventDTO;
import org.springframework.http.ResponseEntity;


public interface EventService {

    ResponseEntity<Object> createEvent(String token, SmallEventDTO smallEventDTO);

    Boolean checkExist(EventModifyDTO eventModifyDTO);

    ResponseEntity<String> updateEventName(String token, String eventName, String newName);
}
