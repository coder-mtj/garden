-- 田块表
CREATE TABLE IF NOT EXISTS `agri_field` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `field_code` varchar(20) NOT NULL COMMENT '田块编号，唯一标识',
  `field_name` varchar(50) NOT NULL COMMENT '田块名称',
  `area` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '面积（亩）',
  `soil_type` varchar(20) DEFAULT NULL COMMENT '土壤类型',
  `irrigation_type` varchar(20) DEFAULT NULL COMMENT '灌溉方式',
  `location_x` double DEFAULT NULL COMMENT '地理坐标X（经度）',
  `location_y` double DEFAULT NULL COMMENT '地理坐标Y（纬度）',
  `position_data` varchar(1000) DEFAULT NULL COMMENT '田块多边形坐标数据(JSON格式)',
  `status` tinyint NOT NULL DEFAULT '1' COMMENT '状态：0-闲置，1-使用中，2-休耕',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_field_code` (`field_code`),
  UNIQUE KEY `uk_field_name` (`field_name`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='农田地块信息表';

-- 作物表
CREATE TABLE IF NOT EXISTS `agri_crop` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `crop_code` varchar(20) NOT NULL COMMENT '作物编号',
  `crop_name` varchar(50) NOT NULL COMMENT '作物名称',
  `crop_type` varchar(20) NOT NULL COMMENT '作物类型（如：葡萄、果树等）',
  `variety` varchar(50) NOT NULL COMMENT '葡萄品种（如：赤霞珠、霞多丽等）',
  `growth_cycle` int DEFAULT NULL COMMENT '生长周期（天）',
  `suitable_soil` varchar(100) DEFAULT NULL COMMENT '适宜土壤',
  `suitable_temp` varchar(50) DEFAULT NULL COMMENT '适宜温度',
  `plant_distance` decimal(5,2) DEFAULT NULL COMMENT '建议株距（米）',
  `row_distance` decimal(5,2) DEFAULT NULL COMMENT '建议行距（米）',
  `water_demand` varchar(20) DEFAULT NULL COMMENT '水分需求（高/中/低）',
  `fertilizer_demand` varchar(20) DEFAULT NULL COMMENT '肥料需求（高/中/低）',
  `pruning_method` varchar(100) DEFAULT NULL COMMENT '推荐修剪方式',
  `disease_resistance` varchar(20) DEFAULT NULL COMMENT '抗病性（强/中/弱）',
  `pest_resistance` varchar(20) DEFAULT NULL COMMENT '抗虫性（强/中/弱）',
  `color` varchar(20) DEFAULT NULL COMMENT '显示颜色（可视化用）',
  `icon` varchar(255) DEFAULT NULL COMMENT '图标URL',
  `status` tinyint NOT NULL DEFAULT '1' COMMENT '状态：0-停用，1-启用',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_crop_code` (`crop_code`),
  UNIQUE KEY `uk_crop_name` (`crop_name`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='葡萄品种信息表';

-- 田块-作物关系表
CREATE TABLE IF NOT EXISTS `agri_field_crop` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `field_id` bigint NOT NULL COMMENT '田块ID',
  `crop_id` bigint NOT NULL COMMENT '作物ID',
  `planting_year` int NOT NULL COMMENT '种植年份',
  `vine_age` int NOT NULL COMMENT '葡萄树龄（年）',
  `area_size` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '种植面积（亩）',
  `position_data` varchar(1000) DEFAULT NULL COMMENT '种植区域多边形坐标数据(JSON格式)',
  `plant_density` int DEFAULT NULL COMMENT '种植密度（株/亩）',
  `total_plants` int DEFAULT NULL COMMENT '植株总数',
  `trellis_type` varchar(20) DEFAULT NULL COMMENT '架式类型（如：篱架、棚架等）',
  `irrigation_method` varchar(20) DEFAULT NULL COMMENT '实际灌溉方式',
  `fertilizer_type` varchar(50) DEFAULT NULL COMMENT '实际肥料类型',
  `expected_yield` decimal(10,2) DEFAULT NULL COMMENT '预计产量（公斤/亩）',
  `actual_yield` decimal(10,2) DEFAULT NULL COMMENT '实际产量（公斤/亩）',
  `status` tinyint NOT NULL DEFAULT '1' COMMENT '状态：0-移除，1-正常生长，2-病害，3-虫害',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`id`),
  KEY `idx_field_id` (`field_id`),
  KEY `idx_crop_id` (`crop_id`),
  CONSTRAINT `fk_field_crop_crop_id` FOREIGN KEY (`crop_id`) REFERENCES `agri_crop` (`id`),
  CONSTRAINT `fk_field_crop_field_id` FOREIGN KEY (`field_id`) REFERENCES `agri_field` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='田块-作物关系表';

