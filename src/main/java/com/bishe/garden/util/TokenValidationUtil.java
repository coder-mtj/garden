package com.bishe.garden.util;

import com.bishe.garden.common.Result;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Token验证工具类
 * 用于处理控制器中的token验证逻辑
 */
@Component
public class TokenValidationUtil {

    @Autowired
    private JwtUtil jwtUtil;
    
    /**
     * 验证请求中的token
     * @param request HTTP请求
     * @return 验证失败返回错误Result，验证成功返回null
     */
    public Result<?> validateToken(HttpServletRequest request) {
        try {
            String token = request.getHeader("Authorization");
            if (token == null || token.isEmpty()) {
                return Result.error("未提供token，请先登录");
            }
            
            // 如果token以"Bearer "开头，则去除前缀
            if (token.startsWith("Bearer ")) {
                token = token.substring(7);
            }
            
            // 验证token
            Map<String, Object> claims = jwtUtil.verifyToken(token);
            if (claims == null) {
                return Result.error("无效的token");
            }
            
            return null; // 返回null表示验证通过
        } catch (Exception e) {
            return Result.error("token验证失败: " + e.getMessage());
        }
    }
    
    /**
     * 从请求的token中获取用户ID
     * @param request HTTP请求
     * @return 用户ID，如果获取失败则返回null
     */
    public Long getUserIdFromToken(HttpServletRequest request) {
        try {
            String token = request.getHeader("Authorization");
            if (token == null || token.isEmpty()) {
                return null;
            }
            
            // 如果token以"Bearer "开头，则去除前缀
            if (token.startsWith("Bearer ")) {
                token = token.substring(7);
            }
            
            // 验证并解析token
            Map<String, Object> claims = jwtUtil.verifyToken(token);
            if (claims != null && claims.containsKey("userId")) {
                return Long.valueOf(claims.get("userId").toString());
            }
            
            return null;
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * 从请求的token中获取用户名
     * @param request HTTP请求
     * @return 用户名，如果获取失败则返回null
     */
    public String getUsernameFromToken(HttpServletRequest request) {
        try {
            String token = request.getHeader("Authorization");
            if (token == null || token.isEmpty()) {
                return null;
            }
            
            // 如果token以"Bearer "开头，则去除前缀
            if (token.startsWith("Bearer ")) {
                token = token.substring(7);
            }
            
            // 验证并解析token
            Map<String, Object> claims = jwtUtil.verifyToken(token);
            if (claims != null && claims.containsKey("username")) {
                return claims.get("username").toString();
            }
            
            return null;
        } catch (Exception e) {
            return null;
        }
    }
} 