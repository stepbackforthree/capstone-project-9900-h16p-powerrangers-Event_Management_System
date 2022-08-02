package com.powerrangers.system.modules.UserAccess.service.impl;

import com.alibaba.fastjson.JSON;
import com.powerrangers.common.utils.JWTUtils;
import com.powerrangers.system.modules.UserAccess.dao.UserMapper;
import com.powerrangers.system.modules.UserAccess.domain.User;
import com.powerrangers.system.modules.UserAccess.service.UserService;
import com.powerrangers.system.modules.UserAccess.service.dto.EmailDTO;
import com.powerrangers.system.modules.UserAccess.service.dto.EventInfoDTO;
import com.powerrangers.system.modules.UserAccess.service.dto.SmallUserDTO;
import com.powerrangers.system.modules.UserAccess.service.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserMapper userMapper;


    @Autowired
    private StringRedisTemplate redisTemplate;

    @Value("${JWT.expiration}")
    private String expiration;

    @Value("${EmailApi.url}")
    private String url;

    @Value("${EmailApi.apiUser}")
    private String apiUser;

    @Value("${EmailApi.apiKey}")
    private String apiKey;

    @Value("${DefaultImage.userAvatar}")
    private String defaultUserAvatar;

    private Map<String, String> responseBody = new HashMap<>();

    @Override
    public Boolean checkExist(SmallUserDTO smallUserDTO) {
        return userMapper.checkExist(smallUserDTO) > 0;
    }

    @Override
    public Boolean createUser(SmallUserDTO smallUserDTO) {

        if (!checkExist(smallUserDTO)) {
            smallUserDTO.setAvatar(defaultUserAvatar);
            userMapper.addUser(smallUserDTO);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public ResponseEntity<Object> logIn(SmallUserDTO smallUserDTO) {

        Map<String, String> map = new HashMap<>();

        if (checkExist(smallUserDTO)) {
            UserDTO userDTO = userMapper.queryUser(smallUserDTO);

            String token = JWTUtils.createToken(userDTO.getId());
            map.put("token", token);
            map.put("expiration", expiration);
            map.put("msg", "login succeed!");

            redisTemplate.opsForValue().set("token_" + token, JSON.toJSONString(userDTO), Integer.parseInt(expiration), TimeUnit.MILLISECONDS);

            return new ResponseEntity<>(JSON.toJSONString(map), HttpStatus.OK);

        } else {
            map.put("msg", "user not exists!");

            return new ResponseEntity<>(JSON.toJSONString(map), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Object> logout(String token) {
        redisTemplate.delete("token_" + token);

        Map<String, String> map = new HashMap<>();
        map.put("msg", "logout succeed!");

        return new ResponseEntity<>(JSON.toJSONString(map), HttpStatus.OK);
    }

    @Override
    public User findCurrentUserByToken(String token) {
        if (token.isBlank()) {
            return null;
        }

        Map<String, Object> stringObjectMap = JWTUtils.checkToken(token);

        if (stringObjectMap == null) {
            return null;
        }

        String userJSON = redisTemplate.opsForValue().get("token_" + token);
        if (userJSON == null || userJSON.isBlank()) {
            return null;
        }

        return JSON.parseObject(userJSON, User.class);
    }

    @Override
    public Boolean checkPassword(String token, String password) {
        User user = JSON.parseObject(redisTemplate.opsForValue().get("token_" + token), User.class);

        if (user == null) {
            return false;
        }

        return user.getPassword().equals(password);
    }

    @Override
    public ResponseEntity<Object> sendEmail(EmailDTO emailDTO) throws IOException {

        String email = emailDTO.getEmailAddress();
        Integer state = emailDTO.getState();
        int code = 0;
        String subject;
        String html;
        String userName;
        String eventName;

        if (state == 0) {
            subject = "Verification code";
            code = (int) ((Math.random() * 9 + 1) * 1000);
            html = "Here you are, the verification code is: " + code;
        } else if (state == 1) {
            userName = emailDTO.getUserName();
            eventName = emailDTO.getEventName();
            subject = "Refund letter";
            html = "Sorry " + userName + ", the host has cancelled the event \"" + eventName +
                    "\", the money will refund back to your account.";
        } else {
            userName = emailDTO.getUserName();
            eventName = emailDTO.getEventName();
            EventInfoDTO eventInfoDTO = userMapper.queryEvent(emailDTO);
            subject = "Reservation letter";
            html = "Dear " + userName + ", you have successfully booked the ticket of \"" + eventName + "\", the event will hold on "
                    + eventInfoDTO.getStartTime() + ", in " + eventInfoDTO.getLocation() + " " + eventInfoDTO.getSiteDescription() + ".";
        }

        HttpPost httpPost = new HttpPost(url);
        HttpClient httpClient = new DefaultHttpClient();

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("apiUser", apiUser));
        params.add(new BasicNameValuePair("apiKey", apiKey));
        params.add(new BasicNameValuePair("to", email));
        params.add(new BasicNameValuePair("from", "sendcloud@sendcloud.org"));
        params.add(new BasicNameValuePair("fromName", "SendCloud"));
        params.add(new BasicNameValuePair("subject", subject));
        params.add(new BasicNameValuePair("html", html));

        httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

        HttpResponse response = httpClient.execute(httpPost);
        Map<String, String> responseBody = new HashMap<>();
        ResponseEntity result;

        if (response.getStatusLine().getStatusCode() == HttpStatus.OK.value()) {
            // Return correctly, parse the returned data
            System.out.println(EntityUtils.toString(response.getEntity()));
            if (state == 0) {
                responseBody.put("msg", "The email has been sent successfully, the code is " + code);
                result = new ResponseEntity<>(responseBody, HttpStatus.OK);
            } else {
                responseBody.put("msg", "The email has been sent successfully");
                result = new ResponseEntity<>(responseBody, HttpStatus.OK);
            }
        } else {
            System.err.println("error");
            responseBody.put("msg", "Failed to send the email");
            result = new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }
        httpPost.releaseConnection();
        return result;

    }

    public ResponseEntity<Object> resetPassword(SmallUserDTO smallUserDTO) {

        responseBody.clear();

        if (!checkExist(smallUserDTO)) {
            responseBody.put("error", "email not exists!");
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }

        userMapper.resetPassword(smallUserDTO.getEmail(), smallUserDTO.getPassword());

        responseBody.put("msg", "reset succeed!");
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> queryUser(String token) {

        responseBody.clear();

        if (token == null || token.length() == 0) {
            responseBody.put("error", "token is invalid!");
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.set("content-type", "application/json");

        return ResponseEntity.ok().headers(headers)
                .body(JSON.parseObject(redisTemplate.opsForValue().get("token_" + token), User.class));
    }


}
