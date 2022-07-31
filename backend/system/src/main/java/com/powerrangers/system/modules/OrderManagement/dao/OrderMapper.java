package com.powerrangers.system.modules.OrderManagement.dao;

import com.powerrangers.system.modules.OrderManagement.domain.Order;
import com.powerrangers.system.modules.OrderManagement.service.dto.OrderDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {

    Order queryOrderById(Integer orderId);

    void insertOrder(OrderDTO orderDTO);

    void refundOrder(Integer orderId);

    Order getOrder(OrderDTO orderDTO);

    List<Order> queryOrdersByCustomer(Integer customerId);

    List<Order> queryEventOrdersByHost(String hostName, String eventName);

    List<Order> getAllOrders(OrderDTO orderDTO);
}
