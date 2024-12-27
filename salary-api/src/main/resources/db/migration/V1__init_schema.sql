-- 创建数据库
IF NOT EXISTS (SELECT *
FROM sys.databases
WHERE name = 'PayrollDB')
BEGIN
    CREATE DATABASE PayrollDB
    ON PRIMARY
    (
        NAME = 'PayrollDB',
        FILENAME = 'C:\Program Files\Microsoft SQL Server\MSSQL16.USER01\MSSQL\DATA\PayrollDB.mdf',
        SIZE = 8MB,
        MAXSIZE = UNLIMITED,
        FILEGROWTH = 65536KB
    )
    LOG ON
    (
        NAME = 'PayrollDB_log',
        FILENAME = 'C:\Program Files\Microsoft SQL Server\MSSQL16.USER01\MSSQL\DATA\PayrollDB_log.ldf',
        SIZE = 8MB,
        MAXSIZE = 2048GB,
        FILEGROWTH = 65536KB
    );
END
GO

USE PayrollDB;
GO

-- 创建部门表
IF NOT EXISTS (SELECT *
FROM sys.objects
WHERE object_id = OBJECT_ID(N'[dbo].[Department]') AND type in (N'U'))
BEGIN
    CREATE TABLE Department
    (
        dept_id INT PRIMARY KEY IDENTITY(1,1),
        dept_name NVARCHAR(50) NOT NULL,
        dept_desc NVARCHAR(200),
        create_time DATETIME DEFAULT GETDATE(),
        update_time DATETIME DEFAULT GETDATE()
    );
END
GO

-- 创建员工表
IF NOT EXISTS (SELECT *
FROM sys.objects
WHERE object_id = OBJECT_ID(N'[dbo].[Employee]') AND type in (N'U'))
BEGIN
    CREATE TABLE Employee
    (
        emp_id INT PRIMARY KEY IDENTITY(1,1),
        emp_no VARCHAR(20) UNIQUE NOT NULL,
        emp_name NVARCHAR(50) NOT NULL,
        gender CHAR(1) CHECK (gender IN ('M', 'F')),
        birth_date DATE,
        id_card VARCHAR(18),
        phone VARCHAR(20),
        email VARCHAR(100),
        address NVARCHAR(200),
        dept_id INT FOREIGN KEY REFERENCES Department(dept_id),
        position NVARCHAR(50),
        entry_date DATE,
        leave_date DATE,
        status TINYINT DEFAULT 1,
        -- 1:在职 2:离职 3:试用期
        create_time DATETIME DEFAULT GETDATE(),
        update_time DATETIME DEFAULT GETDATE()
    );
END
GO

-- 创建工资配置表
IF NOT EXISTS (SELECT *
FROM sys.objects
WHERE object_id = OBJECT_ID(N'[dbo].[SalaryConfig]') AND type in (N'U'))
BEGIN
    CREATE TABLE SalaryConfig
    (
        config_id INT PRIMARY KEY IDENTITY(1,1),
        emp_id INT FOREIGN KEY REFERENCES Employee(emp_id),
        basic_salary DECIMAL(12,2),
        position_salary DECIMAL(12,2),
        insurance_base DECIMAL(12,2),
        housing_fund_base DECIMAL(12,2),
        effective_date DATE,
        create_time DATETIME DEFAULT GETDATE(),
        update_time DATETIME DEFAULT GETDATE()
    );
END
GO

-- 创建考勤记录表
IF NOT EXISTS (SELECT *
FROM sys.objects
WHERE object_id = OBJECT_ID(N'[dbo].[Attendance]') AND type in (N'U'))
BEGIN
    CREATE TABLE Attendance
    (
        attendance_id INT PRIMARY KEY IDENTITY(1,1),
        emp_id INT FOREIGN KEY REFERENCES Employee(emp_id),
        attendance_date DATE,
        check_in DATETIME,
        check_out DATETIME,
        status TINYINT,
        -- 1:正常 2:迟到 3:早退 4:旷工 5:请假
        remark NVARCHAR(200),
        create_time DATETIME DEFAULT GETDATE()
    );
END
GO

-- 创建请假记录表
IF NOT EXISTS (SELECT *
FROM sys.objects
WHERE object_id = OBJECT_ID(N'[dbo].[Leave]') AND type in (N'U'))
BEGIN
    CREATE TABLE Leave
    (
        leave_id INT PRIMARY KEY IDENTITY(1,1),
        emp_id INT FOREIGN KEY REFERENCES Employee(emp_id),
        leave_type TINYINT,
        -- 1:年假 2:病假 3:事假 4:婚假 5:产假 6:丧假
        start_time DATETIME,
        end_time DATETIME,
        duration DECIMAL(5,1),
        -- 请假天数
        reason NVARCHAR(200),
        status TINYINT,
        -- 1:待审批 2:已批准 3:已驳回
        approver_id INT FOREIGN KEY REFERENCES Employee(emp_id),
        create_time DATETIME DEFAULT GETDATE(),
        update_time DATETIME DEFAULT GETDATE()
    );
END
GO

