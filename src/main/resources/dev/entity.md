# 实体类说明文档

## 用户实体 (User)

### 基本信息
- 实体名称：用户（User）
- 表名：sys_user
- 描述：系统用户信息表，存储所有系统用户的基本信息，用于用户认证和授权

### 字段说明

| 字段名 | 类型 | 长度 | 是否为空 | 默认值 | 说明 |
|--------|------|------|----------|--------|------|
| id | Long | - | 否 | - | 主键ID |
| username | String | 50 | 否 | - | 用户名，用于登录 |
| password | String | 100 | 否 | - | 密码，存储加密后的密码 |
| real_name | String | 50 | 是 | - | 真实姓名 |
| phone | String | 20 | 是 | - | 手机号码 |
| email | String | 100 | 是 | - | 电子邮箱 |
| status | Integer | - | 否 | 1 | 状态：0-禁用，1-启用 |
| create_time | Date | - | 否 | CURRENT_TIMESTAMP | 创建时间 |
| update_time | Date | - | 否 | CURRENT_TIMESTAMP | 更新时间 |
| last_login_time | Date | - | 是 | - | 最后登录时间 |
| avatar | String | 255 | 是 | - | 头像URL |
| create_by | String | 50 | 是 | - | 创建人 |
| update_by | String | 50 | 是 | - | 更新人 |
| remark | String | 500 | 是 | - | 备注信息 |

### 业务规则
1. 用户名必须唯一
2. 密码存储前必须进行加密处理
3. 手机号和邮箱必须符合格式要求
4. 状态默认为启用（1）
5. 创建时间和更新时间自动维护
6. 头像URL可以为空，使用默认头像

### 关联关系
- 一个用户对应一个职工（一对一）
- 一个用户可以有多个角色（多对多）
- 一个用户可以创建多个订单（一对多）
- 一个用户可以有多条操作日志（一对多）

### 示例数据
```json
{
    "id": 1,
    "username": "admin",
    "password": "加密后的密码",
    "real_name": "管理员",
    "phone": "13800138000",
    "email": "admin@example.com",
    "status": 1,
    "create_time": "2024-01-01 00:00:00",
    "update_time": "2024-01-01 00:00:00",
    "last_login_time": "2024-01-01 00:00:00",
    "avatar": "/avatar/default.png"
}
```

## 职工实体 (Employee)

### 基本信息
- 实体名称：职工（Employee）
- 表名：sys_employee
- 描述：职工信息表，存储公司职工的基本信息和工作相关信息，与用户表是一对一关系

### 字段说明

| 字段名 | 类型 | 长度 | 是否为空 | 默认值 | 说明 |
|--------|------|------|----------|--------|------|
| id | Long | - | 否 | - | 主键ID |
| user_id | Long | - | 否 | - | 关联的用户ID |
| employee_no | String | 20 | 否 | - | 工号，唯一标识 |
| name | String | 50 | 否 | - | 职工姓名 |
| gender | Integer | - | 否 | 1 | 性别：0-女，1-男 |
| birth_date | Date | - | 是 | - | 出生日期 |
| id_card | String | 18 | 否 | - | 身份证号 |
| department_id | Long | - | 否 | - | 所属部门ID |
| position | String | 50 | 否 | - | 职位 |
| entry_date | Date | - | 否 | - | 入职日期 |
| salary | BigDecimal | - | 否 | 0 | 基本工资 |
| status | Integer | - | 否 | 1 | 状态：0-离职，1-在职 |
| address | String | 200 | 是 | - | 家庭住址 |
| emergency_contact | String | 50 | 是 | - | 紧急联系人 |
| emergency_phone | String | 20 | 是 | - | 紧急联系电话 |
| create_time | Date | - | 否 | CURRENT_TIMESTAMP | 创建时间 |
| update_time | Date | - | 否 | CURRENT_TIMESTAMP | 更新时间 |

### 业务规则
1. 工号必须唯一
2. 身份证号必须符合格式要求且唯一
3. 用户ID必须唯一且存在于用户表中
4. 入职日期不能晚于当前日期
5. 基本工资不能为负数
6. 状态默认为在职（1）
7. 创建时间和更新时间自动维护
8. 部门ID必须存在于部门表中

