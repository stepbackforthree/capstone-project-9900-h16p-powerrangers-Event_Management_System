package com.powerrangers.system.modules.userProfile.service.dto;

import lombok.Data;

@Data
public class UserProfileDTO {
    private String userName;

    private String password;

    private String nickName;

    private Byte[] avatar;

    private String description;

    private String email;

    private String phoneNumber;

    private String preTag;

    private Byte[] qualification;

    private String bankDetails;
}
