package com.powerrangers.system.modules.userAccess.rest;

import com.powerrangers.system.modules.userAccess.domain.User;
import com.powerrangers.system.modules.userAccess.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping(value = "/login")
    public ResponseEntity<Object> checkUser(@RequestBody User user) {
        if (userService.check(user)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>("user not exists!", HttpStatus.OK);
        }
    }

    @PostMapping(value = "signup")
    public ResponseEntity<Object> createUser(@RequestBody User user) {
        if (userService.create(user)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>("existed user!", HttpStatus.OK);
        }
    }
}
