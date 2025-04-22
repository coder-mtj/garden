# 田块作物关系API文档

## 接口概述

本文档描述了田块作物关系管理相关的API接口，用于管理田块与作物之间的种植关系。

## 基础信息

- 基础路径: `/api/fieldcrop`
- 数据格式: JSON
- 响应格式: 
  ```json
  {
    "code": 200,       // 状态码：200成功，其他表示失败
    "msg": "操作成功",  // 响应消息
    "data": {}         // 响应数据
  }
  ```

## 接口列表

### 1. 添加田块作物关系

- **接口**: `/add`
- **方法**: POST
- **描述**: 添加一条田块与作物的关联记录
- **请求体**:
  ```json
  {
    "fieldId": 1,         // 田块ID
    "cropId": 2,          // 作物ID
    "plantingYear": 2023, // 种植年份
    "plantArea": 10.5,    // 种植面积，单位亩
    "plantDate": "2023-04-15", // 种植日期
    "expectedYield": 1050.0,   // 预期产量，单位千克
    "actualYield": 0,          // 实际产量，单位千克
    "harvestDate": null,       // 收获日期
    "plantingLocation": "{\"type\":\"Polygon\",\"coordinates\":[[...]]}", // 种植区域坐标
    "irrigationMethod": "滴灌", // 灌溉方式
    "fertilizerUsage": "有机肥", // 施肥方式
    "diseaseControl": "生物防治", // 病虫害防治
    "remarks": "测试种植",       // 备注
    "status": 1                // 状态，1:正常，0:禁用
  }
  ```
- **响应示例**:
  ```json
  {
    "code": 200,
    "msg": "添加成功",
    "data": null
  }
  ```

### 2. 批量添加田块作物关系

- **接口**: `/batchAdd`
- **方法**: POST
- **描述**: 批量添加多条田块与作物的关联记录
- **请求体**:
  ```json
  [
    {
      "fieldId": 1,
      "cropId": 2,
      "plantingYear": 2023,
      "plantArea": 10.5,
      "plantDate": "2023-04-15",
      "expectedYield": 1050.0,
      "actualYield": 0,
      "status": 1
    },
    {
      "fieldId": 1,
      "cropId": 3,
      "plantingYear": 2023,
      "plantArea": 5.0,
      "plantDate": "2023-04-20",
      "expectedYield": 500.0,
      "actualYield": 0,
      "status": 1
    }
  ]
  ```
- **响应示例**:
  ```json
  {
    "code": 200,
    "msg": "成功添加2条记录",
    "data": null
  }
  ```

### 3. 更新田块作物关系

- **接口**: `/update`
- **方法**: PUT
- **描述**: 更新已有的田块作物关系记录
- **请求体**:
  ```json
  {
    "id": 1,              // 记录ID，必填
    "fieldId": 1,         // 田块ID
    "cropId": 2,          // 作物ID
    "plantingYear": 2023, // 种植年份
    "plantArea": 12.5,    // 更新后的种植面积
    "expectedYield": 1250.0, // 更新后的预期产量
    "actualYield": 1150.0,   // 更新后的实际产量
    "harvestDate": "2023-10-15", // 收获日期
    "status": 1               // 状态
  }
  ```
- **响应示例**:
  ```json
  {
    "code": 200,
    "msg": "更新成功",
    "data": null
  }
  ```

### 4. 删除田块作物关系

- **接口**: `/delete/{id}`
- **方法**: DELETE
- **描述**: 根据ID删除田块作物关系记录
- **路径参数**:
  - `id`: 记录ID
- **响应示例**:
  ```json
  {
    "code": 200,
    "msg": "删除成功",
    "data": null
  }
  ```

### 5. 批量删除田块作物关系

- **接口**: `/batchDelete`
- **方法**: DELETE
- **描述**: 批量删除多条田块作物关系记录
- **请求体**:
  ```json
  [1, 2, 3]  // 要删除的记录ID列表
  ```
- **响应示例**:
  ```json
  {
    "code": 200,
    "msg": "成功删除3条记录",
    "data": null
  }
  ```

### 6. 根据ID查询田块作物关系

- **接口**: `/get/{id}`
- **方法**: GET
- **描述**: 根据ID查询单条田块作物关系记录
- **路径参数**:
  - `id`: 记录ID
