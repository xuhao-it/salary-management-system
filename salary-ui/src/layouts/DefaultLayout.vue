<script setup lang="ts">
import { ref } from 'vue'
import { useRouter } from 'vue-router'

const isCollapse = ref(false)
const router = useRouter()

const handleLogout = () => {
  localStorage.removeItem('token')
  router.push('/login')
}
</script>

<template>
  <el-container class="layout-container">
    <el-aside :width="isCollapse ? '64px' : '200px'">
      <el-menu
        :collapse="isCollapse"
        class="menu-container"
        :router="true"
      >
        <el-menu-item index="/">
          <el-icon><House /></el-icon>
          <span>首页</span>
        </el-menu-item>
        <el-sub-menu index="employee">
          <template #title>
            <el-icon><User /></el-icon>
            <span>员工管理</span>
          </template>
          <el-menu-item index="/employee/list">员工列表</el-menu-item>
          <el-menu-item index="/employee/add">添加员工</el-menu-item>
        </el-sub-menu>
        <el-sub-menu index="salary">
          <template #title>
            <el-icon><Money /></el-icon>
            <span>薪资管理</span>
          </template>
          <el-menu-item index="/salary/list">薪资列表</el-menu-item>
          <el-menu-item index="/salary/calculate">薪资计算</el-menu-item>
        </el-sub-menu>
      </el-menu>
    </el-aside>
    <el-container>
      <el-header>
        <div class="header-left">
          <el-button @click="isCollapse = !isCollapse">
            <el-icon><Fold v-if="!isCollapse" /><Expand v-else /></el-icon>
          </el-button>
        </div>
        <div class="header-right">
          <el-dropdown>
            <span class="user-info">
              管理员 <el-icon><ArrowDown /></el-icon>
            </span>
            <template #dropdown>
              <el-dropdown-menu>
                <el-dropdown-item @click="handleLogout">退出登录</el-dropdown-item>
              </el-dropdown-menu>
            </template>
          </el-dropdown>
        </div>
      </el-header>
      <el-main>
        <router-view />
      </el-main>
    </el-container>
  </el-container>
</template>

<style scoped>
.layout-container {
  height: 100vh;
}

.menu-container {
  height: 100%;
  border-right: none;
}

.el-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  border-bottom: 1px solid #dcdfe6;
}

.user-info {
  cursor: pointer;
}
</style>
