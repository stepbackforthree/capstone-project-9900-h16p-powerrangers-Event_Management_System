package com.powerrangers.system.modules.userAccess.service.dto;

import com.powerrangers.common.base.BaseDTO;
import lombok.Data;

import java.io.Serializable;

@Data
public class SmallUserDTO extends BaseDTO implements Serializable {

    private String username;

    private String email;

    private String password;

    private String role;

    private Boolean isReceived;
}
