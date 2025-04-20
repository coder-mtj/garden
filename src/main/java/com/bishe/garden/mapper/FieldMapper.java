package com.bishe.garden.mapper;

import com.bishe.garden.entity.Field;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 田块Mapper接口
 */
public interface FieldMapper {
    
    /**
     * 插入田块记录
     * @param field 田块信息
     * @return 影响行数
     */
    int insert(Field field);
    
    /**
     * 根据ID更新田块信息
     * @param field 田块信息
     * @return 影响行数
     */
    int updateById(Field field);
    
    /**
     * 根据ID删除田块
     * @param id 田块ID
     * @return 影响行数
     */
    int deleteById(Long id);
    
    /**
     * 根据ID查询田块信息
     * @param id 田块ID
     * @return 田块信息
     */
    Field selectById(Long id);
    
    /**
     * 根据田块编号查询田块信息
     * @param fieldCode 田块编号
     * @return 田块信息
     */
    Field selectByFieldCode(String fieldCode);
    
    /**
     * 查询所有田块
     * @return 田块列表
     */
    List<Field> selectAll();
    
    /**
     * 条件查询田块
     * @param fieldName 田块名称(可选，模糊匹配)
     * @param soilType 土壤类型(可选)
     * @param status 状态(可选)
     * @return 田块列表
     */
    List<Field> selectByCondition(@Param("fieldName") String fieldName, 
                                 @Param("soilType") String soilType,
                                 @Param("status") Integer status);
    
    /**
     * 分页查询田块
     * @param offset 起始位置
     * @param limit 查询数量
     * @param fieldName 田块名称(可选，模糊匹配)
     * @param soilType 土壤类型(可选)
     * @param status 状态(可选)
     * @return 田块列表
     */
    List<Field> selectByPage(@Param("offset") int offset, 
                            @Param("limit") int limit,
                            @Param("fieldName") String fieldName, 
                            @Param("soilType") String soilType,
                            @Param("status") Integer status);
    
    /**
     * 统计符合条件的记录数
     * @param fieldName 田块名称(可选，模糊匹配)
     * @param soilType 土壤类型(可选)
     * @param status 状态(可选)
     * @return 记录数
     */
    int countByCondition(@Param("fieldName") String fieldName, 
                        @Param("soilType") String soilType,
                        @Param("status") Integer status);
    
    /**
     * 更新田块状态
     * @param id 田块ID
     * @param status 状态
     * @return 影响行数
     */
    int updateStatus(@Param("id") Long id, @Param("status") Integer status);
} 