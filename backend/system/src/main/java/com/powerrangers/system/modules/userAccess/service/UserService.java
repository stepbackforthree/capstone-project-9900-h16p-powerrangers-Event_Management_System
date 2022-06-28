package com.powerrangers.system.modules.userAccess.service;

import com.powerrangers.system.modules.userAccess.service.dto.SmallUserDTO;
import org.springframework.http.ResponseEntity;

public interface UserService {
    Boolean checkExist(SmallUserDTO smallUserDTO);

    Boolean createUser(SmallUserDTO smallUserDTO);

    ResponseEntity login(SmallUserDTO smallUserDTO);

    public Object findCurrentUserByToken(String token);
}
