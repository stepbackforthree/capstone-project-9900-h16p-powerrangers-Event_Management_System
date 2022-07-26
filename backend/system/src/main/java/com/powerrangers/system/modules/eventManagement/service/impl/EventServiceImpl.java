package com.powerrangers.system.modules.eventManagement.service.impl;

import com.alibaba.fastjson.JSON;
import com.powerrangers.system.modules.eventManagement.dao.EventMapper;
import com.powerrangers.system.modules.eventManagement.service.EventService;
import com.powerrangers.system.modules.eventManagement.service.dto.EventFilterDTO;
import com.powerrangers.system.modules.eventManagement.service.dto.EventModifyDTO;
import com.powerrangers.system.modules.eventManagement.service.dto.SmallEventDTO;
import com.powerrangers.system.modules.userAccess.dao.UserMapper;
import com.powerrangers.system.modules.userAccess.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    @Autowired
    private final StringRedisTemplate redisTemplate;

    @Autowired
    private final UserMapper userMapper;

    @Autowired
    private final EventMapper eventMapper;

    @Override
    public ResponseEntity<Object> createEvent(String token, SmallEventDTO smallEventDTO) {

        User currUser = JSON.parseObject(redisTemplate.opsForValue().get("token_" + token), User.class);

        Map<String, String> responseBody = new HashMap<>();

        if (currUser != null && !currUser.getIsAuth()) {
            responseBody.put("error", "Not a host or is not authenticated");
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }

        EventModifyDTO eventModifyDTO = new EventModifyDTO();
        eventModifyDTO.setEventName(smallEventDTO.getEventName());
        eventModifyDTO.setHostId(currUser.getId());
        smallEventDTO.setHostId(currUser.getId());

        if (eventMapper.checkExist(eventModifyDTO) > 0) {
            responseBody.put("error", "Duplicated event name!");
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }

        eventMapper.createEvent(smallEventDTO);
        eventMapper.createEventInsertFullPriceTicket(smallEventDTO);

        responseBody.put("msg", "Create event succeed!");
        return new ResponseEntity<>(responseBody ,HttpStatus.OK);
    }

    @Override
    public Boolean checkExist(EventModifyDTO eventModifyDTO) {
        return eventMapper.checkExist(eventModifyDTO) > 0;
    }

    @Override
    public ResponseEntity<Object> updateEventName(String token, EventModifyDTO eventModifyDTO){

        User currUser = JSON.parseObject(redisTemplate.opsForValue().get("token_"+token), User.class);
        eventModifyDTO.setHostId(currUser.getId());

        Map<String, String> responseBody = new HashMap<>();

        if (checkExist(eventModifyDTO)){
            String eventName = eventModifyDTO.getEventName();
            eventModifyDTO.setEventName(eventModifyDTO.getNewString());
            if (checkExist(eventModifyDTO)){
                responseBody.put("error", "The new event name has existed");
                return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
            }else{
                eventModifyDTO.setEventName(eventName);
                eventMapper.updateEventName(eventModifyDTO);
                responseBody.put("msg", "Update event name succeed!");
                return new ResponseEntity<>(responseBody, HttpStatus.OK);
            }

        }else{
            responseBody.put("error", "The event you want to modify did not exist");
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }

    }

    @Override
    public ResponseEntity<Object> updateEventTime(String token, EventModifyDTO eventModifyDTO){
        User currUser = JSON.parseObject(redisTemplate.opsForValue().get("token_"+token), User.class);
        eventModifyDTO.setHostId(currUser.getId());

        Map<String, String> responseBody = new HashMap<>();

        if (checkExist(eventModifyDTO)){
            eventMapper.updateEventTime(eventModifyDTO);

            responseBody.put("msg", "Update event time succeed!");

            return new ResponseEntity<>(responseBody, HttpStatus.OK);
        }else{

            responseBody.put("error", "The event you want to modify did not exist");

            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }

    }

    @Override
    public ResponseEntity<Object> updateEventDescription(String token, EventModifyDTO eventModifyDTO) {
        User currUser = JSON.parseObject(redisTemplate.opsForValue().get("token_"+token), User.class);
        eventModifyDTO.setHostId(currUser.getId());

        Map<String, String> responseBody = new HashMap<>();

        if (checkExist(eventModifyDTO)){
            eventMapper.updateEventDescription(eventModifyDTO);
            responseBody.put("msg","Update event description succeed!");
            return new ResponseEntity<>(responseBody, HttpStatus.OK);
        }else{
            responseBody.put("error","The event you want to modify did not exist");
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }

    }

    @Override
    public ResponseEntity<Object> updateEventAddress(String token, EventModifyDTO eventModifyDTO) {
        User currUser = JSON.parseObject(redisTemplate.opsForValue().get("token_"+token), User.class);
        eventModifyDTO.setHostId(currUser.getId());

        Map<String, String> responseBody = new HashMap<>();

        if (checkExist(eventModifyDTO)){
            eventMapper.updateEventAddress(eventModifyDTO);
            responseBody.put("msg","Update event address succeed!");
            return new ResponseEntity<>(responseBody, HttpStatus.OK);
        }else{
            responseBody.put("error","The event you want to modify did not exist");
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Object> updateEventType(String token, EventModifyDTO eventModifyDTO) {
        User currUser = JSON.parseObject(redisTemplate.opsForValue().get("token_"+token), User.class);
        eventModifyDTO.setHostId(currUser.getId());

        Map<String, String> responseBody = new HashMap<>();

        if (checkExist(eventModifyDTO)){
            eventMapper.updateEventType(eventModifyDTO);
            responseBody.put("msg","Update event type succeed!");
            return new ResponseEntity<>(responseBody, HttpStatus.OK);
        }else{
            responseBody.put("error","The event you want to modify did not exist");
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Object> changeEventCancelState(String token, EventModifyDTO eventModifyDTO) {
        User currUser = JSON.parseObject(redisTemplate.opsForValue().get("token_"+token), User.class);
        eventModifyDTO.setHostId(currUser.getId());

        Map<String, String> responseBody = new HashMap<>();

        if (checkExist(eventModifyDTO)){
            eventMapper.changeEventCancelState(eventModifyDTO);
            responseBody.put("msg","Update event cancel_state succeed!");
            return new ResponseEntity<>(responseBody, HttpStatus.OK);
        }else{
            responseBody.put("error","The event you want to modify did not exist");
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Object> changeEventDisplayState(String token, EventModifyDTO eventModifyDTO) {
        User currUser = JSON.parseObject(redisTemplate.opsForValue().get("token_"+token), User.class);
        eventModifyDTO.setHostId(currUser.getId());

        Map<String, String> responseBody = new HashMap<>();

        if (checkExist(eventModifyDTO)){
            eventMapper.changeEventDisplayState(eventModifyDTO);
            responseBody.put("msg","Update event display_state succeed!");
            return new ResponseEntity<>(responseBody, HttpStatus.OK);
        }else{
            responseBody.put("error","The event you want to modify did not exist");
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Object> changeEventTag(String token, EventModifyDTO eventModifyDTO) {
        User currUser = JSON.parseObject(redisTemplate.opsForValue().get("token_"+token), User.class);
        eventModifyDTO.setHostId(currUser.getId());

        Map<String, String> responseBody = new HashMap<>();

        if (checkExist(eventModifyDTO)){
            eventMapper.changeEventTag(eventModifyDTO);
            responseBody.put("msg","Update event tag succeed!");
            return new ResponseEntity<>(responseBody, HttpStatus.OK);
        }else{
            responseBody.put("error","The event you want to modify did not exist");
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Object> queryEvent(String token, String eventName, String userName) {
        User currUser = JSON.parseObject(redisTemplate.opsForValue().get("token_" + token), User.class);

        Map<String, String> responseBody = new HashMap<>();

        EventModifyDTO eventModifyDTO = new EventModifyDTO();
        if (currUser != null) {
            eventModifyDTO.setHostId(userMapper.getUserIdByUserName(userName));
            eventModifyDTO.setEventName(eventName);
        } else {
            responseBody.put("error", "token is invalid!");
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.set("content-type", "application/json");

        return ResponseEntity.ok().headers(headers)
                .body(JSON.parseObject(JSON.toJSONString(eventMapper.queryEvent(eventModifyDTO))));
    }

    @Override
    public ResponseEntity<Object> getEvents(String token, String userName) {
        User currUser = JSON.parseObject(redisTemplate.opsForValue().get("token_" + token), User.class);

        if (currUser == null) {
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("error", "User is not a host!");
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(eventMapper.getEvents(userMapper.getUserIdByUserName(userName)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> getAllEvents(String token, EventFilterDTO eventFilterDTO) {
        User currUser = JSON.parseObject(redisTemplate.opsForValue().get("token_" + token), User.class);


        if (currUser == null) {
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("error", "User is not a host!");
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(eventMapper.getAllEvents(eventFilterDTO), HttpStatus.OK);
    }
}
