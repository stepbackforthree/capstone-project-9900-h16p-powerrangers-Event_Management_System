package com.powerrangers.system.modules.userAccess.service;

import com.powerrangers.system.modules.userAccess.service.dto.SmallUserDTO;
import org.springframework.http.ResponseEntity;

public interface UserService {
    Boolean checkExist(SmallUserDTO smallUserDTO);

    Boolean createUser(SmallUserDTO smallUserDTO);

    ResponseEntity<Object> login(SmallUserDTO smallUserDTO);

    ResponseEntity<Object> logout(String token);

    Object findCurrentUserByToken(String token);

    Boolean checkPassword(String token, String password);
}
