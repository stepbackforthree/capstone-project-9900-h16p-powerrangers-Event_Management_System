package com.powerrangers.system.modules.userAccess.dao;

import com.powerrangers.system.modules.userAccess.domain.User;
import com.powerrangers.system.modules.userAccess.service.dto.*;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    Integer checkExist(SmallUserDTO smallUserDTO);

    void addUser(SmallUserDTO smallUserDTO);

    UserDTO queryUser(SmallUserDTO smallUserDTO);
}
