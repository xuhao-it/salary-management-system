package com.xuhao.salary.domain.service.interfaces;

import com.xuhao.salary.application.dto.request.auth.LoginRequest;
import com.xuhao.salary.application.dto.response.auth.LoginResponse;
import com.xuhao.salary.domain.model.system.User;

public interface AuthDomainService {
	LoginResponse authenticate(LoginRequest loginRequest);

	User authenticate(String username, String password);

	String generateToken(String username);

	boolean validateToken(String token);
}
