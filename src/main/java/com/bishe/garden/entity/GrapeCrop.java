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
 * 葡萄农作物实体类
 * 对应数据库表：agri_crop
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GrapeCrop implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 主键ID
     */
    private Long id;
    
    /**
     * 作物编号
     */
    private String cropCode;
    
    /**
     * 作物名称
     */
    private String cropName;
    
    /**
     * 作物类型（如：葡萄、果树等）
     */
    private String cropType;
    
    /**
     * 葡萄品种（如：赤霞珠、霞多丽等）
     */
    private String variety;
    
    /**
     * 生长周期（天）
     */
    private Integer growthCycle;
    
    /**
     * 适宜土壤
     */
    private String suitableSoil;
    
    /**
     * 适宜温度
     */
    private String suitableTemp;
    
    /**
     * 建议株距（米）
     */
    private BigDecimal plantDistance;
    
    /**
     * 建议行距（米）
     */
    private BigDecimal rowDistance;
    
    /**
     * 水分需求（高/中/低）
     */
    private String waterDemand;
    
    /**
     * 肥料需求（高/中/低）
     */
    private String fertilizerDemand;
    
    /**
     * 推荐修剪方式
     */
    private String pruningMethod;
    
    /**
     * 抗病性（强/中/弱）
     */
    private String diseaseResistance;
    
    /**
     * 抗虫性（强/中/弱）
     */
    private String pestResistance;
    
    /**
     * 显示颜色（可视化用）
     */
    private String color;
    
    /**
     * 图标URL
     */
    private String icon;
    
    /**
     * 状态：0-停用，1-启用
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
} 