import axios from 'axios'
import { ElMessage } from 'element-plus'
import router from '@/router'  // 修正导入路径

const service = axios.create({
    baseURL: import.meta.env.VITE_API_URL,
    timeout: 10000,
    headers: {
        'Content-Type': 'application/json'
    }
})

// 请求拦截器
service.interceptors.request.use(
    config => {
        console.log('Request:', config)  // 添加请求日志
        const token = localStorage.getItem('token')
        if (token) {
            config.headers['Authorization'] = `Bearer ${token}`
        }
        return config
    },
    error => {
        console.error('Request Error:', error)  // 添加错误日志
        return Promise.reject(error)
    }
)

// 响应拦截器
service.interceptors.response.use(
    response => {
        console.log('Response:', response)  // 添加响应日志
        const res = response.data
        if (res.code !== 200) {
            ElMessage.error(res.message || '请求失败')
            return Promise.reject(res)
        }
        return res
    },
    error => {
        console.error('Response Error:', error)  // 添加错误日志
        if (error.response) {
            const { status, data } = error.response
            switch (status) {
                case 401:
                    ElMessage.error('未登录或登录已过期')
                    localStorage.removeItem('token')
                    localStorage.removeItem('userInfo')
                    router.push('/login')
                    break
                case 403:
                    ElMessage.error('没有权限访问')
                    break
                default:
                    ElMessage.error(data?.message || `请求失败: ${status}`)
            }
        } else if (error.request) {
            ElMessage.error('服务器无响应')
        } else {
            ElMessage.error('请求配置错误')
        }
        return Promise.reject(error)
    }
)

export default service
