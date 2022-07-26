package com.powerrangers.system.modules.UserAccess.service;

import com.powerrangers.system.modules.UserAccess.service.dto.EmailDTO;
import com.powerrangers.system.modules.UserAccess.service.dto.SmallUserDTO;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface UserService {
    Boolean checkExist(SmallUserDTO smallUserDTO);

    Boolean createUser(SmallUserDTO smallUserDTO);

    ResponseEntity<Object> logIn(SmallUserDTO smallUserDTO);

    ResponseEntity<Object> logout(String token);

    Object findCurrentUserByToken(String token);

    Boolean checkPassword(String token, String password);

    ResponseEntity<Object> sendEmail(EmailDTO emailDTO) throws IOException;

    ResponseEntity<Object> resetPassword(SmallUserDTO smallUserDTO);

    ResponseEntity<Object> queryUser(String token);
}
