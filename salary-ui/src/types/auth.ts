export interface LoginParams {
  username: string
  password: string
}

export interface UserInfo {
  id: number
  username: string
  role: string
}

export interface LoginResult {
  code: number
  message: string
  data?: {
    token: string
    userInfo: UserInfo
  }
}
