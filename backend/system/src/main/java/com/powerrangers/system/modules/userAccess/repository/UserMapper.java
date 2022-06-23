package com.powerrangers.system.modules.userAccess.repository;

import com.powerrangers.system.modules.userAccess.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    public Boolean check(User user);

    public void addHost(User user);

    public void addCustomer(User user);

    public void addAdmin(User user);
}
