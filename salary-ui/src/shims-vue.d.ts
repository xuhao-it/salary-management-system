declare module '*.vue' {
  import type { DefineComponent } from 'vue'
  const component: DefineComponent<{}, {}, any>
  export default component
}

// Element Plus 类型声明
declare module 'element-plus/dist/locale/zh-cn.mjs'
