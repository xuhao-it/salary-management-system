package com.xuhao.salary.domain.repository;

import com.xuhao.salary.domain.model.system.User;
import java.util.Optional;

public interface UserRepository {
    Optional<User> findByUsername(String username);
}