### 关联关系
- 一个职工对应一个用户（一对一）
- 一个职工属于一个部门（多对一）
- 一个职工可以有多条考勤记录（一对多）
- 一个职工可以有多条薪资记录（一对多）
- 一个职工可以有多条培训记录（一对多）

### 示例数据
```json
{
    "id": 1,
    "user_id": 1,
    "employee_no": "EMP2024001",
    "name": "张三",
    "gender": 1,
    "birth_date": "1990-01-01",
    "id_card": "110101199001011234",
    "department_id": 1,
    "position": "Java开发工程师",
    "entry_date": "2024-01-01",
    "salary": 15000.00,
    "status": 1,
    "address": "北京市朝阳区xxx街道",
    "emergency_contact": "李四",
    "emergency_phone": "13900139000",
    "create_time": "2024-01-01 00:00:00",
    "update_time": "2024-01-01 00:00:00"
}
```

## 部门实体 (Department)

### 基本信息
- 实体名称：部门（Department）
- 表名：sys_department
- 描述：部门信息表，存储公司组织架构中的部门信息

### 字段说明

| 字段名 | 类型 | 长度 | 是否为空 | 默认值 | 说明 |
|--------|------|------|----------|--------|------|
| id | Long | - | 否 | - | 主键ID |
| dept_code | String | 20 | 否 | - | 部门编码，唯一标识 |
| dept_name | String | 50 | 否 | - | 部门名称 |
| leader_id | Long | - | 是 | - | 部门负责人ID |
| order_num | Integer | - | 否 | 0 | 显示顺序 |
| phone | String | 20 | 是 | - | 联系电话 |
| email | String | 100 | 是 | - | 部门邮箱 |
| status | Integer | - | 否 | 1 | 状态：0-停用，1-启用 |
| create_time | Date | - | 否 | CURRENT_TIMESTAMP | 创建时间 |
| update_time | Date | - | 否 | CURRENT_TIMESTAMP | 更新时间 |
| remark | String | 500 | 是 | - | 备注信息 |

### 业务规则
1. 部门编码必须唯一
2. 部门名称必须唯一
3. 部门负责人ID必须存在于职工表中
4. 显示顺序用于部门的排序
5. 状态默认为启用（1）
6. 创建时间和更新时间自动维护

### 关联关系
- 一个部门有一个负责人（多对一）
- 一个部门可以有多个职工（一对多）

### 示例数据
```json
{
    "id": 1,
    "dept_code": "DEPT001",
    "dept_name": "研发部",
    "leader_id": 1,
    "order_num": 1,
    "phone": "010-12345678",
    "email": "rd@example.com",
    "status": 1,
    "create_time": "2024-01-01 00:00:00",
    "update_time": "2024-01-01 00:00:00",
    "remark": "负责公司产品研发工作"
}
```

## 农业工具实体 (AgriculturalTool)

### 基本信息
- 实体名称：农业工具（AgriculturalTool）
- 表名：agri_tool
- 描述：农业工具信息表，存储农业生产中使用的各类工具信息

### 字段说明

| 字段名 | 类型 | 长度 | 是否为空 | 默认值 | 说明 |
|--------|------|------|----------|--------|------|
| id | Long | - | 否 | - | 主键ID |
| tool_code | String | 20 | 否 | - | 工具编号，唯一标识 |
| tool_name | String | 50 | 否 | - | 工具名称 |
| tool_type | String | 20 | 否 | - | 工具类型（如：拖拉机、播种机、收割机等） |
| brand | String | 50 | 是 | - | 品牌 |
| model | String | 50 | 是 | - | 型号 |
| purchase_date | Date | - | 否 | - | 购买日期 |
| price | BigDecimal | - | 否 | 0 | 购买价格 |
| status | Integer | - | 否 | 1 | 状态：0-报废，1-正常，2-维修中 |
| location | String | 100 | 是 | - | 存放位置 |
| maintenance_cycle | Integer | - | 是 | - | 维护周期（天） |
| last_maintenance_date | Date | - | 是 | - | 上次维护日期 |
| next_maintenance_date | Date | - | 是 | - | 下次维护日期 |
| create_time | Date | - | 否 | CURRENT_TIMESTAMP | 创建时间 |
| update_time | Date | - | 否 | CURRENT_TIMESTAMP | 更新时间 |
| remark | String | 500 | 是 | - | 备注信息 |

