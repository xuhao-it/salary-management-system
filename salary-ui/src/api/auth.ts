import request from './request'
import type { LoginParams, LoginResult } from '@/types/auth'

export interface LoginRequest {
  username: string;
  password: string;
}

export interface LoginResponse {
  code: number;
  data: {
    token: string;
    userInfo: {
      id: number;
      username: string;
      role: string;
    };
  };
  message: string;
}

export const login = (data: LoginParams): Promise<LoginResult> => {
  return request({
    url: '/login',  // 修改为实际的登录路径
    method: 'post',
    data
  })
}

export const logout = () => {
  return request({
    url: '/auth/logout',
    method: 'post'
  })
}
