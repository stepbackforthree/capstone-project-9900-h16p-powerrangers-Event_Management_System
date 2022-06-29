package com.powerrangers.system.modules.userAccess.service.impl;

import com.alibaba.fastjson.JSON;
import com.powerrangers.common.utils.JWTUtils;
import com.powerrangers.system.modules.userAccess.dao.UserMapper;
import com.powerrangers.system.modules.userAccess.domain.User;
import com.powerrangers.system.modules.userAccess.service.UserService;
import com.powerrangers.system.modules.userAccess.service.dto.SmallUserDTO;
import com.powerrangers.system.modules.userAccess.service.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;

    private RedisTemplate<String, String> redisTemplate;

    @Value("${JWT.expiration}")
    private String expiration;

    @Override
    public Boolean checkExist(SmallUserDTO smallUserDTO) {
        return userMapper.checkExist(smallUserDTO) > 0;
    }

    @Override
    public Boolean createUser(SmallUserDTO smallUserDTO) {

        if (!checkExist(smallUserDTO)) {
            userMapper.addUser(smallUserDTO);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public ResponseEntity<Object> login(SmallUserDTO smallUserDTO) {

        Map<String, String> map = new HashMap<>();

        if (!checkExist(smallUserDTO)) {

            UserDTO userDTO = userMapper.queryUser(smallUserDTO);

            String token = JWTUtils.createToken(userDTO.getId());
            map.put("token", token);
            map.put("expiration", expiration);
            map.put("msg", "login succeed!");

            redisTemplate.opsForValue().set("token_" + token, JSON.toJSONString(userDTO), Integer.parseInt(expiration), TimeUnit.DAYS);

        } else {
            map.put("msg", "user not exists!");

        }

        return new ResponseEntity<>(JSON.toJSONString(map), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> logout(String token) {
        redisTemplate.delete("token_" + token);

        Map<String, String> map = new HashMap<>();
        map.put("msg", "logout succeed!");

        return new ResponseEntity<>(JSON.toJSONString(map), HttpStatus.OK);
    }

    @Override
    public User findCurrentUserByToken(String token) {
        if (token.isBlank()) {
            return null;
        }

        Map<String, Object> stringObjectMap = JWTUtils.checkToken(token);

        if (stringObjectMap == null) {
            return null;
        }

        String userJSON = redisTemplate.opsForValue().get("token_" + token);
        if (userJSON == null || userJSON.isBlank()) {
            return null;
        }

        return JSON.parseObject(userJSON, User.class);
    }

    @Override
    public Boolean checkPassword(String token, String password) {
        User user = JSON.parseObject(redisTemplate.opsForValue().get("token_" + token), User.class);

        if (user == null) {
            return false;
        }

        return user.getPassword().equals(password);
    }
}
