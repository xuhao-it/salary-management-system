# 薪资管理系统后端服务

## 项目介绍
本项目是一个基于 Spring Boot 的薪资管理系统后端服务，提供员工薪资管理、考勤管理等功能的 RESTful API。

## 技术栈
- Spring Boot 3.2.x
- SQL Server 2022
- MyBatis-Plus
- Spring Security
- JWT 认证
- OpenAPI 3.0

## 快速开始

### 环境要求
- JDK 21+
- Maven 3.8+
- SQL Server 2022

### 配置说明
1. 克隆项目到本地
2. 配置数据库连接（application.yml）
3. 执行 SQL 脚本创建数据库和表

### 启动服务
```bash
mvn spring-boot:run
```

服务默认启动在 8080 端口

## 详细启动步骤

### 1. 环境准备
- 确保已安装 JDK 21
- 确保已安装 SQL Server 2022
- 确保已安装 Maven 3.8+
- 确保已安装 Redis（可选，用于缓存）

### 2. 数据库配置
1. 在 SQL Server 中创建数据库：
```sql
CREATE DATABASE salary_management;
```

2. 执行数据库初始化脚本：
```bash
# 脚本位置：src/main/resources/db/init.sql
```

### 3. 配置文件设置
修改 `src/main/resources/application.yml`：

```yaml
spring:
  datasource:
    url: jdbc:sqlserver://localhost:1433;databaseName=salary_management;encrypt=true;trustServerCertificate=true
    username: your_username
    password: your_password
  
  redis:
    host: localhost
    port: 6379
```

### 4. 启动服务
方式一：IDE启动
- 在IDE中打开项目
- 运行 SalaryApiApplication.java 主类

方式二：命令行启动
```bash
# 打包
mvn clean package

# 运行jar包
java -jar target/salary-api.jar
```

### 5. 验证服务
- 访问 API 文档：http://localhost:8080/swagger-ui.html
- 测试健康检查接口：http://localhost:8080/actuator/health

### 常见问题排查
1. 端口占用：修改 application.yml 中的 server.port
2. 数据库连接失败：检查数据库配置和网络
3. JDK版本问题：确保使用JDK 21

## 开发模式运行
使用开发模式可以启用热重载：
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

## API 文档
访问 `http://localhost:8080/swagger-ui.html` 查看在线 API 文档

## 项目结构
```
salary-api/
├── src/
│   ├── main/
│   │   ├── java/
│   │   └── resources/
│   └── docs/
├── pom.xml
└── README.md
```
