/*
 * 农业工具数据库表结构
 * 创建时间：2024-01-01
 * 字符集：utf8mb4
 * 存储引擎：InnoDB
 */

-- =============================================
-- 农业工具表（agri_tool）
-- 功能：存储农业生产中使用的各类工具信息
-- 说明：包含工具的基本信息、采购信息和维护信息
-- =============================================
CREATE TABLE agri_tool (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    tool_code VARCHAR(20) NOT NULL COMMENT '工具编号，唯一标识',
    tool_name VARCHAR(50) NOT NULL COMMENT '工具名称',
    tool_type VARCHAR(20) NOT NULL COMMENT '工具类型（如：拖拉机、播种机、收割机等）',
    brand VARCHAR(50) COMMENT '品牌',
    model VARCHAR(50) COMMENT '型号',
    purchase_date DATE NOT NULL COMMENT '购买日期',
    price DECIMAL(10,2) NOT NULL DEFAULT 0 COMMENT '购买价格',
    status INT NOT NULL DEFAULT 1 COMMENT '状态：0-报废，1-正常，2-维修中',
    location VARCHAR(100) COMMENT '存放位置',
    maintenance_cycle INT COMMENT '维护周期（天）',
    last_maintenance_date DATE COMMENT '上次维护日期',
    next_maintenance_date DATE COMMENT '下次维护日期',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    remark VARCHAR(500) COMMENT '备注信息',
    UNIQUE KEY uk_tool_code (tool_code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='农业工具信息表';

-- =============================================
-- 工具使用记录表（agri_tool_usage）
-- 功能：记录农业工具的使用情况
-- 说明：记录谁在什么时间使用了什么工具，使用目的和状态等
-- =============================================
CREATE TABLE agri_tool_usage (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    tool_id BIGINT NOT NULL COMMENT '工具ID',
    employee_id BIGINT NOT NULL COMMENT '使用人ID',
    usage_date DATE NOT NULL COMMENT '使用日期',
    start_time DATETIME NOT NULL COMMENT '开始时间',
    end_time DATETIME COMMENT '结束时间',
    usage_purpose VARCHAR(200) NOT NULL COMMENT '使用目的',
    usage_area VARCHAR(100) COMMENT '使用区域',
    usage_hours DECIMAL(5,1) DEFAULT 0 COMMENT '使用时长（小时）',
    fuel_consumption DECIMAL(5,1) DEFAULT 0 COMMENT '燃油消耗（升）',
    status INT NOT NULL DEFAULT 1 COMMENT '状态：0-异常，1-正常',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    remark VARCHAR(500) COMMENT '备注信息',
    KEY idx_tool_id (tool_id),
    KEY idx_employee_id (employee_id),
    KEY idx_usage_date (usage_date),
    CONSTRAINT fk_usage_tool FOREIGN KEY (tool_id) REFERENCES agri_tool(id),
    CONSTRAINT fk_usage_employee FOREIGN KEY (employee_id) REFERENCES sys_employee(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='工具使用记录表';

-- =============================================
-- 测试数据插入脚本
-- =============================================

-- 1. 插入农业工具数据
INSERT INTO agri_tool (tool_code, tool_name, tool_type, brand, model, purchase_date, price, status, location, maintenance_cycle, last_maintenance_date, next_maintenance_date, create_time, update_time, remark) VALUES
('TL2024001', '大型拖拉机', '拖拉机', '东方红', 'LX2004', '2023-10-15', 250000.00, 1, '农机库房A区', 90, '2024-01-10', '2024-04-10', NOW(), NOW(), '主要用于大田作业'),
('TL2024002', '小型拖拉机', '拖拉机', '福田', 'FT254', '2023-11-20', 120000.00, 1, '农机库房A区', 60, '2024-01-15', '2024-03-15', NOW(), NOW(), '用于小面积作业'),
('TL2024003', '自走式喷雾机', '喷雾机', '约翰迪尔', 'JD4066R', '2023-12-05', 180000.00, 1, '农机库房B区', 30, '2024-01-20', '2024-02-20', NOW(), NOW(), '用于农药喷洒'),
('TL2024004', '播种机', '播种机', '德国LEMKEN', 'L2000', '2023-09-10', 85000.00, 1, '农机库房B区', 120, '2023-12-10', '2024-04-10', NOW(), NOW(), '精准播种设备'),
('TL2024005', '旋耕机', '耕作机械', '久保田', 'KB500', '2023-08-25', 35000.00, 1, '农机库房C区', 60, '2024-01-05', '2024-03-05', NOW(), NOW(), '用于土壤耕作'),
('TL2024006', '收割机', '收割机', '雷沃', 'LW988', '2023-07-15', 320000.00, 2, '维修车间', 90, '2023-11-20', '2024-02-20', NOW(), NOW(), '大型联合收割机，目前在维修'),
('TL2024007', '农用运输车', '运输工具', '五菱', 'WL3C', '2023-06-10', 45000.00, 1, '停车场', 60, '2023-12-15', '2024-02-15', NOW(), NOW(), '用于农产品和工具运输'),
('TL2024008', '土壤取样器', '检测设备', 'AMS', 'SS10', '2023-05-20', 12000.00, 1, '工具室', 180, '2023-11-25', '2024-05-25', NOW(), NOW(), '用于土壤样本采集和分析'),
('TL2024009', '葡萄绑枝机', '果园设备', '百绑', 'BB2000', '2023-04-15', 5800.00, 1, '工具室', 90, '2024-01-12', '2024-04-12', NOW(), NOW(), '用于葡萄园绑枝作业'),
('TL2024010', '果园喷药机', '喷雾机', '科霸', 'KB200', '2023-03-10', 28000.00, 0, '报废库房', 60, '2023-10-10', '2024-01-10', NOW(), NOW(), '设备老化严重，已报废');

-- 2. 插入工具使用记录数据
-- 假设员工ID为1-8（实际使用时请确保这些ID在sys_employee表中存在）
INSERT INTO agri_tool_usage (tool_id, employee_id, usage_date, start_time, end_time, usage_purpose, usage_area, usage_hours, fuel_consumption, status, create_time, update_time, remark) VALUES
(1, 1, '2024-01-15', '2024-01-15 08:00:00', '2024-01-15 16:00:00', '大田翻耕', 'A区1号田', 8.0, 45.5, 1, NOW(), NOW(), '完成A区1号田翻耕作业'),
(1, 2, '2024-01-16', '2024-01-16 07:30:00', '2024-01-16 15:30:00', '大田整地', 'A区2号田', 8.0, 42.0, 1, NOW(), NOW(), '完成A区2号田整地作业'),
(2, 3, '2024-01-15', '2024-01-15 09:00:00', '2024-01-15 14:00:00', '果园作业', 'B区葡萄园', 5.0, 15.0, 1, NOW(), NOW(), '完成B区葡萄园整地'),
(3, 4, '2024-01-17', '2024-01-17 08:30:00', '2024-01-17 11:30:00', '葡萄园喷药', 'B区葡萄园', 3.0, 8.5, 1, NOW(), NOW(), '完成葡萄园春季第一次喷药'),
(4, 5, '2024-01-18', '2024-01-18 07:00:00', '2024-01-18 16:00:00', '小麦播种', 'C区1号田', 9.0, 25.0, 1, NOW(), NOW(), '完成C区1号田小麦播种'),
(5, 6, '2024-01-19', '2024-01-19 08:00:00', '2024-01-19 12:00:00', '土壤耕作', 'D区2号田', 4.0, 10.0, 0, NOW(), NOW(), '设备出现异常，提前结束作业'),
(7, 7, '2024-01-20', '2024-01-20 09:00:00', '2024-01-20 17:00:00', '农产品运输', '市场配送', 8.0, 20.0, 1, NOW(), NOW(), '完成当日市场配送任务'),
(8, 8, '2024-01-21', '2024-01-21 10:00:00', '2024-01-21 15:00:00', '土壤取样', '各区土壤监测点', 5.0, 0, 1, NOW(), NOW(), '完成季度土壤监测取样'),
(9, 1, '2024-01-22', '2024-01-22 08:00:00', '2024-01-22 16:00:00', '葡萄绑枝', 'B区葡萄园', 8.0, 0, 1, NOW(), NOW(), '完成春季葡萄绑枝作业'),
(1, 2, '2024-01-23', '2024-01-23 07:30:00', '2024-01-23 16:30:00', '大田耕作', 'E区新开发地块', 9.0, 50.0, 1, NOW(), NOW(), '完成新地块首次耕作'),
(2, 3, '2024-01-24', '2024-01-24 08:00:00', '2024-01-24 12:00:00', '小型整地', 'F区试验田', 4.0, 12.0, 1, NOW(), NOW(), '完成试验田整地'),
(3, 4, '2024-01-25', '2024-01-25 09:00:00', '2024-01-25 12:00:00', '果树喷药', 'G区果园', 3.0, 7.5, 1, NOW(), NOW(), '完成果园病虫害防治喷药'),
(4, 5, '2024-01-26', '2024-01-26 07:00:00', '2024-01-26 15:00:00', '玉米播种', 'H区1号田', 8.0, 22.0, 1, NOW(), NOW(), '完成玉米播种任务'),
(5, 6, '2024-01-27', '2024-01-27 08:00:00', '2024-01-27 16:00:00', '深耕作业', 'I区2号田', 8.0, 18.0, 1, NOW(), NOW(), '完成深耕作业'),
(7, 7, '2024-01-28', '2024-01-28 07:30:00', '2024-01-28 16:30:00', '设备运输', '设备调配', 9.0, 25.0, 1, NOW(), NOW(), '完成设备春季调配任务'),
(8, 8, '2024-01-29', '2024-01-29 08:30:00', '2024-01-29 14:30:00', '水质检测', '灌溉水源监测点', 6.0, 0, 1, NOW(), NOW(), '完成水源季度监测取样'),
(9, 1, '2024-01-30', '2024-01-30 08:00:00', '2024-01-30 17:00:00', '葡萄园管理', 'J区新葡萄园', 9.0, 0, 1, NOW(), NOW(), '完成新葡萄园支架搭建'),
(1, 2, '2024-01-31', '2024-01-31 07:00:00', '2024-01-31 16:00:00', '大田翻耕', 'K区1号田', 9.0, 48.0, 0, NOW(), NOW(), '设备出现过热情况，需要检查'); 