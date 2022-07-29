package com.powerrangers.system.modules.EventManagement.service.impl;

import com.alibaba.fastjson.JSON;
import com.powerrangers.system.modules.EventManagement.dao.EventMapper;
import com.powerrangers.system.modules.EventManagement.dao.EventTicketMapper;
import com.powerrangers.system.modules.EventManagement.service.EventTicketService;
import com.powerrangers.system.modules.EventManagement.service.dto.EventModifyDTO;
import com.powerrangers.system.modules.EventManagement.service.dto.TicketDTO;
import com.powerrangers.system.modules.UserAccess.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EventTicketServiceImpl implements EventTicketService {

    @Autowired
    private final StringRedisTemplate redisTemplate;

    @Autowired
    private final EventMapper eventMapper;

    @Autowired
    private final EventTicketMapper eventTicketMapper;

    private Map<String, String> responseBody = new HashMap<>();

    @Override
    public ResponseEntity<Object> addTicketType(String token, TicketDTO ticketDTO) {
        User currUser = JSON.parseObject(redisTemplate.opsForValue().get("token_" + token), User.class);

        responseBody.clear();

        if (currUser != null) {
            EventModifyDTO eventModifyDTO = new EventModifyDTO();

            eventModifyDTO.setHostId(currUser.getId());
            eventModifyDTO.setEventName(ticketDTO.getEventName());
            ticketDTO.setHostName(currUser.getUserName());

            if (eventMapper.queryEvent(eventModifyDTO) == null) {
                responseBody.put("error", "host and event is not match!");
                return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
            }

            if (eventTicketMapper.checkExist(ticketDTO) > 0) {
                responseBody.put("error", "ticket type has already existed!");
                return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
            }

            eventTicketMapper.insertEventTicketType(ticketDTO);

            responseBody.put("msg", "add ticket type succeed!");
            return new ResponseEntity<>(responseBody, HttpStatus.OK);

        } else {
            responseBody.put("error", "token is invalid!");
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Object> getTicketType(String token, TicketDTO ticketDTO) {
        User currUser = JSON.parseObject(redisTemplate.opsForValue().get("token_" + token), User.class);

        responseBody.clear();

        if (currUser == null) {
            responseBody.put("error", "token is invalid!");
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }

        List<TicketDTO> ticketList = eventTicketMapper.getTicketType(ticketDTO);

        if (ticketList == null || ticketList.size() == 0) {
            responseBody.put("error", "ticket type is not exist!");
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(ticketList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> updateTicketAmount(String token, TicketDTO ticketDTO) {
        User currUser = JSON.parseObject(redisTemplate.opsForValue().get("token_" + token), User.class);

        responseBody.clear();

        if (currUser == null) {
            responseBody.put("error", "token is invalid!");
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }

        if (ticketDTO.getTicketAmount() > eventTicketMapper.getRemainTicketAmount(ticketDTO)) {
            responseBody.put("error", "buying beyond the remaining amount!");
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }

        eventTicketMapper.updateTicketAmount(ticketDTO);

        responseBody.put("msg", "Buy ticket succeed!");
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }
}
