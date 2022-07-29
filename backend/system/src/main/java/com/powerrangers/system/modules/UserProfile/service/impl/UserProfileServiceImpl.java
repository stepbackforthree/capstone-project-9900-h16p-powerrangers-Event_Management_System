package com.powerrangers.system.modules.UserProfile.service.impl;

import com.alibaba.fastjson.JSON;
import com.powerrangers.system.modules.UserAccess.domain.User;
import com.powerrangers.system.modules.UserProfile.dao.UserProfileMapper;
import com.powerrangers.system.modules.UserProfile.service.UserProfileService;
import com.powerrangers.system.modules.UserProfile.service.dto.UserProfileDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private final UserProfileMapper userProfileMapper;

    private Map<String, String> responseBody = new HashMap<>();

    @Override
    public Map<String, String> updateNickname(String token, String nickName) {

        responseBody.clear();

        User currUser = JSON.parseObject(redisTemplate.opsForValue().get("token_"+token), User.class);

        if (currUser == null) {
            responseBody.put("error", "token is invalid!");
            return responseBody;
        }



        if (currUser.getNickName().equals(nickName)) {
            responseBody.put("error", "duplicated nickname!");
            return responseBody;
        }

        UserProfileDTO userProfileDTO = new UserProfileDTO();
        userProfileDTO.setUserName(currUser.getUserName());
        userProfileDTO.setNickName(nickName);

        userProfileMapper.updateNickname(userProfileDTO);

        currUser.setNickName(nickName);
        redisTemplate.opsForValue().set("token_"+token, JSON.toJSONString(currUser),
                redisTemplate.getExpire("token_"+token), TimeUnit.SECONDS);

        responseBody.put("msg", "update nickname success!");
        return responseBody;
    }

    @Override
    public Map<String, String> updateEmail(String token, String email) {

        responseBody.clear();

        User currUser = JSON.parseObject(redisTemplate.opsForValue().get("token_"+token), User.class);

        if (currUser == null) {
            responseBody.put("error", "token is invalid!");
            return responseBody;
        }


        if (currUser.getEmail().equals(email)) {
            responseBody.put("error", "duplicated email!");
            return responseBody;
        }

        UserProfileDTO userProfileDTO = new UserProfileDTO();
        userProfileDTO.setUserName(currUser.getUserName());
        userProfileDTO.setEmail(email);

        userProfileMapper.updateEmail(userProfileDTO);

        currUser.setEmail(email);
        redisTemplate.opsForValue().set("token_"+token, JSON.toJSONString(currUser),
                redisTemplate.getExpire("token_"+token), TimeUnit.SECONDS);

        responseBody.put("msg", "update email success!");
        return responseBody;
    }

    @Override
    public Map<String, String> updateAvatar(String token, String avatar) {
        User currUser = JSON.parseObject(redisTemplate.opsForValue().get("token_"+token), User.class);

        responseBody.clear();

        if (currUser == null) {
            responseBody.put("error", "token is invalid!");
            return responseBody;
        }

        if (currUser.getAvatar() != null && currUser.getAvatar().equals(avatar)) {
            responseBody.put("error", "duplicated avatar!");
            return responseBody;
        }

        UserProfileDTO userProfileDTO = new UserProfileDTO();
        userProfileDTO.setUserName(currUser.getUserName());
        userProfileDTO.setAvatar(avatar);

        userProfileMapper.updateAvatar(userProfileDTO);

        currUser.setAvatar(avatar);
        redisTemplate.opsForValue().set("token_"+token, JSON.toJSONString(currUser),
                redisTemplate.getExpire("token_"+token), TimeUnit.SECONDS);

        responseBody.put("msg", "update avatar success!");
        return responseBody;
    }

    @Override
    public Map<String, String> updateDescription(String token, String description) {
        User currUser = JSON.parseObject(redisTemplate.opsForValue().get("token_"+token), User.class);

        responseBody.clear();

        if (currUser == null) {
            responseBody.put("error", "token is invalid!");
            return responseBody;
        }

        if (currUser.getDescription() != null || !currUser.getDescription().isEmpty() && currUser.getDescription().equals(description)) {
            responseBody.put("error", "duplicated description!");
            return responseBody;
        }

        UserProfileDTO userProfileDTO = new UserProfileDTO();
        userProfileDTO.setUserName(currUser.getUserName());
        userProfileDTO.setDescription(description);

        userProfileMapper.updateDescription(userProfileDTO);

        currUser.setDescription(description);
        redisTemplate.opsForValue().set("token_"+token, JSON.toJSONString(currUser),
                redisTemplate.getExpire("token_"+token), TimeUnit.SECONDS);

        responseBody.put("msg", "update description success!");
        return responseBody;
    }

    @Override
    public Map<String, String> updatePrefTag(String token, String prefTag) {
        User currUser = JSON.parseObject(redisTemplate.opsForValue().get("token_"+token), User.class);

        responseBody.clear();

        if (currUser == null) {
            responseBody.put("error", "token is invalid!");
            return responseBody;
        }

        if (currUser.getPrefTag() != null || !currUser.getPrefTag().isEmpty() && currUser.getPrefTag().equals(prefTag)) {
            responseBody.put("error", "duplicate preTag!");
            return responseBody;
        }

        UserProfileDTO userProfileDTO = new UserProfileDTO();
        userProfileDTO.setUserName(currUser.getUserName());
        userProfileDTO.setPreTag(prefTag);

        userProfileMapper.updatePrefTag(userProfileDTO);

        currUser.setPrefTag(prefTag);
        redisTemplate.opsForValue().set("token_"+token, JSON.toJSONString(currUser),
                redisTemplate.getExpire("token_"+token), TimeUnit.SECONDS);

        responseBody.put("msg", "update preTag success!");
        return responseBody;
    }

    @Override
    public Map<String, String> updateQualification(String token, String qualification) {
        User currUser = JSON.parseObject(redisTemplate.opsForValue().get("token_"+token), User.class);

        responseBody.clear();

        if (currUser == null) {
            responseBody.put("error", "token is invalid!");
            return responseBody;
        }

        if (currUser.getQualification() != null && currUser.getQualification().equals(qualification)) {
            responseBody.put("error", "duplicated qualification!");
            return responseBody;
        }

        UserProfileDTO userProfileDTO = new UserProfileDTO();
        userProfileDTO.setUserName(currUser.getUserName());
        userProfileDTO.setQualification(qualification);

        userProfileMapper.updateQualification(userProfileDTO);

        currUser.setQualification(qualification);
        redisTemplate.opsForValue().set("token_"+token, JSON.toJSONString(currUser),
                redisTemplate.getExpire("token_"+token), TimeUnit.SECONDS);

        responseBody.put("msg", "udpate qualification success!");
        return responseBody;
    }

    @Override
    public Map<String, String> updateBankDetails(String token, String bankDetails) {
        User currUser = JSON.parseObject(redisTemplate.opsForValue().get("token_"+token), User.class);

        responseBody.clear();

        if (currUser == null) {
            responseBody.put("error", "token is invalid!");
            return responseBody;
        }

        if (currUser.getBankDetails() != null || !currUser.getBankDetails().isEmpty() && currUser.getBankDetails().equals(bankDetails)) {
            responseBody.put("error", "duplicated bankDetails!");
            return responseBody;

        }

        UserProfileDTO userProfileDTO = new UserProfileDTO();
        userProfileDTO.setUserName(currUser.getUserName());
        userProfileDTO.setBankDetails(bankDetails);

        userProfileMapper.updateBankDetails(userProfileDTO);

        currUser.setBankDetails(bankDetails);
        redisTemplate.opsForValue().set("token_"+token, JSON.toJSONString(currUser),
                redisTemplate.getExpire("token_"+token), TimeUnit.SECONDS);

        responseBody.put("msg", "update bankDetails success!");
        return responseBody;
    }

    @Override
    public Map<String, String> updateBalance(String token, BigDecimal balance) {
        User currUser = JSON.parseObject(redisTemplate.opsForValue().get("token_" + token), User.class);

        responseBody.clear();


        if (currUser == null || currUser.getBalance() == null) {
            responseBody.put("error", "token is invalid!");
            return responseBody;
        }

        UserProfileDTO userProfileDTO = new UserProfileDTO();
        userProfileDTO.setUserName(currUser.getUserName());
        userProfileDTO.setBalance(balance);

        userProfileMapper.updateBalance(userProfileDTO);

        currUser.setBalance(balance);
        redisTemplate.opsForValue().set("token_"+token, JSON.toJSONString(currUser),
                redisTemplate.getExpire("token_"+token), TimeUnit.SECONDS);

        responseBody.put("msg", "update balance succeed!");

        return responseBody;
    }

    @Override
    public Map<String, String> updatePassword(String token, String password) {
        User currUser = JSON.parseObject(redisTemplate.opsForValue().get("token_"+token), User.class);

        responseBody.clear();

        if (currUser == null) {
            responseBody.put("error", "token is invalid!");
            return responseBody;
        }

        if (currUser.getPassword() != null && currUser.getPassword().equals(password)) {
            responseBody.put("error", "duplicated password!");
            return responseBody;
        }

        UserProfileDTO userProfileDTO = new UserProfileDTO();
        userProfileDTO.setUserName(currUser.getUserName());
        userProfileDTO.setPassword(password);

        userProfileMapper.updatePassword(userProfileDTO);

        currUser.setPassword(password);
        redisTemplate.opsForValue().set("token_"+token, JSON.toJSONString(currUser),
                redisTemplate.getExpire("token_"+token), TimeUnit.SECONDS);

        responseBody.put("msg", "update password success!");
        return responseBody;
    }
}
