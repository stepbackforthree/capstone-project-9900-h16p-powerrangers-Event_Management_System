package com.powerrangers.system.modules.userAccess.dao;

import com.powerrangers.system.modules.userAccess.domain.User;
import com.powerrangers.system.modules.userAccess.service.dto.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    Integer checkExist(SmallUserDTO smallUserDTO);

    void addUser(SmallUserDTO smallUserDTO);

    UserDTO queryUser(SmallUserDTO smallUserDTO);

    void resetPassword(@Param("email") String email, @Param("password") String password);

    Integer getUserIdByUserName(String userName);

    EventInfoDTO queryEvent(EmailDTO emailDTO);
}
