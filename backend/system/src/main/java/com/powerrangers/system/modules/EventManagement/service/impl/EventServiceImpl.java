package com.powerrangers.system.modules.EventManagement.service.impl;

import com.alibaba.fastjson.JSON;
import com.powerrangers.system.modules.EventManagement.dao.EventMapper;
import com.powerrangers.system.modules.EventManagement.dao.EventTicketMapper;
import com.powerrangers.system.modules.EventManagement.service.EventService;
import com.powerrangers.system.modules.EventManagement.service.dto.*;
import com.powerrangers.system.modules.UserAccess.dao.UserMapper;
import com.powerrangers.system.modules.UserAccess.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Autowired
    private final EventTicketMapper eventTicketMapper;

    @Value("${DefaultImage.eventImage}")
    private String defaultEventImage;

    @Override
    public ResponseEntity<Object> createEvent(String token, SmallEventDTO smallEventDTO) {

        User currUser = JSON.parseObject(redisTemplate.opsForValue().get("token_" + token), User.class);

        Map<String, String> responseBody = new HashMap<>();

        if (currUser != null && !currUser.getIsAuth()) {
            responseBody.put("error", "Not a host or is not authenticated");
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }
        if (currUser == null) {
            responseBody.put("error", "token is invalid!");
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }

        EventModifyDTO eventModifyDTO = new EventModifyDTO();
        eventModifyDTO.setEventName(smallEventDTO.getEventName());
        eventModifyDTO.setHostId(currUser.getId());
        smallEventDTO.setHostId(currUser.getId());

        if (smallEventDTO.getImage().equals("") || smallEventDTO.getImage().length() == 0) {
            smallEventDTO.setImage(defaultEventImage);
        }

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

        Map<String, String> responseBody = new HashMap<>();

        if (currUser != null && !currUser.getIsAuth()) {
            responseBody.put("error", "Not a host or is not authenticated");
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }
        if (currUser == null) {
            responseBody.put("error", "token is invalid!");
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }
        eventModifyDTO.setHostId(currUser.getId());



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

        Map<String, String> responseBody = new HashMap<>();

        if (currUser != null && !currUser.getIsAuth()) {
            responseBody.put("error", "Not a host or is not authenticated");
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }
        if (currUser == null) {
            responseBody.put("error", "token is invalid!");
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }
        eventModifyDTO.setHostId(currUser.getId());


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

        Map<String, String> responseBody = new HashMap<>();

        if (currUser != null && !currUser.getIsAuth()) {
            responseBody.put("error", "Not a host or is not authenticated");
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }
        if (currUser == null) {
            responseBody.put("error", "token is invalid!");
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }
        eventModifyDTO.setHostId(currUser.getId());


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

        Map<String, String> responseBody = new HashMap<>();

        if (currUser != null && !currUser.getIsAuth()) {
            responseBody.put("error", "Not a host or is not authenticated");
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }
        if (currUser == null) {
            responseBody.put("error", "token is invalid!");
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }
        eventModifyDTO.setHostId(currUser.getId());


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

        Map<String, String> responseBody = new HashMap<>();

        if (currUser != null && !currUser.getIsAuth()) {
            responseBody.put("error", "Not a host or is not authenticated");
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }
        if (currUser == null) {
            responseBody.put("error", "token is invalid!");
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }
        eventModifyDTO.setHostId(currUser.getId());

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

        Map<String, String> responseBody = new HashMap<>();

        if (currUser != null && !currUser.getIsAuth()) {
            responseBody.put("error", "Not a host or is not authenticated");
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }
        if (currUser == null) {
            responseBody.put("error", "token is invalid!");
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }
        eventModifyDTO.setHostId(currUser.getId());


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

        Map<String, String> responseBody = new HashMap<>();

        if (currUser != null && !currUser.getIsAuth()) {
            responseBody.put("error", "Not a host or is not authenticated");
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }
        if (currUser == null) {
            responseBody.put("error", "token is invalid!");
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }
        eventModifyDTO.setHostId(currUser.getId());


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

        Map<String, String> responseBody = new HashMap<>();

        if (currUser != null && !currUser.getIsAuth()) {
            responseBody.put("error", "Not a host or is not authenticated");
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }
        if (currUser == null) {
            responseBody.put("error", "token is invalid!");
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }
        eventModifyDTO.setHostId(currUser.getId());


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
    public ResponseEntity<Object> queryEvent(String token, String eventName, String hostName) {
        User currUser = JSON.parseObject(redisTemplate.opsForValue().get("token_" + token), User.class);

        Map<String, String> responseBody = new HashMap<>();

        if (currUser != null && !currUser.getIsAuth()) {
            responseBody.put("error", "Not a host or is not authenticated");
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }


        EventModifyDTO eventModifyDTO = new EventModifyDTO();
        if (currUser != null) {
            eventModifyDTO.setHostId(userMapper.getUserIdByUserName(hostName));
            eventModifyDTO.setEventName(eventName);
        } else {
            responseBody.put("error", "token is invalid!");
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.set("content-type", "application/json");

        EventDTO eventDTO = eventMapper.queryEvent(eventModifyDTO);

        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setEventName(eventName);
        ticketDTO.setHostName(hostName);

        eventDTO.setTickets(eventTicketMapper.getTicketType(ticketDTO));

        return ResponseEntity.ok().headers(headers)
                .body(JSON.parseObject(JSON.toJSONString(eventDTO)));
    }

    @Override
    public ResponseEntity<Object> getEvents(String token, String hostName) {
        User currUser = JSON.parseObject(redisTemplate.opsForValue().get("token_" + token), User.class);

        Map<String, String> responseBody = new HashMap<>();

        if (currUser != null && !currUser.getIsAuth()) {
            responseBody.put("error", "Not a host or is not authenticated");
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }

        if (currUser == null) {
            responseBody.put("error", "token is invalid!");
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(eventMapper.getEvents(userMapper.getUserIdByUserName(hostName)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> getAllEvents(EventFilterDTO eventFilterDTO) {
        /*User currUser = JSON.parseObject(redisTemplate.opsForValue().get("token_" + token), User.class);

        Map<String, String> responseBody = new HashMap<>();

        if (currUser != null && !currUser.getIsAuth()) {
            responseBody.put("error", "Not a host or is not authenticated");
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }

        if (currUser == null) {
            responseBody.put("error", "token is invalid!");
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }*/

        return new ResponseEntity<>(eventMapper.getAllEvents(eventFilterDTO), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> searchEvents(String token, String keyWords) {
        User currUser = JSON.parseObject(redisTemplate.opsForValue().get("token_" + token), User.class);

        Map<String, String> responseBody = new HashMap<>();

        if (currUser == null) {
            responseBody.put("error", "token is invalid!");
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }

        if(keyWords != null && keyWords.length() != 0) {
            return new ResponseEntity<>(eventMapper.searchEvents(keyWords), HttpStatus.OK);
        }

        responseBody.put("error","empty content to search");
        return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Object> checkSpendingHistory(String userName) {
        Map<String, String> responseBody = new HashMap<>();
        if(eventMapper.checkUserExist(userName)>0){
            if(eventMapper.checkSpendingHistory(userName)>0){
                return new ResponseEntity<>(eventMapper.getSpendingHistory(userName), HttpStatus.BAD_REQUEST);
            }
            responseBody.put("error", "the user did not spend anything");
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }
        responseBody.put("error", "the user did not exist");
        return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
    }

    @Override
    public void updateStarLevel(EventDTO eventDTO) {
        eventMapper.updateStarLevel(eventDTO);
    }
}
