# 工资管理系统

基于 JavaFX 和 Spring Boot 的现代工资管理系统，采用 DDD（领域驱动设计）架构。

## 项目架构

### 领域驱动设计(DDD)层次结构
```
src/main/java/com/xuhao/payroll/
├── application/          # 应用服务层
│   ├── dto/             # 数据传输对象
│   │   ├── request/     # 请求DTO
│   │   └── response/    # 响应DTO
│   ├── impl/            # 服务实现
│   └── service/         # 服务接口
├── domain/              # 领域层
│   ├── model/           # 领域模型
│   ├── repository/      # 仓储接口
│   │   └── impl/       # 仓储实现
│   └── service/         # 领域服务
│       ├── salary/      # 薪资服务
│       ├── attendance/  # 考勤服务
│       └── employee/    # 员工服务
├── infrastructure/      # 基础设施层
│   ├── common/          # 公共组件
│   ├── config/          # 配置
│   ├── persistence/     # 持久化
│   └── task/           # 系统任务
├── interfaces/          # 接口层
│   ├── assembler/       # DTO转换
│   ├── controller/      # 控制器
│   └── facade/         # 外观模式
└── presentation/        # 表现层
    ├── component/       # UI组件
    ├── controller/      # 视图控制器
    └── view/           # 视图
```

### 核心功能模块
1. 员工管理（Employee Management）
   - 员工信息管理
   - 部门管理
   - 职位管理

2. 考勤管理（Attendance）
   - 考勤记录
   - 请假管理
   - 加班管理

3. 薪资管理（Salary）
   - 工资配置
   - 工资计算
   - 奖惩管理

4. 报表统计（Statistics）
   - 工资报表
   - 考勤统计
   - 数据可视化

## 技术栈

### 后端技术
- Spring Boot 3.x
- MyBatis
- MySQL 8.0+
- Log4j2

### 前端技术
- JavaFX 21
- CSS3
- FXML

### 工具与框架
- Maven
- Lombok
- JWT
- Spring AOP

## 开发环境要求
- JDK 21+
- Maven 3.6+
- MySQL 8.0+
- IDE: IntelliJ IDEA/Eclipse/VS Code

## 快速开始

### 环境准备
1. 安装 JDK 21
2. 安装 MySQL 8.0+
3. 安装 Maven 3.6+

### 构建步骤
```bash
# 克隆项目
git clone [repository-url]

# 进入项目目录
cd salary-management-system

# 初始化数据库
mysql -u root -p < src/main/resources/sql/schema.sql
mysql -u root -p < src/main/resources/sql/init.sql

# 编译打包
mvn clean package

# 运行应用
java -jar target/salary-management-system-1.0-SNAPSHOT.jar
```

## 项目特点
- 领域驱动设计架构
- 面向切面的日志系统
- 完整的权限管理
- 响应式UI设计
- 数据可视化报表
- 主题切换支持

## 贡献指南
1. Fork 项目
2. 创建特性分支
3. 提交更改
4. 推送到分支
5. 创建 Pull Request

## 许可证
[MIT License](LICENSE.md)