- **响应示例**:
  ```json
  {
    "code": 200,
    "msg": "操作成功",
    "data": {
      "id": 1,
      "fieldId": 1,
      "fieldName": "东区A田块", // 田块名称
      "cropId": 2,
      "cropName": "赤霞珠",    // 作物名称
      "plantingYear": 2023,
      "plantArea": 12.5,
      "plantDate": "2023-04-15",
      "expectedYield": 1250.0,
      "actualYield": 1150.0,
      "harvestDate": "2023-10-15",
      "plantingLocation": "{\"type\":\"Polygon\",\"coordinates\":[[...]]}", 
      "irrigationMethod": "滴灌",
      "fertilizerUsage": "有机肥",
      "diseaseControl": "生物防治",
      "remarks": "测试种植",
      "createTime": "2023-04-01 10:00:00",
      "updateTime": "2023-10-15 15:30:00",
      "status": 1
    }
  }
  ```

### 7. 根据田块ID查询关联的作物关系

- **接口**: `/getByFieldId/{fieldId}`
- **方法**: GET
- **描述**: 查询指定田块下的所有作物关联记录
- **路径参数**:
  - `fieldId`: 田块ID
- **响应示例**:
  ```json
  {
    "code": 200,
    "msg": "操作成功",
    "data": [
      {
        "id": 1,
        "fieldId": 1,
        "fieldName": "东区A田块",
        "cropId": 2,
        "cropName": "赤霞珠",
        "plantingYear": 2023,
        "plantArea": 12.5,
        "plantDate": "2023-04-15",
        "expectedYield": 1250.0,
        "actualYield": 1150.0,
        "status": 1
      },
      {
        "id": 2,
        "fieldId": 1,
        "fieldName": "东区A田块",
        "cropId": 3,
        "cropName": "梅鹿辄",
        "plantingYear": 2023,
        "plantArea": 5.0,
        "plantDate": "2023-04-20",
        "expectedYield": 500.0,
        "actualYield": 480.0,
        "status": 1
      }
    ]
  }
  ```

### 8. 根据作物ID查询关联的田块关系

- **接口**: `/getByCropId/{cropId}`
- **方法**: GET
- **描述**: 查询指定作物的所有田块关联记录
- **路径参数**:
  - `cropId`: 作物ID
- **响应示例**:
  ```json
  {
    "code": 200,
    "msg": "操作成功",
    "data": [
      {
        "id": 1,
        "fieldId": 1,
        "fieldName": "东区A田块",
        "cropId": 2,
        "cropName": "赤霞珠",
        "plantingYear": 2023,
        "plantArea": 12.5,
        "plantDate": "2023-04-15",
        "expectedYield": 1250.0,
        "actualYield": 1150.0,
        "status": 1
      },
      {
        "id": 3,
        "fieldId": 2,
        "fieldName": "南区B田块",
        "cropId": 2,
        "cropName": "赤霞珠",
        "plantingYear": 2023,
        "plantArea": 8.0,
        "plantDate": "2023-03-25",
        "expectedYield": 800.0,
        "actualYield": 750.0,
        "status": 1
      }
    ]
  }
  ```

### 9. 根据年份、田块ID和作物ID查询田块作物关系

- **接口**: `/getByYearAndIds`
- **方法**: GET
- **描述**: 根据年份、田块ID和作物ID查询田块作物关系
- **请求参数**:
  - `plantingYear`: 种植年份
  - `fieldId`: 田块ID
  - `cropId`: 作物ID
- **响应示例**:
  ```json
  {
    "code": 200,
    "msg": "操作成功",
    "data": [
      {
        "id": 1,
        "fieldId": 1,
        "fieldName": "东区A田块",
        "cropId": 2,
        "cropName": "赤霞珠",
        "plantingYear": 2023,
        "plantArea": 12.5,
        "plantDate": "2023-04-15",
        "expectedYield": 1250.0,
        "actualYield": 1150.0,
        "status": 1
      }
    ]
  }
  ```

### 10. 查询所有田块作物关系

- **接口**: `/list`
- **方法**: GET
- **描述**: 查询所有田块作物关系记录
- **响应示例**:
  ```json
  {
    "code": 200,
    "msg": "操作成功",
    "data": [
      {
        "id": 1,
        "fieldId": 1,
        "cropId": 1,
        // 其他字段...
      },
      {
        "id": 2,
        "fieldId": 1,
        "cropId": 2,
        // 其他字段...
      }
      // 更多记录...
    ]
  }
  ```

### 11. 条件查询田块作物关系

- **接口**: `/search`
- **方法**: GET
- **描述**: 根据条件查询田块作物关系记录
- **请求参数**:
  - `fieldId`: 田块ID
  - `cropId`: 作物ID
  - `plantingYear`: 种植年份
  - `status`: 状态
