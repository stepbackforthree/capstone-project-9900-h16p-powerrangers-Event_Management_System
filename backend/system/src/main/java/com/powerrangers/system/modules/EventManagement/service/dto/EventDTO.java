package com.powerrangers.system.modules.EventManagement.service.dto;

import com.powerrangers.system.modules.EventManagement.domain.Event;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Data
public class EventDTO extends Event  {

    private Integer ticketType;

    private Integer ticketAmount;

    private BigDecimal ticketPrice;

    private List<TicketDTO> tickets;

    private String hostName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        EventDTO eventDTO = (EventDTO) o;
        return Objects.equals(ticketType, eventDTO.ticketType) && Objects.equals(ticketAmount, eventDTO.ticketAmount) && Objects.equals(ticketPrice, eventDTO.ticketPrice) && Objects.equals(tickets, eventDTO.tickets) && Objects.equals(hostName, eventDTO.hostName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), ticketType, ticketAmount, ticketPrice, tickets, hostName);
    }
}
