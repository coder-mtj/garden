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
 * 农业工具实体类
 * 对应数据库表：agri_tool
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AgriculturalTool implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 主键ID
     */
    private Long id;
    
    /**
     * 工具编号，唯一标识
     */
    private String toolCode;
    
    /**
     * 工具名称
     */
    private String toolName;
    
    /**
     * 工具类型（如：拖拉机、播种机、收割机等）
     */
    private String toolType;
    
    /**
     * 品牌
     */
    private String brand;
    
    /**
     * 型号
     */
    private String model;
    
    /**
     * 购买日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date purchaseDate;
    
    /**
     * 购买价格
     */
    private BigDecimal price;
    
    /**
     * 状态：0-报废，1-正常，2-维修中
     */
    private Integer status;
    
    /**
     * 存放位置
     */
    private String location;
    
    /**
     * 维护周期（天）
     */
    private Integer maintenanceCycle;
    
    /**
     * 上次维护日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date lastMaintenanceDate;
    
    /**
     * 下次维护日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date nextMaintenanceDate;
    
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