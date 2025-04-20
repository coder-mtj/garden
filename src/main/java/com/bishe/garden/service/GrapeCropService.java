package com.bishe.garden.service;

import com.bishe.garden.entity.GrapeCrop;

import java.util.List;

/**
 * 葡萄作物服务接口
 */
public interface GrapeCropService {
    
    /**
     * 添加葡萄作物信息
     * @param crop 葡萄作物信息
     * @return 操作结果
     */
    boolean add(GrapeCrop crop);
    
    /**
     * 更新葡萄作物信息
     * @param crop 葡萄作物信息
     * @return 操作结果
     */
    boolean update(GrapeCrop crop);
    
    /**
     * 根据ID删除葡萄作物
     * @param id 葡萄作物ID
     * @return 操作结果
     */
    boolean delete(Long id);
    
    /**
     * 根据ID查询葡萄作物信息
     * @param id 葡萄作物ID
     * @return 葡萄作物信息
     */
    GrapeCrop getById(Long id);
    
    /**
     * 根据作物编号查询葡萄作物信息
     * @param cropCode 作物编号
     * @return 葡萄作物信息
     */
    GrapeCrop getByCropCode(String cropCode);
    
    /**
     * 根据作物名称查询葡萄作物信息
     * @param cropName 作物名称
     * @return 葡萄作物信息
     */
    GrapeCrop getByCropName(String cropName);
    
    /**
     * 查询所有葡萄作物
     * @return 葡萄作物列表
     */
    List<GrapeCrop> getAll();
    
    /**
     * 条件查询葡萄作物
     * @param cropType 作物类型(可选)
     * @param variety 品种(可选，模糊匹配)
     * @param status 状态(可选)
     * @return 葡萄作物列表
     */
    List<GrapeCrop> getByCondition(String cropType, String variety, Integer status);
    
    /**
     * 分页查询葡萄作物
     * @param pageNum 页码
     * @param pageSize 每页记录数
     * @param cropType 作物类型(可选)
     * @param variety 品种(可选，模糊匹配)
     * @param status 状态(可选)
     * @return 葡萄作物分页列表
     */
    List<GrapeCrop> getByPage(int pageNum, int pageSize, String cropType, String variety, Integer status);
    
    /**
     * 统计符合条件的记录数
     * @param cropType 作物类型(可选)
     * @param variety 品种(可选，模糊匹配)
     * @param status 状态(可选)
     * @return 记录数
     */
    int count(String cropType, String variety, Integer status);
    
    /**
     * 更新葡萄作物状态
     * @param id 葡萄作物ID
     * @param status 状态
     * @return 操作结果
     */
    boolean updateStatus(Long id, Integer status);
} 