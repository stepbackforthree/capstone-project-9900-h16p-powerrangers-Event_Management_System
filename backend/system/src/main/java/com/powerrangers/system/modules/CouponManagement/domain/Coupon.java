package com.powerrangers.system.modules.CouponManagement.domain;

import com.powerrangers.common.base.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
public class Coupon extends BaseEntity implements Serializable {

    private Integer couponId;

    private String couponName;

    private Integer couponType;

    private Integer eventId;

    private Boolean isVisible;

    private Integer amount;

    private BigDecimal threshold;

    private BigDecimal money;

    private Timestamp startTime;

    private Timestamp endTime;
}
