package com.powerrangers.system.modules.eventManagement.dao;

import com.powerrangers.system.modules.eventManagement.service.dto.SmallEventDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EventMapper {

    void createEvent(SmallEventDTO smallEventDTO);

    Integer checkExist(SmallEventDTO smallEventDTO);
}
