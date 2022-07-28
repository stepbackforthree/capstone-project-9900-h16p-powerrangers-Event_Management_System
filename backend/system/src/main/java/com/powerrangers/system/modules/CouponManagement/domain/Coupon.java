package com.powerrangers.system.modules.CouponManagement.domain;

import com.powerrangers.common.base.BaseEntity;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class Coupon extends BaseEntity {

    private String couponName;

    private Integer couponType;

    private Integer eventId;

    private Boolean isSpecified;

    private Integer assignAmount;

    private Integer usedAmount;

    private Timestamp receiveStartTime;

    private Timestamp receiveEndTime;

    private Timestamp validStartTime;

    private Timestamp validEndTime;
}
