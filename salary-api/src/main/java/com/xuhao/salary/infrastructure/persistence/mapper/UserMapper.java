package com.xuhao.salary.infrastructure.persistence.mapper;

import com.xuhao.salary.infrastructure.persistence.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    
    @Results({
        @Result(property = "id", column = "id"),
        @Result(property = "username", column = "username"),
        @Result(property = "password", column = "password"),
        @Result(property = "email", column = "email"),
        @Result(property = "roles", column = "roles"),
        @Result(property = "accountNonExpired", column = "account_non_expired"),
        @Result(property = "accountNonLocked", column = "account_non_locked"),
        @Result(property = "credentialsNonExpired", column = "credentials_non_expired"),
        @Result(property = "enabled", column = "enabled")
    })
    @Select("SELECT * FROM sys_user WHERE username = #{username}")
    UserEntity findByUsername(String username);
}
