package com.powerrangers.system.modules.OrderManagement.service.impl;

import com.alibaba.fastjson.JSON;
import com.powerrangers.system.modules.OrderManagement.dao.OrderMapper;
import com.powerrangers.system.modules.OrderManagement.service.OrderService;
import com.powerrangers.system.modules.OrderManagement.service.dto.OrderDTO;
import com.powerrangers.system.modules.UserAccess.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    @Autowired
    private final StringRedisTemplate redisTemplate;

    @Autowired
    private final OrderMapper orderMapper;

    private Map<String, String> responseBody = new HashMap<>();

    @Override
    public ResponseEntity<Object> insertOrder(String token, OrderDTO orderDTO) {

        User currUser = JSON.parseObject(redisTemplate.opsForValue().get("token_" + token), User.class);

        responseBody.clear();

        if (currUser == null) {
            responseBody.put("error", "token is invalid!");
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }

        orderDTO.setCustomerId(currUser.getId());
        orderMapper.insertOrder(orderDTO);
        responseBody.put("msg", "insert order succeed!");

        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }
}
