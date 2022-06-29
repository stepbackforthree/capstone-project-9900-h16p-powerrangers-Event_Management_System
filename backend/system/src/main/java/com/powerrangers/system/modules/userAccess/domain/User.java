package com.powerrangers.system.modules.userAccess.domain;

import com.powerrangers.common.base.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class User extends BaseEntity {
    private Integer id;

    private Integer roleId;

    private String username;

    private String password;

    private String nickname;

    private Byte[] avatar;

    private String description;

    private String email;

    private String phoneNumber;

    private String prefTag;

    private BigDecimal balance;

    private Byte[] qualification;

    private Boolean isAuth;

    private Boolean isVerified;

    private Boolean isReceived;

    private String bankDetails;
}
