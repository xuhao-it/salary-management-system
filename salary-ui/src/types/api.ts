export interface LoginRequest {
  username: string;
  password: string;
}

export interface LoginResponse {
  token: string;
  userInfo: {
    userId: string;
    username: string;
    role: string;
  }
}
