package com.bishe.garden.service.impl;

import com.bishe.garden.entity.AgriculturalTool;
import com.bishe.garden.mapper.AgriculturalToolMapper;
import com.bishe.garden.service.AgriculturalToolService;
import com.bishe.garden.common.PageResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 农业工具服务实现类
 */
@Service
public class AgriculturalToolServiceImpl implements AgriculturalToolService {
    
    @Autowired
    private AgriculturalToolMapper agriculturalToolMapper;
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(AgriculturalTool tool) {
        // 检查工具编码是否已存在
        AgriculturalTool existTool = agriculturalToolMapper.selectByToolCode(tool.getToolCode());
        if (existTool != null) {
            throw new RuntimeException("工具编码已存在");
        }
        
        // 设置默认值
        if (tool.getStatus() == null) {
            tool.setStatus(1); // 默认状态为正常
        }
        
        return agriculturalToolMapper.insert(tool) > 0;
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(AgriculturalTool tool) {
        // 检查工具是否存在
        AgriculturalTool existTool = agriculturalToolMapper.selectById(tool.getId());
        if (existTool == null) {
            throw new RuntimeException("工具不存在");
        }
        
        // 如果工具编码有变更，检查是否重复
        if (!existTool.getToolCode().equals(tool.getToolCode())) {
            AgriculturalTool codeExistTool = agriculturalToolMapper.selectByToolCode(tool.getToolCode());
            if (codeExistTool != null) {
                throw new RuntimeException("工具编码已存在");
            }
        }
        
        return agriculturalToolMapper.updateById(tool) > 0;
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Long id) {
        // 检查工具是否存在
        AgriculturalTool existTool = agriculturalToolMapper.selectById(id);
        if (existTool == null) {
            throw new RuntimeException("工具不存在");
        }
        
        // TODO: 检查工具是否有关联的使用记录，如果有则不允许删除
        
        return agriculturalToolMapper.deleteById(id) > 0;
    }
    
    @Override
    public AgriculturalTool getById(Long id) {
        return agriculturalToolMapper.selectById(id);
    }
    
    @Override
    public AgriculturalTool getByToolCode(String toolCode) {
        return agriculturalToolMapper.selectByToolCode(toolCode);
    }
    
    @Override
    public List<AgriculturalTool> listAll() {
        return agriculturalToolMapper.selectAll();
    }
    
    @Override
    public List<AgriculturalTool> listByCondition(String toolName, String toolType, Integer status) {
        return agriculturalToolMapper.selectByCondition(toolName, toolType, status);
    }
    
    @Override
    public PageResult<AgriculturalTool> page(Integer pageNum, Integer pageSize, 
                                          String toolName, String toolType, Integer status) {
        // 设置默认值
        pageNum = pageNum == null ? 1 : pageNum;
        pageSize = pageSize == null ? 10 : pageSize;
        
        // 分页查询
        PageHelper.startPage(pageNum, pageSize);
        Page<AgriculturalTool> page = (Page<AgriculturalTool>) agriculturalToolMapper.selectByPage(toolName, toolType, status);
        
        // 封装分页结果
        PageResult<AgriculturalTool> pageResult = new PageResult<>();
        pageResult.setTotal(page.getTotal());
        pageResult.setList(page.getResult());
        pageResult.setPageNum(page.getPageNum());
        pageResult.setPageSize(page.getPageSize());
        pageResult.setPages(page.getPages());
        pageResult.setHasNextPage(page.getPageNum() < page.getPages());
        pageResult.setHasPreviousPage(page.getPageNum() > 1);
        
        return pageResult;
    }
    
    @Override
    public List<AgriculturalTool> listNeedMaintenance() {
        return agriculturalToolMapper.selectNeedMaintenance(new Date());
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateStatus(Long id, Integer status) {
        // 检查工具是否存在
        AgriculturalTool existTool = agriculturalToolMapper.selectById(id);
        if (existTool == null) {
            throw new RuntimeException("工具不存在");
        }
        
        return agriculturalToolMapper.updateStatus(id, status) > 0;
    }
} 