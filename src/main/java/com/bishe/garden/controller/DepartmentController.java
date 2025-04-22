package com.bishe.garden.controller;

import com.bishe.garden.common.PageResult;
import com.bishe.garden.common.Result;
import com.bishe.garden.entity.Department;
import com.bishe.garden.entity.Employee;
import com.bishe.garden.service.DepartmentService;
import com.bishe.garden.service.EmployeeService;
import com.bishe.garden.util.TokenValidationUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 部门管理控制器
 * 处理部门信息的增删改查等操作
 */
@RestController
@RequestMapping("/department")
public class DepartmentController {
    
    @Autowired
    private DepartmentService departmentService;
    
    @Autowired
    private EmployeeService employeeService;
    
    @Autowired
    private TokenValidationUtil tokenValidationUtil;
    
    /**
     * 添加部门信息
     * @param department 部门信息
     * @param request HTTP请求
     * @return 添加结果
     */
    @PostMapping("/add")
    public Result<String> add(@RequestBody Department department, HttpServletRequest request) {
        // 验证token
        Result<?> validationResult = tokenValidationUtil.validateToken(request);
        if (validationResult != null) {
            return Result.error(validationResult.getMessage());
        }
        
        // 检查部门编码是否已存在
        Department existDepartment = departmentService.getByDeptCode(department.getDeptCode());
        if (existDepartment != null) {
            return Result.error("部门编码已存在");
        }
        
        // 检查部门名称是否已存在
        existDepartment = departmentService.getByDeptName(department.getDeptName());
        if (existDepartment != null) {
            return Result.error("部门名称已存在");
        }
        
        // 设置默认排序
        if (department.getOrderNum() == null) {
            department.setOrderNum(0);
        }
        
        // 设置默认状态为启用
        department.setStatus(1);
        
        // The Leader ID should exist if provided
        if (department.getLeaderId() != null) {
            Employee leader = employeeService.getById(department.getLeaderId());
            if (leader == null) {
                return Result.error("部门负责人不存在");
            }
        }
        
        // 设置创建和更新时间
        department.setCreateTime(new Date());
        department.setUpdateTime(new Date());
        
        // 保存部门信息
        if (departmentService.save(department)) {
            return Result.success("添加成功");
        } else {
            return Result.error("添加失败");
        }
    }
    
    /**
     * 更新部门信息
     * @param department 部门信息
     * @param request HTTP请求
     * @return 更新结果
     */
    @PutMapping("/update")
    public Result<String> update(@RequestBody Department department, HttpServletRequest request) {
        // 验证token
        Result<?> validationResult = tokenValidationUtil.validateToken(request);
        if (validationResult != null) {
            return Result.error(validationResult.getMessage());
        }
        
        // 检查部门是否存在
        Department existDepartment = departmentService.getById(department.getId());
        if (existDepartment == null) {
            return Result.error("部门不存在");
        }
        
        // 检查部门编码唯一性（排除自身）
        Department departmentByCode = departmentService.getByDeptCode(department.getDeptCode());
        if (departmentByCode != null && !departmentByCode.getId().equals(department.getId())) {
            return Result.error("部门编码已存在");
        }
        
        // 检查部门名称唯一性（排除自身）
        Department departmentByName = departmentService.getByDeptName(department.getDeptName());
        if (departmentByName != null && !departmentByName.getId().equals(department.getId())) {
            return Result.error("部门名称已存在");
        }
        
        // The Leader ID should exist if provided
        if (department.getLeaderId() != null) {
            Employee leader = employeeService.getById(department.getLeaderId());
            if (leader == null) {
                return Result.error("部门负责人不存在");
            }
        }
        
        // 设置更新时间
        department.setUpdateTime(new Date());
        
        // 保留创建时间
        department.setCreateTime(existDepartment.getCreateTime());
        
        // 更新部门信息
        if (departmentService.update(department)) {
            return Result.success("更新成功");
        } else {
            return Result.error("更新失败");
        }
    }
    
    /**
     * 删除部门信息
     * @param id 部门ID
     * @param request HTTP请求
     * @return 删除结果
     */
    @DeleteMapping("/delete/{id}")
    public Result<String> delete(@PathVariable Long id, HttpServletRequest request) {
        // 验证token
        Result<?> validationResult = tokenValidationUtil.validateToken(request);
        if (validationResult != null) {
            return Result.error(validationResult.getMessage());
        }
        
        // 检查部门是否存在
        Department department = departmentService.getById(id);
        if (department == null) {
            return Result.error("部门不存在");
        }
        
        // 检查部门下是否有员工
        List<Employee> employees = employeeService.listByDepartmentId(id);
        if (employees != null && !employees.isEmpty()) {
            return Result.error("该部门下有员工，无法删除");
        }
        
        // 删除部门信息
        if (departmentService.deleteById(id)) {
            return Result.success("删除成功");
        } else {
            return Result.error("删除失败");
        }
    }
    
    /**
     * 根据ID查询部门信息
     * @param id 部门ID
     * @param request HTTP请求
     * @return 部门信息
     */
    @GetMapping("/get/{id}")
    public Result<Department> getById(@PathVariable Long id, HttpServletRequest request) {
        // 验证token
        Result<?> validationResult = tokenValidationUtil.validateToken(request);
        if (validationResult != null) {
            return Result.error(validationResult.getMessage());
        }
        
        Department department = departmentService.getById(id);
        if (department != null) {
            // 如果有负责人，获取负责人信息
            if (department.getLeaderId() != null) {
                Employee leader = employeeService.getById(department.getLeaderId());
                department.setLeader(leader);
            }
            return Result.success(department);
        } else {
            return Result.error("部门不存在");
        }
    }
    
    /**
     * 查询所有部门信息
     * @param request HTTP请求
     * @return 部门列表
     */
    @GetMapping("/list")
    public Result<List<Department>> list(HttpServletRequest request) {
        // 验证token
        Result<?> validationResult = tokenValidationUtil.validateToken(request);
        if (validationResult != null) {
            return Result.error(validationResult.getMessage());
        }
        
        List<Department> departmentList = departmentService.listAll();
        // 设置部门负责人信息
        for (Department dept : departmentList) {
            if (dept.getLeaderId() != null) {
                Employee leader = employeeService.getById(dept.getLeaderId());
                dept.setLeader(leader);
            }
        }
        return Result.success(departmentList);
    }
    
    /**
     * 分页查询部门信息
     * @param pageNum 页码
     * @param pageSize 每页数量
     * @param deptName 部门名称（模糊查询）
     * @param status 状态
     * @param request HTTP请求
     * @return 部门分页结果
     */
    @GetMapping("/page")
    public Result<PageResult<Department>> page(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) String deptName,
            @RequestParam(required = false) Integer status,
            HttpServletRequest request) {
        // 验证token
        Result<?> validationResult = tokenValidationUtil.validateToken(request);
        if (validationResult != null) {
            return Result.error(validationResult.getMessage());
        }
        
        PageResult<Department> pageResult = departmentService.page(pageNum, pageSize, deptName, status);
        // 设置部门负责人信息
        for (Department dept : pageResult.getList()) {
            if (dept.getLeaderId() != null) {
                Employee leader = employeeService.getById(dept.getLeaderId());
                dept.setLeader(leader);
            }
        }
        return Result.success(pageResult);
    }
} 