### 业务规则
1. 工具编号必须唯一
2. 工具名称在同一类型下必须唯一
3. 购买日期不能晚于当前日期
4. 价格不能为负数
5. 状态默认为正常（1）
6. 创建时间和更新时间自动维护
7. 维护周期必须大于0（如果填写）

### 关联关系
- 一个工具可以有多条使用记录（一对多）
- 一个工具可以有多条维护记录（一对多）

### 示例数据
```json
{
    "id": 1,
    "tool_code": "TOOL2024001",
    "tool_name": "大型拖拉机",
    "tool_type": "拖拉机",
    "brand": "东方红",
    "model": "LX2004",
    "purchase_date": "2024-01-01",
    "price": 250000.00,
    "status": 1,
    "location": "农机库房A区",
    "maintenance_cycle": 90,
    "last_maintenance_date": "2024-01-01",
    "next_maintenance_date": "2024-04-01",
    "create_time": "2024-01-01 00:00:00",
    "update_time": "2024-01-01 00:00:00",
    "remark": "主要用于大田作业"
}
```

## 工具使用记录实体 (ToolUsageRecord)

### 基本信息
- 实体名称：工具使用记录（ToolUsageRecord）
- 表名：agri_tool_usage
- 描述：记录农业工具的使用情况

### 字段说明

| 字段名 | 类型 | 长度 | 是否为空 | 默认值 | 说明 |
|--------|------|------|----------|--------|------|
| id | Long | - | 否 | - | 主键ID |
| tool_id | Long | - | 否 | - | 工具ID |
| employee_id | Long | - | 否 | - | 使用人ID |
| usage_date | Date | - | 否 | - | 使用日期 |
| start_time | Date | - | 否 | - | 开始时间 |
| end_time | Date | - | 是 | - | 结束时间 |
| usage_purpose | String | 200 | 否 | - | 使用目的 |
| usage_area | String | 100 | 是 | - | 使用区域 |
| usage_hours | BigDecimal | - | 是 | 0 | 使用时长（小时） |
| fuel_consumption | BigDecimal | - | 是 | 0 | 燃油消耗（升） |
| status | Integer | - | 否 | 1 | 状态：0-异常，1-正常 |
| create_time | Date | - | 否 | CURRENT_TIMESTAMP | 创建时间 |
| update_time | Date | - | 否 | CURRENT_TIMESTAMP | 更新时间 |
| remark | String | 500 | 是 | - | 备注信息 |

### 业务规则
1. 工具ID必须存在于工具表中
2. 使用人ID必须存在于职工表中
3. 使用日期不能晚于当前日期
4. 开始时间不能晚于结束时间
5. 使用时长不能为负数
6. 燃油消耗不能为负数
7. 状态默认为正常（1）
8. 创建时间和更新时间自动维护

### 关联关系
- 一条记录属于一个工具（多对一）
- 一条记录属于一个职工（多对一）

### 示例数据
```json
{
    "id": 1,
    "tool_id": 1,
    "employee_id": 1,
    "usage_date": "2024-01-01",
    "start_time": "2024-01-01 08:00:00",
    "end_time": "2024-01-01 17:00:00",
    "usage_purpose": "大田翻耕",
    "usage_area": "A区1号田",
    "usage_hours": 9.0,
    "fuel_consumption": 45.5,
    "status": 1,
    "create_time": "2024-01-01 00:00:00",
    "update_time": "2024-01-01 00:00:00",
    "remark": "作业正常，无异常情况"
}
```

## 田块实体 (Field)

### 基本信息
- 实体名称：田块（Field）
- 表名：agri_field
- 描述：农田地块信息表，存储农田的基本信息和空间数据

### 字段说明

