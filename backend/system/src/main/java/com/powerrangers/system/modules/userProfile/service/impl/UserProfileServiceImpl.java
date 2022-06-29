package com.powerrangers.system.modules.userProfile.service.impl;

import com.alibaba.fastjson.JSON;
import com.powerrangers.system.modules.userAccess.domain.User;
import com.powerrangers.system.modules.userProfile.dao.UserProfileMapper;
import com.powerrangers.system.modules.userProfile.service.UserProfileService;
import com.powerrangers.system.modules.userProfile.service.dto.UserProfileDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserProfileServiceImpl implements UserProfileService {

    private final RedisTemplate<String, String> redisTemplate;

    private final UserProfileMapper userProfileMapper;

    @Override
    public String updateNickname(String token, String nickname) {

        User currUser = JSON.parseObject(redisTemplate.opsForValue().get("token_"+token), User.class);

        if (currUser.getNickname().equals(nickname)) {
            return "duplicated nickname!";
        }

        UserProfileDTO userProfileDTO = new UserProfileDTO();
        userProfileDTO.setUsername(currUser.getUsername());
        userProfileDTO.setNickname(nickname);

        userProfileMapper.updateNickname(userProfileDTO);

        return "update nickname success!";
    }

    @Override
    public String updateEmail(String token, String email) {
        User currUser = JSON.parseObject(redisTemplate.opsForValue().get("token_"+token), User.class);

        if (currUser.getEmail().equals(email)) {
            return "duplicated email!";
        }

        UserProfileDTO userProfileDTO = new UserProfileDTO();
        userProfileDTO.setUsername(currUser.getUsername());
        userProfileDTO.setEmail(email);

        userProfileMapper.updateEmail(userProfileDTO);

        return "update email success!";
    }

    @Override
    public String updateAvatar(String token, Byte[] avatar) {
        User currUser = JSON.parseObject(redisTemplate.opsForValue().get("token_"+token), User.class);

        if (currUser.getAvatar() != null && currUser.getAvatar() == avatar) {
            return "duplicated avatar!";
        }

        UserProfileDTO userProfileDTO = new UserProfileDTO();
        userProfileDTO.setUsername(currUser.getUsername());
        userProfileDTO.setAvatar(avatar);

        userProfileMapper.updateAvatar(userProfileDTO);

        return "update avatar success!";
    }

    @Override
    public String updateDescription(String token, String description) {
        User currUser = JSON.parseObject(redisTemplate.opsForValue().get("token_"+token), User.class);

        if (currUser.getDescription() != null || !currUser.getDescription().isEmpty() && currUser.getDescription().equals(description)) {
            return "duplicated description!";
        }

        UserProfileDTO userProfileDTO = new UserProfileDTO();
        userProfileDTO.setUsername(currUser.getUsername());
        userProfileDTO.setDescription(description);

        userProfileMapper.updateDescription(userProfileDTO);

        return "update description success!";
    }

    @Override
    public String updatePrefTag(String token, String prefTag) {
        User currUser = JSON.parseObject(redisTemplate.opsForValue().get("token_"+token), User.class);

        if (currUser.getPrefTag() != null || !currUser.getPrefTag().isEmpty() && currUser.getPrefTag().equals(prefTag)) {
            return "duplicated prefTag!";
        }

        UserProfileDTO userProfileDTO = new UserProfileDTO();
        userProfileDTO.setUsername(currUser.getUsername());
        userProfileDTO.setPreTag(prefTag);

        userProfileMapper.updatePrefTag(userProfileDTO);

        return "update prefTag success!";
    }

    @Override
    public String updateQualification(String token, Byte[] qualification) {
        User currUser = JSON.parseObject(redisTemplate.opsForValue().get("token_"+token), User.class);

        if (currUser.getQualification() != null && currUser.getQualification() == qualification) {
            return "duplicated qualification!";
        }

        UserProfileDTO userProfileDTO = new UserProfileDTO();
        userProfileDTO.setUsername(currUser.getUsername());
        userProfileDTO.setQualification(qualification);

        userProfileMapper.updateQualification(userProfileDTO);

        return "update qualification success!";
    }

    @Override
    public String updateBankDetails(String token, String bankDetails) {
        User currUser = JSON.parseObject(redisTemplate.opsForValue().get("token_"+token), User.class);

        if (currUser.getBankDetails() != null || !currUser.getBankDetails().isEmpty() && currUser.getBankDetails().equals(bankDetails)) {
            return "duplicated bankDetails!";
        }

        UserProfileDTO userProfileDTO = new UserProfileDTO();
        userProfileDTO.setUsername(currUser.getUsername());
        userProfileDTO.setBankDetails(bankDetails);

        userProfileMapper.updateBankDetails(userProfileDTO);

        return "update bankDetails success!";
    }
}
