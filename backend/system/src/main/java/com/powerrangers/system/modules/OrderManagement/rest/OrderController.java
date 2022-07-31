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

    @ApiOperation(value = "Customer canceal the order and refund")
    @GetMapping(value = "refundOrder")
    public ResponseEntity<Object> refundOrder(@RequestHeader("Authorization") String token, @RequestParam Integer orderId) {
        return orderService.refundOrder(token, orderId);
    }

    @ApiOperation(value = "get orders for specific event")
    @GetMapping("queryEventOrderByHost")
    public ResponseEntity<Object> queryEventOrdersByHost(@RequestHeader("Authorization") String token, @RequestParam String hostName, @RequestParam String eventName) {
        return orderService.queryEventOrdersByHost(token, hostName, eventName);
    }

    @ApiOperation(value = "get orders for specific customer")
    @GetMapping("queryOrdersByCustomer")
    public ResponseEntity<Object> queryOrdersByCustomer(@RequestHeader("Authorization") String token) {
        return orderService.queryOrdersByCustomer(token);
    }

}
