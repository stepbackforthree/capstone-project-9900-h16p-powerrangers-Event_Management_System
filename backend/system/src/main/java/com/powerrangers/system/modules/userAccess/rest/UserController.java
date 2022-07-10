package com.powerrangers.system.modules.userAccess.rest;

import com.powerrangers.system.modules.userAccess.service.UserService;
import com.powerrangers.system.modules.userAccess.service.dto.SmallUserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

    @Autowired
    private final UserService userService;

    @PostMapping(value = "/sendemail")
    public ResponseEntity<Object> sendEmail(@RequestParam String email) {
        try {
            return userService.sendEmail(email);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something error, please try again!", HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<Object> login(@RequestBody SmallUserDTO smallUserDTO) {
        return userService.login(smallUserDTO);
    }

    @PostMapping(value = "signup")
    public ResponseEntity<Object> signUp(@RequestBody SmallUserDTO smallUserDTO) {
        if (userService.createUser(smallUserDTO)) {
            return new ResponseEntity<>("user sign up succeed!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("user already existed!", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "logout")
    public ResponseEntity<Object> logout(@RequestHeader("Authorization") String token) {
        return userService.logout(token);
    }
}
