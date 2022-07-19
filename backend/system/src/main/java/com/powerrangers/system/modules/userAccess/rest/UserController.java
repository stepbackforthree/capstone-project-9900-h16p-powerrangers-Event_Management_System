package com.powerrangers.system.modules.userAccess.rest;

import com.powerrangers.system.modules.userAccess.service.UserService;
import com.powerrangers.system.modules.userAccess.service.dto.SmallUserDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Api(description = "Interface of user login and sign up")
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

    @Autowired
    private final UserService userService;

    @ApiImplicitParam(name = "email", value = "email", required = true, dataType = "String")
    @PostMapping(value = "/sendEmail")
    public ResponseEntity<Object> sendEmail(@RequestParam String email) {
        try {
            return userService.sendEmail(email);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something error, please try again!", HttpStatus.BAD_REQUEST);
    }

    @ApiOperation(value = "User login")
    @PostMapping(value = "/logIn")
    public ResponseEntity<Object> logIn(@RequestBody SmallUserDTO smallUserDTO) {
        return userService.logIn(smallUserDTO);
    }

    @ApiOperation(value = "User sign up")
    @PostMapping(value = "/signUp")
    public ResponseEntity<Object> signUp(@RequestBody SmallUserDTO smallUserDTO) {
        if (userService.createUser(smallUserDTO)) {
            return new ResponseEntity<>("user sign up succeed!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("user already existed!", HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation(value = "User log out")
    @ApiImplicitParam(name = "token", value = "token", required = true, dataType = "String")
    @GetMapping(value = "/logOut")
    public ResponseEntity<Object> logout(@RequestHeader("Authorization") String token) {
        return userService.logout(token);
    }

    @ApiOperation(value = "User reset password")
    @ApiImplicitParams({@ApiImplicitParam(name = "email", value = "email", required = true, dataType = "String"), @ApiImplicitParam(name = "password", value = "password", required = true, dataType = "String")})
    @PostMapping(value = "resetPassword")
    public ResponseEntity<Object> resetPassword(@RequestBody SmallUserDTO smallUserDTO) {
        return userService.resetPassword(smallUserDTO);
    }

    @ApiOperation(value = "information of specific user")
    @GetMapping(value = "queryUser")
    public ResponseEntity<Object> queryUser(@RequestHeader("Authorization") String token) {
        return userService.queryUser(token);
    }
}
