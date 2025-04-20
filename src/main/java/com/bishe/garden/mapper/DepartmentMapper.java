package com.bishe.garden.mapper;

import com.bishe.garden.entity.Department;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 部门数据访问接口
 */
@Mapper
public interface DepartmentMapper {
    
    /**
     * 插入部门
     * @param department 部门信息
     * @return 影响行数
     */
    int insert(Department department);
    
    /**
     * 更新部门
     * @param department 部门信息
     * @return 影响行数
     */
    int update(Department department);
    
    /**
     * 根据ID删除部门
     * @param id 部门ID
     * @return 影响行数
     */
    int deleteById(Long id);
    
    /**
     * 根据ID查询部门
     * @param id 部门ID
     * @return 部门信息
     */
    Department selectById(Long id);
    
    /**
     * 根据部门编码查询部门
     * @param deptCode 部门编码
     * @return 部门信息
     */
    Department selectByDeptCode(String deptCode);
    
    /**
     * 根据部门名称查询部门
     * @param deptName 部门名称
     * @return 部门信息
     */
    Department selectByDeptName(String deptName);
    
    /**
     * 查询所有部门
     * @return 部门列表
     */
    List<Department> selectAll();
    
    /**
     * 分页查询部门
     * @param deptName 部门名称（模糊查询）
     * @param status 状态
     * @return 部门列表
     */
    List<Department> selectPage(@Param("deptName") String deptName, @Param("status") Integer status);
} 