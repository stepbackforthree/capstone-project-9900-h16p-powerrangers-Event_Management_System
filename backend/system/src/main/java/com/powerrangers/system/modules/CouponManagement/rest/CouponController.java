package com.powerrangers.system.modules.CouponManagement.rest;

import com.powerrangers.system.modules.CouponManagement.service.dto.CouponDTO;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/coupon")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CouponController {

    @ApiOperation("add coupon")
    @PostMapping("addCoupon")
    public ResponseEntity<Object> addCoupon(@RequestHeader("Authorization") String token, @RequestBody CouponDTO couponDTO) {
        return null;
    }
}
