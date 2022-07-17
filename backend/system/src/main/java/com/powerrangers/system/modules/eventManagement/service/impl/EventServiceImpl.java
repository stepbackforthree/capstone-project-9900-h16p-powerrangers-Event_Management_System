package com.powerrangers.system.modules.eventManagement.service.impl;

import com.alibaba.fastjson.JSON;
import com.powerrangers.system.modules.eventManagement.dao.EventMapper;
import com.powerrangers.system.modules.eventManagement.service.EventService;
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

        User currUser = JSON.parseObject( (String) redisTemplate.opsForValue().get("token_" + token), User.class);

        if (!currUser.getIsAuth()) {
            return new ResponseEntity<>("Not a host or is not authenticated", HttpStatus.BAD_REQUEST);
        }

        smallEventDTO.setHostId(currUser.getId());

        eventMapper.createEvent(smallEventDTO);

        return new ResponseEntity<>("Create event succeed!", HttpStatus.OK);
    }

    @Override
    public Boolean checkExist(SmallEventDTO smallEventDTO) {
        return eventMapper.checkExist(smallEventDTO) > 0;
    }
}
