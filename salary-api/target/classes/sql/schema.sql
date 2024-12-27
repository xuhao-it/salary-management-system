CREATE DATABASE IF NOT EXISTS salary_management DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE salary_management;

-- 员工表
CREATE TABLE IF NOT EXISTS employee (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL COMMENT '姓名',
    employee_no VARCHAR(20) NOT NULL UNIQUE COMMENT '工号',
    gender VARCHAR(10) COMMENT '性别',
    birth_date DATE COMMENT '出生日期',
    id_card VARCHAR(18) COMMENT '身份证号',
    phone VARCHAR(20) COMMENT '电话',
    email VARCHAR(50) COMMENT '邮箱',
    address TEXT COMMENT '地址',
    department VARCHAR(50) COMMENT '部门',
    position VARCHAR(50) COMMENT '职位',
    entry_date DATE COMMENT '入职日期',
    status TINYINT DEFAULT 1 COMMENT '状态：1-在职，0-离职',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 考勤表
CREATE TABLE IF NOT EXISTS attendance (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    employee_id BIGINT NOT NULL COMMENT '员工ID',
    date DATE NOT NULL COMMENT '考勤日期',
    check_in TIME COMMENT '上班时间',
    check_out TIME COMMENT '下班时间',
    status VARCHAR(20) COMMENT '考勤状态：正常、迟到、早退、旷工、请假',
    remark TEXT COMMENT '备注',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (employee_id) REFERENCES employee(id)
);

-- 奖惩记录表
CREATE TABLE IF NOT EXISTS reward (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    employee_id BIGINT NOT NULL COMMENT '员工ID',
    type VARCHAR(20) NOT NULL COMMENT '类型：奖励、处罚',
    amount DECIMAL(10,2) NOT NULL COMMENT '金额',
    reason TEXT COMMENT '原因',
    date DATE NOT NULL COMMENT '日期',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (employee_id) REFERENCES employee(id)
);

-- 工资表
CREATE TABLE IF NOT EXISTS salary (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    employee_id BIGINT NOT NULL COMMENT '员工ID',
    year INT NOT NULL COMMENT '年份',
    month INT NOT NULL COMMENT '月份',
    basic_salary DECIMAL(10,2) NOT NULL COMMENT '基本工资',
    bonus DECIMAL(10,2) DEFAULT 0 COMMENT '奖金',
    overtime_pay DECIMAL(10,2) DEFAULT 0 COMMENT '加班费',
    deduction DECIMAL(10,2) DEFAULT 0 COMMENT '扣款',
    insurance DECIMAL(10,2) DEFAULT 0 COMMENT '保险金额',
    tax DECIMAL(10,2) DEFAULT 0 COMMENT '个人所得税',
    net_salary DECIMAL(10,2) NOT NULL COMMENT '实发工资',
    status VARCHAR(20) DEFAULT 'PENDING' COMMENT '状态：待发放、已发放',
    remark TEXT COMMENT '备注',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (employee_id) REFERENCES employee(id)
);