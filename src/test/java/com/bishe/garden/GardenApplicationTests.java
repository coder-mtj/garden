package com.bishe.garden;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.bishe.garden.entity.FieldCrop;
import com.bishe.garden.entity.GrapeCrop;
import com.bishe.garden.service.GrapeCropService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class GardenApplicationTests {

    @Test
    void getToken() {
        // claims就是一个hashMap，存储用户信息
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", "马天俊");
        claims.put("password", "295152");

        // withClaim就是放入一个载荷，得去一个名字，value一般是hashmap，其实其他的也可以
        String token = JWT.create()
            .withClaim("user", claims)
            .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 3))
            .sign(Algorithm.HMAC256("HIT-MTJ"));
        System.out.println(token);
    }

    @Test
    void verifyToken() {
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjp7InBhc3N3b3JkIjoiMjk1MTUyIiwidXNlcm5hbWUiOiLpqazlpKnkv4oifSwiZXhwIjoxNzQ1MzAyMDUzfQ.6nUbqBluFZxhhAdGO_aAzNcT1Iqc8Jxe9Gfn_OZu5Hw";

        JWTVerifier verifier = JWT.require(Algorithm.HMAC256("HIT-MTJ")).build();
        DecodedJWT jwt = verifier.verify(token);
        //得到所有的载荷
        System.out.println(jwt.getClaims().get("user"));
    }
}
