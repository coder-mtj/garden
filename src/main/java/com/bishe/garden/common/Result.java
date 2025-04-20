package com.bishe.garden.common;

import java.io.Serializable;

/**
 * 通用响应结果类
 * <p>
 * 使用说明:
 * 1. 成功响应示例:
 *    - 无数据返回: Result.success()
 *    - 有数据返回: Result.success(data)
 *    - 自定义消息: Result.success("操作成功", data)
 * 
 * 2. 失败响应示例:
 *    - 默认错误: Result.error()
 *    - 自定义消息: Result.error("用户名已存在")
 *    - 自定义状态码: Result.error(400, "参数错误")
 * 
 * 3. 控制器使用示例:
 *    <pre>
 *    @GetMapping("/user")
 *    public Result<User> getUser() {
 *        try {
 *            User user = userService.getUser();
 *            return Result.success(user);
 *        } catch (Exception e) {
 *            return Result.error("获取用户信息失败");
 *        }
 *    }
 *    </pre>
 * </p>
 * @param <T> 数据类型
 */
public class Result<T> implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 状态码
     */
    private Integer code;
    
    /**
     * 消息
     */
    private String message;
    
    /**
     * 数据
     */
    private T data;
    
    /**
     * 是否成功
     */
    private Boolean success;
    
    /**
     * 默认构造函数
     */
    public Result() {
    }
    
    /**
     * 全参构造函数
     * @param code 状态码
     * @param message 消息
     * @param data 数据
     * @param success 是否成功
     */
    public Result(Integer code, String message, T data, Boolean success) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.success = success;
    }
    
    /**
     * 成功响应（无数据）
     * @return Result对象
     */
    public static <T> Result<T> success() {
        return new Result<>(200, "操作成功", null, true);
    }
    
    /**
     * 成功响应（有数据）
     * @param data 数据
     * @return Result对象
     */
    public static <T> Result<T> success(T data) {
        return new Result<>(200, "操作成功", data, true);
    }
    
    /**
     * 成功响应（自定义消息和数据）
     * @param message 消息
     * @param data 数据
     * @return Result对象
     */
    public static <T> Result<T> success(String message, T data) {
        return new Result<>(200, message, data, true);
    }
    
    /**
     * 失败响应（无数据）
     * @return Result对象
     */
    public static <T> Result<T> error() {
        return new Result<>(500, "操作失败", null, false);
    }
    
    /**
     * 失败响应（自定义消息）
     * @param message 消息
     * @return Result对象
     */
    public static <T> Result<T> error(String message) {
        return new Result<>(500, message, null, false);
    }
    
    /**
     * 失败响应（自定义状态码和消息）
     * @param code 状态码
     * @param message 消息
     * @return Result对象
     */
    public static <T> Result<T> error(Integer code, String message) {
        return new Result<>(code, message, null, false);
    }
    
    public Integer getCode() {
        return code;
    }
    
    public void setCode(Integer code) {
        this.code = code;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public T getData() {
        return data;
    }
    
    public void setData(T data) {
        this.data = data;
    }
    
    public Boolean getSuccess() {
        return success;
    }
    
    public void setSuccess(Boolean success) {
        this.success = success;
    }
} 