| 字段名 | 类型 | 长度 | 是否为空 | 默认值 | 说明 |
|--------|------|------|----------|--------|------|
| id | Long | - | 否 | - | 主键ID |
| field_code | String | 20 | 否 | - | 田块编号（如：东1-12） |
| field_name | String | 50 | 否 | - | 田块名称 |
| area | BigDecimal | - | 否 | 0 | 面积（亩） |
| soil_type | String | 20 | 是 | - | 土壤类型 |
| irrigation_type | String | 20 | 是 | - | 灌溉方式 |
| location_x | Double | - | 是 | - | 地理坐标X（经度） |
| location_y | Double | - | 是 | - | 地理坐标Y（纬度） |
| position_data | String | 1000 | 是 | - | 田块多边形坐标数据(JSON格式) |
| status | Integer | - | 否 | 1 | 状态：0-闲置，1-使用中，2-休耕 |
| create_time | Date | - | 否 | CURRENT_TIMESTAMP | 创建时间 |
| update_time | Date | - | 否 | CURRENT_TIMESTAMP | 更新时间 |
| remark | String | 500 | 是 | - | 备注信息 |

### 业务规则
1. 田块编号必须唯一
2. 田块名称必须唯一
3. 面积必须大于0
4. position_data存储田块的多边形坐标点，用于可视化展示
5. 状态默认为使用中（1）
6. 创建时间和更新时间自动维护

### 关联关系
- 一个田块可以有多条灌溉记录（一对多）
- 一个田块可以有多条施肥记录（一对多）
- 一个田块可以种植多种作物，通过田块-作物关系表关联（多对多）

### 示例数据
```json
{
    "id": 1,
    "field_code": "东1-12",
    "field_name": "东区1号田12号地块",
    "area": 5.2,
    "soil_type": "沙壤土",
    "irrigation_type": "滴灌",
    "location_x": 116.3252,
    "location_y": 39.9876,
    "position_data": "[{\"x\":116.3250,\"y\":39.9875},{\"x\":116.3255,\"y\":39.9875},{\"x\":116.3255,\"y\":39.9880},{\"x\":116.3250,\"y\":39.9880}]",
    "status": 1,
    "create_time": "2024-01-01 00:00:00",
    "update_time": "2024-01-01 00:00:00",
    "remark": "地势平坦，适合多种葡萄种植"
}
```

## 葡萄农作物实体 (GrapeCrop)

### 基本信息
- 实体名称：葡萄农作物（GrapeCrop）
- 表名：agri_crop
- 描述：葡萄品种信息表，记录葡萄品种的基本特性和管理要求

### 字段说明

| 字段名 | 类型 | 长度 | 是否为空 | 默认值 | 说明 |
|--------|------|------|----------|--------|------|
| id | Long | - | 否 | - | 主键ID |
| crop_code | String | 20 | 否 | - | 作物编号 |
| crop_name | String | 50 | 否 | - | 作物名称 |
| crop_type | String | 20 | 否 | - | 作物类型（如：葡萄、果树等） |
| variety | String | 50 | 否 | - | 葡萄品种（如：赤霞珠、霞多丽等） |
| growth_cycle | Integer | - | 是 | - | 生长周期（天） |
| suitable_soil | String | 100 | 是 | - | 适宜土壤 |
| suitable_temp | String | 50 | 是 | - | 适宜温度 |
| plant_distance | BigDecimal | - | 是 | - | 建议株距（米） |
| row_distance | BigDecimal | - | 是 | - | 建议行距（米） |
| water_demand | String | 20 | 是 | - | 水分需求（高/中/低） |
| fertilizer_demand | String | 20 | 是 | - | 肥料需求（高/中/低） |
| pruning_method | String | 100 | 是 | - | 推荐修剪方式 |
| disease_resistance | String | 20 | 是 | - | 抗病性（强/中/弱） |
| pest_resistance | String | 20 | 是 | - | 抗虫性（强/中/弱） |
| color | String | 20 | 是 | - | 显示颜色（可视化用） |
| icon | String | 255 | 是 | - | 图标URL |
| status | Integer | - | 否 | 1 | 状态：0-停用，1-启用 |
| create_time | Date | - | 否 | CURRENT_TIMESTAMP | 创建时间 |
| update_time | Date | - | 否 | CURRENT_TIMESTAMP | 更新时间 |
| remark | String | 500 | 是 | - | 备注信息 |

### 业务规则
1. 作物编号必须唯一
2. 作物名称必须唯一
3. 状态默认为启用（1）
4. 创建时间和更新时间自动维护

### 关联关系
- 一种葡萄作物可以种植在多个田块上，通过田块-作物关系表关联（多对多）

