package com.powerrangers.system.modules.OrderManagement.service;

import com.powerrangers.system.modules.OrderManagement.service.dto.OrderDTO;
import org.springframework.http.ResponseEntity;

public interface OrderService {

    ResponseEntity<Object> insertOrder(String token, OrderDTO orderDTO);
}