-- 创建加班记录表
IF NOT EXISTS (SELECT *
FROM sys.objects
WHERE object_id = OBJECT_ID(N'[dbo].[Overtime]') AND type in (N'U'))
BEGIN
    CREATE TABLE Overtime
    (
        overtime_id INT PRIMARY KEY IDENTITY(1,1),
        emp_id INT FOREIGN KEY REFERENCES Employee(emp_id),
        overtime_date DATE,
        start_time DATETIME,
        end_time DATETIME,
        duration DECIMAL(5,1),
        reason NVARCHAR(200),
        status TINYINT,
        -- 1:待审批 2:已批准 3:已驳回
        approver_id INT FOREIGN KEY REFERENCES Employee(emp_id),
        create_time DATETIME DEFAULT GETDATE(),
        update_time DATETIME DEFAULT GETDATE()
    );
END
GO

-- 创建奖惩记录表
IF NOT EXISTS (SELECT *
FROM sys.objects
WHERE object_id = OBJECT_ID(N'[dbo].[Reward]') AND type in (N'U'))
BEGIN
    CREATE TABLE Reward
    (
        reward_id INT PRIMARY KEY IDENTITY(1,1),
        emp_id INT FOREIGN KEY REFERENCES Employee(emp_id),
        type TINYINT,
        -- 1:奖励 2:处罚
        amount DECIMAL(12,2),
        reason NVARCHAR(200),
        occur_date DATE,
        create_time DATETIME DEFAULT GETDATE(),
        update_time DATETIME DEFAULT GETDATE()
    );
END
GO

-- 创建工资记录表
IF NOT EXISTS (SELECT *
FROM sys.objects
WHERE object_id = OBJECT_ID(N'[dbo].[Salary]') AND type in (N'U'))
BEGIN
    CREATE TABLE Salary
    (
        salary_id INT PRIMARY KEY IDENTITY(1,1),
        emp_id INT FOREIGN KEY REFERENCES Employee(emp_id),
        year INT,
        month INT,
        basic_salary DECIMAL(12,2),
        position_salary DECIMAL(12,2),
        overtime_pay DECIMAL(12,2),
        bonus DECIMAL(12,2),
        deduction DECIMAL(12,2),
        insurance DECIMAL(12,2),
        housing_fund DECIMAL(12,2),
        tax DECIMAL(12,2),
        net_salary DECIMAL(12,2),
        status TINYINT,
        -- 1:未发放 2:已发放
        remark NVARCHAR(200),
        create_time DATETIME DEFAULT GETDATE(),
        update_time DATETIME DEFAULT GETDATE()
    );
END
GO

-- 创建系统用户表
IF NOT EXISTS (SELECT *
FROM sys.objects
WHERE object_id = OBJECT_ID(N'[dbo].[SysUser]') AND type in (N'U'))
BEGIN
    CREATE TABLE SysUser
    (
        user_id INT PRIMARY KEY IDENTITY(1,1),
        username VARCHAR(50) UNIQUE NOT NULL,
        password VARCHAR(100) NOT NULL,
        emp_id INT FOREIGN KEY REFERENCES Employee(emp_id),
        role_type TINYINT,
        -- 1:超级管理员 2:管理员 3:人事 4:财务 5:普通用户
        status TINYINT DEFAULT 1,
        -- 1:启用 2:禁用
        last_login_time DATETIME,
        create_time DATETIME DEFAULT GETDATE(),
        update_time DATETIME DEFAULT GETDATE()
    );
END
GO

-- 创建系统日志表
IF NOT EXISTS (SELECT *
FROM sys.objects
WHERE object_id = OBJECT_ID(N'[dbo].[sys_log]') AND type in (N'U'))
BEGIN
    CREATE TABLE sys_log
    (
        log_id BIGINT PRIMARY KEY IDENTITY(1,1),
        username VARCHAR(50),
        operation VARCHAR(200),
        method VARCHAR(200),
        params TEXT,
        ip VARCHAR(50),
        create_time DATETIME,
        execution_time BIGINT,
        status VARCHAR(20),
        error_message TEXT
    );
END
GO

-- 插入初始管理员账号
IF NOT EXISTS (SELECT *
FROM SysUser
WHERE username = 'admin')
BEGIN
    -- 先创建一个管理员员工记录
    INSERT INTO Department
        (dept_name, dept_desc)
    VALUES
        ('系统管理部', '负责系统管理维护');

    INSERT INTO Employee
        (emp_no, emp_name, gender, position, dept_id, entry_date, status)
    VALUES
        ('ADMIN001', '系统管理员', 'M', '系统管理员', 1, GETDATE(), 1);

    -- 创建管理员账号（密码为: admin123）
    INSERT INTO SysUser
        (username, password, emp_id, role_type, status)
    VALUES
        ('admin', 'e10adc3949ba59abbe56e057f20f883e', 1, 1, 1);
END
GO

-- 添加索引
CREATE INDEX idx_employee_emp_no ON Employee(emp_no);
CREATE INDEX idx_employee_dept_id ON Employee(dept_id);
CREATE INDEX idx_attendance_emp_id ON Attendance(emp_id, attendance_date);
CREATE INDEX idx_salary_emp_id ON Salary(emp_id, year, month);
CREATE INDEX idx_leave_emp_id ON Leave(emp_id, start_time, end_time);
GO

-- 添加约束
ALTER TABLE Employee ADD CONSTRAINT chk_phone CHECK (phone LIKE '[0-9]%');
ALTER TABLE Employee ADD CONSTRAINT chk_email CHECK (email LIKE '%@%.%');
ALTER TABLE Employee ADD CONSTRAINT chk_id_card CHECK (LEN(id_card) = 18);
GO