-- 插入测试数据：田块
INSERT INTO `agri_field` 
  (`field_code`, `field_name`, `area`, `soil_type`, `irrigation_type`, `location_x`, `location_y`, `position_data`, `status`, `remark`) 
VALUES
  ('EAST01', '东区1号田', 10.50, '沙壤土', '滴灌', 116.3252, 39.9876, '[{"x":116.3250,"y":39.9875},{"x":116.3255,"y":39.9875},{"x":116.3255,"y":39.9880},{"x":116.3250,"y":39.9880}]', 1, '东区主要田块，土壤肥沃'),
  ('EAST02', '东区2号田', 8.20, '壤土', '喷灌', 116.3272, 39.9866, '[{"x":116.3270,"y":39.9865},{"x":116.3275,"y":39.9865},{"x":116.3275,"y":39.9870},{"x":116.3270,"y":39.9870}]', 1, '东区次要田块，阳光充足'),
  ('SOUTH01', '南区1号田', 12.30, '黏土', '沟渠灌溉', 116.3242, 39.9846, '[{"x":116.3240,"y":39.9845},{"x":116.3245,"y":39.9845},{"x":116.3245,"y":39.9850},{"x":116.3240,"y":39.9850}]', 1, '南区主要田块，排水良好'),
  ('WEST01', '西区1号田', 9.80, '砂质土', '滴灌', 116.3222, 39.9866, '[{"x":116.3220,"y":39.9865},{"x":116.3225,"y":39.9865},{"x":116.3225,"y":39.9870},{"x":116.3220,"y":39.9870}]', 1, '西区主要田块，光照充足'),
  ('NORTH01', '北区1号田', 7.60, '石灰质土', '微喷灌', 116.3242, 39.9896, '[{"x":116.3240,"y":39.9895},{"x":116.3245,"y":39.9895},{"x":116.3245,"y":39.9900},{"x":116.3240,"y":39.9900}]', 1, '北区主要田块，地势较高');

-- 插入测试数据：葡萄品种
INSERT INTO `agri_crop`
  (`crop_code`, `crop_name`, `crop_type`, `variety`, `growth_cycle`, `suitable_soil`, `suitable_temp`, `plant_distance`, `row_distance`, `water_demand`, `fertilizer_demand`, `pruning_method`, `disease_resistance`, `pest_resistance`, `color`, `icon`, `status`, `remark`)
VALUES
  ('CAB-SAU', '赤霞珠', '葡萄', '赤霞珠', 240, '砂砾质壤土，石灰质土壤', '15-25°C', 1.5, 3.0, '中', '中', '短梢修剪', '中', '中', '#8B0000', '/images/crops/cabernet-sauvignon.png', 1, '法国波尔多地区著名红葡萄品种，适合酿造陈年红葡萄酒'),
  ('CHARD', '霞多丽', '葡萄', '霞多丽', 230, '石灰质土壤', '12-22°C', 1.4, 2.8, '中', '中', '短梢修剪', '中', '低', '#F0E68C', '/images/crops/chardonnay.png', 1, '法国勃艮第地区著名白葡萄品种，适合酿造多种风格的白葡萄酒'),
  ('MER-LOT', '梅洛', '葡萄', '梅洛', 235, '粘土壤土，石灰质土壤', '14-24°C', 1.5, 2.9, '中', '中', '短梢修剪', '中', '中', '#800000', '/images/crops/merlot.png', 1, '法国波尔多地区红葡萄品种，果实成熟较早，单宁柔和'),
  ('RIES-LING', '雷司令', '葡萄', '雷司令', 220, '石板土壤，页岩土壤', '10-20°C', 1.3, 2.7, '低', '低', '短梢修剪', '中', '中', '#FFFF00', '/images/crops/riesling.png', 1, '德国著名白葡萄品种，具有高酸度和香气复杂特点'),
  ('SYRAH', '西拉', '葡萄', '西拉', 245, '砾石土壤，花岗岩土壤', '16-26°C', 1.6, 3.1, '低', '中', '短梢修剪', '高', '高', '#2E0854', '/images/crops/syrah.png', 1, '法国罗讷河谷著名红葡萄品种，适合温暖气候，单宁丰富');

