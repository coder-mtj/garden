package com.bishe.garden.mapper;

import com.bishe.garden.entity.ToolUsageRecord;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public interface ToolUsageRecordMapper {
    
    /**
     * 插入新的工具使用记录
     * @param record 使用记录信息
     * @return 影响的行数
     */
    int insert(ToolUsageRecord record);
    
    /**
     * 根据ID更新使用记录
     * @param record 使用记录信息
     * @return 影响的行数
     */
    int updateById(ToolUsageRecord record);
    
    /**
     * 根据ID删除使用记录
     * @param id 记录ID
     * @return 影响的行数
     */
    int deleteById(Long id);
    
    /**
     * 根据ID查询使用记录
     * @param id 记录ID
     * @return 使用记录信息
     */
    ToolUsageRecord selectById(Long id);
    
    /**
     * 根据工具ID查询使用记录
     * @param toolId 工具ID
     * @return 使用记录列表
     */
    List<ToolUsageRecord> selectByToolId(Long toolId);
    
    /**
     * 根据员工ID查询使用记录
     * @param employeeId 员工ID
     * @return 使用记录列表
     */
    List<ToolUsageRecord> selectByEmployeeId(Long employeeId);
    
    /**
     * 查询指定日期范围内的使用记录
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 使用记录列表
     */
    List<ToolUsageRecord> selectByDateRange(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
    
    /**
     * 根据多个条件查询使用记录
     * @param toolId 工具ID，可为null
     * @param employeeId 员工ID，可为null
     * @param startDate 开始日期，可为null
     * @param endDate 结束日期，可为null
     * @param status 状态，可为null
     * @return 使用记录列表
     */
    List<ToolUsageRecord> selectByCondition(@Param("toolId") Long toolId,
                                          @Param("employeeId") Long employeeId,
                                          @Param("startDate") Date startDate,
                                          @Param("endDate") Date endDate,
                                          @Param("status") Integer status);
    
    /**
     * 分页查询使用记录
     * @param toolId 工具ID，可为null
     * @param employeeId 员工ID，可为null
     * @param startDate 开始日期，可为null
     * @param endDate 结束日期，可为null
     * @param status 状态，可为null
     * @return 使用记录列表
     */
    List<ToolUsageRecord> selectByPage(@Param("toolId") Long toolId,
                                      @Param("employeeId") Long employeeId,
                                      @Param("startDate") Date startDate,
                                      @Param("endDate") Date endDate,
                                      @Param("status") Integer status);
    
    /**
     * 统计各工具的使用时长
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 工具ID和对应的使用总时长列表
     */
    List<Map<String, Object>> statisticsByTool(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
    
    /**
     * 统计各员工的工具使用情况
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 员工ID和对应的使用记录统计列表
     */
    List<Map<String, Object>> statisticsByEmployee(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
    
    /**
     * 更新使用记录状态
     * @param id 记录ID
     * @param status 状态值
     * @return 影响的行数
     */
    int updateStatus(@Param("id") Long id, @Param("status") Integer status);
} 
 