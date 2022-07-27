package com.powerrangers.system.modules.EventManagement.service.dto;

import com.powerrangers.common.base.BaseDTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

@ApiModel(value = "Event create object")
@Data
public class SmallEventDTO extends BaseDTO implements Serializable {

    private Integer hostId;

    private String eventName;

    private Integer eventType;

    private String location;

    private Timestamp startTime;

    private Timestamp endTime;

    private Boolean isDisplayed;

    private String image;

    private String description;

    private Integer ticketAmount;

    private BigDecimal ticketPrice;
}
