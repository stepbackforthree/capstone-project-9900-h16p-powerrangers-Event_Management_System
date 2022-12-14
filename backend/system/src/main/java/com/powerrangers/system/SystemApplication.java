package com.powerrangers.system;

import com.alibaba.fastjson.JSON;
import com.powerrangers.system.modules.UserAccess.dao.UserMapper;
import com.powerrangers.system.modules.UserAccess.service.dto.SmallUserDTO;
import com.powerrangers.system.modules.UserAccess.service.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class, SecurityFilterAutoConfiguration.class})
@RestController
@RequiredArgsConstructor
public class SystemApplication {
    @Autowired
    private final UserMapper userMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Value("${spring.web.swaggerStartUpUrl}")
    private String swaggerStartUpUrl;

    public static void main(String[] args) {
        SpringApplication.run(SystemApplication.class, args);
    }

//    // Only work on Windows
//    @EventListener(ApplicationReadyEvent.class)
//    public void applicationReadyEvent() {
//        String url = swaggerStartUpUrl;
//
//        try {
//            Runtime.getRuntime().exec("cmd   /c   start   " + url);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    @GetMapping("/test")
    public String index() {
        SmallUserDTO smallUserDTO1 = new SmallUserDTO();
        smallUserDTO1.setUserName("doom slayer");
        UserDTO user1 = userMapper.queryUser(smallUserDTO1);

        SmallUserDTO smallUserDTO2 = new SmallUserDTO();
        smallUserDTO2.setUserName("NIKO");
        UserDTO user2 = userMapper.queryUser(smallUserDTO2);

        SmallUserDTO smallUserDTO3 = new SmallUserDTO();
        smallUserDTO3.setUserName("harden");
        UserDTO user3 = userMapper.queryUser(smallUserDTO3);

        SmallUserDTO smallUserDTO4 = new SmallUserDTO();
        smallUserDTO4.setUserName("james");
        UserDTO user4 = userMapper.queryUser(smallUserDTO4);

        SmallUserDTO smallUserDTO5 = new SmallUserDTO();
        smallUserDTO5.setUserName("music top");
        UserDTO user5 = userMapper.queryUser(smallUserDTO5);

        SmallUserDTO smallUserDTO6 = new SmallUserDTO();
        smallUserDTO6.setUserName("mystery campper");
        UserDTO user6 = userMapper.queryUser(smallUserDTO6);

        SmallUserDTO smallUserDTO7 = new SmallUserDTO();
        smallUserDTO7.setUserName("hades");
        UserDTO user7 = userMapper.queryUser(smallUserDTO7);

        System.out.println(JSON.toJSONString(user1));
        System.out.println(JSON.toJSONString(user2));
        System.out.println(JSON.toJSONString(user3));
        System.out.println(JSON.toJSONString(user4));
        System.out.println(JSON.toJSONString(user5));
        System.out.println(JSON.toJSONString(user6));
        System.out.println(JSON.toJSONString(user7));

        redisTemplate.opsForValue().set("conn test", "redis success");
        System.out.println(redisTemplate.opsForValue().get("conn test"));

        return "Hang on! We almost there!";
    }
}
