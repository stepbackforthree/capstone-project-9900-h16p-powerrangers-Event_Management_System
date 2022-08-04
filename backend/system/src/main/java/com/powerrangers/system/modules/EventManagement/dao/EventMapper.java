package com.powerrangers.system.modules.EventManagement.dao;

import com.powerrangers.system.modules.EventManagement.service.dto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EventMapper {

    void createEvent(SmallEventDTO smallEventDTO);

    void createEventInsertFullPriceTicket(SmallEventDTO smallEventDTO);

    Integer checkExist(EventModifyDTO eventModifyDTO);

    void updateEventName(EventModifyDTO eventModifyDTO);

    void updateEventTime(EventModifyDTO eventModifyDTO);

    void updateEventDescription(EventModifyDTO eventModifyDTO);

    void updateEventAddress(EventModifyDTO eventModifyDTO);

    void updateEventType(EventModifyDTO eventModifyDTO);

    void changeEventCancelState(EventModifyDTO eventModifyDTO);

    void changeEventDisplayState(EventModifyDTO eventModifyDTO);

    void changeEventTag(EventModifyDTO eventModifyDTO);

    EventDTO queryEvent(EventModifyDTO eventModifyDTO);

    List<EventDTO> getEvents(Integer hostId);

    List<EventDTO> getAllEvents(EventFilterDTO eventFilterDTO);

    List<EventDTO> searchEvents(String keyWords);

    Integer checkUserExist(String userName);

    Integer checkSpendingHistory(String userName);

    List<OrderDTO> getSpendingHistory(String userName);

    void updateStarLevel(EventDTO eventDTO);

    List<EventDTO> getOneMonthEvents();

    List<EventDTO> randomRecommendation();

    List<EventDTO> getEventsByType(String typeName);

    void updateImage(EventModifyDTO eventModifyDTO);
}
