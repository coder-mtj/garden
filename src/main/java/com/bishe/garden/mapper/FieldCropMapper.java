package com.bishe.garden.mapper;

import com.bishe.garden.entity.FieldCrop;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 田块作物关系Mapper接口
 */
@Mapper
public interface FieldCropMapper {
    
    /**
     * 插入田块作物关系
     * @param fieldCrop 田块作物关系信息
     * @return 影响行数
     */
    int insert(FieldCrop fieldCrop);
    
    /**
     * 根据ID更新田块作物关系
     * @param fieldCrop 田块作物关系信息
     * @return 影响行数
     */
    int updateById(FieldCrop fieldCrop);
    
    /**
     * 根据ID删除田块作物关系
     * @param id ID
     * @return 影响行数
     */
    int deleteById(Long id);
    
    /**
     * 根据ID查询田块作物关系
     * @param id ID
     * @return 田块作物关系信息
     */
    FieldCrop selectById(Long id);
    
    /**
     * 根据田块ID查询田块作物关系
     * @param fieldId 田块ID
     * @return 田块作物关系列表
     */
    List<FieldCrop> selectByFieldId(Long fieldId);
    
    /**
     * 根据作物ID查询田块作物关系
     * @param cropId 作物ID
     * @return 田块作物关系列表
     */
    List<FieldCrop> selectByCropId(Long cropId);
    
    /**
     * 查询所有田块作物关系
     * @return 田块作物关系列表
     */
    List<FieldCrop> selectAll();
    
    /**
     * 条件查询田块作物关系
     * @param fieldId 田块ID
     * @param cropId 作物ID
     * @param plantingYear 种植年份
     * @param status 状态
     * @return 田块作物关系列表
     */
    List<FieldCrop> selectByCondition(@Param("fieldId") Long fieldId, 
                                     @Param("cropId") Long cropId, 
                                     @Param("plantingYear") Integer plantingYear, 
                                     @Param("status") Integer status);
    
    /**
     * 分页查询田块作物关系
     * @param offset 偏移量
     * @param limit 限制数
     * @param fieldId 田块ID
     * @param cropId 作物ID
     * @param plantingYear 种植年份
     * @param status 状态
     * @return 田块作物关系列表
     */
    List<FieldCrop> selectByPage(@Param("offset") int offset, 
                                @Param("limit") int limit, 
                                @Param("fieldId") Long fieldId, 
                                @Param("cropId") Long cropId, 
                                @Param("plantingYear") Integer plantingYear, 
                                @Param("status") Integer status);
    
    /**
     * 统计符合条件的记录数
     * @param fieldId 田块ID
     * @param cropId 作物ID
     * @param plantingYear 种植年份
     * @param status 状态
     * @return 记录数
     */
    int countByCondition(@Param("fieldId") Long fieldId, 
                         @Param("cropId") Long cropId, 
                         @Param("plantingYear") Integer plantingYear, 
                         @Param("status") Integer status);
    
    /**
     * 更新状态
     * @param id ID
     * @param status 状态
     * @return 影响行数
     */
    int updateStatus(@Param("id") Long id, @Param("status") Integer status);
    
    /**
     * 根据种植年份、田块ID和作物ID查询记录
     * @param plantingYear 种植年份
     * @param fieldId 田块ID
     * @param cropId 作物ID
     * @return 田块作物关系列表
     */
    List<FieldCrop> selectByYearAndIds(@Param("plantingYear") Integer plantingYear, 
                                      @Param("fieldId") Long fieldId, 
                                      @Param("cropId") Long cropId);
} 