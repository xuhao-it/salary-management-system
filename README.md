# 企业工资管理系统

一个基于 Spring Boot 3 和 Vue 3 的现代化企业工资管理系统，采用整洁架构设计。

## 项目架构

采用 Clean Architecture（整洁架构）设计，分为以下层次：

- 企业业务规则层（Entities）：核心业务实体
- 应用业务规则层（Use Cases）：业务用例实现
- 接口适配层（Interface Adapters）：控制器和数据转换
- 框架和驱动层（Frameworks & Drivers）：外部框架和工具

## 技术栈

### 后端
- Spring Boot 3.x
- Java 21
- MyBatis-Plus
- Spring Security + JWT
- Redis
- RabbitMQ
- SQL Server 2022

### 前端
- Vue 3
- Element Plus
- Vite

### 部署
- Docker
- Nginx

## 详细项目结构

### 后端服务 (salary-api)
```plaintext
src/main/java/com/xuhao/salary/
├── domain/                # 领域层
│   ├── entity/           # 领域实体
│   ├── repository/       # 仓储接口
│   ├── service/          # 领域服务
│   └── event/            # 领域事件
├── application/          # 应用层
│   ├── dto/              # 数据传输对象
│   ├── service/          # 应用服务
│   └── assembler/        # 对象转换器
├── interfaces/           # 接口层
│   ├── controller/       # REST控制器
│   └── facade/          # 外观模式接口
└── infrastructure/       # 基础设施层
    ├── persistence/      # 持久化实现
    ├── security/         # 安全配置
    └── cache/           # 缓存实现
```

### 前端项目 (salary-ui)
```plaintext
src/
├── api/                 # API接口
├── components/          # 公共组件
├── views/              # 页面组件
├── router/             # 路由配置
└── store/              # 状态管理
```

## 开发指南

### 环境要求
- JDK 21
- Node.js 18+
- SQL Server 2022
- Redis
- RabbitMQ
- Docker & Docker Compose

### 本地开发

1. 克隆项目
```bash
git clone [https://github.com/xuhao-it/salary-management-system.git]
```

2. 后端服务启动
```bash
cd salary-api
mvn spring-boot:run
```

3. 前端开发
```bash
cd salary-ui
npm install
npm run dev
```

### 部署

使用 Docker Compose 进行部署：
```bash
cd docker
docker-compose up -d
```

## 文档

- API文档：`docs/api/`
- 数据库设计：`docs/database/`
- 部署文档：`docs/deployment/`

## 贡献指南

### 分支管理
- `main`: 主分支，用于产品发布
- `develop`: 开发分支，日常开发在此分支进行
- `feature/*`: 功能分支，开发新功能时使用
- `hotfix/*`: 修复分支，用于修复生产环境紧急问题

### 提交规范
提交信息必须遵循以下格式：
```
<type>(<scope>): <subject>

<body>

<footer>
```

类型（type）:
- feat: 新功能
- fix: 修复bug
- docs: 文档更改
- style: 代码格式化
- refactor: 代码重构
- test: 测试用例
- chore: 构建过程或辅助工具的变动

### 开发流程
1. 从 `develop` 分支创建功能分支
2. 完成开发并提交代码
3. 创建 Pull Request 到 `develop` 分支
4. 通过代码审查后合并

### 代码规范
- 后端遵循 Alibaba Java 编码规范
- 前端遵循 Vue.js 风格指南
- 所有代码必须通过 ESLint/SonarLint 检查
- 关键代码需要编写单元测试

## 问题反馈

如果你有任何问题或建议，请通过以下方式反馈：
1. 提交 Issue
2. 发送邮件到 [维护者邮箱]

## 许可证

本项目采用 [MIT 许可证](LICENSE) 进行许可。

## 更新日志

请查看 [CHANGELOG.md](CHANGELOG.md) 了解项目更新历史。

## 作者

- 作者名称：xuhao
- 联系方式：17794275342@163.com

## 特别鸣谢

感谢所有贡献者对本项目的支持！