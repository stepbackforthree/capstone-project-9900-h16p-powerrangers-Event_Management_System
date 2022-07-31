package com.powerrangers.system.modules.CouponManagement.service.dto;

import com.powerrangers.common.base.BaseDTO;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CouponDTO extends BaseDTO {

    private String couponName;

    private Integer couponType;

    private BigDecimal threshold;

    private BigDecimal couponMoney;

    private Integer couponAmount;

    private String hostName;

    private String eventName;
}
