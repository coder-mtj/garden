/*
 * 系统数据库表结构
 * 创建时间：2024-01-01
 * 字符集：utf8mb4
 * 存储引擎：InnoDB
 */

-- =============================================
-- 用户表（sys_user）
-- 功能：存储系统用户的基本信息
-- 说明：用户表是系统的基础表，用于用户认证和授权
-- =============================================
CREATE TABLE sys_user (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    username VARCHAR(50) NOT NULL COMMENT '用户名，用于登录',
    password VARCHAR(100) NOT NULL COMMENT '密码，存储加密后的密码',
    real_name VARCHAR(50) COMMENT '真实姓名',
    phone VARCHAR(20) COMMENT '手机号码',
    email VARCHAR(100) COMMENT '电子邮箱',
    status INT NOT NULL DEFAULT 1 COMMENT '状态：0-禁用，1-启用',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    last_login_time DATETIME COMMENT '最后登录时间',
    avatar VARCHAR(255) COMMENT '头像URL',
    remark VARCHAR(500) COMMENT '备注信息',
    UNIQUE KEY uk_username (username)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统用户信息表';

-- =============================================
-- 部门表（sys_department）
-- 功能：存储公司组织架构中的部门信息
-- 说明：部门表是组织架构的基础表，用于构建公司的组织结构
-- =============================================
CREATE TABLE sys_department (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    dept_code VARCHAR(20) NOT NULL COMMENT '部门编码，唯一标识',
    dept_name VARCHAR(50) NOT NULL COMMENT '部门名称',
    leader_id BIGINT COMMENT '部门负责人ID',
    order_num INT NOT NULL DEFAULT 0 COMMENT '显示顺序',
    phone VARCHAR(20) COMMENT '联系电话',
    email VARCHAR(100) COMMENT '部门邮箱',
    status INT NOT NULL DEFAULT 1 COMMENT '状态：0-停用，1-启用',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    remark VARCHAR(500) COMMENT '备注信息',
    UNIQUE KEY uk_dept_code (dept_code),
    UNIQUE KEY uk_dept_name (dept_name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='部门信息表';

-- =============================================
-- 职工表（sys_employee）
-- 功能：存储公司职工的基本信息和工作相关信息
-- 说明：职工表与用户表是一对一关系，每个职工必须对应一个系统用户
-- =============================================
CREATE TABLE sys_employee (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    user_id BIGINT NOT NULL COMMENT '关联的用户ID',
    employee_no VARCHAR(20) NOT NULL COMMENT '工号，唯一标识',
    name VARCHAR(50) NOT NULL COMMENT '职工姓名',
    gender INT NOT NULL DEFAULT 1 COMMENT '性别：0-女，1-男',
    birth_date DATE COMMENT '出生日期',
    id_card VARCHAR(18) NOT NULL COMMENT '身份证号',
    department_id BIGINT NOT NULL COMMENT '所属部门ID',
    position VARCHAR(50) NOT NULL COMMENT '职位',
    entry_date DATE NOT NULL COMMENT '入职日期',
    salary DECIMAL(10,2) NOT NULL DEFAULT 0 COMMENT '基本工资',
    status INT NOT NULL DEFAULT 1 COMMENT '状态：0-离职，1-在职',
    address VARCHAR(200) COMMENT '家庭住址',
    emergency_contact VARCHAR(50) COMMENT '紧急联系人',
    emergency_phone VARCHAR(20) COMMENT '紧急联系电话',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间   ',
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    -- 工号唯一索引：确保每个职工的工号都是唯一的
    UNIQUE KEY uk_employee_no (employee_no),
    -- 身份证号唯一索引：确保每个职工的身份证号都是唯一的
    UNIQUE KEY uk_id_card (id_card),
    -- 用户ID唯一索引：确保每个职工只能关联一个用户（一对一关系）
    UNIQUE KEY uk_user_id (user_id),
    -- 部门ID普通索引：用于加速按部门查询职工的速度
    KEY idx_department_id (department_id),
    -- 部门外键约束：确保职工所属的部门必须存在于部门表中
    CONSTRAINT fk_employee_department FOREIGN KEY (department_id) REFERENCES sys_department(id),
    -- 用户外键约束：确保职工关联的用户必须存在于用户表中
    CONSTRAINT fk_employee_user FOREIGN KEY (user_id) REFERENCES sys_user(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='职工信息表';

-- =============================================
-- 测试数据插入脚本
-- 注意：按照用户、部门、职工的顺序插入，确保引用完整性
-- =============================================

-- 清空现有数据（如果需要）
-- SET FOREIGN_KEY_CHECKS = 0;
-- TRUNCATE TABLE sys_employee;
-- TRUNCATE TABLE sys_department;
-- TRUNCATE TABLE sys_user;
-- SET FOREIGN_KEY_CHECKS = 1;

-- 1. 插入用户数据
INSERT INTO sys_user (id, username, password, real_name, phone, email, status, create_time, update_time, last_login_time, avatar, remark) VALUES
(1, 'admin', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '系统管理员', '13800138000', 'admin@example.com', 1, '2024-01-01 00:00:00', '2024-01-01 00:00:00', '2024-01-01 08:00:00', null, '超级管理员'),
(2, 'zhangsan', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '张三', '13800138001', 'zhangsan@example.com', 1, '2024-01-01 00:00:00', '2024-01-01 00:00:00', '2024-01-01 08:30:00', null, '研发部门负责人'),
(3, 'lisi', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '李四', '13800138002', 'lisi@example.com', 1, '2024-01-01 00:00:00', '2024-01-01 00:00:00', '2024-01-01 09:00:00', null, '市场部门负责人'),
(4, 'wangwu', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '王五', '13800138003', 'wangwu@example.com', 1, '2024-01-01 00:00:00', '2024-01-01 00:00:00', '2024-01-01 09:30:00', null, '财务部门负责人'),
(5, 'zhaoliu', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '赵六', '13800138004', 'zhaoliu@example.com', 1, '2024-01-01 00:00:00', '2024-01-01 00:00:00', '2024-01-01 10:00:00', null, '研发部门成员'),
(6, 'sunqi', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '孙七', '13800138005', 'sunqi@example.com', 1, '2024-01-01 00:00:00', '2024-01-01 00:00:00', '2024-01-01 10:30:00', null, '研发部门成员'),
(7, 'zhouba', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '周八', '13800138006', 'zhouba@example.com', 1, '2024-01-01 00:00:00', '2024-01-01 00:00:00', '2024-01-01 11:00:00', null, '市场部门成员'),
(8, 'wujiu', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '吴九', '13800138007', 'wujiu@example.com', 1, '2024-01-01 00:00:00', '2024-01-01 00:00:00', '2024-01-01 11:30:00', null, '市场部门成员'),
(9, 'zhengshi', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '郑十', '13800138008', 'zhengshi@example.com', 1, '2024-01-01 00:00:00', '2024-01-01 00:00:00', '2024-01-01 12:00:00', null, '财务部门成员'),
(10, 'test', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '测试用户', '13800138009', 'test@example.com', 0, '2024-01-01 00:00:00', '2024-01-01 00:00:00', null, null, '禁用的测试账号');

-- 2. 插入部门数据
INSERT INTO sys_department (id, dept_code, dept_name, leader_id, order_num, phone, email, status, create_time, update_time, remark) VALUES
(1, 'DEV001', '研发部', null, 1, '010-12345678', 'dev@example.com', 1, '2024-01-01 00:00:00', '2024-01-01 00:00:00', '负责产品研发'),
(2, 'MKT001', '市场部', null, 2, '010-12345679', 'marketing@example.com', 1, '2024-01-01 00:00:00', '2024-01-01 00:00:00', '负责市场推广'),
(3, 'FIN001', '财务部', null, 3, '010-12345680', 'finance@example.com', 1, '2024-01-01 00:00:00', '2024-01-01 00:00:00', '负责财务管理'),
(4, 'ADM001', '行政部', null, 4, '010-12345681', 'admin@example.com', 1, '2024-01-01 00:00:00', '2024-01-01 00:00:00', '负责行政管理'),
(5, 'HR001', '人力资源部', null, 5, '010-12345682', 'hr@example.com', 1, '2024-01-01 00:00:00', '2024-01-01 00:00:00', '负责人力资源管理'),
(6, 'TEST001', '测试部门', null, 6, '010-12345683', 'test@example.com', 0, '2024-01-01 00:00:00', '2024-01-01 00:00:00', '测试用部门，已停用');

-- 3. 插入职工数据
INSERT INTO sys_employee (id, user_id, employee_no, name, gender, birth_date, id_card, department_id, position, entry_date, salary, status, address, emergency_contact, emergency_phone, create_time, update_time) VALUES
(1, 2, 'EMP001', '张三', 1, '1985-01-01', '110101198501011234', 1, '技术总监', '2020-01-01', 20000.00, 1, '北京市海淀区', '张三配偶', '13900001111', '2024-01-01 00:00:00', '2024-01-01 00:00:00'),
(2, 3, 'EMP002', '李四', 1, '1986-02-02', '110101198602022345', 2, '市场总监', '2020-01-02', 18000.00, 1, '北京市朝阳区', '李四父亲', '13900002222', '2024-01-01 00:00:00', '2024-01-01 00:00:00'),
(3, 4, 'EMP003', '王五', 1, '1987-03-03', '110101198703033456', 3, '财务总监', '2020-01-03', 16000.00, 1, '北京市西城区', '王五母亲', '13900003333', '2024-01-01 00:00:00', '2024-01-01 00:00:00'),
(4, 5, 'EMP004', '赵六', 1, '1988-04-04', '110101198804044567', 1, '高级工程师', '2020-02-01', 15000.00, 1, '北京市海淀区', '赵六配偶', '13900004444', '2024-01-01 00:00:00', '2024-01-01 00:00:00'),
(5, 6, 'EMP005', '孙七', 1, '1989-05-05', '110101198905055678', 1, '软件工程师', '2020-03-01', 12000.00, 1, '北京市昌平区', '孙七父亲', '13900005555', '2024-01-01 00:00:00', '2024-01-01 00:00:00'),
(6, 7, 'EMP006', '周八', 0, '1990-06-06', '110101199006066789', 2, '市场专员', '2020-04-01', 10000.00, 1, '北京市通州区', '周八母亲', '13900006666', '2024-01-01 00:00:00', '2024-01-01 00:00:00'),
(7, 8, 'EMP007', '吴九', 0, '1991-07-07', '110101199107077890', 2, '销售专员', '2020-05-01', 8000.00, 1, '北京市大兴区', '吴九父亲', '13900007777', '2024-01-01 00:00:00', '2024-01-01 00:00:00'),
(8, 9, 'EMP008', '郑十', 0, '1992-08-08', '110101199208088901', 3, '会计', '2020-06-01', 9000.00, 1, '北京市丰台区', '郑十母亲', '13900008888', '2024-01-01 00:00:00', '2024-01-01 00:00:00'),
(9, 10, 'EMP009', '测试用户', 1, '1993-09-09', '110101199309099012', 6, '测试职位', '2020-07-01', 7000.00, 0, '北京市石景山区', '测试联系人', '13900009999', '2024-01-01 00:00:00', '2024-01-01 00:00:00');

-- 4. 更新部门领导信息
UPDATE sys_department SET leader_id = 1 WHERE id = 1;  -- 张三是研发部负责人
UPDATE sys_department SET leader_id = 2 WHERE id = 2;  -- 李四是市场部负责人
UPDATE sys_department SET leader_id = 3 WHERE id = 3;  -- 王五是财务部负责人

-- 5. 添加更多测试数据 - 用户
INSERT INTO sys_user (username, password, real_name, phone, email, status, create_time, update_time, remark) VALUES
('user1', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '用户一', '13900001000', 'user1@example.com', 1, NOW(), NOW(), '测试用户1'),
('user2', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '用户二', '13900002000', 'user2@example.com', 1, NOW(), NOW(), '测试用户2'),
('user3', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '用户三', '13900003000', 'user3@example.com', 1, NOW(), NOW(), '测试用户3'),
('user4', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '用户四', '13900004000', 'user4@example.com', 1, NOW(), NOW(), '测试用户4'),
('user5', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '用户五', '13900005000', 'user5@example.com', 1, NOW(), NOW(), '测试用户5'),
('inactive_user', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '禁用用户', '13900006000', 'inactive@example.com', 0, NOW(), NOW(), '已禁用的测试用户');

-- 6. 添加更多测试数据 - 部门
INSERT INTO sys_department (dept_code, dept_name, order_num, phone, email, status, create_time, update_time, remark) VALUES
('IT001', 'IT部门', 7, '010-12345684', 'it@example.com', 1, NOW(), NOW(), 'IT支持部门'),
('OPS001', '运维部门', 8, '010-12345685', 'ops@example.com', 1, NOW(), NOW(), '系统运维部门'),
('QA001', '测试部门', 9, '010-12345686', 'qa@example.com', 1, NOW(), NOW(), '质量保证部门'),
('PM001', '项目管理部', 10, '010-12345687', 'pm@example.com', 1, NOW(), NOW(), '项目管理部门'),
('CUS001', '客服部门', 11, '010-12345688', 'customer@example.com', 1, NOW(), NOW(), '客户服务部门'),
('BRANCH001', '分公司', 12, '010-12345689', 'branch@example.com', 0, NOW(), NOW(), '已停用的分公司部门');

-- 7. 获取更多测试用户的ID
SET @user1_id = (SELECT id FROM sys_user WHERE username = 'user1');
SET @user2_id = (SELECT id FROM sys_user WHERE username = 'user2');
SET @user3_id = (SELECT id FROM sys_user WHERE username = 'user3');
SET @user4_id = (SELECT id FROM sys_user WHERE username = 'user4');
SET @user5_id = (SELECT id FROM sys_user WHERE username = 'user5');

-- 8. 获取更多测试部门的ID
SET @it_dept_id = (SELECT id FROM sys_department WHERE dept_code = 'IT001');
SET @ops_dept_id = (SELECT id FROM sys_department WHERE dept_code = 'OPS001');
SET @qa_dept_id = (SELECT id FROM sys_department WHERE dept_code = 'QA001');
SET @pm_dept_id = (SELECT id FROM sys_department WHERE dept_code = 'PM001');
SET @cus_dept_id = (SELECT id FROM sys_department WHERE dept_code = 'CUS001');

-- 9. 添加更多测试数据 - 职工
INSERT INTO sys_employee (user_id, employee_no, name, gender, birth_date, id_card, department_id, position, entry_date, salary, status, address, create_time, update_time) VALUES
(@user1_id, 'EMP010', '用户一', 1, '1994-10-10', '110101199410101234', @it_dept_id, 'IT工程师', '2021-01-01', 11000.00, 1, '北京市海淀区', NOW(), NOW()),
(@user2_id, 'EMP011', '用户二', 0, '1995-11-11', '110101199511112345', @ops_dept_id, '运维工程师', '2021-02-01', 10000.00, 1, '北京市朝阳区', NOW(), NOW()),
(@user3_id, 'EMP012', '用户三', 1, '1996-12-12', '110101199612123456', @qa_dept_id, '测试工程师', '2021-03-01', 9500.00, 1, '北京市海淀区', NOW(), NOW()),
(@user4_id, 'EMP013', '用户四', 0, '1997-01-01', '110101199701014567', @pm_dept_id, '项目经理', '2021-04-01', 13000.00, 1, '北京市西城区', NOW(), NOW()),
(@user5_id, 'EMP014', '用户五', 1, '1998-02-02', '110101199802025678', @cus_dept_id, '客服主管', '2021-05-01', 9000.00, 1, '北京市东城区', NOW(), NOW());

-- 10. 设置部门领导
UPDATE sys_department SET leader_id = (SELECT id FROM sys_employee WHERE employee_no = 'EMP010') WHERE dept_code = 'IT001';
UPDATE sys_department SET leader_id = (SELECT id FROM sys_employee WHERE employee_no = 'EMP011') WHERE dept_code = 'OPS001';
UPDATE sys_department SET leader_id = (SELECT id FROM sys_employee WHERE employee_no = 'EMP012') WHERE dept_code = 'QA001';
UPDATE sys_department SET leader_id = (SELECT id FROM sys_employee WHERE employee_no = 'EMP013') WHERE dept_code = 'PM001';
UPDATE sys_department SET leader_id = (SELECT id FROM sys_employee WHERE employee_no = 'EMP014') WHERE dept_code = 'CUS001';
