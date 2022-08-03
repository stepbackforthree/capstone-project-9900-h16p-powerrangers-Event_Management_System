package com.powerrangers.system.modules.UserProfile.rest;

import com.powerrangers.system.modules.UserProfile.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserProfileController {

    private final UserProfileService userProfileService;

    @PostMapping(value = "updateNickname")
    public ResponseEntity<Object> updateNickname(@RequestHeader("Authorization") String token, @RequestBody Map<String, String> request) {
        return new ResponseEntity<>(userProfileService.updateNickname(token, request.get("nickName")), HttpStatus.OK);
    }

    @PostMapping(value = "updateEmail")
    public ResponseEntity<Object> updateEmail(@RequestHeader("Authorization") String token, @RequestBody Map<String, String> request) {
        return new ResponseEntity<>(userProfileService.updateEmail(token, request.get("email")), HttpStatus.OK);
    }

    @PostMapping(value = "updateAvatar")
    public ResponseEntity<Object> updateAvatar(@RequestHeader("Authorization") String token, @RequestBody Map<String, String> request) {
        return new ResponseEntity<>(userProfileService.updateAvatar(token, request.get("avatar")), HttpStatus.OK);
    }

    @PostMapping(value = "updateDescription")
    public ResponseEntity<Object> updateDescription(@RequestHeader("Authorization") String token, @RequestBody Map<String, String> request) {
        return new ResponseEntity<>(userProfileService.updateDescription(token, request.get("description")), HttpStatus.OK);
    }

    @PostMapping(value = "updatePrefTag")
    public ResponseEntity<Object> updatePrefTag(@RequestHeader("Authorization") String token, @RequestBody Map<String, String> request) {
        return new ResponseEntity<>(userProfileService.updatePrefTag(token, request.get("prefTag")), HttpStatus.OK);
    }

    @PostMapping(value = "updateQualification")
    public ResponseEntity<Object> updateQualification(@RequestHeader("Authorization") String token, @RequestBody Map<String, String> request) {
        return new ResponseEntity<>(userProfileService.updateQualification(token, request.get("qualification")), HttpStatus.OK);
    }

    @PostMapping(value = "updateBankDetails")
    public ResponseEntity<Object> updateBankDetails(@RequestHeader("Authorization") String token, @RequestBody Map<String, String> request) {
        return new ResponseEntity<>(userProfileService.updateBankDetails(token, request.get("bankDetails")), HttpStatus.OK);
    }

    @PostMapping(value = "updatePassword")
    public ResponseEntity<Object> updatePassword(@RequestHeader("Authorization") String token, @RequestBody Map<String, String> request) {
        return new ResponseEntity<>(userProfileService.updatePassword(token, request.get("password")), HttpStatus.OK);
    }
}
