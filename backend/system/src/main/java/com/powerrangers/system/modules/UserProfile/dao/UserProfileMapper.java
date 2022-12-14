package com.powerrangers.system.modules.UserProfile.dao;

import com.powerrangers.system.modules.UserProfile.service.dto.UserProfileDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserProfileMapper {
    void updateNickname(UserProfileDTO userProfileDTO);

    void updateEmail(UserProfileDTO userProfileDTO);

    void updateAvatar(UserProfileDTO userProfileDTO);

    void updateDescription(UserProfileDTO userProfileDTO);

    void updatePrefTag(UserProfileDTO userProfileDTO);

    void updateQualification(UserProfileDTO userProfileDTO);

    void updateBankDetails(UserProfileDTO userProfileDTO);

    void updateBalance(UserProfileDTO userProfileDTO);

    void updatePassword(UserProfileDTO userProfileDTO);
}
