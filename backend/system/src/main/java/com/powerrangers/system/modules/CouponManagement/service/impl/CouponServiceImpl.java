package com.powerrangers.system.modules.CouponManagement.service.impl;

import com.alibaba.fastjson.JSON;
import com.powerrangers.system.modules.CouponManagement.dao.CouponMapper;
import com.powerrangers.system.modules.CouponManagement.domain.Coupon;
import com.powerrangers.system.modules.CouponManagement.service.CouponService;
import com.powerrangers.system.modules.CouponManagement.service.dto.CouponDTO;
import com.powerrangers.system.modules.UserAccess.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CouponServiceImpl implements CouponService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private CouponMapper couponMapper;

    private Map<String, String> responseBody = new HashMap<>();

    @Override
    public ResponseEntity<Object> addCoupon(String token, CouponDTO couponDTO) {

        User currUser = JSON.parseObject(redisTemplate.opsForValue().get("token_" + token), User.class);

        responseBody.clear();

        if (currUser == null) {
            responseBody.put("error", "token is invalid!");
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }

        if (couponMapper.getCoupon(couponDTO.getCouponName()).size() > 0) {
            responseBody.put("error", "coupon code is duplicated!");
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }

        couponMapper.addCoupon(couponDTO);
        couponMapper.addCouponRule(couponDTO);

        responseBody.put("msg", "add coupon succeed!");
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> getCoupon(String token, String couponName) {

        User currUser = JSON.parseObject(redisTemplate.opsForValue().get("token_" + token), User.class);

        responseBody.clear();

        if (currUser == null) {
            responseBody.put("error", "token is invalid!");
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }

        List<Coupon> coupon = couponMapper.getCoupon(couponName);

        if (coupon.size() == 0) {
            responseBody.put("error", "coupon does not exist!");
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(coupon.get(0), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> changeVisibility(String token, String couponName) {

        User currUser = JSON.parseObject(redisTemplate.opsForValue().get("token_" + token), User.class);

        responseBody.clear();

        if (currUser == null) {
            responseBody.put("error", "token is invalid!");
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }

        if (couponMapper.getCoupon(couponName).size() == 0) {
            responseBody.put("error", "coupon code does not exist!");
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }

        couponMapper.changeVisibility(couponName);

        responseBody.put("msg", "change visibility succeed!");
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> getCoupons(String token, Integer eventId) {

        User currUser = JSON.parseObject(redisTemplate.opsForValue().get("token_" + token), User.class);

        responseBody.clear();

        if (currUser == null) {
            responseBody.put("error", "token is invalid!");
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(couponMapper.getCoupons(eventId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> getVisibleCoupons(String token, Integer eventId) {

        User currUser = JSON.parseObject(redisTemplate.opsForValue().get("token_" + token), User.class);

        responseBody.clear();

        if (currUser == null) {
            responseBody.put("error", "token is invalid!");
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);

        }

        return new ResponseEntity<>(couponMapper.getVisibleCoupons(eventId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> updateCouponAmount(String token, CouponDTO couponDTO) {

        User currUser = JSON.parseObject(redisTemplate.opsForValue().get("token_" + token), User.class);

        responseBody.clear();

        if (currUser == null) {
            responseBody.put("error", "token is invalid!");
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);

        }

        List<Coupon> coupon = couponMapper.getCoupon(couponDTO.getCouponName());

        // check coupon code is exists or not
        if (coupon.size() == 0) {
            responseBody.put("error", "coupon code does not exist!");
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }

        // check coupon code amount is enough for use or not
        if (coupon.get(0).getAmount() < couponDTO.getAmount()) {
            responseBody.put("error", "coupon code is out of stock!");
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }

        // check coupon code is beyond valid time or not
        Timestamp currTime = new Timestamp(System.currentTimeMillis());
        if (coupon.get(0).getStartTime().after(currTime) || coupon.get(0).getEndTime().before(currTime)) {
            responseBody.put("error", "coupon code is exceed valid time!");
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }

        couponMapper.updateCouponAmount(couponDTO);
        responseBody.put("msg", "update coupon amount succeed!");
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }
}
