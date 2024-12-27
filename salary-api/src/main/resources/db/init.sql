-- 删除数据库如果存在
IF EXISTS (SELECT *
FROM sys.databases
WHERE name = 'PayrollDB')
BEGIN
    DROP DATABASE PayrollDB;
END
GO

-- 包含V1__init_schema.sql的所有内容
-- ...existing code...

-- 包含V2__init_data.sql的所有内容
-- ...existing code...

-- 添加数据库维护存储过程
CREATE PROCEDURE sp_backup_database
AS
BEGIN
    DECLARE @backup_file NVARCHAR(200)
    SET @backup_file = N'C:\Program Files\Microsoft SQL Server\MSSQL16.USER01\MSSQL\Backup\PayrollDB_' + 
                      CONVERT(NVARCHAR(8), GETDATE(), 112) + '.bak'
    BACKUP DATABASE PayrollDB TO DISK = @backup_file
END
GO

-- 创建数据清理存储过程
CREATE PROCEDURE sp_clean_old_logs
AS
BEGIN
    DELETE FROM sys_log 
    WHERE create_time < DATEADD(MONTH, -3, GETDATE())
END
GO