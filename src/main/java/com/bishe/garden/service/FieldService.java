package com.bishe.garden.service;

import com.bishe.garden.entity.Field;

import java.util.List;

/**
 * 田块服务接口
 */
public interface FieldService {
    
    /**
     * 添加田块信息
     * @param field 田块信息
     * @return 操作结果
     */
    boolean add(Field field);
    
    /**
     * 更新田块信息
     * @param field 田块信息
     * @return 操作结果
     */
    boolean update(Field field);
    
    /**
     * 根据ID删除田块
     * @param id 田块ID
     * @return 操作结果
     */
    boolean delete(Long id);
    
    /**
     * 根据ID查询田块信息
     * @param id 田块ID
     * @return 田块信息
     */
    Field getById(Long id);
    
    /**
     * 根据田块编号查询田块信息
     * @param fieldCode 田块编号
     * @return 田块信息
     */
    Field getByFieldCode(String fieldCode);
    
    /**
     * 查询所有田块
     * @return 田块列表
     */
    List<Field> getAll();
    
    /**
     * 条件查询田块
     * @param fieldName 田块名称(可选，模糊匹配)
     * @param soilType 土壤类型(可选)
     * @param status 状态(可选)
     * @return 田块列表
     */
    List<Field> getByCondition(String fieldName, String soilType, Integer status);
    
    /**
     * 分页查询田块
     * @param pageNum 页码
     * @param pageSize 每页记录数
     * @param fieldName 田块名称(可选，模糊匹配)
     * @param soilType 土壤类型(可选)
     * @param status 状态(可选)
     * @return 田块分页列表
     */
    List<Field> getByPage(int pageNum, int pageSize, String fieldName, String soilType, Integer status);
    
    /**
     * 统计符合条件的记录数
     * @param fieldName 田块名称(可选，模糊匹配)
     * @param soilType 土壤类型(可选)
     * @param status 状态(可选)
     * @return 记录数
     */
    int count(String fieldName, String soilType, Integer status);
    
    /**
     * 更新田块状态
     * @param id 田块ID
     * @param status 状态
     * @return 操作结果
     */
    boolean updateStatus(Long id, Integer status);
} 