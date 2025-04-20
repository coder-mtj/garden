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
                // 设置签发时间
                .withIssuedAt(new Date())
                // 设置过期时间
                .withExpiresAt(expiresAt)
                // 设置JWT ID
                .withJWTId(java.util.UUID.randomUUID().toString())
                // 添加自定义声明
                .withPayload(claims)
                // 使用HMAC256算法签名
                .sign(Algorithm.HMAC256(secret));
    }
    
    /**
     * 验证并解析JWT Token
     * @param token JWT Token字符串
     * @return 解析后的JWT对象
     * @throws JWTVerificationException Token验证失败时抛出异常
     */
    public DecodedJWT verifyToken(String token) throws JWTVerificationException {
        // 创建JWT验证器
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
                .build();
        
        // 验证并返回解析后的JWT
        return verifier.verify(token);
    }
    
    /**
     * 从Token中获取指定声明
     * @param token JWT Token字符串
     * @param claimName 声明名称
     * @return 声明值
     * @throws JWTVerificationException Token验证失败时抛出异常
     */
    public Object getClaim(String token, String claimName) throws JWTVerificationException {
        DecodedJWT jwt = verifyToken(token);
        return jwt.getClaim(claimName).as(Object.class);
    }
    
    /**
     * 从Token中获取所有声明
     * @param token JWT Token字符串
     * @return 所有声明的Map
     * @throws JWTVerificationException Token验证失败时抛出异常
     */
    public Map<String, Object> getAllClaims(String token) throws JWTVerificationException {
        DecodedJWT jwt = verifyToken(token);
        Map<String, Object> claims = new HashMap<>();
        
        // 获取所有标准声明
        claims.put("issuer", jwt.getIssuer());
        claims.put("subject", jwt.getSubject());
        claims.put("audience", jwt.getAudience());
        claims.put("expiresAt", jwt.getExpiresAt());
        claims.put("notBefore", jwt.getNotBefore());
        claims.put("issuedAt", jwt.getIssuedAt());
        claims.put("jwtId", jwt.getId());
        
        // 获取所有自定义声明
        jwt.getClaims().forEach((key, value) -> claims.put(key, value.as(Object.class)));
        
        return claims;
    }
    
    /**
     * 检查Token是否过期
     * @param token JWT Token字符串
     * @return true-未过期，false-已过期
     */
    public boolean isTokenExpired(String token) {
        try {
            DecodedJWT jwt = verifyToken(token);
            return jwt.getExpiresAt().after(new Date());
        } catch (JWTVerificationException e) {
            return false;
        }
    }
    
    /**
     * 获取Token的剩余有效期（小时）
     * @param token JWT Token字符串
     * @return 剩余有效期（小时），如果Token无效则返回-1
     */
    public long getRemainingHours(String token) {
        try {
            DecodedJWT jwt = verifyToken(token);
            long remainingTime = jwt.getExpiresAt().getTime() - System.currentTimeMillis();
            return remainingTime / (1000 * 60 * 60);
        } catch (JWTVerificationException e) {
            return -1;
        }
    }
    
    /**
     * 刷新Token（生成新的Token，保留原有声明）
     * @param token 旧的JWT Token字符串
     * @return 新的JWT Token字符串
     * @throws JWTVerificationException Token验证失败时抛出异常
     */
    public String refreshToken(String token) throws JWTVerificationException {
        // 验证旧Token
        DecodedJWT oldJwt = verifyToken(token);
        
        // 获取所有声明
        Map<String, Object> claims = new HashMap<>();
        oldJwt.getClaims().forEach((key, value) -> claims.put(key, value.as(Object.class)));
        
        // 生成新Token
        return generateToken(claims);
    }
} 