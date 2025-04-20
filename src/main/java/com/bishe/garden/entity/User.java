package com.bishe.garden.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户实体类
 * 对应数据库表：sys_user
 */
@Data                   // 自动生成getter、setter、equals、hashCode和toString方法
@NoArgsConstructor      // 自动生成无参构造函数
@AllArgsConstructor     // 自动生成全参构造函数
@Builder                // 自动生成建造者模式
public class User implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 主键ID
     */
    private Long id;
    
    /**
     * 用户名，用于登录
     */
    private String username;
    
    /**
     * 密码，存储加密后的密码
     */
    private String password;
    
    /**
     * 真实姓名
     */
    private String realName;
    
    /**
     * 手机号码
     */
    private String phone;
    
    /**
     * 电子邮箱
     */
    private String email;
    
    /**
     * 状态：0-禁用，1-启用
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
     * 最后登录时间
     */
    private Date lastLoginTime;
    
    /**
     * 头像URL
     */
    private String avatar;
    
    /**
     * 创建人
     */
    private String createBy;
    
    /**
     * 更新人
     */
    private String updateBy;
    
    /**
     * 备注信息
     */
    private String remark;
} 