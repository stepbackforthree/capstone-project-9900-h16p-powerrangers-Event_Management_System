package com.powerrangers.system.modules.OrderManagement.service.dto;

import com.powerrangers.common.base.BaseDTO;
import com.powerrangers.system.modules.EventManagement.service.dto.TicketDTO;
import lombok.Data;

import java.io.Serializable;

@Data
public class OrderDTO extends TicketDTO implements Serializable {

    private Integer customerId;

    private Integer paymentType;
}
