# 天道葡萄园中控系统

## 项目概述

园艺管理系统是一个专注于葡萄园管理的综合平台，提供田块管理、作物种植监控、农具使用记录等功能，旨在提高农业生产效率和管理水平。系统采用现代化的技术栈开发，支持图形化显示田块信息，实时监控农作物生长状态，便于农场主和管理人员进行高效的农业经营决策。

## 核心功能

### 田块管理
- 支持田块的添加、编辑、删除和查询
- 提供田块位置和形状的图形化管理
- 实现田块使用情况统计和可视化展示

### 葡萄品种管理
- 多种葡萄品种的详细信息维护
- 品种特性记录和分类管理
- 品种可视化属性（颜色、图标）定制

### 田块-作物关联管理
- 实现田块与作物的多对多关系
- 记录种植区域、种植面积、预期产量等数据
- 支持按年份、作物类型的统计分析

### 农具使用记录
- 跟踪农具使用情况，包括使用时间、使用人员等
- 统计农具使用效率和燃油消耗
- 农具维护提醒和状态管理

### 用户管理
- 完善的用户权限控制
- 用户登录认证和token验证
- 用户操作日志记录

## 技术架构

### 后端技术栈
- 开发语言：Java
- 框架：Spring Boot
- 数据库：MySQL
- ORM框架：MyBatis
- 接口文档：RESTful API

### 前端技术
- 基于现代Web框架开发的用户界面
- 响应式设计，支持多种设备访问
- 集成地图可视化组件，展示田块分布

### 系统架构
- 采用分层架构设计：控制层、服务层、数据访问层
- RESTful API设计风格
- 统一的数据返回格式和异常处理机制

## 安装与配置

### 环境要求
- JDK 17或以上
- Maven 3.6或以上
- MySQL 8.0或以上

### 安装步骤

1. 克隆代码库
```bash
git clone https://github.com/coder-mtj/garden.git
cd garden
```

2. 配置数据库
- 创建数据库：`field_visualization`
- 导入初始化SQL脚本：`src/main/resources/sql/field_visualization.sql`

3. 修改配置
编辑`src/main/resources/application.properties`，配置数据库连接信息：
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/field_visualization?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8
spring.datasource.username=your_username
spring.datasource.password=your_password
```

4. 编译打包
```bash
mvn clean package
```

5. 运行应用
```bash
java -jar target/garden-0.0.1-SNAPSHOT.jar
```

6. 访问系统
打开浏览器，访问：`http://localhost:8080`

## API文档

系统提供了完善的API文档，包括：

- 田块API：`/src/main/resources/api/field_api.md`
- 葡萄作物API：`/src/main/resources/api/grape_crop_api.md`
- 田块作物关系API：`/src/main/resources/api/field_crop_api.md`
- 农具使用记录API：`/src/main/resources/api/tool_usage_api.md`
- 用户API：`/src/main/resources/api/user_api.md`

所有API均需要通过token验证，请在请求头中添加token参数。

## 数据模型

系统主要包含以下实体：

1. **田块（Field）**：记录田块的基本信息，包括位置、形状等
2. **葡萄作物（GrapeCrop）**：葡萄品种信息，包括品种特性和可视化属性
3. **田块作物关系（FieldCrop）**：田块与作物的关联，记录种植情况
4. **农具使用记录（ToolUsageRecord）**：记录农具的使用情况
5. **用户（User）**：系统用户信息

## 开发指南

### 代码结构
```
src/main/java/com/bishe/garden/
├── common/        # 通用工具类和常量
├── config/        # 配置类
├── controller/    # 控制器层
├── entity/        # 实体类
├── mapper/        # MyBatis映射接口
├── service/       # 服务层
│   └── impl/      # 服务实现类
└── util/          # 工具类

src/main/resources/
├── api/           # API文档
├── mapper/        # MyBatis XML映射文件
└── sql/           # SQL脚本
```

### 开发规范
- 代码风格遵循Java标准编码规范
- API设计遵循RESTful风格
- 异常统一处理，返回标准格式的错误信息
- 所有接口必须进行token验证

## 许可证

[HIT](License)

## 联系方式

如有问题或建议，请联系开发团队或提交Issue。 
[邮箱](mtj295152@gmail.com)
