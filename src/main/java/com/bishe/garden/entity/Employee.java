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
 * 职工实体类
 * 对应数据库表：sys_employee
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 主键ID
     */
    private Long id;
    
    /**
     * 关联的用户ID
     */
    private Long userId;
    
    /**
     * 工号，唯一标识
     */
    private String employeeNo;
    
    /**
     * 职工姓名
     */
    private String name;
    
    /**
     * 性别：0-女，1-男
     */
    private Integer gender;
    
    /**
     * 出生日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "GMT+8")
    private Date birthDate;
    
    /**
     * 身份证号
     */
    private String idCard;
    
    /**
     * 所属部门ID
     */
    private Long departmentId;
    
    /**
     * 职位
     */
    private String position;
    
    /**
     * 入职日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "GMT+8")
    private Date entryDate;
    
    /**
     * 基本工资
     */
    private BigDecimal salary;
    
    /**
     * 状态：0-离职，1-在职
     */
    private Integer status;
    
    /**
     * 家庭住址
     */
    private String address;
    
    /**
     * 紧急联系人
     */
    private String emergencyContact;
    
    /**
     * 紧急联系电话
     */
    private String emergencyPhone;
    
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "GMT+8")
    private Date createTime;
    
    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "GMT+8")
    private Date updateTime;
    
    /**
     * 关联的用户信息
     * 非数据库字段，用于展示关联的用户信息
     */
    private User user;
    
    /**
     * 关联的部门信息
     * 非数据库字段，用于展示关联的部门信息
     */
    private Department department;
}       