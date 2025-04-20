package com.bishe.garden.controller;

import com.bishe.garden.common.PageResult;
import com.bishe.garden.common.Result;
import com.bishe.garden.entity.Employee;
import com.bishe.garden.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 职工管理控制器
 * 处理职工信息的增删改查等操作
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    
    @Autowired
    private EmployeeService employeeService;
    
    /**
     * 添加职工信息
     * @param employee 职工信息
     * @return 添加结果
     */
    @PostMapping("/add")
    public Result<String> add(@RequestBody Employee employee) {
        // 检查工号是否已存在
        Employee existEmployee = employeeService.getByEmployeeNo(employee.getEmployeeNo());
        if (existEmployee != null) {
            return Result.error("工号已存在");
        }
        
        // 检查身份证号是否已存在
        existEmployee = employeeService.getByIdCard(employee.getIdCard());
        if (existEmployee != null) {
            return Result.error("身份证号已存在");
        }
        
        // 检查用户ID是否已关联职工
        existEmployee = employeeService.getByUserId(employee.getUserId());
        if (existEmployee != null) {
            return Result.error("该用户已关联职工信息");
        }
        
        // 默认为在职状态
        employee.setStatus(1);
        
        // 设置创建时间和更新时间
        employee.setCreateTime(new Date());
        employee.setUpdateTime(new Date());
        
        // 保存职工信息
        if (employeeService.save(employee)) {
            return Result.success("添加成功");
        } else {
            return Result.error("添加失败");
        }
    }
    
    /**
     * 更新职工信息
     * @param employee 职工信息
     * @return 更新结果
     */
    @PutMapping("/update")
    public Result<String> update(@RequestBody Employee employee) {
        // 检查职工是否存在
        Employee existEmployee = employeeService.getById(employee.getId());
        if (existEmployee == null) {
            return Result.error("职工不存在");
        }
        
        // 检查工号唯一性（排除自身）
        Employee employeeByNo = employeeService.getByEmployeeNo(employee.getEmployeeNo());
        if (employeeByNo != null && !employeeByNo.getId().equals(employee.getId())) {
            return Result.error("工号已存在");
        }
        
        // 检查身份证号唯一性（排除自身）
        Employee employeeByIdCard = employeeService.getByIdCard(employee.getIdCard());
        if (employeeByIdCard != null && !employeeByIdCard.getId().equals(employee.getId())) {
            return Result.error("身份证号已存在");
        }
        
        // 设置更新时间
        employee.setUpdateTime(new Date());
        
        // 保留创建时间
        employee.setCreateTime(existEmployee.getCreateTime());
        
        // 更新职工信息
        if (employeeService.update(employee)) {
            return Result.success("更新成功");
        } else {
            return Result.error("更新失败");
        }
    }
    
    /**
     * 删除职工信息
     * @param id 职工ID
     * @return 删除结果
     */
    @DeleteMapping("/delete/{id}")
    public Result<String> delete(@PathVariable Long id) {
        // 检查职工是否存在
        Employee employee = employeeService.getById(id);
        if (employee == null) {
            return Result.error("职工不存在");
        }
        
        // 删除职工信息
        if (employeeService.deleteById(id)) {
            return Result.success("删除成功");
        } else {
            return Result.error("删除失败");
        }
    }
    
    /**
     * 根据ID查询职工信息
     * @param id 职工ID
     * @return 职工信息
     */
    @GetMapping("/get/{id}")
    public Result<Employee> getById(@PathVariable Long id) {
        Employee employee = employeeService.getById(id);
        if (employee != null) {
            return Result.success(employee);
        } else {
            return Result.error("职工不存在");
        }
    }
    
    /**
     * 查询所有职工信息
     * @return 职工列表
     */
    @GetMapping("/list")
    public Result<List<Employee>> list() {
        List<Employee> employeeList = employeeService.listAll();
        return Result.success(employeeList);
    }
    
    /**
     * 根据部门ID查询职工信息
     * @param departmentId 部门ID
     * @return 职工列表
     */
    @GetMapping("/list/department/{departmentId}")
    public Result<List<Employee>> listByDepartmentId(@PathVariable Long departmentId) {
        List<Employee> employeeList = employeeService.listByDepartmentId(departmentId);
        return Result.success(employeeList);
    }
    
    /**
     * 分页查询职工信息
     * @param pageNum 页码
     * @param pageSize 每页数量
     * @param name 职工姓名（模糊查询）
     * @param departmentId 部门ID
     * @param status 状态
     * @return 职工分页结果
     */
    @GetMapping("/page")
    public Result<PageResult<Employee>> page(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Long departmentId,
            @RequestParam(required = false) Integer status) {
        PageResult<Employee> pageResult = employeeService.page(pageNum, pageSize, name, departmentId, status);
        return Result.success(pageResult);
    }
} 