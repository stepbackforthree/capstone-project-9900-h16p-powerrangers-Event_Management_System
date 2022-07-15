package com.powerrangers.system.modules.eventManagement.service.dto;

import com.powerrangers.common.base.BaseDTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@ApiModel(value = "Event create object")
@Data
public class SmallEventDTO extends BaseDTO implements Serializable {

    private Integer hostId;

    private String eventName;

    private Integer eventType;

    private String siteName;

    private Timestamp startTime;

    private Timestamp endTime;

    private Boolean isDisplayed;

    private Byte[] image;

    private String description;
}