### 示例数据
```json
{
    "id": 1,
    "crop_code": "CROP001",
    "crop_name": "赤霞珠",
    "crop_type": "葡萄",
    "variety": "赤霞珠",
    "growth_cycle": 240,
    "suitable_soil": "砂砾质壤土，石灰质土壤",
    "suitable_temp": "15-25°C",
    "plant_distance": 1.5,
    "row_distance": 3.0,
    "water_demand": "中",
    "fertilizer_demand": "中",
    "pruning_method": "短梢修剪",
    "disease_resistance": "中",
    "pest_resistance": "中",
    "color": "#8B0000",
    "icon": "/images/crops/cabernet-sauvignon.png",
    "status": 1,
    "create_time": "2024-01-01 00:00:00",
    "update_time": "2024-01-01 00:00:00",
    "remark": "法国波尔多地区著名红葡萄品种，适合酿造陈年红葡萄酒"
}
```

## 田块-作物关系实体 (FieldCrop)

### 基本信息
- 实体名称：田块-作物关系（FieldCrop）
- 表名：agri_field_crop
- 描述：田块与作物的多对多关系表，用于记录每个田块中种植的作物情况，支持可视化展示

### 字段说明

| 字段名 | 类型 | 长度 | 是否为空 | 默认值 | 说明 |
|--------|------|------|----------|--------|------|
| id | Long | - | 否 | - | 主键ID |
| field_id | Long | - | 否 | - | 田块ID |
| crop_id | Long | - | 否 | - | 作物ID |
| planting_year | Integer | - | 否 | - | 种植年份 |
| vine_age | Integer | - | 否 | - | 葡萄树龄（年） |
| area_size | BigDecimal | - | 否 | 0 | 种植面积（亩） |
| position_data | String | 1000 | 是 | - | 种植区域多边形坐标数据(JSON格式) |
| plant_density | Integer | - | 是 | - | 种植密度（株/亩） |
| total_plants | Integer | - | 是 | - | 植株总数 |
| trellis_type | String | 20 | 是 | - | 架式类型（如：篱架、棚架等） |
| irrigation_method | String | 20 | 是 | - | 实际灌溉方式 |
| fertilizer_type | String | 50 | 是 | - | 实际肥料类型 |
| expected_yield | BigDecimal | - | 是 | - | 预计产量（公斤/亩） |
| actual_yield | BigDecimal | - | 是 | - | 实际产量（公斤/亩） |
| status | Integer | - | 否 | 1 | 状态：0-移除，1-正常生长，2-病害，3-虫害 |
| create_time | Date | - | 否 | CURRENT_TIMESTAMP | 创建时间 |
| update_time | Date | - | 否 | CURRENT_TIMESTAMP | 更新时间 |
| remark | String | 500 | 是 | - | 备注信息 |

### 业务规则
1. 田块ID必须存在于田块表中
2. 作物ID必须存在于作物表中
3. 种植年份不能晚于当前年份
4. 一个田块可以种植多种作物，一种作物可以种植在多个田块上
5. position_data用于在田块内部显示不同作物的种植区域
6. 创建时间和更新时间自动维护

### 关联关系
- 一条记录关联一个田块和一种作物（多对多关系表）
- 多条记录可以关联到同一个田块（一个田块可以种植多种作物）
- 多条记录可以关联到同一种作物（一种作物可以种植在多个田块上）

### 示例数据
```json
{
    "id": 1,
    "field_id": 1,
    "crop_id": 1,
    "planting_year": 2020,
    "vine_age": 4,
    "area_size": 3.2,
    "position_data": "[{\"x\":116.3250,\"y\":39.9875},{\"x\":116.3253,\"y\":39.9875},{\"x\":116.3253,\"y\":39.9878},{\"x\":116.3250,\"y\":39.9878}]",
    "plant_density": 350,
    "total_plants": 1120,
    "trellis_type": "篱架",
    "irrigation_method": "滴灌",
    "fertilizer_type": "有机肥",
    "expected_yield": 1000.00,
    "actual_yield": 950.00,
    "status": 1,
    "create_time": "2024-01-01 00:00:00",
    "update_time": "2024-01-01 00:00:00",
    "remark": "该区域土壤肥沃，阳光充足，赤霞珠生长良好"
}
```
