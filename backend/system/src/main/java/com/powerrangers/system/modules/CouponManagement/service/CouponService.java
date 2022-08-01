package com.powerrangers.system.modules.CouponManagement.service;

import com.powerrangers.system.modules.CouponManagement.service.dto.CouponDTO;
import org.springframework.http.ResponseEntity;

public interface CouponService {

    ResponseEntity<Object> addCoupon(String token, CouponDTO couponDTO);

    ResponseEntity<Object> getCoupon(String token, String couponName);

    ResponseEntity<Object> changeVisibility(String token, String couponName);

    ResponseEntity<Object> getCoupons(String token, Integer eventId);

    ResponseEntity<Object> getVisibleCoupons(String token, Integer eventId);

    ResponseEntity<Object> updateCouponAmount(String token, CouponDTO couponDTO);
}
