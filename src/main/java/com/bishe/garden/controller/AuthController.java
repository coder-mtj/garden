package com.bishe.garden.controller;

import com.bishe.garden.common.Result;
import com.bishe.garden.entity.User;
import com.bishe.garden.service.UserService;
import com.bishe.garden.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户认证控制器
 * 处理用户注册、登录等认证相关操作
 */
@RestController
@RequestMapping("/auth")
public class AuthController {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private JwtUtil jwtUtil;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    /**
     * 用户注册
     * @param user 用户信息（包含用户名、密码、真实姓名、手机号）
     * @return 注册结果
     */
    @PostMapping("/register")
    public Result<String> register(@RequestBody User user) {
        // 检查用户名是否已存在
        User existUser = userService.getByUsername(user.getUsername());
        if (existUser != null) {
            return Result.error("用户名已存在");
        }
        
        // 检查手机号是否为空
        if (user.getPhone() == null || user.getPhone().trim().isEmpty()) {
            return Result.error("手机号不能为空");
        }
        
        // 加密密码
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        
        // 设置默认状态为启用
        user.setStatus(1);
        
        // 保存用户
        if (userService.save(user)) {
            return Result.success("注册成功");
        } else {
            return Result.error("注册失败");
        }
    }
    
    /**
     * 用户登录
     * @param user 登录信息（用户名和密码）
     * @return 登录结果和Token
     */
    @PostMapping("/login")
    public Result<Map<String, Object>> login(@RequestBody User user) {
        // 根据用户名查询用户
        User existUser = userService.getByUsername(user.getUsername());
        if (existUser == null) {
            return Result.error("用户名或密码错误");
        }
        
        // 验证密码
        if (!passwordEncoder.matches(user.getPassword(), existUser.getPassword())) {
            return Result.error("用户名或密码错误");
        }
        
        // 检查用户状态
        if (existUser.getStatus() != 1) {
            return Result.error("账号已被禁用");
        }
        
        // 生成Token载荷
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", existUser.getId());
        claims.put("realName", existUser.getRealName());
        
        // 生成Token
        String token = jwtUtil.generateToken(claims);
        
        // 构建返回结果
        Map<String, Object> result = new HashMap<>();
        result.put("token", token);
        result.put("user", existUser);
        
        return Result.success(result);
    }
} 