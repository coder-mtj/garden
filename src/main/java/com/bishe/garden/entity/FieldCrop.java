package com.bishe.garden.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 田块作物关系实体类
 * 对应数据库表：agri_field_crop
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FieldCrop implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 主键ID
     */
    private Long id;
    
    /**
     * 田块ID
     */
    private Long fieldId;
    
    /**
     * 作物ID
     */
    private Long cropId;
    
    /**
     * 种植年份
     */
    private Integer plantingYear;
    
    /**
     * 葡萄树龄（年）
     */
    private Integer vineAge;
    
    /**
     * 种植面积（亩）
     */
    private BigDecimal areaSize;
    
    /**
     * 种植区域多边形坐标数据(JSON格式)
     */
    private String positionData;
    
    /**
     * 种植密度（株/亩）
     */
    private Integer plantDensity;
    
    /**
     * 植株总数
     */
    private Integer totalPlants;
    
    /**
     * 架式类型（如：篱架、棚架等）
     */
    private String trellisType;
    
    /**
     * 实际灌溉方式
     */
    private String irrigationMethod;
    
    /**
     * 实际肥料类型
     */
    private String fertilizerType;
    
    /**
     * 预计产量（公斤/亩）
     */
    private BigDecimal expectedYield;
    
    /**
     * 实际产量（公斤/亩）
     */
    private BigDecimal actualYield;
    
    /**
     * 状态：0-移除，1-正常生长，2-病害，3-虫害
     */
    private Integer status;
    
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    
    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
    
    /**
     * 备注信息
     */
    private String remark;
    
    /**
     * 关联的田块信息（非数据库字段）
     */
    private Field field;
    
    /**
     * 关联的作物信息（非数据库字段）
     */
    private GrapeCrop crop;
} 