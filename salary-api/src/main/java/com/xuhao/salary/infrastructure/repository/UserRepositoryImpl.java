package com.xuhao.salary.infrastructure.repository;

import com.xuhao.salary.domain.model.system.User;
import com.xuhao.salary.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    
    private final JpaUserRepository jpaUserRepository;
    
    @Override
    public Optional<User> findByUsername(String username) {
        return jpaUserRepository.findByUsername(username);
    }
}
