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
}
