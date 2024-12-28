package com.xuhao.salary.infrastructure.persistence.mapper;

import com.xuhao.salary.domain.model.employee.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface EmployeeMapper {
    void insert(Employee employee);
    void update(Employee employee);
    Employee findById(@Param("empId") Integer empId);  // 修改为 Integer
    List<Employee> findByPage(@Param("offset") int offset, @Param("size") int size);
    void deleteById(@Param("empId") Integer empId);  // 修改为 Integer
    int countTotal();
}
