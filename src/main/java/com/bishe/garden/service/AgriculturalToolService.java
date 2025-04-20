package com.bishe.garden.service;

import com.bishe.garden.entity.AgriculturalTool;
import com.bishe.garden.common.PageResult;

import java.util.List;

/**
 * 农业工具服务接口
 */
public interface AgriculturalToolService {
    
    /**
     * 保存农业工具信息
     * @param tool 工具信息
     * @return true：成功 false：失败
     */
    boolean save(AgriculturalTool tool);
    
    /**
     * 更新农业工具信息
     * @param tool 工具信息
     * @return true：成功 false：失败
     */
    boolean update(AgriculturalTool tool);
    
    /**
     * 删除农业工具
     * @param id 工具ID
     * @return true：成功 false：失败
     */
    boolean delete(Long id);
    
    /**
     * 根据ID查询工具
     * @param id 工具ID
     * @return 工具信息
     */
    AgriculturalTool getById(Long id);
    
    /**
     * 根据工具编码查询工具
     * @param toolCode 工具编码
     * @return 工具信息
     */
    AgriculturalTool getByToolCode(String toolCode);
    
    /**
     * 查询所有工具
     * @return 工具列表
     */
    List<AgriculturalTool> listAll();
    
    /**
     * 根据条件查询工具列表
     * @param toolName 工具名称，模糊匹配
     * @param toolType 工具类型
     * @param status 状态
     * @return 工具列表
     */
    List<AgriculturalTool> listByCondition(String toolName, String toolType, Integer status);
    
    /**
     * 分页查询
     * @param pageNum 页码
     * @param pageSize 每页数量
     * @param toolName 工具名称，模糊匹配
     * @param toolType 工具类型
     * @param status 状态
     * @return 分页结果
     */
    PageResult<AgriculturalTool> page(Integer pageNum, Integer pageSize, 
                                    String toolName, String toolType, Integer status);
    
    /**
     * 查询需要维护的工具列表
     * @return 需要维护的工具列表
     */
    List<AgriculturalTool> listNeedMaintenance();
    
    /**
     * 更新工具状态
     * @param id 工具ID
     * @param status 状态值
     * @return true：成功 false：失败
     */
    boolean updateStatus(Long id, Integer status);
} 