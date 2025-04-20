package com.bishe.garden.mapper;

import com.bishe.garden.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 用户Mapper接口
 */
@Mapper
public interface UserMapper {
    
    /**
     * 根据ID查询用户
     * @param id 用户ID
     * @return 用户对象
     */
    User selectById(Long id);
    
    /**
     * 根据用户名查询用户
     * @param username 用户名
     * @return 用户对象
     */
    User selectByUsername(String username);
    
    /**
     * 查询所有用户
     * @return 用户列表
     */
    List<User> selectAll();
    
    /**
     * 条件查询用户
     * @param params 查询参数
     * @return 用户列表
     */
    List<User> selectByCondition(Map<String, Object> params);
    
    /**
     * 查询总记录数
     * @param params 查询参数
     * @return 总记录数
     */
    Long selectCount(Map<String, Object> params);
    
    /**
     * 根据ID列表批量查询用户
     * @param ids ID列表
     * @return 用户列表
     */
    List<User> selectByIds(@Param("ids") List<Long> ids);
    
    /**
     * 新增用户
     * @param user 用户对象
     * @return 影响行数
     */
    int insert(User user);
    
    /**
     * 批量新增用户
     * @param users 用户列表
     * @return 影响行数
     */
    int batchInsert(@Param("users") List<User> users);
    
    /**
     * 更新用户
     * @param user 用户对象
     * @return 影响行数
     */
    int update(User user);
    
    /**
     * 删除用户
     * @param id 用户ID
     * @return 影响行数
     */
    int deleteById(Long id);
    
    /**
     * 批量删除用户
     * @param ids ID列表
     * @return 影响行数
     */
    int batchDeleteByIds(@Param("ids") List<Long> ids);
    
    /**
     * 更新用户状态
     * @param id 用户ID
     * @param status 状态
     * @return 影响行数
     */
    int updateStatus(@Param("id") Long id, @Param("status") Integer status);
    
    /**
     * 批量更新用户状态
     * @param ids ID列表
     * @param status 状态
     * @return 影响行数
     */
    int batchUpdateStatus(@Param("ids") List<Long> ids, @Param("status") Integer status);
} 