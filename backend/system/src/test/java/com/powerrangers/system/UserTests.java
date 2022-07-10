package com.powerrangers.system;

import com.powerrangers.system.modules.userAccess.service.UserService;
import com.powerrangers.system.modules.userAccess.service.dto.SmallUserDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserTests {

    @Autowired
    UserService userService;

    @Test
    void checkExistTest() {
        SmallUserDTO userDTO = new SmallUserDTO();
        userDTO.setUserName("niko");
        userDTO.setPassword("nk123");
        userDTO.setEmail("nikonikoni@gmail.com");
        userDTO.setRole("customer");

        System.out.println(userService.checkExist(userDTO));
    }

    @Test
    void addUserTest() {
        SmallUserDTO userDTO = new SmallUserDTO();
        userDTO.setUserName("messi");
        userDTO.setPassword("ms123");
        userDTO.setEmail("lionel@gmail.com");
        userDTO.setRole("customer");
        userDTO.setIsReceived(false);

        System.out.println(userService.createUser(userDTO));
    }
}
