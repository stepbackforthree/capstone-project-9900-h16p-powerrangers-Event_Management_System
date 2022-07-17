package com.powerrangers.system.modules.eventManagement.service;

import com.powerrangers.system.modules.eventManagement.service.dto.SmallEventDTO;
import com.powerrangers.system.modules.userAccess.service.dto.SmallUserDTO;
import org.springframework.http.ResponseEntity;


public interface EventService {

    ResponseEntity<Object> createEvent(String token, SmallEventDTO smallEventDTO);

    Boolean checkExist(SmallEventDTO smallEventDTO);
}
