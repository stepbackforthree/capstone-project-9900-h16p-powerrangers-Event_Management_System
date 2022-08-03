package com.powerrangers.system.modules.UserProfile.service;

import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Map;

public interface UserProfileService {
    Map<String, String> updateNickname(String token, String nickName);

    Map<String, String> updateEmail(String token, String email);

    Map<String, String> updateAvatar(String token, String avatar);

    Map<String, String> updateDescription(String token, String description);

    Map<String, String> updatePrefTag(String token, String prefTag);

    Map<String, String> updateQualification(String token, String qualification);

    Map<String, String> updateBankDetails(String token, String bankDetails);

    ResponseEntity<Object> updateBalance(String token, BigDecimal balance);

    Map<String, String> updatePassword(String token, String password);
}
