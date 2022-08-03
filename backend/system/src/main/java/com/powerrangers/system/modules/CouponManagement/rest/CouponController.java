package com.powerrangers.system.modules.CouponManagement.rest;

import com.powerrangers.system.modules.CouponManagement.service.CouponService;
import com.powerrangers.system.modules.CouponManagement.service.dto.CouponDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/coupon")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CouponController {

    @Autowired
    private CouponService couponService;

    @ApiOperation("add coupon")
    @PostMapping("addCoupon")
    public ResponseEntity<Object> addCoupon(@RequestHeader("Authorization") String token, @RequestBody CouponDTO couponDTO) {
        return couponService.addCoupon(token, couponDTO);
    }

    @ApiOperation("get coupon info by coupon name")
    @GetMapping("getCoupon")
    public ResponseEntity<Object> getCoupon(@RequestHeader("Authorization") String token, @RequestParam String couponName) {
        return couponService.getCoupon(token, couponName);
    }

    @ApiOperation("change visibility of a coupon")
    @PutMapping("changeVisibility")
    public ResponseEntity<Object> changeVisibility(@RequestHeader("Authorization") String token, @RequestBody Map<String, String> request) {
        return couponService.changeVisibility(token, request.get("couponName"));
    }

    @ApiOperation("get coupon list for specific event")
    @GetMapping("getCoupons")
    public ResponseEntity<Object> getCoupons(@RequestHeader("Authorization") String token, @RequestParam Integer eventId) {
        return couponService.getCoupons(token, eventId);
    }

    @ApiOperation("get visible coupon list for specific event")
    @GetMapping("getVisibleCoupons")
    public ResponseEntity<Object> getVisibleCoupons(@RequestHeader("Authorization") String token, @RequestParam Integer eventId) {
        return couponService.getVisibleCoupons(token, eventId);
    }

    @ApiOperation("update the amount of a coupon")
    @PostMapping("updateCouponAmount")
    public ResponseEntity<Object> updateCouponAmount(@RequestHeader("Authorization") String token, @RequestBody CouponDTO couponDTO) {
        return couponService.updateCouponAmount(token, couponDTO);
    }
}
