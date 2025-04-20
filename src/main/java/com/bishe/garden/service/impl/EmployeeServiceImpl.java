package com.bishe.garden.service.impl;

import com.bishe.garden.common.PageResult;
import com.bishe.garden.entity.Employee;
import com.bishe.garden.mapper.EmployeeMapper;
import com.bishe.garden.service.EmployeeService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 职工服务实现类
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {
    
    @Autowired
    private EmployeeMapper employeeMapper;
    
    @Override
    public boolean save(Employee employee) {
        return employeeMapper.insert(employee) > 0;
    }
    
    @Override
    public boolean update(Employee employee) {
        return employeeMapper.update(employee) > 0;
    }
    
    @Override
    public boolean deleteById(Long id) {
        return employeeMapper.deleteById(id) > 0;
    }
    
    @Override
    public Employee getById(Long id) {
        return employeeMapper.selectById(id);
    }
    
    @Override
    public Employee getByUserId(Long userId) {
        return employeeMapper.selectByUserId(userId);
    }
    
    @Override
    public Employee getByEmployeeNo(String employeeNo) {
        return employeeMapper.selectByEmployeeNo(employeeNo);
    }
    
    @Override
    public Employee getByIdCard(String idCard) {
        return employeeMapper.selectByIdCard(idCard);
    }
    
    @Override
    public List<Employee> listAll() {
        return employeeMapper.selectAll();
    }
    
    @Override
    public List<Employee> listByDepartmentId(Long departmentId) {
        return employeeMapper.selectByDepartmentId(departmentId);
    }
    
    @Override
    public PageResult<Employee> page(Integer pageNum, Integer pageSize, String name, Long departmentId, Integer status) {
        // 使用PageHelper进行分页
        Page<Employee> page = PageHelper.startPage(pageNum, pageSize);
        List<Employee> list = employeeMapper.selectPage(name, departmentId, status);
        
        // 封装分页结果
        return PageResult.of(list, page.getTotal(), pageNum, pageSize);
    }
} 