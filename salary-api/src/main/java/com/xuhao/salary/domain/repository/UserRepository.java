package com.xuhao.salary.domain.repository;

import com.xuhao.salary.domain.entity.system.User;
import com.xuhao.salary.infrastructure.mapper.UserMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
    private final UserMapper userMapper;

    public UserRepository(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    public void updateToken(String username, String token) {
        userMapper.updateToken(username, token);
    }
}
