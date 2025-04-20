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
 * 工具使用记录实体类
 * 对应数据库表：agri_tool_usage
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ToolUsageRecord implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 主键ID
     */
    private Long id;
    
    /**
     * 工具ID
     */
    private Long toolId;
    
    /**
     * 使用人ID
     */
    private Long employeeId;
    
    /**
     * 使用日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date usageDate;
    
    /**
     * 开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTime;
    
    /**
     * 结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;
    
    /**
     * 使用目的
     */
    private String usagePurpose;
    
    /**
     * 使用区域
     */
    private String usageArea;
    
    /**
     * 使用时长（小时）
     */
    private BigDecimal usageHours;
    
    /**
     * 燃油消耗（升）
     */
    private BigDecimal fuelConsumption;
    
    /**
     * 状态：0-异常，1-正常
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
     * 关联的工具信息
     * 非数据库字段，用于展示关联的工具信息
     */
    private AgriculturalTool tool;
    
    /**
     * 关联的员工信息
     * 非数据库字段，用于展示关联的员工信息
     */
    private Employee employee;
} 