- **响应示例**:
  ```json
  {
    "code": 200,
    "msg": "操作成功",
    "data": [
      {
        "id": 1,
        "fieldId": 1,
        "cropId": 1,
        "plantingYear": 2023,
        "status": 1,
        // 其他字段...
      }
      // 更多记录...
    ]
  }
  ```

### 12. 分页查询

- **接口**: `/page`
- **方法**: GET
- **描述**: 分页查询田块作物关系记录
- **请求参数**:
  - `pageNum`: 页码
  - `pageSize`: 每页记录数
  - `fieldId`: 田块ID
  - `cropId`: 作物ID
  - `plantingYear`: 种植年份
  - `status`: 状态
- **响应示例**:
  ```json
  {
    "code": 200,
    "msg": "操作成功",
    "data": {
      "records": [
        {
          "id": 1,
          "fieldId": 1,
          "cropId": 1,
          // 其他字段...
        }
        // 更多记录...
      ],
      "total": 25,
      "pageNum": 1,
      "pageSize": 10,
      "totalPages": 3
    }
  }
  ```

### 13. 更新状态

- **接口**: `/updateStatus`
- **方法**: PUT
- **描述**: 更新田块作物关系的状态
- **请求参数**:
  - `id`: 记录ID
  - `status`: 新状态
- **响应示例**:
  ```json
  {
    "code": 200,
    "msg": "状态更新成功",
    "data": null
  }
  ```

### 14. 导出Excel

- **接口**: `/export`
- **方法**: GET
- **描述**: 将田块作物关系数据导出为Excel文件
- **请求参数**:
  - `fieldId`: 田块ID
  - `cropId`: 作物ID
  - `plantingYear`: 种植年份
  - `status`: 状态
- **响应**: 二进制流，Excel文件

### 15. 导入Excel

- **接口**: `/import`
- **方法**: POST
- **描述**: 从Excel文件导入田块作物关系数据
- **请求**: `multipart/form-data`类型，包含名为`file`的Excel文件
- **响应示例**:
  ```json
  {
    "code": 200,
    "msg": "成功导入10条记录",
    "data": null
  }
  ```

### 16. 按年度统计产量

- **接口**: `/statsByYear`
- **方法**: GET
- **描述**: 统计指定条件下各年度的产量数据
- **请求参数**:
  - `fieldId`: 田块ID
  - `cropId`: 作物ID
- **响应示例**:
  ```json
  {
    "code": 200,
    "msg": "操作成功",
    "data": {
      "2021": 2500.5,
      "2022": 2800.0,
      "2023": 3100.0
    }
  }
  ```

### 17. 按作物类型统计种植面积

- **接口**: `/statsByCrop`
- **方法**: GET
- **描述**: 统计指定年度内各作物的种植面积
- **请求参数**:
  - `plantingYear`: 种植年份
- **响应示例**:
  ```json
  {
    "code": 200,
    "msg": "操作成功",
    "data": {
      "赤霞珠": 25.5,
      "梅鹿辄": 18.0,
      "霞多丽": 15.0
    }
  }
  ```

### 18. 获取田块使用情况

- **接口**: `/fieldUsage/{fieldId}`
- **方法**: GET
- **描述**: 获取指定田块的使用情况统计
- **路径参数**:
  - `fieldId`: 田块ID
- **响应示例**:
  ```json
  {
    "code": 200,
    "msg": "操作成功",
    "data": {
      "fieldId": 1,
      "fieldName": "东区A田块",
      "totalArea": 42.5,
      "usedArea": 40.0,
      "utilization": 0.94,
      "cropDistribution": [
        {
          "cropId": 1,
          "cropName": "赤霞珠",
          "plantArea": 23.0,
          "proportion": 0.60
        },
        {
          "cropId": 2,
          "cropName": "梅鹿辄",
          "plantArea": 17.0,
          "proportion": 0.40
        }
      ],
      "yearlyUsage": {
        "2021": 35.0,
        "2022": 40.0,
        "2023": 42.5
      }
    }
  }
  ```

## 错误码说明

| 错误码 | 描述 |
|--------|------|
| 200 | 操作成功 |
| 400 | 请求参数错误 |
| 404 | 资源不存在 |
| 500 | 服务器内部错误 |

## 注意事项

1. 所有日期时间字段格式为：`yyyy-MM-dd HH:mm:ss`
2. 面积单位为亩，重量单位为千克，金额单位为元
3. 种植区域多边形坐标数据为JSON格式的字符串 