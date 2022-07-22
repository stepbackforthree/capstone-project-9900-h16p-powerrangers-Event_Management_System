package com.powerrangers.system.modules.userProfile.service;

public interface UserProfileService {
    String updateNickname(String token, String nickName);

    String updateEmail(String token, String email);

    String updateAvatar(String token, String avatar);

    String updateDescription(String token, String description);

    String updatePrefTag(String token, String prefTag);

    String updateQualification(String token, String qualification);

    String updateBankDetails(String token, String bankDetails);

    String updatePassword(String token, String password);
}
