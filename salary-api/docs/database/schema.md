# 数据库设计文档

## 表结构设计

### 用户表(sys_user)
| 字段名 | 类型 | 说明 | 键 |
|--------|------|------|-----|
| id | bigint | 用户ID | PK |
| username | nvarchar(50) | 用户名 | |
| password | nvarchar(100) | 密码 | |
| real_name | nvarchar(50) | 真实姓名 | |
| email | nvarchar(100) | 邮箱 | |
| phone | nvarchar(20) | 电话 | |
| status | tinyint | 状态(0:禁用,1:启用) | |
| create_time | datetime2 | 创建时间 | |
| update_time | datetime2 | 更新时间 | |

### 部门表(sys_department)
| 字段名 | 类型 | 说明 | 键 |
|--------|------|------|-----|
| id | bigint | 部门ID | PK |
| name | nvarchar(50) | 部门名称 | |
| parent_id | bigint | 父部门ID | |
| sort | int | 排序 | |
| create_time | datetime2 | 创建时间 | |
| update_time | datetime2 | 更新时间 | |

### 薪资记录表(salary_record)
| 字段名 | 类型 | 说明 | 键 |
|--------|------|------|-----|
| id | bigint | 记录ID | PK |
| user_id | bigint | 用户ID | FK |
| base_salary | decimal(10,2) | 基本工资 | |
| bonus | decimal(10,2) | 奖金 | |
| deduction | decimal(10,2) | 扣除金额 | |
| total | decimal(10,2) | 总金额 | |
| year | int | 年份 | |
| month | int | 月份 | |
| status | tinyint | 状态 | |
| create_time | datetime2 | 创建时间 | |
| update_time | datetime2 | 更新时间 | |
