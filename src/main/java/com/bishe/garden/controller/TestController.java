package com.bishe.garden.controller;

import com.bishe.garden.common.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * 测试控制器
 */
@RestController
@RequestMapping("/api/test")
public class TestController {

    /**
     * 测试接口
     * @return 测试信息
     */
    @GetMapping("/hello")
    public Result<String> hello() {
        return Result.success("你好，这是一个测试控制器！");
    }
    
    /**
     * 获取系统时间
     * @return 当前系统时间
     */
    @GetMapping("/time")
    public Result<Map<String, Object>> getTime() {
        Map<String, Object> data = new HashMap<>();
        data.put("time", LocalDateTime.now().toString());
        data.put("timestamp", System.currentTimeMillis());
        
        return Result.success("获取时间成功", data);
    }
} 