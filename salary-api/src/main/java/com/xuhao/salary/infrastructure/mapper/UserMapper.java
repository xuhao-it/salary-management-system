package com.xuhao.salary.infrastructure.mapper;

import com.xuhao.salary.domain.entity.system.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    User findByUsername(@Param("username") String username);
    void updateToken(@Param("username") String username, @Param("token") String token);
}
