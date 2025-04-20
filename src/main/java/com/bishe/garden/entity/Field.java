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
 * 田块实体类
 * 对应数据库表：agri_field
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Field implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 主键ID
     */
    private Long id;
    
    /**
     * 田块编号，唯一标识
     */
    private String fieldCode;
    
    /**
     * 田块名称
     */
    private String fieldName;
    
    /**
     * 面积（亩）
     */
    private BigDecimal area;
    
    /**
     * 土壤类型
     */
    private String soilType;
    
    /**
     * 灌溉方式
     */
    private String irrigationType;
    
    /**
     * 地理坐标X（经度）
     */
    private Double locationX;
    
    /**
     * 地理坐标Y（纬度）
     */
    private Double locationY;
    
    /**
     * 田块多边形坐标数据(JSON格式)
     */
    private String positionData;
    
    /**
     * 状态：0-闲置，1-使用中，2-休耕
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