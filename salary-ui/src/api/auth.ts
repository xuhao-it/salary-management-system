import request from '@/utils/request'

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

export const login = (data: LoginRequest): Promise<LoginResponse> => {
  return request({
    url: '/api/auth/login',  // 添加/api前缀
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
