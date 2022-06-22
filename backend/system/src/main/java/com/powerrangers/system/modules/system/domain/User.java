package com.powerrangers.system.modules.system.domain;

import com.powerrangers.common.base.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class User extends BaseEntity {
    private Integer roleId;

    private String username;

    private String nickname;

    private Byte[] avatar;

    private String password;

    private String description;

    private String email;

    private String phoneNumber;

    private BigDecimal balance;
}
