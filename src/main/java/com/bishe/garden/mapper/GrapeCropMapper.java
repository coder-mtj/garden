package com.bishe.garden.mapper;

import com.bishe.garden.entity.GrapeCrop;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 葡萄作物Mapper接口
 */
public interface GrapeCropMapper {
    
    /**
     * 插入葡萄作物记录
     * @param crop 葡萄作物信息
     * @return 影响行数
     */
    int insert(GrapeCrop crop);
    
    /**
     * 根据ID更新葡萄作物信息
     * @param crop 葡萄作物信息
     * @return 影响行数
     */
    int updateById(GrapeCrop crop);
    
    /**
     * 根据ID删除葡萄作物
     * @param id 葡萄作物ID
     * @return 影响行数
     */
    int deleteById(Long id);
    
    /**
     * 根据ID查询葡萄作物信息
     * @param id 葡萄作物ID
     * @return 葡萄作物信息
     */
    GrapeCrop selectById(Long id);
    
    /**
     * 根据作物编号查询葡萄作物信息
     * @param cropCode 作物编号
     * @return 葡萄作物信息
     */
    GrapeCrop selectByCropCode(String cropCode);
    
    /**
     * 根据作物名称查询葡萄作物信息
     * @param cropName 作物名称
     * @return 葡萄作物信息
     */
    GrapeCrop selectByCropName(String cropName);
    
    /**
     * 查询所有葡萄作物
     * @return 葡萄作物列表
     */
    List<GrapeCrop> selectAll();
    
    /**
     * 条件查询葡萄作物
     * @param cropType 作物类型(可选)
     * @param variety 品种(可选，模糊匹配)
     * @param status 状态(可选)
     * @return 葡萄作物列表
     */
    List<GrapeCrop> selectByCondition(@Param("cropType") String cropType, 
                                    @Param("variety") String variety,
                                    @Param("status") Integer status);
    
    /**
     * 分页查询葡萄作物
     * @param offset 起始位置
     * @param limit 查询数量
     * @param cropType 作物类型(可选)
     * @param variety 品种(可选，模糊匹配)
     * @param status 状态(可选)
     * @return 葡萄作物列表
     */
    List<GrapeCrop> selectByPage(@Param("offset") int offset, 
                               @Param("limit") int limit,
                               @Param("cropType") String cropType, 
                               @Param("variety") String variety,
                               @Param("status") Integer status);
    
    /**
     * 统计符合条件的记录数
     * @param cropType 作物类型(可选)
     * @param variety 品种(可选，模糊匹配)
     * @param status 状态(可选)
     * @return 记录数
     */
    int countByCondition(@Param("cropType") String cropType, 
                       @Param("variety") String variety,
                       @Param("status") Integer status);
    
    /**
     * 更新葡萄作物状态
     * @param id 葡萄作物ID
     * @param status 状态
     * @return 影响行数
     */
    int updateStatus(@Param("id") Long id, @Param("status") Integer status);
} 