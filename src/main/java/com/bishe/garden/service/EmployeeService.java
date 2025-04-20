package com.bishe.garden.service;

import com.bishe.garden.common.PageResult;
import com.bishe.garden.entity.Employee;
import java.util.List;

/**
 * 职工服务接口
 */
public interface EmployeeService {
    
    /**
     * 保存职工信息
     * @param employee 职工信息
     * @return 是否成功
     */
    boolean save(Employee employee);
    
    /**
     * 更新职工信息
     * @param employee 职工信息
     * @return 是否成功
     */
    boolean update(Employee employee);
    
    /**
     * 根据ID删除职工信息
     * @param id 职工ID
     * @return 是否成功
     */
    boolean deleteById(Long id);
    
    /**
     * 根据ID查询职工信息
     * @param id 职工ID
     * @return 职工信息
     */
    Employee getById(Long id);
    
    /**
     * 根据用户ID查询职工信息
     * @param userId 用户ID
     * @return 职工信息
     */
    Employee getByUserId(Long userId);
    
    /**
     * 根据工号查询职工信息
     * @param employeeNo 工号
     * @return 职工信息
     */
    Employee getByEmployeeNo(String employeeNo);
    
    /**
     * 根据身份证号查询职工信息
     * @param idCard 身份证号
     * @return 职工信息
     */
    Employee getByIdCard(String idCard);
    
    /**
     * 查询所有职工信息
     * @return 职工列表
     */
    List<Employee> listAll();
    
    /**
     * 根据部门ID查询职工信息
     * @param departmentId 部门ID
     * @return 职工列表
     */
    List<Employee> listByDepartmentId(Long departmentId);
    
    /**
     * 分页查询职工信息
     * @param pageNum 页码
     * @param pageSize 每页数量
     * @param name 职工姓名（模糊查询）
     * @param departmentId 部门ID
     * @param status 状态
     * @return 分页结果
     */
    PageResult<Employee> page(Integer pageNum, Integer pageSize, String name, Long departmentId, Integer status);
} 