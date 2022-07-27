package com.powerrangers.system.modules.EventManagement.service.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class EventFilterDTO {

    private String location;

    private Integer starLevel;

    private BigDecimal minPrice;

    private BigDecimal maxPrice;

    private Boolean online;

    private Integer eventType;
}
