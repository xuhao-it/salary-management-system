package com.xuhao.salary.domain.service.interfaces;

import com.xuhao.salary.domain.model.system.User;

public interface AuthDomainService {
    User authenticate(String username, String password);
    String generateToken(String username);
    boolean validateToken(String token);
}