-- 插入测试数据：田块-作物关系
INSERT INTO `agri_field_crop`
  (`field_id`, `crop_id`, `planting_year`, `vine_age`, `area_size`, `position_data`, `plant_density`, `total_plants`, `trellis_type`, `irrigation_method`, `fertilizer_type`, `expected_yield`, `actual_yield`, `status`, `create_time`, `update_time`, `remark`)
VALUES
  (1, 1, 2020, 4, 6.3, '[{"x":116.3250,"y":39.9875},{"x":116.3253,"y":39.9875},{"x":116.3253,"y":39.9878},{"x":116.3250,"y":39.9878}]', 350, 2200, '篱架', '滴灌', '有机肥', 1000.00, 950.00, 1, NOW(), NOW(), '东区1号田赤霞珠区域，生长状况良好'),
  (1, 3, 2020, 4, 4.2, '[{"x":116.3253,"y":39.9875},{"x":116.3255,"y":39.9875},{"x":116.3255,"y":39.9878},{"x":116.3253,"y":39.9878}]', 350, 1470, '篱架', '滴灌', '有机肥', 950.00, 920.00, 1, NOW(), NOW(), '东区1号田梅洛区域，生长状况良好'),
  (2, 2, 2021, 3, 5.1, '[{"x":116.3270,"y":39.9865},{"x":116.3273,"y":39.9865},{"x":116.3273,"y":39.9868},{"x":116.3270,"y":39.9868}]', 330, 1680, '篱架', '喷灌', '复合肥', 900.00, 850.00, 1, NOW(), NOW(), '东区2号田霞多丽区域，生长状况良好'),
  (2, 4, 2021, 3, 3.1, '[{"x":116.3273,"y":39.9865},{"x":116.3275,"y":39.9865},{"x":116.3275,"y":39.9868},{"x":116.3273,"y":39.9868}]', 330, 1020, '篱架', '喷灌', '复合肥', 880.00, 820.00, 1, NOW(), NOW(), '东区2号田雷司令区域，生长状况良好'),
  (3, 5, 2019, 5, 7.4, '[{"x":116.3240,"y":39.9845},{"x":116.3243,"y":39.9845},{"x":116.3243,"y":39.9848},{"x":116.3240,"y":39.9848}]', 360, 2660, '篱架', '沟渠灌溉', '有机肥', 1100.00, 1050.00, 1, NOW(), NOW(), '南区1号田西拉区域，生长状况良好'),
  (3, 1, 2019, 5, 4.9, '[{"x":116.3243,"y":39.9845},{"x":116.3245,"y":39.9845},{"x":116.3245,"y":39.9848},{"x":116.3243,"y":39.9848}]', 360, 1760, '篱架', '沟渠灌溉', '有机肥', 1050.00, 980.00, 1, NOW(), NOW(), '南区1号田赤霞珠区域，生长状况良好'),
  (4, 3, 2022, 2, 9.8, '[{"x":116.3220,"y":39.9865},{"x":116.3225,"y":39.9865},{"x":116.3225,"y":39.9870},{"x":116.3220,"y":39.9870}]', 340, 3330, '篱架', '滴灌', '有机肥', 850.00, 800.00, 1, NOW(), NOW(), '西区1号田梅洛区域，幼树生长状况良好'),
  (5, 2, 2020, 4, 7.6, '[{"x":116.3240,"y":39.9895},{"x":116.3245,"y":39.9895},{"x":116.3245,"y":39.9900},{"x":116.3240,"y":39.9900}]', 320, 2430, '篱架', '微喷灌', '有机肥', 920.00, 880.00, 1, NOW(), NOW(), '北区1号田霞多丽区域，生长状况良好'),
  (6, 1, 2022, 2, 10.5, '[{"x":116.3180,"y":39.9860},{"x":116.3187,"y":39.9860},{"x":116.3187,"y":39.9875},{"x":116.3180,"y":39.9875}]', 67, 700, '篱架', '滴灌', '有机肥', 500.00, 0.00, 1, NOW(), NOW(), '西区1号田赤霞珠种植区'),
  (6, 3, 2022, 2, 5.3, '[{"x":116.3188,"y":39.9860},{"x":116.3195,"y":39.9860},{"x":116.3195,"y":39.9875},{"x":116.3188,"y":39.9875}]', 66, 350, '篱架', '滴灌', '有机肥', 462.26, 0.00, 1, NOW(), NOW(), '西区1号田梅洛种植区'),
  (7, 2, 2021, 3, 12.3, '[{"x":116.3180,"y":39.9830},{"x":116.3195,"y":39.9830},{"x":116.3195,"y":39.9845},{"x":116.3180,"y":39.9845}]', 67, 820, '篱架', '喷灌', '有机肥+微量元素', 400.00, 380.00, 1, NOW(), NOW(), '西区2号田霞多丽种植区，生长良好'),
  (9, 1, 2023, 1, 12.8, '[{"x":116.3250,"y":39.9780},{"x":116.3265,"y":39.9780},{"x":116.3265,"y":39.9800},{"x":116.3250,"y":39.9800}]', 66, 850, '棚架', '滴灌', '有机肥+腐殖酸', 498.05, 0.00, 1, NOW(), NOW(), '南区1号田赤霞珠种植区，新种植'),
  (9, 5, 2023, 1, 7.7, '[{"x":116.3266,"y":39.9780},{"x":116.3280,"y":39.9780},{"x":116.3280,"y":39.9800},{"x":116.3266,"y":39.9800}]', 62, 480, '棚架', '滴灌', '有机肥+腐殖酸', 467.53, 0.00, 1, NOW(), NOW(), '南区1号田西拉种植区，新种植'),
  (10, 1, 2020, 4, 18.7, '[{"x":116.3290,"y":39.9780},{"x":116.3320,"y":39.9780},{"x":116.3320,"y":39.9800},{"x":116.3290,"y":39.9800}]', 67, 1250, '篱架', '微喷灌', '有机肥+硼肥', 501.34, 480.00, 1, NOW(), NOW(), '南区2号田赤霞珠种植区，收获表现优异'),
  (12, 3, 2021, 3, 9.5, '[{"x":116.3280,"y":39.9950},{"x":116.3295,"y":39.9950},{"x":116.3295,"y":39.9970},{"x":116.3280,"y":39.9970}]', 66, 630, '立柱', '喷灌', '缓释肥', 497.37, 470.00, 1, NOW(), NOW(), '北区1号田梅洛种植区'),
  (12, 5, 2021, 3, 8.1, '[{"x":116.3296,"y":39.9950},{"x":116.3310,"y":39.9950},{"x":116.3310,"y":39.9970},{"x":116.3296,"y":39.9970}]', 62, 500, '立柱', '喷灌', '缓释肥', 462.96, 440.00, 1, NOW(), NOW(), '北区1号田西拉种植区'),
  (13, 4, 2022, 2, 14.8, '[{"x":116.3320,"y":39.9950},{"x":116.3350,"y":39.9950},{"x":116.3350,"y":39.9970},{"x":116.3320,"y":39.9970}]', 68, 1000, '篱架', '滴灌', '有机肥+钾肥', 337.84, 0.00, 1, NOW(), NOW(), '北区2号田雷司令种植区'),
  (16, 2, 2023, 1, 4.0, '[{"x":116.3380,"y":39.9880},{"x":116.3387,"y":39.9880},{"x":116.3387,"y":39.9895},{"x":116.3380,"y":39.9895}]', 70, 280, '篱架', '微喷灌', '纯有机肥', 350.00, 0.00, 1, NOW(), NOW(), '东区有机田霞多丽种植区，有机认证'),
  (16, 4, 2023, 1, 3.8, '[{"x":116.3388,"y":39.9880},{"x":116.3395,"y":39.9880},{"x":116.3395,"y":39.9895},{"x":116.3388,"y":39.9895}]', 68, 260, '篱架', '微喷灌', '纯有机肥', 342.11, 0.00, 1, NOW(), NOW(), '东区有机田雷司令种植区，有机认证'),
  (17, 1, 2023, 1, 0.7, '[{"x":116.3350,"y":39.9850},{"x":116.3352,"y":39.9850},{"x":116.3352,"y":39.9854},{"x":116.3350,"y":39.9854}]', 64, 45, '棚架', '智能灌溉', '配方肥', 485.71, 0.00, 1, NOW(), NOW(), '研发田赤霞珠试验区'),
  (17, 2, 2023, 1, 0.7, '[{"x":116.3353,"y":39.9850},{"x":116.3355,"y":39.9850},{"x":116.3355,"y":39.9854},{"x":116.3353,"y":39.9854}]', 71, 50, '棚架', '智能灌溉', '配方肥', 357.14, 0.00, 1, NOW(), NOW(), '研发田霞多丽试验区'),
  (17, 3, 2023, 1, 0.7, '[{"x":116.3356,"y":39.9850},{"x":116.3358,"y":39.9850},{"x":116.3358,"y":39.9854},{"x":116.3356,"y":39.9854}]', 64, 45, '棚架', '智能灌溉', '配方肥', 485.71, 0.00, 1, NOW(), NOW(), '研发田梅洛试验区'),
  (17, 4, 2023, 1, 0.7, '[{"x":116.3350,"y":39.9855},{"x":116.3352,"y":39.9855},{"x":116.3352,"y":39.9860},{"x":116.3350,"y":39.9860}]', 71, 50, '棚架', '智能灌溉', '配方肥', 357.14, 0.00, 1, NOW(), NOW(), '研发田雷司令试验区'),
  (17, 5, 2023, 1, 0.7, '[{"x":116.3353,"y":39.9855},{"x":116.3355,"y":39.9855},{"x":116.3355,"y":39.9860},{"x":116.3353,"y":39.9860}]', 64, 45, '棚架', '智能灌溉', '配方肥', 485.71, 0.00, 1, NOW(), NOW(), '研发田西拉试验区');

