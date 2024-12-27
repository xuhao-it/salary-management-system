# 企业工资管理系统项目规划

## 1. 系统架构
采用 DDD（领域驱动设计）分层架构：
- 接口层（Interfaces）：负责对外提供API接口
- 应用层（Application）：负责业务流程编排
- 领域层（Domain）：核心业务逻辑
- 基础设施层（Infrastructure）：提供技术支持

## 2. 技术栈
- 核心框架：Spring Boot 3.x
- 安全框架：Spring Security + JWT
- 数据库访问：MyBatis
- 数据库：MySQL
- 缓存：Redis
- 接口文档：Swagger/OpenAPI 3.0
- 构建工具：Maven

## 3. 目录结构
```plaintext
salary-system/
├── docs/                          # 项目文档
│   ├── api/                      # API文档
│   ├── database/                 # 数据库设计
│   └── deployment/               # 部署文档
│
├── salary-api/                    # 后端服务
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/xuhao/salary/
│   │   │   │   ├── domain/                # 领域层
│   │   │   │   │   ├── entity/           # 领域实体
│   │   │   │   │   │   ├── employee/     # 员工相关
│   │   │   │   │   │   ├── salary/       # 薪资相关
│   │   │   │   │   │   └── system/       # 系统相关
│   │   │   │   │   ├── repository/       # 仓储接口
│   │   │   │   │   ├── service/          # 领域服务
│   │   │   │   │   └── event/            # 领域事件
│   │   │   │   │
│   │   │   │   ├── application/          # 应用层
│   │   │   │   │   ├── dto/              # DTO对象
│   │   │   │   │   │   ├── request/      # 请求DTO
│   │   │   │   │   │   └── response/     # 响应DTO
│   │   │   │   │   ├── service/          # 应用服务
│   │   │   │   │   ├── assembler/        # 对象转换
│   │   │   │   │   └── event/            # 事件处理
│   │   │   │   │
│   │   │   │   ├── interfaces/           # 接口层
│   │   │   │   │   ├── controller/       # REST接口
│   │   │   │   │   ├── facade/          # 外观接口
│   │   │   │   │   └── handler/         # 异常处理
│   │   │   │   │
│   │   │   │   ├── infrastructure/       # 基础设施层
│   │   │   │   │   ├── config/          # 配置类
│   │   │   │   │   ├── persistence/     # 持久化实现
│   │   │   │   │   │   ├── mapper/      # MyBatis映射
│   │   │   │   │   │   ├── po/          # 持久化对象
│   │   │   │   │   │   └── repository/  # 仓储实现
│   │   │   │   │   ├── security/        # 安全配置
│   │   │   │   │   ├── cache/          # 缓存实现
│   │   │   │   │   └── mq/             # 消息队列
│   │   │   │   │
│   │   │   │   └── common/              # 公共模块
│   │   │   │       ├── util/            # 工具类
│   │   │   │       ├── constant/        # 常量
│   │   │   │       └── exception/       # 异常类
│   │   │   │
│   │   │   └── resources/              # 资源文件
│   │   │       ├── mapper/             # MyBatis映射文件
│   │   │       └── application.yml     # 配置文件
│   │   │
│   │   └── test/                       # 测试代码
│   │       ├── java/                   # 单元测试
│   │       └── resources/              # 测试资源
│   │
│   └── pom.xml                         # Maven配置
│
├── salary-ui/                          # 前端项目
│   ├── src/
│   │   ├── api/                       # API请求
│   │   ├── assets/                    # 静态资源
│   │   ├── components/                # 公共组件
│   │   ├── layouts/                   # 布局组件
│   │   ├── router/                    # 路由配置
│   │   ├── store/                     # 状态管理
│   │   ├── utils/                     # 工具函数
│   │   └── views/                     # 页面组件
│   ├── public/                        # 公共资源
│   ├── tests/                         # 测试文件
│   └── package.json                   # 依赖配置
│
├── docker/                            # Docker配置
│   ├── docker-compose.yml            # 容器编排
│   ├── nginx/                        # Nginx配置
│   └── postgres/                     # 数据库初始化
│
└── README.md   
```