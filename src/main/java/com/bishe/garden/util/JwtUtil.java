package com.bishe.garden.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT工具类
 * 用于生成、验证和解析JWT Token
 */
@Component
public class JwtUtil {
    
    /**
     * JWT密钥，从配置文件中读取
     */
    @Value("${jwt.secret:HIT}")
    private String secret;
    
    /**
     * Token过期时间（天），从配置文件中读取，默认3天
     */
    @Value("${jwt.expire:3}")
    private int expireDays;
    
    /**
     * 生成JWT Token
     * @param claims 自定义声明
     * @return JWT Token字符串
     */
    public String generateToken(Map<String, Object> claims) {
        // 计算过期时间
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, expireDays);
        Date expiresAt = calendar.getTime();
        
        // 创建JWT Builder
        return JWT.create()
                // 设置载荷
                .withClaim("claims", claims)
                // 设置签发时间
                .withIssuedAt(new Date())
                // 设置过期时间
                .withExpiresAt(expiresAt)
                // 使用HMAC256算法签名
                .sign(Algorithm.HMAC256(secret));
    }
    
    /**
     * 验证并解析JWT Token
     * @param token JWT Token字符串
     * @return 解析后的JWT对象
     * @throws JWTVerificationException Token验证失败时抛出异常
     */
    public Map<String, Object> verifyToken(String token) throws JWTVerificationException {
        // 按照HMAC256进行解码
        return JWT.require(Algorithm.HMAC256(secret))
                // 建立工具类
                .build()
                // 校验token
                .verify(token)
                // 获得名叫claims的载荷
                .getClaim("claims")
                // json化为map
                .asMap();
    }

} 