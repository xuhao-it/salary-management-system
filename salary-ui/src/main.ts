import { createApp } from 'vue'
import { createPinia } from 'pinia'
import ElementPlus from 'element-plus'
import zhCn from 'element-plus/dist/locale/zh-cn.mjs'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import App from './App.vue'
import type { Router } from 'vue-router'
import router from './router'  // 修改这里的导入路径

import 'element-plus/dist/index.css'
import './assets/styles/main.css'

const app = createApp(App)

for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}

// 声明 use 方法的重载
declare module '@vue/runtime-core' {
    interface App {
        use(router: Router): this
    }
}

app.use(createPinia())
app.use(ElementPlus, {
    locale: zhCn,
    size: 'default'
})
app.use(router)

app.mount('#app')
