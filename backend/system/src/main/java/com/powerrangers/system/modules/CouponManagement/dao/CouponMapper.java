package com.powerrangers.system.modules.CouponManagement.dao;

import com.powerrangers.system.modules.CouponManagement.domain.Coupon;
import com.powerrangers.system.modules.CouponManagement.service.dto.CouponDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CouponMapper {

    List<Coupon> getCoupon(String couponName);

    void addCoupon(CouponDTO couponDTO);

    void addCouponRule(CouponDTO couponDTO);

    void changeVisibility(String couponName);

    List<Coupon> getCoupons(Integer eventId);

    List<Coupon> getVisibleCoupons(Integer eventId);

    void updateCouponAmount(CouponDTO couponDTO);
}
