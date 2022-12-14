package com.powerrangers.system.modules.EventManagement.service.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;


@ApiModel(value = "Event modify object")
@Data
public class EventModifyDTO implements Serializable {
    private String eventName;

    private Integer hostId;

    private String  newString;

    private String  secondNewString;

    private Integer newInteger;

    private Timestamp newStartTime;

    private Timestamp newEndTime;

    private Boolean newBoolean;

}