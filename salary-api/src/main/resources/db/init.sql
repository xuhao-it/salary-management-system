-- 创建数据库
IF NOT EXISTS (SELECT * FROM sys.databases WHERE name = 'salary_db')
BEGIN
    CREATE DATABASE salary_db
END
GO

USE salary_db
GO

-- 创建用户表
IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='sys_user' and xtype='U')
BEGIN
    CREATE TABLE sys_user (
        id BIGINT IDENTITY(1,1) PRIMARY KEY,
        username NVARCHAR(50) NOT NULL UNIQUE,
        password NVARCHAR(100) NOT NULL,
        role NVARCHAR(20) NOT NULL,
        company_name NVARCHAR(100),
        status INT DEFAULT 1,
        token NVARCHAR(500),
        create_time DATETIME DEFAULT GETDATE(),
        update_time DATETIME DEFAULT GETDATE()
    )
END
GO

CREATE TABLE users (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    role VARCHAR(20) NOT NULL,
    company_name VARCHAR(100),
    status INT DEFAULT 1,
    token VARCHAR(500),
    created_at DATETIME DEFAULT GETDATE(),
    updated_at DATETIME DEFAULT GETDATE()
);
GO

-- 添加测试数据
IF NOT EXISTS (SELECT * FROM sys_user)
BEGIN
    INSERT INTO sys_user (username, password, role, company_name, status) VALUES
    ('admin', '$2a$10$x6xzHjB5WOKRYxqic0Q85.TguI8lHDqlX3TWU1KxwHSOPxyJQUquu', 'ADMIN', N'系统管理', 1),
    ('hr001', '$2a$10$x6xzHjB5WOKRYxqic0Q85.TguI8lHDqlX3TWU1KxwHSOPxyJQUquu', 'HR', N'测试公司', 1),
    ('emp001', '$2a$10$x6xzHjB5WOKRYxqic0Q85.TguI8lHDqlX3TWU1KxwHSOPxyJQUquu', 'EMPLOYEE', N'测试公司', 1)
END
GO

-- 插入测试管理员账号，密码为 admin123
INSERT INTO users (username, password, role, company_name)
VALUES ('admin', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVKIUi', 'ADMIN', '系统管理员');
GO
