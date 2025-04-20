package com.bishe.garden.mapper;

import com.bishe.garden.entity.AgriculturalTool;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AgriculturalToolMapper {
    
    /**
     * 插入新的农业工具记录
     * @param tool 工具信息
     * @return 影响的行数
     */
    int insert(AgriculturalTool tool);
    
    /**
     * 根据ID更新工具信息
     * @param tool 工具信息
     * @return 影响的行数
     */
    int updateById(AgriculturalTool tool);
    
    /**
     * 根据ID删除工具
     * @param id 工具ID
     * @return 影响的行数
     */
    int deleteById(Long id);
    
    /**
     * 根据ID查询工具
     * @param id 工具ID
     * @return 工具信息
     */
    AgriculturalTool selectById(Long id);
    
    /**
     * 根据工具编码查询工具
     * @param toolCode 工具编码
     * @return 工具信息
     */
    AgriculturalTool selectByToolCode(String toolCode);
    
    /**
     * 查询所有工具
     * @return 工具列表
     */
    List<AgriculturalTool> selectAll();
    
    /**
     * 根据条件查询工具列表
     * @param toolName 工具名称，模糊匹配
     * @param toolType 工具类型
     * @param status 状态
     * @return 工具列表
     */
    List<AgriculturalTool> selectByCondition(@Param("toolName") String toolName, 
                                            @Param("toolType") String toolType, 
                                            @Param("status") Integer status);
    
    /**
     * 分页查询
     * @param toolName 工具名称，模糊匹配
     * @param toolType 工具类型
     * @param status 状态
     * @return 工具列表
     */
    List<AgriculturalTool> selectByPage(@Param("toolName") String toolName, 
                                       @Param("toolType") String toolType, 
                                       @Param("status") Integer status);
    
    /**
     * 查询需要维护的工具列表（维护日期小于等于当前日期）
     * @param currentDate 当前日期
     * @return 需要维护的工具列表
     */
    List<AgriculturalTool> selectNeedMaintenance(@Param("currentDate") Date currentDate);
    
    /**
     * 更新工具状态
     * @param id 工具ID
     * @param status 状态值
     * @return 影响的行数
     */
    int updateStatus(@Param("id") Long id, @Param("status") Integer status);
} 
 