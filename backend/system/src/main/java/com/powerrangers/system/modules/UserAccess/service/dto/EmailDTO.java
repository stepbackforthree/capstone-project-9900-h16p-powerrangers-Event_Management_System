package com.powerrangers.system.modules.UserAccess.service.dto;
import lombok.Data;


import java.io.Serializable;

@Data
public class EmailDTO implements Serializable {
    private String emailAddress;

    private Integer state;

    private String userName;

    private String eventName;
}
