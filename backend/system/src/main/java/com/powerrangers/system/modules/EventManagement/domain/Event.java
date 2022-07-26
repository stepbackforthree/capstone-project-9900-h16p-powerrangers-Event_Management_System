package com.powerrangers.system.modules.EventManagement.domain;

import com.powerrangers.common.base.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
public class Event extends BaseEntity {
    private Integer eventId;

    private String eventName;

    private Integer eventType;

    private String description;

    private String location;

    private String siteDescription;

    private Timestamp startTime;

    private Timestamp endTime;

    private Boolean isCancelled;

    private Boolean isDisplayed;

    private BigDecimal startLevel;

    private String eventTag;

    private String image;
}
