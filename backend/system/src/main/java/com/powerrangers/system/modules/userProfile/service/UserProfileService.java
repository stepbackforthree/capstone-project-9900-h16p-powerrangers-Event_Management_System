package com.powerrangers.system.modules.userProfile.service;

public interface UserProfileService {
    String updateNickname(String token, String nickname);

    String updateEmail(String token, String email);

    String updateAvatar(String token, Byte[] avatar);

    String updateDescription(String token, String description);

    String updatePrefTag(String token, String prefTag);

    String updateQualification(String token, Byte[] qualification);

    String updateBankDetails(String token, String bankDetails);

    String updatePassword(String token, String password);
}
