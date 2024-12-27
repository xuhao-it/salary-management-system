import request from '@/utils/request'

export interface LoginParams {
    username: string
    password: string
}

export interface UserInfo {
    id: number
    username: string
    role: string
    email?: string
}

export const userApi = {
    login(data: LoginParams) {
        return request({
            url: '/auth/login',
            method: 'post',
            data
        })
    },

    getUserInfo() {
        return request({
            url: '/users/current',
            method: 'get'
        })
    },

    logout() {
        return request({
            url: '/auth/logout',
            method: 'post'
        })
    }
}
