import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'

const routes: Array<RouteRecordRaw> = [
  {
    path: '/login',
    name: 'Login',
    component: () => import('@/views/Login.vue')
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
  history: createWebHistory(),
  routes
})

router.beforeEach((to, from, next) => {
  const token = localStorage.getItem('token')
  if (to.path !== '/login' && !token) {
    next('/login')
  } else {
    next()
  }
})

export default router
