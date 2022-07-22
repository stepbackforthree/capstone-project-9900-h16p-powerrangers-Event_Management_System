package com.powerrangers.system.modules.userAccess.service.dto;

import com.powerrangers.system.modules.userAccess.domain.User;
import lombok.Data;

import java.io.Serializable;

@Data
public class EmailDTO implements Serializable {
    private String emailAddress;
    private Integer state;
}
