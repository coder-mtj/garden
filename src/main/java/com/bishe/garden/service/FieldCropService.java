package com.bishe.garden.service;

import com.bishe.garden.entity.FieldCrop;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * 田块作物关系服务接口
 */
public interface FieldCropService {
    
    /**
     * 添加田块作物关系
     * @param fieldCrop 田块作物关系信息
     * @return 操作结果
     */
    boolean add(FieldCrop fieldCrop);
    
    /**
     * 批量添加田块作物关系
     * @param fieldCrops 田块作物关系列表
     * @return 成功添加的记录数
     */
    int batchAdd(List<FieldCrop> fieldCrops);
    
    /**
     * 更新田块作物关系
     * @param fieldCrop 田块作物关系信息
     * @return 操作结果
     */
    boolean update(FieldCrop fieldCrop);
    
    /**
     * 根据ID删除田块作物关系
     * @param id 记录ID
     * @return 操作结果
     */
    boolean delete(Long id);
    
    /**
     * 批量删除田块作物关系
     * @param ids ID列表
     * @return 成功删除的记录数
     */
    int batchDelete(List<Long> ids);
    
    /**
     * 根据ID查询田块作物关系
     * @param id 记录ID
     * @return 田块作物关系信息
     */
    FieldCrop getById(Long id);
    
    /**
     * 根据田块ID查询关联的作物关系
     * @param fieldId 田块ID
     * @return 田块作物关系列表
     */
    List<FieldCrop> getByFieldId(Long fieldId);
    
    /**
     * 根据作物ID查询关联的田块关系
     * @param cropId 作物ID
     * @return 田块作物关系列表
     */
    List<FieldCrop> getByCropId(Long cropId);
    
    /**
     * 根据年份、田块ID和作物ID查询田块作物关系
     * @param plantingYear 种植年份
     * @param fieldId 田块ID(可选)
     * @param cropId 作物ID(可选)
     * @return 田块作物关系列表
     */
    List<FieldCrop> getByYearAndIds(Integer plantingYear, Long fieldId, Long cropId);
    
    /**
     * 查询所有田块作物关系
     * @return 田块作物关系列表
     */
    List<FieldCrop> getAll();
    
    /**
     * 条件查询田块作物关系
     * @param fieldId 田块ID(可选)
     * @param cropId 作物ID(可选)
     * @param plantingYear 种植年份(可选)
     * @param status 状态(可选)
     * @return 田块作物关系列表
     */
    List<FieldCrop> getByCondition(Long fieldId, Long cropId, Integer plantingYear, Integer status);
    
    /**
     * 分页查询田块作物关系
     * @param pageNum 页码
     * @param pageSize 每页记录数
     * @param fieldId 田块ID(可选)
     * @param cropId 作物ID(可选)
     * @param plantingYear 种植年份(可选)
     * @param status 状态(可选)
     * @return 田块作物关系分页列表
     */
    List<FieldCrop> getByPage(int pageNum, int pageSize, Long fieldId, Long cropId, Integer plantingYear, Integer status);
    
    /**
     * 统计符合条件的记录数
     * @param fieldId 田块ID(可选)
     * @param cropId 作物ID(可选)
     * @param plantingYear 种植年份(可选)
     * @param status 状态(可选)
     * @return 记录数
     */
    int count(Long fieldId, Long cropId, Integer plantingYear, Integer status);
    
    /**
     * 更新状态
     * @param id 记录ID
     * @param status 状态
     * @return 操作结果
     */
    boolean updateStatus(Long id, Integer status);
    
    /**
     * 导出Excel
     * @param fieldId 田块ID(可选)
     * @param cropId 作物ID(可选)
     * @param plantingYear 种植年份(可选)
     * @param status 状态(可选)
     * @return Excel文件字节数组
     * @throws Exception 导出异常
     */
    byte[] exportExcel(Long fieldId, Long cropId, Integer plantingYear, Integer status) throws Exception;
    
    /**
     * 导入Excel
     * @param file Excel文件
     * @return 成功导入的记录数
     * @throws Exception 导入异常
     */
    int importExcel(MultipartFile file) throws Exception;
    
    /**
     * 按年度统计产量
     * @param fieldId 田块ID(可选)
     * @param cropId 作物ID(可选)
     * @return 按年度统计的产量数据
     */
    Map<Integer, Double> getYieldStatsByYear(Long fieldId, Long cropId);
    
    /**
     * 按作物类型统计种植面积
     * @param plantingYear 种植年份(可选)
     * @return 按作物类型统计的种植面积数据
     */
    Map<String, Double> getAreaStatsByCrop(Integer plantingYear);
    
    /**
     * 获取田块使用情况
     * @param fieldId 田块ID
     * @return 田块使用情况数据
     */
    Map<String, Object> getFieldUsage(Long fieldId);
} 