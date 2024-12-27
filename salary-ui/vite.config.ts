import { fileURLToPath, URL } from 'node:url'
import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import vueJsx from '@vitejs/plugin-vue-jsx'
import vueSetupExtend from 'vite-plugin-vue-setup-extend'
import VueRouter from 'unplugin-vue-router/vite'
import path from 'path'  // 修改这里的导入

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [
    VueRouter(),  // 需要在 vue() 之前
    vue(),
    vueJsx(),
    vueSetupExtend()
  ],
  resolve: {
    alias: {
      '@': path.resolve(__dirname, 'src')  // 现在可以正确使用 path.resolve
    }
  },
  server: {
    port: 3000,
    proxy: {
      '/api': {
        target: 'http://localhost:8080', // 修改为你的后端服务地址
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/api/, '')
      }
    }
  },
  optimizeDeps: {
    esbuildOptions: {
      target: 'es2020'
    }
  },
  build: {
    target: 'es2020',
    minify: 'esbuild',
    sourcemap: false,
    chunkSizeWarningLimit: 2000,
    rollupOptions: {
      output: {
        manualChunks: {
          'element-plus': ['element-plus'],
          'vue': ['vue', 'vue-router', 'pinia']
        }
      }
    }
  }
})
