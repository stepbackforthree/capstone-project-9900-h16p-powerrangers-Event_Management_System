package com.powerrangers.system.modules.OrderManagement.service;

import com.powerrangers.system.modules.OrderManagement.service.dto.OrderDTO;
import org.springframework.http.ResponseEntity;

public interface OrderService {

    ResponseEntity<Object> insertOrder(String token, OrderDTO orderDTO);

    ResponseEntity<Object> refundOrder(String token, Integer orderId);

    ResponseEntity<Object> queryEventOrdersByHost(String token, Integer eventId);

    ResponseEntity<Object> queryOrdersByCustomer(String token);
}