-- 先插入新的田块数据
INSERT INTO `agri_field` 
  (`field_code`, `field_name`, `area`, `soil_type`, `irrigation_type`, `location_x`, `location_y`, `position_data`, `status`, `remark`) 
VALUES
  ('FIELD-W01', '西区1号田', 15.8, '砂质壤土', '滴灌', 116.3180, 39.9860, '[{"x":116.3180,"y":39.9860},{"x":116.3195,"y":39.9860},{"x":116.3195,"y":39.9875},{"x":116.3180,"y":39.9875}]', 1, '西区优质葡萄种植基地，土壤肥沃'),
  ('FIELD-W02', '西区2号田', 12.3, '石灰质土壤', '喷灌', 116.3180, 39.9830, '[{"x":116.3180,"y":39.9830},{"x":116.3195,"y":39.9830},{"x":116.3195,"y":39.9845},{"x":116.3180,"y":39.9845}]', 1, '西区石灰土特性田块，适合霞多丽种植'),
  ('FIELD-W03', '西区3号田', 8.5, '粘土', '渗灌', 116.3160, 39.9840, '[{"x":116.3160,"y":39.9840},{"x":116.3170,"y":39.9840},{"x":116.3170,"y":39.9855},{"x":116.3160,"y":39.9855}]', 1, '粘土特性田块，保水性强'),
  ('FIELD-S01', '南区1号田', 20.5, '壤土', '滴灌', 116.3250, 39.9780, '[{"x":116.3250,"y":39.9780},{"x":116.3280,"y":39.9780},{"x":116.3280,"y":39.9800},{"x":116.3250,"y":39.9800}]', 1, '南区大型葡萄种植基地，阳光充足'),
  ('FIELD-S02', '南区2号田', 18.7, '砂砾质壤土', '微喷灌', 116.3290, 39.9780, '[{"x":116.3290,"y":39.9780},{"x":116.3320,"y":39.9780},{"x":116.3320,"y":39.9800},{"x":116.3290,"y":39.9800}]', 1, '南区赤霞珠种植基地');

