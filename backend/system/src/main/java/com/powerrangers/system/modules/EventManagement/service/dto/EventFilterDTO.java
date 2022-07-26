package com.powerrangers.system.modules.EventManagement.service.dto;

import lombok.Data;

@Data
public class EventFilterDTO {

    private String location;

    private Integer starLevel;

    private Double minPrice;

    private Double maxPrice;

    private Boolean online;

    private Integer eventType;
}
