package com.powerrangers.system.modules.OrderManagement.rest;

import com.powerrangers.system.modules.OrderManagement.service.OrderService;
import com.powerrangers.system.modules.OrderManagement.service.dto.OrderDTO;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "order")
@CrossOrigin(origins = "*", maxAge = 3600)
public class OrderController {

    @Autowired
    private final OrderService orderService;

    @ApiOperation(value = "Place one order record when a customer book a event")
    @PostMapping(value = "insertOrder")
    public ResponseEntity<Object> insertOrder(@RequestHeader("Authorization") String token, @RequestBody OrderDTO orderDTO) {
        return orderService.insertOrder(token, orderDTO);
    }
}
