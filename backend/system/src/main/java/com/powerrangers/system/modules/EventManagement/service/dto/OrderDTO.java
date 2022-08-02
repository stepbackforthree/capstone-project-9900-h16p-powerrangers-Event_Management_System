package com.powerrangers.system.modules.EventManagement.service.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderDTO implements Serializable {
    Integer eventId;

    String eventName;

    String eventType;

    String customerName;

    String hostName;

    String description;

    String paymentType;

    Double paymentAmount;

}
