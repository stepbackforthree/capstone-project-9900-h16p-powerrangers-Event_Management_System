package com.powerrangers.system.modules.userAccess.service.impl;

import com.powerrangers.system.modules.userAccess.domain.User;
import com.powerrangers.system.modules.userAccess.repository.UserMapper;
import com.powerrangers.system.modules.userAccess.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;

    @Override
    public Boolean check(User user) {
        return false;
    }

    @Override
    public Boolean create(User user) {

        if (!check(user)) {
            if (user.getRole().equalsIgnoreCase("customer")) {
                userMapper.addCustomer(user);
            } else if (user.getRole().equalsIgnoreCase("host")) {
                userMapper.addHost(user);
            } else {
                userMapper.addAdmin(user);
            }

            return true;
        } else {
            return false;
        }
    }
}
