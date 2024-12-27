# 薪资管理系统数据库设计

## 实体说明

### Employee (员工)
| 字段名 | 类型 | 说明 |
|--------|------|------|
| id | bigint | 主键 |
| employee_no | varchar(32) | 员工工号 |
| name | varchar(64) | 姓名 |
| department | varchar(64) | 部门 |
| position | varchar(64) | 职位 |
| base_salary | decimal(10,2) | 基本工资 |
| status | varchar(16) | 状态(在职/离职) |
| entry_date | date | 入职日期 |
| created_at | timestamp | 创建时间 |
| updated_at | timestamp | 更新时间 |

### Salary (薪资)
| 字段名 | 类型 | 说明 |
|--------|------|------|
| id | bigint | 主键 |
| employee_id | bigint | 员工ID |
| month | varchar(7) | 薪资月份(YYYY-MM) |
| base_amount | decimal(10,2) | 基本工资 |
| overtime_pay | decimal(10,2) | 加班费 |
| bonus | decimal(10,2) | 奖金 |
| deduction | decimal(10,2) | 扣除项 |
| social_insurance | decimal(10,2) | 社保金额 |
| tax | decimal(10,2) | 个人所得税 |
| net_amount | decimal(10,2) | 实发金额 |

### User (系统用户)
| 字段名 | 类型 | 说明 |
|--------|------|------|
| id | bigint | 主键 |
| username | varchar(64) | 用户名 |
| password | varchar(256) | 密码(加密) |
| role | varchar(32) | 角色(ADMIN/HR) |
| status | varchar(16) | 状态(启用/禁用) |