-- 查询获取新插入的田块ID (请在插入田块后执行这部分查询来获取实际ID)
-- SELECT id, field_name FROM agri_field WHERE field_code in ('FIELD-W01', 'FIELD-W02', 'FIELD-S01', 'FIELD-S02');

-- 插入田块作物关系数据 (下面的field_id值需要根据实际查询到的田块ID修改)
-- 以下假设查询后获得的ID分别是 
-- FIELD-W01: 新ID为6
-- FIELD-W02: 新ID为7
-- FIELD-S01: 新ID为8
-- FIELD-S02: 新ID为9

-- 请根据实际查询到的ID替换以下SQL中的field_id值
INSERT INTO `agri_field_crop`
  (`field_id`, `crop_id`, `planting_year`, `vine_age`, `area_size`, `position_data`, `plant_density`, `total_plants`, `trellis_type`, `irrigation_method`, `fertilizer_type`, `expected_yield`, `actual_yield`, `status`, `remark`)
VALUES
  -- 西区1号田(FIELD-W01)种植赤霞珠和梅洛，假设ID为6
  (6, 1, 2022, 2, 10.5, '[{"x":116.3180,"y":39.9860},{"x":116.3187,"y":39.9860},{"x":116.3187,"y":39.9875},{"x":116.3180,"y":39.9875}]', 67, 700, '篱架', '滴灌', '有机肥', 500.00, 0.00, 1, '西区1号田赤霞珠种植区'),
  (6, 3, 2022, 2, 5.3, '[{"x":116.3188,"y":39.9860},{"x":116.3195,"y":39.9860},{"x":116.3195,"y":39.9875},{"x":116.3188,"y":39.9875}]', 66, 350, '篱架', '滴灌', '有机肥', 462.26, 0.00, 1, '西区1号田梅洛种植区'),
  
  -- 西区2号田(FIELD-W02)种植霞多丽，假设ID为7
  (7, 2, 2021, 3, 12.3, '[{"x":116.3180,"y":39.9830},{"x":116.3195,"y":39.9830},{"x":116.3195,"y":39.9845},{"x":116.3180,"y":39.9845}]', 67, 820, '篱架', '喷灌', '有机肥+微量元素', 400.00, 380.00, 1, '西区2号田霞多丽种植区，生长良好'),
  
  -- 南区1号田(FIELD-S01)种植赤霞珠和西拉，假设ID为8
  (8, 1, 2023, 1, 12.8, '[{"x":116.3250,"y":39.9780},{"x":116.3265,"y":39.9780},{"x":116.3265,"y":39.9800},{"x":116.3250,"y":39.9800}]', 66, 850, '棚架', '滴灌', '有机肥+腐殖酸', 498.05, 0.00, 1, '南区1号田赤霞珠种植区，新种植'),
  (8, 5, 2023, 1, 7.7, '[{"x":116.3266,"y":39.9780},{"x":116.3280,"y":39.9780},{"x":116.3280,"y":39.9800},{"x":116.3266,"y":39.9800}]', 62, 480, '棚架', '滴灌', '有机肥+腐殖酸', 467.53, 0.00, 1, '南区1号田西拉种植区，新种植'),
  
  -- 南区2号田(FIELD-S02)种植赤霞珠，假设ID为9
  (9, 1, 2020, 4, 18.7, '[{"x":116.3290,"y":39.9780},{"x":116.3320,"y":39.9780},{"x":116.3320,"y":39.9800},{"x":116.3290,"y":39.9800}]', 67, 1250, '篱架', '微喷灌', '有机肥+硼肥', 501.34, 480.00, 1, '南区2号田赤霞珠种植区，收获表现优异'); 