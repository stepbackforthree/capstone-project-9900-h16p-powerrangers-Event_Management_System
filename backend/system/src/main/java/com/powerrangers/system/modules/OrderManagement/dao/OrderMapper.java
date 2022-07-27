package com.powerrangers.system.modules.OrderManagement.dao;

import com.powerrangers.system.modules.OrderManagement.domain.Order;
import com.powerrangers.system.modules.OrderManagement.service.dto.OrderDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {

    void insertOrder(OrderDTO orderDTO);

    void refundOrder(OrderDTO orderDTO);

    Order getOrder(OrderDTO orderDTO);

    List<Order> queryOrdersByCustomer(OrderDTO orderDTO);

    List<Order> queryOrdersByHost(OrderDTO orderDTO);

    List<Order> getAllOrders(OrderDTO orderDTO);
}
