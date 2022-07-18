package com.powerrangers.system.modules.userProfile.service.impl;

import com.alibaba.fastjson.JSON;
import com.powerrangers.system.modules.userAccess.domain.User;
import com.powerrangers.system.modules.userProfile.dao.UserProfileMapper;
import com.powerrangers.system.modules.userProfile.service.UserProfileService;
import com.powerrangers.system.modules.userProfile.service.dto.UserProfileDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private final UserProfileMapper userProfileMapper;

    @Override
    public String updateNickname(String token, String nickName) {

        User currUser = JSON.parseObject(redisTemplate.opsForValue().get("token_"+token), User.class);

        if (currUser.getNickName().equals(nickName)) {
            return "duplicated nickname!";
        }

        UserProfileDTO userProfileDTO = new UserProfileDTO();
        userProfileDTO.setUserName(currUser.getUserName());
        userProfileDTO.setNickName(nickName);

        userProfileMapper.updateNickname(userProfileDTO);

        currUser.setNickName(nickName);
        redisTemplate.opsForValue().set("token_"+token, JSON.toJSONString(currUser),
                redisTemplate.getExpire("token_"+token), TimeUnit.SECONDS);

        return "update nickname success!";
    }

    @Override
    public String updateEmail(String token, String email) {
        User currUser = JSON.parseObject(redisTemplate.opsForValue().get("token_"+token), User.class);

        if (currUser.getEmail().equals(email)) {
            return "duplicated email!";
        }

        UserProfileDTO userProfileDTO = new UserProfileDTO();
        userProfileDTO.setUserName(currUser.getUserName());
        userProfileDTO.setEmail(email);

        userProfileMapper.updateEmail(userProfileDTO);

        currUser.setEmail(email);
        redisTemplate.opsForValue().set("token_"+token, JSON.toJSONString(currUser),
                redisTemplate.getExpire("token_"+token), TimeUnit.SECONDS);

        return "update email success!";
    }

    @Override
    public String updateAvatar(String token, Byte[] avatar) {
        User currUser = JSON.parseObject(redisTemplate.opsForValue().get("token_"+token), User.class);

        if (currUser.getAvatar() != null && currUser.getAvatar() == avatar) {
            return "duplicated avatar!";
        }

        UserProfileDTO userProfileDTO = new UserProfileDTO();
        userProfileDTO.setUserName(currUser.getUserName());
        userProfileDTO.setAvatar(avatar);

        userProfileMapper.updateAvatar(userProfileDTO);

        currUser.setAvatar(avatar);
        redisTemplate.opsForValue().set("token_"+token, JSON.toJSONString(currUser),
                redisTemplate.getExpire("token_"+token), TimeUnit.SECONDS);

        return "update avatar success!";
    }

    @Override
    public String updateDescription(String token, String description) {
        User currUser = JSON.parseObject(redisTemplate.opsForValue().get("token_"+token), User.class);

        if (currUser.getDescription() != null || !currUser.getDescription().isEmpty() && currUser.getDescription().equals(description)) {
            return "duplicated description!";
        }

        UserProfileDTO userProfileDTO = new UserProfileDTO();
        userProfileDTO.setUserName(currUser.getUserName());
        userProfileDTO.setDescription(description);

        userProfileMapper.updateDescription(userProfileDTO);

        currUser.setDescription(description);
        redisTemplate.opsForValue().set("token_"+token, JSON.toJSONString(currUser),
                redisTemplate.getExpire("token_"+token), TimeUnit.SECONDS);

        return "update description success!";
    }

    @Override
    public String updatePrefTag(String token, String prefTag) {
        User currUser = JSON.parseObject(redisTemplate.opsForValue().get("token_"+token), User.class);

        if (currUser.getPrefTag() != null || !currUser.getPrefTag().isEmpty() && currUser.getPrefTag().equals(prefTag)) {
            return "duplicated prefTag!";
        }

        UserProfileDTO userProfileDTO = new UserProfileDTO();
        userProfileDTO.setUserName(currUser.getUserName());
        userProfileDTO.setPreTag(prefTag);

        userProfileMapper.updatePrefTag(userProfileDTO);

        currUser.setPrefTag(prefTag);
        redisTemplate.opsForValue().set("token_"+token, JSON.toJSONString(currUser),
                redisTemplate.getExpire("token_"+token), TimeUnit.SECONDS);

        return "update prefTag success!";
    }

    @Override
    public String updateQualification(String token, Byte[] qualification) {
        User currUser = JSON.parseObject(redisTemplate.opsForValue().get("token_"+token), User.class);

        if (currUser.getQualification() != null && currUser.getQualification() == qualification) {
            return "duplicated qualification!";
        }

        UserProfileDTO userProfileDTO = new UserProfileDTO();
        userProfileDTO.setUserName(currUser.getUserName());
        userProfileDTO.setQualification(qualification);

        userProfileMapper.updateQualification(userProfileDTO);

        currUser.setQualification(qualification);
        redisTemplate.opsForValue().set("token_"+token, JSON.toJSONString(currUser),
                redisTemplate.getExpire("token_"+token), TimeUnit.SECONDS);

        return "update qualification success!";
    }

    @Override
    public String updateBankDetails(String token, String bankDetails) {
        User currUser = JSON.parseObject(redisTemplate.opsForValue().get("token_"+token), User.class);

        if (currUser.getBankDetails() != null || !currUser.getBankDetails().isEmpty() && currUser.getBankDetails().equals(bankDetails)) {
            return "duplicated bankDetails!";
        }

        UserProfileDTO userProfileDTO = new UserProfileDTO();
        userProfileDTO.setUserName(currUser.getUserName());
        userProfileDTO.setBankDetails(bankDetails);

        userProfileMapper.updateBankDetails(userProfileDTO);

        currUser.setBankDetails(bankDetails);
        redisTemplate.opsForValue().set("token_"+token, JSON.toJSONString(currUser),
                redisTemplate.getExpire("token_"+token), TimeUnit.SECONDS);

        return "update bankDetails success!";
    }

    @Override
    public String updatePassword(String token, String password) {
        User currUser = JSON.parseObject(redisTemplate.opsForValue().get("token_"+token), User.class);
        if (currUser.getPassword() != null && currUser.getPassword().equals(password)) {
            return "duplicated bankDetails!";
        }

        UserProfileDTO userProfileDTO = new UserProfileDTO();
        userProfileDTO.setUserName(currUser.getUserName());
        userProfileDTO.setPassword(password);

        userProfileMapper.updatePassword(userProfileDTO);

        currUser.setPassword(password);
        redisTemplate.opsForValue().set("token_"+token, JSON.toJSONString(currUser),
                redisTemplate.getExpire("token_"+token), TimeUnit.SECONDS);

        return "update password success!";
    }
}
