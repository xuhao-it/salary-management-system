# 工资管理系统项目结构设计文档

## 1. 微服务架构

```markdown
salary-management-system/
├── salary-gateway/                # API网关服务
├── salary-auth/                   # 认证服务
├── salary-employee/              # 员工服务
├── salary-payroll/               # 工资服务
├── salary-common/                # 公共组件
└── salary-ui/                    # 前端应用
```

## 2. 服务模块结构

### 2.1 网关服务(salary-gateway)
```markdown
salary-gateway/
├── src/main/java/com/xuhao/gateway/
│   ├── config/                   # 网关配置
│   │   ├── RouteConfig.java     # 路由配置
│   │   └── CorsConfig.java      # 跨域配置
│   └── filter/                   # 网关过滤器
│       └── AuthFilter.java       # 认证过滤器
└── resources/
    └── application.yml           # 配置文件
```

### 2.2 认证服务(salary-auth)
```markdown
salary-auth/
├── src/main/java/com/xuhao/auth/
│   ├── api/                      # API接口
│   │   └── AuthController.java
│   ├── domain/                   # 领域模型
│   │   └── entity/
│   │       └── User.java
│   ├── infrastructure/           # 基础设施
│   │   ├── config/              # 配置类
│   │   └── security/            # 安全组件
│   └── service/                  # 业务服务
└── resources/
    └── application.yml
```

### 2.3 员工服务(salary-employee)
```markdown
salary-employee/
├── src/main/java/com/xuhao/employee/
│   ├── api/                      # API接口
│   │   └── EmployeeController.java
│   ├── domain/                   # 领域模型
│   │   ├── entity/
│   │   └── repository/
│   ├── infrastructure/           # 基础设施
│   │   ├── mapper/              # MyBatis映射
│   │   └── repository/          # 仓储实现
│   └── service/                  # 业务服务
└── resources/
    └── application.yml
```

### 2.4 工资服务(salary-payroll)
```markdown
salary-payroll/
├── src/main/java/com/xuhao/payroll/
│   ├── api/                      # API接口
│   │   └── PayrollController.java
│   ├── domain/                   # 领域模型
│   │   ├── entity/
│   │   └── service/
│   ├── infrastructure/           # 基础设施
│   └── service/                  # 业务服务
└── resources/
    └── application.yml
```

### 2.5 公共组件(salary-common)
```markdown
salary-common/
├── src/main/java/com/xuhao/common/
│   ├── config/                   # 通用配置
│   ├── exception/               # 异常处理
│   ├── response/                # 响应对象
│   └── util/                    # 工具类
```

### 2.6 前端应用(salary-ui)
```markdown
salary-ui/
├── src/
│   ├── api/                      # API请求
│   ├── components/              # 通用组件
│   ├── hooks/                   # 组合式函数
│   ├── layouts/                 # 布局组件
│   ├── router/                  # 路由配置
│   ├── stores/                  # 状态管理
│   ├── types/                   # 类型定义
│   ├── utils/                   # 工具函数
│   └── views/                   # 页面视图
└── public/                      # 静态资源
```

## 3. 核心配置文件

```markdown
salary-management-system/
├── docker/                      # Docker配置
│   └── docker-compose.yml
├── docs/                        # 项目文档
│   ├── api/
│   ├── architecture/
│   └── database/
└── scripts/                     # 脚本文件
    ├── build.sh
    └── deploy.sh
```

## 4. 依赖管理

```markdown
salary-management-system/
├── pom.xml                      # 父级POM
└── dependencies/                # 依赖管理
    └── dependencies-bom.xml     # BOM文件
```

## 5. 开发规范

每个微服务模块需遵循以下结构:
- api: 对外接口
- domain: 领域模型
- infrastructure: 基础设施
- service: 业务服务
- resources: 配置文件

## 6. 部署结构

```markdown
deployment/
├── kubernetes/                  # K8s配置
│   ├── gateway/
│   ├── auth/
│   ├── employee/
│   └── payroll/
├── monitoring/                 # 监控配置
└── database/                   # 数据库脚本
```