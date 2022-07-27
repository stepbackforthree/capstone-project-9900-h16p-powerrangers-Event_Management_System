package com.powerrangers.system.modules.UserProfile.service.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UserProfileDTO {
    private String userName;

    private String password;

    private String nickName;

    private String avatar;

    private String description;

    private String email;

    private String phoneNumber;

    private String preTag;

    private BigDecimal balance;

    private String qualification;

    private String bankDetails;
}
