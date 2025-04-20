package com.bishe.garden.service;

import com.bishe.garden.common.PageResult;
import com.bishe.garden.entity.Department;
import java.util.List;

/**
 * 部门服务接口
 */
public interface DepartmentService {
    
    /**
     * 保存部门信息
     * @param department 部门信息
     * @return 是否成功
     */
    boolean save(Department department);
    
    /**
     * 更新部门信息
     * @param department 部门信息
     * @return 是否成功
     */
    boolean update(Department department);
    
    /**
     * 根据ID删除部门信息
     * @param id 部门ID
     * @return 是否成功
     */
    boolean deleteById(Long id);
    
    /**
     * 根据ID查询部门信息
     * @param id 部门ID
     * @return 部门信息
     */
    Department getById(Long id);
    
    /**
     * 根据部门编码查询部门信息
     * @param deptCode 部门编码
     * @return 部门信息
     */
    Department getByDeptCode(String deptCode);
    
    /**
     * 根据部门名称查询部门信息
     * @param deptName 部门名称
     * @return 部门信息
     */
    Department getByDeptName(String deptName);
    
    /**
     * 查询所有部门信息
     * @return 部门列表
     */
    List<Department> listAll();
    
    /**
     * 分页查询部门信息
     * @param pageNum 页码
     * @param pageSize 每页数量
     * @param deptName 部门名称（模糊查询）
     * @param status 状态
     * @return 分页结果
     */
    PageResult<Department> page(Integer pageNum, Integer pageSize, String deptName, Integer status);
} 