package com.powerrangers.system.modules.OrderManagement.domain;

import com.powerrangers.common.base.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class Order extends BaseEntity {

    private Integer orderId;

    private Integer eventId;

    private Integer customerId;

    private Integer hostId;

    private String ticketType;

    private Integer paymentType;

    private BigDecimal paymentAmount;

    private Boolean isPaid;

    private Boolean isRefund;

    private String eventName;

    private String hostName;
}
