package com.bishe.garden.service.impl;

import com.bishe.garden.common.PageResult;
import com.bishe.garden.entity.User;
import com.bishe.garden.mapper.UserMapper;
import com.bishe.garden.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户服务实现类
 */
@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserMapper userMapper;
    
    /**
     * 根据ID查询用户
     * @param id 用户ID
     * @return 用户对象
     */
    @Override
    public User getById(Long id) {
        return userMapper.selectById(id);
    }
    
    /**
     * 根据用户名查询用户
     * @param username 用户名
     * @return 用户对象
     */
    @Override
    public User getByUsername(String username) {
        return userMapper.selectByUsername(username);
    }
    
    /**
     * 查询所有用户
     * @return 用户列表
     */
    @Override
    public List<User> listAll() {
        return userMapper.selectAll();
    }
    
    /**
     * 根据条件查询用户
     * @param params 查询参数
     * @return 用户列表
     */
    @Override
    public List<User> listByCondition(Map<String, Object> params) {
        return userMapper.selectByCondition(params);
    }
    
    /**
     * 根据条件分页查询用户
     * @param params 查询参数
     * @param pageNum 页码
     * @param pageSize 每页数量
     * @return 分页结果
     */
    @Override
    public PageResult<User> pageByCondition(Map<String, Object> params, Integer pageNum, Integer pageSize) {
        // 如果参数为空，创建一个空的Map
        if (params == null) {
            params = new HashMap<>();
        }
        
        // 使用PageHelper进行分页查询
        Page<User> page = PageHelper.startPage(pageNum, pageSize);
        List<User> list = userMapper.selectByCondition(params);
        
        // 封装分页结果
        return PageResult.of(list, page.getTotal(), pageNum, pageSize);
    }
    
    /**
     * 根据ID列表批量查询用户
     * @param ids ID列表
     * @return 用户列表
     */
    @Override
    public List<User> listByIds(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return List.of();
        }
        return userMapper.selectByIds(ids);
    }
    
    /**
     * 新增用户
     * @param user 用户对象
     * @return 是否成功
     */
    @Override
    @Transactional
    public boolean save(User user) {
        // 设置默认值
        if (user.getStatus() == null) {
            user.setStatus(1); // 默认启用
        }
        if (user.getCreateTime() == null) {
            user.setCreateTime(new Date());
        }
        if (user.getUpdateTime() == null) {
            user.setUpdateTime(new Date());
        }
        
        return userMapper.insert(user) > 0;
    }
    
    /**
     * 批量新增用户
     * @param users 用户列表
     * @return 是否成功
     */
    @Override
    @Transactional
    public boolean saveBatch(List<User> users) {
        if (users == null || users.isEmpty()) {
            return false;
        }
        
        // 设置默认值
        Date now = new Date();
        for (User user : users) {
            if (user.getStatus() == null) {
                user.setStatus(1); // 默认启用
            }
            if (user.getCreateTime() == null) {
                user.setCreateTime(now);
            }
            if (user.getUpdateTime() == null) {
                user.setUpdateTime(now);
            }
        }
        
        return userMapper.batchInsert(users) > 0;
    }
    
    /**
     * 更新用户
     * @param user 用户对象
     * @return 是否成功
     */
    @Override
    @Transactional
    public boolean update(User user) {
        // 设置更新时间
        user.setUpdateTime(new Date());
        
        return userMapper.update(user) > 0;
    }
    
    /**
     * 删除用户
     * @param id 用户ID
     * @return 是否成功
     */
    @Override
    @Transactional
    public boolean removeById(Long id) {
        return userMapper.deleteById(id) > 0;
    }
    
    /**
     * 批量删除用户
     * @param ids ID列表
     * @return 是否成功
     */
    @Override
    @Transactional
    public boolean removeByIds(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return false;
        }
        return userMapper.batchDeleteByIds(ids) > 0;
    }
    
    /**
     * 更新用户状态
     * @param id 用户ID
     * @param status 状态
     * @return 是否成功
     */
    @Override
    @Transactional
    public boolean updateStatus(Long id, Integer status) {
        return userMapper.updateStatus(id, status) > 0;
    }
    
    /**
     * 批量更新用户状态
     * @param ids ID列表
     * @param status 状态
     * @return 是否成功
     */
    @Override
    @Transactional
    public boolean updateStatusBatch(List<Long> ids, Integer status) {
        if (ids == null || ids.isEmpty()) {
            return false;
        }
        return userMapper.batchUpdateStatus(ids, status) > 0;
    }
} 