package com.xuhao.salary.infrastructure.persistence.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xuhao.salary.domain.model.system.User;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    User findByUsername(@Param("username") String username);
    
    void updateToken(@Param("username") String username, @Param("token") String token);
}
