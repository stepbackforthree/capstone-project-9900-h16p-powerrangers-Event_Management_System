package com.powerrangers.system.modules.eventManagement.dao;

import com.powerrangers.system.modules.eventManagement.service.dto.EventModifyDTO;
import com.powerrangers.system.modules.eventManagement.service.dto.SmallEventDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EventMapper {

    void createEvent(SmallEventDTO smallEventDTO);

    Integer checkExist(EventModifyDTO eventModifyDTO);

    void updateEventName(EventModifyDTO eventModifyDTO);

    void updateEventTime(EventModifyDTO eventModifyDTO);

    void updateEventDescription(EventModifyDTO eventModifyDTO);

    void updateEventAddress(EventModifyDTO eventModifyDTO);

    void updateEventType(EventModifyDTO eventModifyDTO);

    void changeEventCancelState(EventModifyDTO eventModifyDTO);

    void changeEventDisplayState(EventModifyDTO eventModifyDTO);

    void changeEventTag(EventModifyDTO eventModifyDTO);
}
