package com.powerrangers.system.modules.userAccess.service;

import com.powerrangers.system.modules.userAccess.domain.User;

public interface UserService {
    Boolean check(User user);

    Boolean create(User user);
}
