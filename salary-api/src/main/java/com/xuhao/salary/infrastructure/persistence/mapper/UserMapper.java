package com.xuhao.salary.infrastructure.persistence.mapper;

import com.xuhao.salary.infrastructure.persistence.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("SELECT " +
           "u.user_id as id, " +
           "u.username, " +
           "u.password, " +
           "u.role_type as roles, " +
           "u.status as enabled, " +
           "e.email, " +
           "1 as account_non_expired, " +
           "1 as account_non_locked, " +
           "1 as credentials_non_expired " +
           "FROM SysUser u " +
           "LEFT JOIN Employee e ON u.emp_id = e.emp_id " +
           "WHERE u.username = #{username}")
    UserEntity findByUsername(String username);
}
