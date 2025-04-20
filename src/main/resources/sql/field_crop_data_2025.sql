-- 2025年田块作物关系数据
-- 格式：INSERT INTO agri_field_crop (field_id, crop_id, planting_year, vine_age, area_size, position_data, plant_density, total_plants, trellis_type, irrigation_method, fertilizer_type, expected_yield, status, remark) VALUES (...);

-- 东区1号田 - 赤霞珠
INSERT INTO agri_field_crop (field_id, crop_id, planting_year, vine_age, area_size, position_data, plant_density, total_plants, trellis_type, irrigation_method, fertilizer_type, expected_yield, status, remark) 
VALUES (1, 1, 2025, 3, 10.50, '[{"x":116.3250,"y":39.9875},{"x":116.3255,"y":39.9875},{"x":116.3255,"y":39.9880},{"x":116.3250,"y":39.9880}]', 200, 2100, '篱架', '滴灌', '有机肥', 500.00, 1, '东区主要田块，适合赤霞珠生长');

-- 东区2号田 - 霞多丽
INSERT INTO agri_field_crop (field_id, crop_id, planting_year, vine_age, area_size, position_data, plant_density, total_plants, trellis_type, irrigation_method, fertilizer_type, expected_yield, status, remark) 
VALUES (2, 2, 2025, 2, 8.20, '[{"x":116.3270,"y":39.9865},{"x":116.3275,"y":39.9865},{"x":116.3275,"y":39.9870},{"x":116.3270,"y":39.9870}]', 200, 1640, '篱架', '喷灌', '有机肥', 500.00, 1, '东区次要田块，适合霞多丽生长');

-- 南区1号田 - 梅洛
INSERT INTO agri_field_crop (field_id, crop_id, planting_year, vine_age, area_size, position_data, plant_density, total_plants, trellis_type, irrigation_method, fertilizer_type, expected_yield, status, remark) 
VALUES (3, 3, 2025, 4, 12.30, '[{"x":116.3240,"y":39.9845},{"x":116.3245,"y":39.9845},{"x":116.3245,"y":39.9850},{"x":116.3240,"y":39.9850}]', 200, 2460, '篱架', '沟渠灌溉', '有机肥', 500.00, 1, '南区主要田块，适合梅洛生长');

-- 西区1号田 - 西拉
INSERT INTO agri_field_crop (field_id, crop_id, planting_year, vine_age, area_size, position_data, plant_density, total_plants, trellis_type, irrigation_method, fertilizer_type, expected_yield, status, remark) 
VALUES (4, 5, 2025, 3, 9.80, '[{"x":116.3220,"y":39.9865},{"x":116.3225,"y":39.9865},{"x":116.3225,"y":39.9870},{"x":116.3220,"y":39.9870}]', 200, 1960, '篱架', '滴灌', '有机肥', 500.00, 1, '西区主要田块，适合西拉生长');

-- 北区1号田 - 雷司令
INSERT INTO agri_field_crop (field_id, crop_id, planting_year, vine_age, area_size, position_data, plant_density, total_plants, trellis_type, irrigation_method, fertilizer_type, expected_yield, status, remark) 
VALUES (5, 4, 2025, 2, 7.60, '[{"x":116.3240,"y":39.9895},{"x":116.3245,"y":39.9895},{"x":116.3245,"y":39.9900},{"x":116.3240,"y":39.9900}]', 200, 1520, '篱架', '微喷灌', '有机肥', 500.00, 1, '北区主要田块，适合雷司令生长');

-- 西区2号田 - 霞多丽
INSERT INTO agri_field_crop (field_id, crop_id, planting_year, vine_age, area_size, position_data, plant_density, total_plants, trellis_type, irrigation_method, fertilizer_type, expected_yield, status, remark) 
VALUES (28, 2, 2025, 3, 12.30, '[{"x":116.3180,"y":39.9830},{"x":116.3195,"y":39.9830},{"x":116.3195,"y":39.9845},{"x":116.3180,"y":39.9845}]', 200, 2460, '篱架', '喷灌', '有机肥', 500.00, 1, '西区石灰土特性田块，适合霞多丽种植');

-- 西区3号田 - 梅洛
INSERT INTO agri_field_crop (field_id, crop_id, planting_year, vine_age, area_size, position_data, plant_density, total_plants, trellis_type, irrigation_method, fertilizer_type, expected_yield, status, remark) 
VALUES (29, 3, 2025, 2, 8.50, '[{"x":116.3160,"y":39.9840},{"x":116.3170,"y":39.9840},{"x":116.3170,"y":39.9855},{"x":116.3160,"y":39.9855}]', 200, 1700, '篱架', '渗灌', '有机肥', 500.00, 1, '粘土特性田块，适合梅洛种植');

-- 南区2号田 - 赤霞珠
INSERT INTO agri_field_crop (field_id, crop_id, planting_year, vine_age, area_size, position_data, plant_density, total_plants, trellis_type, irrigation_method, fertilizer_type, expected_yield, status, remark) 
VALUES (30, 1, 2025, 4, 18.70, '[{"x":116.3290,"y":39.9780},{"x":116.3320,"y":39.9780},{"x":116.3320,"y":39.9800},{"x":116.3290,"y":39.9800}]', 200, 3740, '篱架', '微喷灌', '有机肥', 500.00, 1, '南区赤霞珠种植基地');

