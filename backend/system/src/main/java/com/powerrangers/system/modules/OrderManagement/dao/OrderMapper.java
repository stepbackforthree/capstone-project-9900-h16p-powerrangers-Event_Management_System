package com.powerrangers.system.modules.OrderManagement.dao;

import com.powerrangers.system.modules.OrderManagement.service.dto.OrderDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {

    void insertOrder(OrderDTO orderDTO);
}
