package com.bishe.garden.controller;

import com.bishe.garden.common.PageRequest;
import com.bishe.garden.common.PageResult;
import com.bishe.garden.common.Result;
import com.bishe.garden.entity.User;
import com.bishe.garden.service.UserService;
import com.bishe.garden.util.TokenValidationUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户控制器
 */
@RestController
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private TokenValidationUtil tokenValidationUtil;
    
    /**
     * 根据ID查询用户
     * @param id 用户ID
     * @return 用户信息
     */
    @GetMapping("/{id}")
    public Result<User> getById(@PathVariable Long id, HttpServletRequest request) {
        // 验证token
        Result<?> validationResult = tokenValidationUtil.validateToken(request);
        if (validationResult != null) {
            return Result.error(validationResult.getMessage());
        }
        
        User user = userService.getById(id);
        if (user != null) {
            return Result.success(user);
        } else {
            return Result.error("用户不存在");
        }
    }
    
    /**
     * 查询所有用户
     * @return 用户列表
     */
    @GetMapping("/list")
    public Result<List<User>> list(HttpServletRequest request) {
        // 验证token
        Result<?> validationResult = tokenValidationUtil.validateToken(request);
        if (validationResult != null) {
            return Result.error(validationResult.getMessage());
        }
        
        List<User> userList = userService.listAll();
        return Result.success(userList);
    }
    
    /**
     * 根据条件查询用户
     * @param user 查询条件
     * @return 用户列表
     */
    @PostMapping("/list/condition")
    public Result<List<User>> listByCondition(
            @RequestBody(required = false) User user,
            HttpServletRequest request) {
        // 验证token
        Result<?> validationResult = tokenValidationUtil.validateToken(request);
        if (validationResult != null) {
            return Result.error(validationResult.getMessage());
        }
        
        Map<String, Object> params = buildSearchParams(user);
        List<User> userList = userService.listByCondition(params);
        return Result.success(userList);
    }
    
    /**
     * 分页查询用户
     * @param user 查询条件
     * @param pageRequest 分页参数
     * @return 分页结果
     */
    @PostMapping("/page")
    public Result<PageResult<User>> page(
            @RequestBody(required = false) User user,
            PageRequest pageRequest,
            HttpServletRequest request) {
        // 验证token
        Result<?> validationResult = tokenValidationUtil.validateToken(request);
        if (validationResult != null) {
            return Result.error(validationResult.getMessage());
        }
        
        Map<String, Object> params = buildSearchParams(user);
        
        // 添加排序参数
        if (pageRequest.getOrderBy() != null && !pageRequest.getOrderBy().isEmpty()) {
            params.put("orderBy", pageRequest.getOrderBy());
            params.put("orderType", pageRequest.getOrderType());
        }
        
        PageResult<User> pageResult = userService.pageByCondition(
                params, 
                pageRequest.getPageNum(), 
                pageRequest.getPageSize()
        );
        return Result.success(pageResult);
    }
    
    /**
     * 分页查询用户（GET方式）
     * @param pageNum 页码，默认1
     * @param pageSize 每页数量，默认10
     * @param username 用户名（可选）
     * @param realName 真实姓名（可选）
     * @param status 状态（可选）
     * @return 分页结果
     */
    @GetMapping("/page")
    public Result<PageResult<User>> pageByGet(
            @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String realName,
            @RequestParam(required = false) Integer status,
            HttpServletRequest request) {
        // 验证token
        Result<?> validationResult = tokenValidationUtil.validateToken(request);
        if (validationResult != null) {
            return Result.error(validationResult.getMessage());
        }
        
        // 构建查询参数
        Map<String, Object> params = new HashMap<>();
        if (username != null && !username.isEmpty()) {
            params.put("username", username);
        }
        if (realName != null && !realName.isEmpty()) {
            params.put("realName", realName);
        }
        if (status != null) {
            params.put("status", status);
        }
        
        // 进行分页查询
        PageResult<User> pageResult = userService.pageByCondition(params, pageNum, pageSize);
        return Result.success(pageResult);
    }
    
    /**
     * 根据ID列表批量查询用户
     * @param ids ID列表
     * @return 用户列表
     */
    @PostMapping("/list/ids")
    public Result<List<User>> listByIds(
            @RequestBody List<Long> ids,
            HttpServletRequest request) {
        // 验证token
        Result<?> validationResult = tokenValidationUtil.validateToken(request);
        if (validationResult != null) {
            return Result.error(validationResult.getMessage());
        }
        
        List<User> userList = userService.listByIds(ids);
        return Result.success(userList);
    }
    
    /**
     * 新增用户
     * @param user 用户对象
     * @return 结果
     */
    @PostMapping
    public Result<Void> save(
            @RequestBody User user,
            HttpServletRequest request) {
        // 验证token
        Result<?> validationResult = tokenValidationUtil.validateToken(request);
        if (validationResult != null) {
            return Result.error(validationResult.getMessage());
        }
        
        // 检查用户名是否已存在
        User existUser = userService.getByUsername(user.getUsername());
        if (existUser != null) {
            return Result.error("用户名已存在");
        }
        
        if (userService.save(user)) {
            return Result.success();
        } else {
            return Result.error("添加用户失败");
        }
    }
    
    /**
     * 批量新增用户
     * @param users 用户列表
     * @return 结果
     */
    @PostMapping("/batch")
    public Result<Void> saveBatch(
            @RequestBody List<User> users,
            HttpServletRequest request) {
        // 验证token
        Result<?> validationResult = tokenValidationUtil.validateToken(request);
        if (validationResult != null) {
            return Result.error(validationResult.getMessage());
        }
        
        // 检查用户名是否已存在
        for (User user : users) {
            User existUser = userService.getByUsername(user.getUsername());
            if (existUser != null) {
                return Result.error("用户名 " + user.getUsername() + " 已存在");
            }
        }
        
        if (userService.saveBatch(users)) {
            return Result.success();
        } else {
            return Result.error("批量添加用户失败");
        }
    }
    
    /**
     * 更新用户
     * @param user 用户对象
     * @return 结果
     */
    @PutMapping
    public Result<Void> update(
            @RequestBody User user,
            HttpServletRequest request) {
        // 验证token
        Result<?> validationResult = tokenValidationUtil.validateToken(request);
        if (validationResult != null) {
            return Result.error(validationResult.getMessage());
        }
        
        if (user.getId() == null) {
            return Result.error("用户ID不能为空");
        }
        
        if (user.getUsername() != null) {
            // 检查用户名是否已存在（排除自己）
            User existUser = userService.getByUsername(user.getUsername());
            if (existUser != null && !existUser.getId().equals(user.getId())) {
                return Result.error("用户名已存在");
            }
        }
        
        if (userService.update(user)) {
            return Result.success();
        } else {
            return Result.error("更新用户失败");
        }
    }
    
    /**
     * 删除用户
     * @param id 用户ID
     * @return 结果
     */
    @DeleteMapping("/{id}")
    public Result<Void> remove(
            @PathVariable Long id,
            HttpServletRequest request) {
        // 验证token
        Result<?> validationResult = tokenValidationUtil.validateToken(request);
        if (validationResult != null) {
            return Result.error(validationResult.getMessage());
        }
        
        if (userService.removeById(id)) {
            return Result.success();
        } else {
            return Result.error("删除用户失败");
        }
    }
    
    /**
     * 批量删除用户
     * @param ids ID列表
     * @return 结果
     */
    @DeleteMapping("/batch")
    public Result<Void> removeBatch(
            @RequestBody List<Long> ids,
            HttpServletRequest request) {
        // 验证token
        Result<?> validationResult = tokenValidationUtil.validateToken(request);
        if (validationResult != null) {
            return Result.error(validationResult.getMessage());
        }
        
        if (userService.removeByIds(ids)) {
            return Result.success();
        } else {
            return Result.error("批量删除用户失败");
        }
    }
    
    /**
     * 更新用户状态
     * @param id 用户ID
     * @param status 状态
     * @return 结果
     */
    @PutMapping("/{id}/status/{status}")
    public Result<Void> updateStatus(
            @PathVariable Long id,
            @PathVariable Integer status,
            HttpServletRequest request) {
        // 验证token
        Result<?> validationResult = tokenValidationUtil.validateToken(request);
        if (validationResult != null) {
            return Result.error(validationResult.getMessage());
        }
        
        if (userService.updateStatus(id, status)) {
            return Result.success();
        } else {
            return Result.error("更新状态失败");
        }
    }
    
    /**
     * 批量更新用户状态
     * @param ids ID列表
     * @param status 状态
     * @return 结果
     */
    @PutMapping("/batch/status/{status}")
    public Result<Void> updateStatusBatch(
            @RequestBody List<Long> ids,
            @PathVariable Integer status,
            HttpServletRequest request) {
        // 验证token
        Result<?> validationResult = tokenValidationUtil.validateToken(request);
        if (validationResult != null) {
            return Result.error(validationResult.getMessage());
        }
        
        if (userService.updateStatusBatch(ids, status)) {
            return Result.success();
        } else {
            return Result.error("批量更新状态失败");
        }
    }
    
    /**
     * 构建查询参数
     * @param user 用户对象
     * @return 查询参数Map
     */
    private Map<String, Object> buildSearchParams(User user) {
        Map<String, Object> params = new HashMap<>();
        if (user != null) {
            if (user.getId() != null) {
                params.put("id", user.getId());
            }
            if (user.getUsername() != null) {
                params.put("username", user.getUsername());
            }
            if (user.getRealName() != null) {
                params.put("realName", user.getRealName());
            }
            if (user.getPhone() != null) {
                params.put("phone", user.getPhone());
            }
            if (user.getEmail() != null) {
                params.put("email", user.getEmail());
            }
            if (user.getStatus() != null) {
                params.put("status", user.getStatus());
            }
        }
        return params;
    }
} 