package com.powerrangers.system.modules.userProfile.rest;

import com.powerrangers.system.modules.userProfile.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("profile")
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;

    @PostMapping(value = "updateNickname")
    public ResponseEntity<Object> updateNickname(@RequestHeader("Authorization") String token, @RequestParam String nickname) {
        return new ResponseEntity<>(userProfileService.updateNickname(token, nickname), HttpStatus.OK);
    }

    @PostMapping(value = "updateEmail")
    public ResponseEntity<Object> updateEmail(@RequestHeader String token, @RequestParam String email) {
        return new ResponseEntity<>(userProfileService.updateEmail(token, email), HttpStatus.OK);
    }

    @PostMapping(value = "updateAvatar")
    public ResponseEntity<Object> updateAvatar(@RequestHeader String token, @RequestParam Byte[] avatar) {
        return new ResponseEntity<>(userProfileService.updateAvatar(token, avatar), HttpStatus.OK);
    }

    @PostMapping(value = "updateDescription")
    public ResponseEntity<Object> updateDescription(@RequestHeader String token, @RequestParam String description) {
        return new ResponseEntity<>(userProfileService.updateDescription(token, description), HttpStatus.OK);
    }

    @PostMapping(value = "updatePrefTag")
    public ResponseEntity<Object> updatePrefTag(@RequestHeader String token, @RequestParam String prefTag) {
        return new ResponseEntity<>(userProfileService.updatePrefTag(token, prefTag), HttpStatus.OK);
    }

    @PostMapping(value = "updateQualification")
    public ResponseEntity<Object> updateQualification(@RequestHeader String token, @RequestParam Byte[] qualification) {
        return new ResponseEntity<>(userProfileService.updateQualification(token, qualification), HttpStatus.OK);
    }

    @PostMapping(value = "updateBankDetails")
    public ResponseEntity<Object> updateBankDetails(@RequestHeader String token, @RequestParam String bankDetails) {
        return new ResponseEntity<>(userProfileService.updateBankDetails(token, bankDetails), HttpStatus.OK);
    }
}
