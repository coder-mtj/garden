package com.bishe.garden.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

import java.io.Serializable;
import java.util.Date;

/**
 * 部门实体类
 * 对应数据库表：sys_department
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Department implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 主键ID
     */
    private Long id;
    
    /**
     * 部门编码，唯一标识
     */
    private String deptCode;
    
    /**
     * 部门名称
     */
    private String deptName;
    
    /**
     * 部门负责人ID
     */
    private Long leaderId;
    
    /**
     * 显示顺序
     */
    private Integer orderNum;
    
    /**
     * 联系电话
     */
    private String phone;
    
    /**
     * 部门邮箱
     */
    private String email;
    
    /**
     * 状态：0-停用，1-启用
     */
    private Integer status;
    
    /**
     * 创建时间
     */
    private Date createTime;
    
    /**
     * 更新时间
     */
    private Date updateTime;
    
    /**
     * 备注信息
     */
    private String remark;
    
    /**
     * 关联的部门负责人信息
     * 非数据库字段，用于展示关联的负责人信息
     */
    private Employee leader;
} 