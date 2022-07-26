package com.powerrangers.system.modules.EventManagement.dao;

import com.powerrangers.system.modules.EventManagement.service.dto.TicketDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EventTicketMapper {

    void insertEventTicketType(TicketDTO ticketDTO);

    List<TicketDTO> getTicketType(TicketDTO ticketDTO);

    Integer getRemainTicketAmount(TicketDTO ticketDTO);

    void updateTicketAmount(TicketDTO ticketDTO);
}
