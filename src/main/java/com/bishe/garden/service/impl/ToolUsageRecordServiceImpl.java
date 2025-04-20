package com.bishe.garden.service.impl;

import com.bishe.garden.entity.ToolUsageRecord;
import com.bishe.garden.entity.AgriculturalTool;
import com.bishe.garden.mapper.ToolUsageRecordMapper;
import com.bishe.garden.mapper.AgriculturalToolMapper;
import com.bishe.garden.service.ToolUsageRecordService;
import com.bishe.garden.common.PageResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 工具使用记录服务实现类
 */
@Service
public class ToolUsageRecordServiceImpl implements ToolUsageRecordService {
    
    @Autowired
    private ToolUsageRecordMapper toolUsageRecordMapper;
    
    @Autowired
    private AgriculturalToolMapper agriculturalToolMapper;
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(ToolUsageRecord record) {
        // 检查工具是否存在
        AgriculturalTool tool = agriculturalToolMapper.selectById(record.getToolId());
        if (tool == null) {
            throw new RuntimeException("工具不存在");
        }
        
        // 检查工具状态是否正常
        if (tool.getStatus() != 1) {
            throw new RuntimeException("工具状态不正常，无法使用");
        }
        
        // 设置默认值
        if (record.getStatus() == null) {
            record.setStatus(1); // 默认状态为正常
        }
        
        return toolUsageRecordMapper.insert(record) > 0;
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(ToolUsageRecord record) {
        // 检查记录是否存在
        ToolUsageRecord existRecord = toolUsageRecordMapper.selectById(record.getId());
        if (existRecord == null) {
            throw new RuntimeException("使用记录不存在");
        }
        
        return toolUsageRecordMapper.updateById(record) > 0;
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Long id) {
        // 检查记录是否存在
        ToolUsageRecord existRecord = toolUsageRecordMapper.selectById(id);
        if (existRecord == null) {
            throw new RuntimeException("使用记录不存在");
        }
        
        return toolUsageRecordMapper.deleteById(id) > 0;
    }
    
    @Override
    public ToolUsageRecord getById(Long id) {
        return toolUsageRecordMapper.selectById(id);
    }
    
    @Override
    public List<ToolUsageRecord> listByToolId(Long toolId) {
        return toolUsageRecordMapper.selectByToolId(toolId);
    }
    
    @Override
    public List<ToolUsageRecord> listByEmployeeId(Long employeeId) {
        return toolUsageRecordMapper.selectByEmployeeId(employeeId);
    }
    
    @Override
    public List<ToolUsageRecord> listByDateRange(Date startDate, Date endDate) {
        return toolUsageRecordMapper.selectByDateRange(startDate, endDate);
    }
    
    @Override
    public List<ToolUsageRecord> listByCondition(Long toolId, Long employeeId, 
                                               Date startDate, Date endDate, Integer status) {
        return toolUsageRecordMapper.selectByCondition(toolId, employeeId, startDate, endDate, status);
    }
    
    @Override
    public PageResult<ToolUsageRecord> page(Integer pageNum, Integer pageSize, Long toolId, Long employeeId, 
                                          Date startDate, Date endDate, Integer status) {
        // 设置默认值
        pageNum = pageNum == null ? 1 : pageNum;
        pageSize = pageSize == null ? 10 : pageSize;
        
        // 分页查询
        PageHelper.startPage(pageNum, pageSize);
        Page<ToolUsageRecord> page = (Page<ToolUsageRecord>) toolUsageRecordMapper.selectByPage(
            toolId, employeeId, startDate, endDate, status);
        
        // 封装分页结果
        PageResult<ToolUsageRecord> pageResult = new PageResult<>();
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
    public List<Map<String, Object>> statisticsByTool(Date startDate, Date endDate) {
        return toolUsageRecordMapper.statisticsByTool(startDate, endDate);
    }
    
    @Override
    public List<Map<String, Object>> statisticsByEmployee(Date startDate, Date endDate) {
        return toolUsageRecordMapper.statisticsByEmployee(startDate, endDate);
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateStatus(Long id, Integer status) {
        // 检查记录是否存在
        ToolUsageRecord existRecord = toolUsageRecordMapper.selectById(id);
        if (existRecord == null) {
            throw new RuntimeException("使用记录不存在");
        }
        
        return toolUsageRecordMapper.updateStatus(id, status) > 0;
    }
} 