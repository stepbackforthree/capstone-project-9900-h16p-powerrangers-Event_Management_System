package com.powerrangers.system.modules.eventManagement.dao;

import com.powerrangers.system.modules.eventManagement.service.dto.TicketDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EventTicketMapper {

    void insertEventTicketType(TicketDTO ticketDTO);

    List<TicketDTO> getTicketType(TicketDTO ticketDTO);
}
