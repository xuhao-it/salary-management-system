import { createRouter, createWebHistory } from 'vue-router'
import type { RouteRecordRaw } from 'vue-router'

// 定义带有meta的路由记录类型
interface AppRouteRecordRaw extends Omit<RouteRecordRaw, 'meta'> {
  meta?: {
    requiresAuth?: boolean
  }
  children?: AppRouteRecordRaw[]
}

// 扩展 RouteMeta 接口
declare module 'vue-router' {
  interface RouteMeta {
    requiresAuth?: boolean
  }
}

const routes: AppRouteRecordRaw[] = [
  {
    path: '/login',
    name: 'Login',
    component: () => import('../views/login/Login.vue'),  // 修改为正确的路径
    meta: { requiresAuth: false }
  },
  {
    path: '/',
    component: () => import('@/layouts/DefaultLayout.vue'),
    meta: { requiresAuth: true },
    children: [
      {
        path: '',
        name: 'Home',
        component: () => import('@/views/Home.vue')
      },
      {
        path: 'employee',
        children: [
          {
            path: 'list',
            component: () => import('@/views/employee/List.vue')
          },
          {
            path: 'add',
            component: () => import('@/views/employee/Add.vue')
          }
        ]
      },
      {
        path: 'salary',
        children: [
          {
            path: 'list',
            component: () => import('@/views/salary/List.vue')
          },
          {
            path: 'calculate',
            component: () => import('@/views/salary/Calculate.vue')
          }
        ]
      }
    ]
  }
]

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes
})

// 修改路由守卫，删除未使用的 from 参数
router.beforeEach((to, _from, next) => {
  const token = localStorage.getItem('token')
  
  if (to.meta.requiresAuth && !token) {
    next('/login')
  } else {
    next()
  }
})

export default router
