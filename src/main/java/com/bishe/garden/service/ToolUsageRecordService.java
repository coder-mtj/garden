package com.bishe.garden.service;

import com.bishe.garden.entity.ToolUsageRecord;
import com.bishe.garden.common.PageResult;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 工具使用记录服务接口
 */
public interface ToolUsageRecordService {
    
    /**
     * 保存工具使用记录
     * @param record 使用记录信息
     * @return true：成功 false：失败
     */
    boolean save(ToolUsageRecord record);
    
    /**
     * 更新工具使用记录
     * @param record 使用记录信息
     * @return true：成功 false：失败
     */
    boolean update(ToolUsageRecord record);
    
    /**
     * 删除工具使用记录
     * @param id 记录ID
     * @return true：成功 false：失败
     */
    boolean delete(Long id);
    
    /**
     * 根据ID查询使用记录
     * @param id 记录ID
     * @return 使用记录信息
     */
    ToolUsageRecord getById(Long id);
    
    /**
     * 根据工具ID查询使用记录
     * @param toolId 工具ID
     * @return 使用记录列表
     */
    List<ToolUsageRecord> listByToolId(Long toolId);
    
    /**
     * 根据员工ID查询使用记录
     * @param employeeId 员工ID
     * @return 使用记录列表
     */
    List<ToolUsageRecord> listByEmployeeId(Long employeeId);
    
    /**
     * 查询指定日期范围内的使用记录
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 使用记录列表
     */
    List<ToolUsageRecord> listByDateRange(Date startDate, Date endDate);
    
    /**
     * 根据多个条件查询使用记录
     * @param toolId 工具ID，可为null
     * @param employeeId 员工ID，可为null
     * @param startDate 开始日期，可为null
     * @param endDate 结束日期，可为null
     * @param status 状态，可为null
     * @return 使用记录列表
     */
    List<ToolUsageRecord> listByCondition(Long toolId, Long employeeId, 
                                        Date startDate, Date endDate, Integer status);
    
    /**
     * 分页查询使用记录
     * @param pageNum 页码
     * @param pageSize 每页数量
     * @param toolId 工具ID，可为null
     * @param employeeId 员工ID，可为null
     * @param startDate 开始日期，可为null
     * @param endDate 结束日期，可为null
     * @param status 状态，可为null
     * @return 分页结果
     */
    PageResult<ToolUsageRecord> page(Integer pageNum, Integer pageSize, Long toolId, Long employeeId, 
                                   Date startDate, Date endDate, Integer status);
    
    /**
     * 统计各工具的使用时长
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 工具使用统计列表
     */
    List<Map<String, Object>> statisticsByTool(Date startDate, Date endDate);
    
    /**
     * 统计各员工的工具使用情况
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 员工使用统计列表
     */
    List<Map<String, Object>> statisticsByEmployee(Date startDate, Date endDate);
    
    /**
     * 更新使用记录状态
     * @param id 记录ID
     * @param status 状态值
     * @return true：成功 false：失败
     */
    boolean updateStatus(Long id, Integer status);
} 