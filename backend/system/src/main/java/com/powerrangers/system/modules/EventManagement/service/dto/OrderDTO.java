package com.powerrangers.system.modules.EventManagement.service.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderDTO implements Serializable {
    String eventName;
    String customerName;
    String hostName;
    String paymentType;
    Double paymentAmount;

}
