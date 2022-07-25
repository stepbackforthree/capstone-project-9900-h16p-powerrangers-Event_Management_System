package com.powerrangers.system.modules.eventManagement.service.impl;

import com.alibaba.fastjson.JSON;
import com.powerrangers.system.modules.eventManagement.dao.EventMapper;
import com.powerrangers.system.modules.eventManagement.dao.EventTicketMapper;
import com.powerrangers.system.modules.eventManagement.service.EventTicketService;
import com.powerrangers.system.modules.eventManagement.service.dto.EventModifyDTO;
import com.powerrangers.system.modules.eventManagement.service.dto.TicketDTO;
import com.powerrangers.system.modules.userAccess.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventTicketServiceImpl implements EventTicketService {

    @Autowired
    private final StringRedisTemplate redisTemplate;

    @Autowired
    private final EventMapper eventMapper;

    @Autowired
    private final EventTicketMapper eventTicketMapper;

    @Override
    public ResponseEntity<Object> addTicketType(String token, TicketDTO ticketDTO) {
        User currUser = JSON.parseObject(redisTemplate.opsForValue().get("token_" + token), User.class);

        if (currUser != null) {
            EventModifyDTO eventModifyDTO = new EventModifyDTO();

            eventModifyDTO.setHostId(currUser.getId());
            eventModifyDTO.setEventName(ticketDTO.getEventName());

            if (eventMapper.queryEvent(eventModifyDTO) == null) {
                return new ResponseEntity<>("host and event is not match!", HttpStatus.BAD_REQUEST);
            }

            eventTicketMapper.insertEventTicketType(ticketDTO);

            return new ResponseEntity<>("add ticket type succeed!", HttpStatus.OK);

        } else {
            return new ResponseEntity<>("token is invalid!", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Object> getTicketType(String token, TicketDTO ticketDTO) {
        User currUser = JSON.parseObject(redisTemplate.opsForValue().get("token_" + token), User.class);

        if (currUser == null) {
            return new ResponseEntity<>("token is invalid!", HttpStatus.BAD_REQUEST);
        }

        List<TicketDTO> ticketList = eventTicketMapper.getTicketType(ticketDTO);

        if (ticketList == null || ticketList.size() == 0) {
            return new ResponseEntity<>("ticket type is not exist!", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(ticketList, HttpStatus.OK);
    }
}
