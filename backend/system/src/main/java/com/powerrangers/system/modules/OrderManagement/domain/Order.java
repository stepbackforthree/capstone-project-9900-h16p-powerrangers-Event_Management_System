package com.powerrangers.system.modules.OrderManagement.domain;

import com.powerrangers.common.base.BaseEntity;
import lombok.Data;

@Data
public class Order extends BaseEntity {

    private Integer orderId;

    private Integer eventId;

    private Integer customerId;

    private Integer hostId;

    private Integer paymentType;

    private Double paymentAmount;

    private Boolean isPaid;

    private Boolean isRefund;
}
