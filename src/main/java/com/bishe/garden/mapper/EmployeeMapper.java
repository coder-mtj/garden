package com.bishe.garden.mapper;

import com.bishe.garden.entity.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 职工数据访问接口
 */
@Mapper
public interface EmployeeMapper {
    
    /**
     * 插入职工
     * @param employee 职工信息
     * @return 影响行数
     */
    int insert(Employee employee);
    
    /**
     * 更新职工
     * @param employee 职工信息
     * @return 影响行数
     */
    int update(Employee employee);
    
    /**
     * 根据ID删除职工
     * @param id 职工ID
     * @return 影响行数
     */
    int deleteById(Long id);
    
    /**
     * 根据ID查询职工
     * @param id 职工ID
     * @return 职工信息
     */
    Employee selectById(Long id);
    
    /**
     * 根据用户ID查询职工
     * @param userId 用户ID
     * @return 职工信息
     */
    Employee selectByUserId(Long userId);
    
    /**
     * 根据工号查询职工
     * @param employeeNo 工号
     * @return 职工信息
     */
    Employee selectByEmployeeNo(String employeeNo);
    
    /**
     * 根据身份证号查询职工
     * @param idCard 身份证号
     * @return 职工信息
     */
    Employee selectByIdCard(String idCard);
    
    /**
     * 查询所有职工
     * @return 职工列表
     */
    List<Employee> selectAll();
    
    /**
     * 根据部门ID查询职工
     * @param departmentId 部门ID
     * @return 职工列表
     */
    List<Employee> selectByDepartmentId(Long departmentId);
    
    /**
     * 分页查询职工
     * @param name 职工姓名（模糊查询）
     * @param departmentId 部门ID
     * @param status 状态
     * @return 职工列表
     */
    List<Employee> selectPage(@Param("name") String name, 
                             @Param("departmentId") Long departmentId, 
                             @Param("status") Integer status);
} 