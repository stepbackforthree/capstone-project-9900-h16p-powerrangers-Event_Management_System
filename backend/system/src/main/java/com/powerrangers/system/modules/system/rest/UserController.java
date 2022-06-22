package com.powerrangers.system.modules.system.rest;

import com.powerrangers.system.modules.system.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    @GetMapping(value = "/login")
    public ResponseEntity<Object> createUser(@RequestBody User user) {
        return null;
    }
}
