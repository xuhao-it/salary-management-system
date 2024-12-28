-- 员工表
CREATE TABLE employees (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    employee_no VARCHAR(50) UNIQUE NOT NULL,
    name NVARCHAR(100) NOT NULL,
    department NVARCHAR(100),
    position NVARCHAR(100),
    entry_date DATE,
    created_at DATETIME2 DEFAULT GETDATE(),
    updated_at DATETIME2 DEFAULT GETDATE()
);

-- 工资表
CREATE TABLE salaries (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    employee_id BIGINT NOT NULL,
    base_salary DECIMAL(10,2),
    bonus DECIMAL(10,2),
    deductions DECIMAL(10,2),
    payment_date DATE,
    created_at DATETIME2 DEFAULT GETDATE(),
    FOREIGN KEY (employee_id) REFERENCES employees(id)
);
