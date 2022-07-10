package com.powerrangers.system.modules.userAccess.service.impl;

import com.alibaba.fastjson.JSON;
import com.powerrangers.common.utils.JWTUtils;
import com.powerrangers.system.modules.userAccess.dao.UserMapper;
import com.powerrangers.system.modules.userAccess.domain.User;
import com.powerrangers.system.modules.userAccess.service.UserService;
import com.powerrangers.system.modules.userAccess.service.dto.SmallUserDTO;
import com.powerrangers.system.modules.userAccess.service.dto.UserDTO;
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

    @Override
    public Boolean checkExist(SmallUserDTO smallUserDTO) {
        return userMapper.checkExist(smallUserDTO) > 0;
    }

    @Override
    public Boolean createUser(SmallUserDTO smallUserDTO) {

        if (!checkExist(smallUserDTO)) {
            userMapper.addUser(smallUserDTO);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public ResponseEntity<Object> login(SmallUserDTO smallUserDTO) {

        Map<String, String> map = new HashMap<>();

        if (checkExist(smallUserDTO)) {

            UserDTO userDTO = userMapper.queryUser(smallUserDTO);

            String token = JWTUtils.createToken(userDTO.getId());
            map.put("token", token);
            map.put("expiration", expiration);
            map.put("msg", "login succeed!");

            redisTemplate.opsForValue().set("token_"+token, JSON.toJSONString(userDTO), Integer.parseInt(expiration), TimeUnit.MILLISECONDS);

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

        String userJSON = (String) redisTemplate.opsForValue().get("token_" + token);
        if (userJSON == null || userJSON.isBlank()) {
            return null;
        }

        return JSON.parseObject(userJSON, User.class);
    }

    @Override
    public Boolean checkPassword(String token, String password) {
        User user = JSON.parseObject((String) redisTemplate.opsForValue().get("token_" + token), User.class);

        if (user == null) {
            return false;
        }

        return user.getPassword().equals(password);
    }

    @Override
    public ResponseEntity<Object> sendEmail(String email) throws IOException {
        final String url = "http://api.sendcloud.net/apiv2/mail/send";

        final String apiUser = "sc_bjklvn_test_dBTXGm";

        final String apiKey = "826a23a47afc7cc97e721a03b35bce6b";
        final String rcpt_to = email;
        boolean flag = false;

        String subject = "Verification code";
        int code = (int)((Math.random()*9+1)*1000);
        String html = "Thanks for your registration, the verify code is: " + code;

        HttpPost httpPost = new HttpPost(url);
        HttpClient httpClient = new DefaultHttpClient();

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("apiUser", apiUser));
        params.add(new BasicNameValuePair("apiKey", apiKey));
        params.add(new BasicNameValuePair("to", rcpt_to));
        params.add(new BasicNameValuePair("from", "sendcloud@sendcloud.org"));
        params.add(new BasicNameValuePair("fromName", "SendCloud"));
        params.add(new BasicNameValuePair("subject", subject));
        params.add(new BasicNameValuePair("html", html));

        httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

        HttpResponse response = httpClient.execute(httpPost);



        if (response.getStatusLine().getStatusCode() == HttpStatus.OK.value()) {
            // Return correctly, parse the returned data
            System.out.println(EntityUtils.toString(response.getEntity()));
            flag = true;
        } else {
            System.err.println("error");
        }
        httpPost.releaseConnection();

        if(flag){
            return new ResponseEntity<>("Verification code has been sent, the code is "+code, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Failed to send the verification code, try again!", HttpStatus.OK);
        }

    }

}