-- 南区3号田 - 西拉
INSERT INTO agri_field_crop (field_id, crop_id, planting_year, vine_age, area_size, position_data, plant_density, total_plants, trellis_type, irrigation_method, fertilizer_type, expected_yield, status, remark) 
VALUES (31, 5, 2025, 3, 10.20, '[{"x":116.3270,"y":39.9750},{"x":116.3290,"y":39.9750},{"x":116.3290,"y":39.9770},{"x":116.3270,"y":39.9770}]', 200, 2040, '篱架', '滴灌', '有机肥', 500.00, 1, '沙质土壤透气性好，适合西拉生长');

-- 北区2号田 - 雷司令
INSERT INTO agri_field_crop (field_id, crop_id, planting_year, vine_age, area_size, position_data, plant_density, total_plants, trellis_type, irrigation_method, fertilizer_type, expected_yield, status, remark) 
VALUES (32, 4, 2025, 3, 14.80, '[{"x":116.3320,"y":39.9950},{"x":116.3350,"y":39.9950},{"x":116.3350,"y":39.9970},{"x":116.3320,"y":39.9970}]', 200, 2960, '篱架', '滴灌', '有机肥', 500.00, 1, '北区雷司令种植基地');

-- 北区3号田 - 雷司令
INSERT INTO agri_field_crop (field_id, crop_id, planting_year, vine_age, area_size, position_data, plant_density, total_plants, trellis_type, irrigation_method, fertilizer_type, expected_yield, status, remark) 
VALUES (33, 4, 2025, 2, 9.30, '[{"x":116.3300,"y":39.9980},{"x":116.3320,"y":39.9980},{"x":116.3320,"y":39.9995},{"x":116.3300,"y":39.9995}]', 200, 1860, '篱架', '渗灌', '有机肥', 500.00, 1, '特殊土壤结构，矿物质丰富，适合雷司令生长');

-- 东区试验田 - 赤霞珠
INSERT INTO agri_field_crop (field_id, crop_id, planting_year, vine_age, area_size, position_data, plant_density, total_plants, trellis_type, irrigation_method, fertilizer_type, expected_yield, status, remark) 
VALUES (34, 1, 2025, 1, 5.20, '[{"x":116.3380,"y":39.9900},{"x":116.3390,"y":39.9900},{"x":116.3390,"y":39.9910},{"x":116.3380,"y":39.9910}]', 200, 1040, '篱架', '智能滴灌', '有机肥', 500.00, 1, '富硒土壤试验种植基地，种植赤霞珠');

-- 东区有机田 - 霞多丽
INSERT INTO agri_field_crop (field_id, crop_id, planting_year, vine_age, area_size, position_data, plant_density, total_plants, trellis_type, irrigation_method, fertilizer_type, expected_yield, status, remark) 
VALUES (35, 2, 2025, 2, 7.80, '[{"x":116.3380,"y":39.9880},{"x":116.3395,"y":39.9880},{"x":116.3395,"y":39.9895},{"x":116.3380,"y":39.9895}]', 200, 1560, '篱架', '微喷灌', '有机肥', 500.00, 1, '有机葡萄种植基地，无化肥农药使用，种植霞多丽');

-- 研发试验田 - 混合种植
INSERT INTO agri_field_crop (field_id, crop_id, planting_year, vine_age, area_size, position_data, plant_density, total_plants, trellis_type, irrigation_method, fertilizer_type, expected_yield, status, remark) 
VALUES (36, 1, 2025, 1, 1.20, '[{"x":116.3350,"y":39.9850},{"x":116.3355,"y":39.9850},{"x":116.3355,"y":39.9855},{"x":116.3350,"y":39.9855}]', 200, 240, '篱架', '智能灌溉', '有机肥', 500.00, 1, '葡萄新品种试验区，种植赤霞珠');

INSERT INTO agri_field_crop (field_id, crop_id, planting_year, vine_age, area_size, position_data, plant_density, total_plants, trellis_type, irrigation_method, fertilizer_type, expected_yield, status, remark) 
VALUES (36, 2, 2025, 1, 1.15, '[{"x":116.3355,"y":39.9850},{"x":116.3360,"y":39.9850},{"x":116.3360,"y":39.9855},{"x":116.3355,"y":39.9855}]', 200, 230, '篱架', '智能灌溉', '有机肥', 500.00, 1, '葡萄新品种试验区，种植霞多丽');

INSERT INTO agri_field_crop (field_id, crop_id, planting_year, vine_age, area_size, position_data, plant_density, total_plants, trellis_type, irrigation_method, fertilizer_type, expected_yield, status, remark) 
VALUES (36, 3, 2025, 1, 1.15, '[{"x":116.3350,"y":39.9855},{"x":116.3355,"y":39.9855},{"x":116.3355,"y":39.9860},{"x":116.3350,"y":39.9860}]', 200, 230, '篱架', '智能灌溉', '有机肥', 500.00, 1, '葡萄新品种试验区，种植梅洛'); 