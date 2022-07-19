package com.powerrangers.system.modules.eventManagement.service.impl;

import com.alibaba.fastjson.JSON;
import com.powerrangers.system.modules.eventManagement.dao.EventMapper;
import com.powerrangers.system.modules.eventManagement.service.EventService;
import com.powerrangers.system.modules.eventManagement.service.dto.EventModifyDTO;
import com.powerrangers.system.modules.eventManagement.service.dto.SmallEventDTO;
import com.powerrangers.system.modules.userAccess.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    @Autowired
    private final StringRedisTemplate redisTemplate;

    @Autowired
    private final EventMapper eventMapper;

    @Override
    public ResponseEntity<Object> createEvent(String token, SmallEventDTO smallEventDTO) {

        User currUser = JSON.parseObject(redisTemplate.opsForValue().get("token_" + token), User.class);

        if (!currUser.getIsAuth()) {
            return new ResponseEntity<>("Not a host or is not authenticated", HttpStatus.BAD_REQUEST);
        }

        smallEventDTO.setHostId(currUser.getId());

        eventMapper.createEvent(smallEventDTO);

        return new ResponseEntity<>("Create event succeed!", HttpStatus.OK);
    }

    @Override
    public Boolean checkExist(EventModifyDTO eventModifyDTO) {
        return eventMapper.checkExist(eventModifyDTO) > 0;
    }

    @Override
    public ResponseEntity<Object> updateEventName(String token, EventModifyDTO eventModifyDTO){

        User currUser = JSON.parseObject(redisTemplate.opsForValue().get("token_"+token), User.class);
        eventModifyDTO.setHostId(currUser.getId());

        if (checkExist(eventModifyDTO)){
            String eventName = eventModifyDTO.getEventName();
            eventModifyDTO.setEventName(eventModifyDTO.getNewString());
            if (checkExist(eventModifyDTO)){
                return new ResponseEntity<>("The new event name has existed", HttpStatus.BAD_REQUEST);
            }else{
                eventModifyDTO.setEventName(eventName);
                eventMapper.updateEventName(eventModifyDTO);
                return new ResponseEntity<>("Update event name succeed!", HttpStatus.OK);
            }

        }else{
            return new ResponseEntity<>("The event you want to modify did not exist", HttpStatus.BAD_REQUEST);
        }

    }

    @Override
    public ResponseEntity<Object> updateEventTime(String token, EventModifyDTO eventModifyDTO){
        User currUser = JSON.parseObject(redisTemplate.opsForValue().get("token_"+token), User.class);
        eventModifyDTO.setHostId(currUser.getId());

        if (checkExist(eventModifyDTO)){
            eventMapper.updateEventTime(eventModifyDTO);
            return new ResponseEntity<>("Update event time succeed!", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("The event you want to modify did not exist", HttpStatus.BAD_REQUEST);
        }

    }

    @Override
    public ResponseEntity<Object> updateEventDescription(String token, EventModifyDTO eventModifyDTO) {
        User currUser = JSON.parseObject(redisTemplate.opsForValue().get("token_"+token), User.class);
        eventModifyDTO.setHostId(currUser.getId());

        if (checkExist(eventModifyDTO)){
            eventMapper.updateEventDescription(eventModifyDTO);
            return new ResponseEntity<>("Update event description succeed!", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("The event you want to modify did not exist", HttpStatus.BAD_REQUEST);
        }

    }
}
