package com.powerrangers.system.modules.UserAccess.domain;

import com.powerrangers.common.base.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class User extends BaseEntity {
    private Integer id;

    private Integer roleId;

    private String userName;

    private String password;

    private String nickName;

    private String avatar;

    private String description;

    private String email;

    private String phoneNumber;

    private String prefTag;

    private BigDecimal balance;

    private String qualification;

    private Boolean isAuth;

    private Boolean isVerified;

    private Boolean isReceived;

    private String bankDetails;
}
