import axios from 'axios'
import { ElMessage } from 'element-plus'
import router from '@/routes'

const service = axios.create({
    baseURL: import.meta.env.VITE_API_URL,
    timeout: 10000
})

// 请求拦截器
service.interceptors.request.use(
    config => {
        const token = localStorage.getItem('token')
        if (token) {
            config.headers['Authorization'] = `Bearer ${token}`
        }
        return config
    },
    error => {
        return Promise.reject(error)
    }
)

// 响应拦截器
service.interceptors.response.use(
    (    response: { data: any }) => response.data,
    (    error: { response: { status: any; data: { message: any } } }) => {
        if (error.response) {
            switch (error.response.status) {
                case 401:
                    localStorage.removeItem('token')
                    router.push('/login')
                    break
                default:
                    ElMessage.error(error.response.data.message || '服务器错误')
            }
        }
        return Promise.reject(error)
    }
)

export default service
