package com.bishe.garden.service.impl;

import com.bishe.garden.common.PageResult;
import com.bishe.garden.entity.Department;
import com.bishe.garden.mapper.DepartmentMapper;
import com.bishe.garden.service.DepartmentService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 部门服务实现类
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {
    
    @Autowired
    private DepartmentMapper departmentMapper;
    
    @Override
    public boolean save(Department department) {
        return departmentMapper.insert(department) > 0;
    }
    
    @Override
    public boolean update(Department department) {
        return departmentMapper.update(department) > 0;
    }
    
    @Override
    public boolean deleteById(Long id) {
        return departmentMapper.deleteById(id) > 0;
    }
    
    @Override
    public Department getById(Long id) {
        return departmentMapper.selectById(id);
    }
    
    @Override
    public Department getByDeptCode(String deptCode) {
        return departmentMapper.selectByDeptCode(deptCode);
    }
    
    @Override
    public Department getByDeptName(String deptName) {
        return departmentMapper.selectByDeptName(deptName);
    }
    
    @Override
    public List<Department> listAll() {
        return departmentMapper.selectAll();
    }
    
    @Override
    public PageResult<Department> page(Integer pageNum, Integer pageSize, String deptName, Integer status) {
        // 使用PageHelper进行分页
        Page<Department> page = PageHelper.startPage(pageNum, pageSize);
        List<Department> list = departmentMapper.selectPage(deptName, status);
        
        // 封装分页结果
        return PageResult.of(list, page.getTotal(), pageNum, pageSize);
    }
} 