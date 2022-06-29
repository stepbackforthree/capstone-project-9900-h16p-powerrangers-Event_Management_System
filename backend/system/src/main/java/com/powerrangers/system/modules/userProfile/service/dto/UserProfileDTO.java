package com.powerrangers.system.modules.userProfile.service.dto;

import lombok.Data;

@Data
public class UserProfileDTO {
    private String username;

    private String password;

    private String nickname;

    private Byte[] avatar;

    private String description;

    private String email;

    private String phoneNumber;

    private String preTag;

    private Byte[] qualification;

    private String bankDetails;
}
