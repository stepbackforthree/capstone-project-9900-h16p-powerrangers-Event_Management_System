package com.powerrangers.system.modules.CouponManagement.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/coupon")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CouponController {


}
