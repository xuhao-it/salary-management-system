import axios from 'axios'
import type { AxiosInstance, AxiosRequestConfig, AxiosResponse } from 'axios'
import { ElMessage } from 'element-plus'

// ...existing code...

const instance: AxiosInstance = axios.create({
  baseURL: 'http://localhost:8080',  // 直接连接后端服务
  timeout: 5000,
  headers: {
    'Content-Type': 'application/json'
  },
  withCredentials: true  // 允许跨域携带认证信息
})

// 请求拦截器
instance.interceptors.request.use(
  (config) => {
    console.log('Request config:', config) // 添加请求日志
    const token = localStorage.getItem('token')
    if (token) {
      config.headers['Authorization'] = `Bearer ${token}`
    }
    return config
  },
  (error) => {
    console.error('Request error:', error) // 添加错误日志
    return Promise.reject(error)
  }
)

// 响应拦截器
instance.interceptors.response.use(
  (response: AxiosResponse) => {
    console.log('Response:', response) // 添加响应日志
    return response.data
  },
  (error) => {
    console.error('Response error:', error) // 添加错误日志
    if (error.response) {
      switch (error.response.status) {
        case 400:
          ElMessage.error('请求参数错误')
          break
        case 401:
          ElMessage.error('未授权，请重新登录')
          localStorage.removeItem('token')
          window.location.href = '/login'
          break
        case 500:
          ElMessage.error('服务器错误，请稍后重试')
          break
        default:
          ElMessage.error(error.response.data?.message || '未知错误')
      }
    } else {
      ElMessage.error('网络错误，请检查网络连接')
    }
    return Promise.reject(error)
  }
)

export default instance
