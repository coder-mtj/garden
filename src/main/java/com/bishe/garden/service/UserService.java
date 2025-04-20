package com.bishe.garden.service;

import com.bishe.garden.common.PageResult;
import com.bishe.garden.entity.User;

import java.util.List;
import java.util.Map;

/**
 * 用户服务接口
 */
public interface UserService {
    
    /**
     * 根据ID查询用户
     * @param id 用户ID
     * @return 用户对象
     */
    User getById(Long id);
    
    /**
     * 根据用户名查询用户
     * @param username 用户名
     * @return 用户对象
     */
    User getByUsername(String username);
    
    /**
     * 查询所有用户
     * @return 用户列表
     */
    List<User> listAll();
    
    /**
     * 根据条件查询用户
     * @param params 查询参数
     * @return 用户列表
     */
    List<User> listByCondition(Map<String, Object> params);
    
    /**
     * 根据条件分页查询用户
     * @param params 查询参数
     * @param pageNum 页码
     * @param pageSize 每页数量
     * @return 分页结果
     */
    PageResult<User> pageByCondition(Map<String, Object> params, Integer pageNum, Integer pageSize);
    
    /**
     * 根据ID列表批量查询用户
     * @param ids ID列表
     * @return 用户列表
     */
    List<User> listByIds(List<Long> ids);
    
    /**
     * 新增用户
     * @param user 用户对象
     * @return 是否成功
     */
    boolean save(User user);
    
    /**
     * 批量新增用户
     * @param users 用户列表
     * @return 是否成功
     */
    boolean saveBatch(List<User> users);
    
    /**
     * 更新用户
     * @param user 用户对象
     * @return 是否成功
     */
    boolean update(User user);
    
    /**
     * 删除用户
     * @param id 用户ID
     * @return 是否成功
     */
    boolean removeById(Long id);
    
    /**
     * 批量删除用户
     * @param ids ID列表
     * @return 是否成功
     */
    boolean removeByIds(List<Long> ids);
    
    /**
     * 更新用户状态
     * @param id 用户ID
     * @param status 状态
     * @return 是否成功
     */
    boolean updateStatus(Long id, Integer status);
    
    /**
     * 批量更新用户状态
     * @param ids ID列表
     * @param status 状态
     * @return 是否成功
     */
    boolean updateStatusBatch(List<Long> ids, Integer status);
} 