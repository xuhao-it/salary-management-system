import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'

const routes: RouteRecordRaw[] = [
  {
    path: '/login',
    name: 'Login',
    component: () => import('../views/Login.vue')
  },
  {
    path: '/',
    component: () => import('../layouts/DefaultLayout.vue'),
    children: [
      {
        path: '',
        name: 'Home',
        component: () => import('../views/Home.vue')
      },
      {
        path: 'employee/list',
        component: () => import('@/views/employee/List.vue')
      },
      {
        path: 'employee/add',
        component: () => import('@/views/employee/Add.vue')
      },
      {
        path: 'salary/list',
        component: () => import('@/views/salary/List.vue')
      },
      {
        path: 'salary/calculate',
        component: () => import('@/views/salary/Calculate.vue')
      }
    ]
  }
]

export const router = createRouter({
  history: createWebHistory(),
  routes
})

// 路由守卫
router.beforeEach((to, from, next) => {
  const token = localStorage.getItem('token')
  if (to.path !== '/login' && !token) {
    next('/login')
  } else {
    next()
  }
})

export default router
