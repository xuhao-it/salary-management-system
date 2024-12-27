-- 插入部门数据
INSERT INTO Department (dept_name, dept_desc) VALUES 
('人力资源部', '负责人员招聘、培训及人事管理'),
('财务部', '负责公司财务、工资管理'),
('研发部', '负责产品研发'),
('市场部', '负责市场营销'),
('运营部', '负责日常运营');
GO

-- 插入初始员工数据
INSERT INTO Employee (emp_no, emp_name, gender, birth_date, position, dept_id, entry_date, status) VALUES 
('HR001', '张三', 'M', '1990-01-01', '人事经理', 1, '2023-01-01', 1),
('FN001', '李四', 'F', '1988-03-15', '财务经理', 2, '2023-01-01', 1),
('DEV001', '王五', 'M', '1992-07-20', '技术经理', 3, '2023-01-01', 1);
GO

-- 插入工资配置数据
INSERT INTO SalaryConfig (emp_id, basic_salary, position_salary, insurance_base, housing_fund_base, effective_date) VALUES 
(1, 8000.00, 4000.00, 10000.00, 10000.00, '2023-01-01'),
(2, 8000.00, 4000.00, 10000.00, 10000.00, '2023-01-01'),
(3, 10000.00, 5000.00, 12000.00, 12000.00, '2023-01-01');
GO

-- 插入系统用户数据
INSERT INTO SysUser (username, password, emp_id, role_type, status) VALUES 
('hr001', 'e10adc3949ba59abbe56e057f20f883e', 1, 3, 1),  -- 密码：123456
('fn001', 'e10adc3949ba59abbe56e057f20f883e', 2, 4, 1),  -- 密码：123456
('dev001', 'e10adc3949ba59abbe56e057f20f883e', 3, 5, 1); -- 密码：123456
GO