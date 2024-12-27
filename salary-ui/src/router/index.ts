import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'

const routes: RouteRecordRaw[] = [
  {
    path: '/login',
    name: 'Login',
    component: () => import('../views/login/Login.vue')  // 修改为正确的路径
  },
  {
    path: '/',
    component: () => import('@/layouts/DefaultLayout.vue'),
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

// 添加路由守卫
router.beforeEach((to, from, next) => {
  const token = localStorage.getItem('token')
  
  if (to.path !== '/login' && !token) {
    next('/login')
  } else if (to.path === '/login' && token) {
    next('/')
  } else {
    next()
  }
})

export default router
